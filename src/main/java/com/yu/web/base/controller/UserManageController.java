package com.yu.web.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.base.annotation.SystemLog;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.util.MD5;
import com.yu.base.util.ReturnMapUtil;
import com.yu.web.base.condition.UserCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.json.UserAgreementJson;
import com.yu.web.base.json.UserJson;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAgreement;
import com.yu.web.base.service.UserAgreementService;
import com.yu.web.base.service.UserManageService;

@Controller
@RequestMapping(value = "/userManage")
public class UserManageController {
	
	@Resource
	private UserManageService userManageService;
	
	@Resource
	private UserAgreementService userAgreementService;
	
	@Value("#{propertiesSetting['reset.password']}")
	private String resetPassword;
	
	private Logger log = Logger.getLogger(UserManageController.class);
	
	@RequestMapping(value="/ordinaryUserList",method=RequestMethod.GET)
	public String ordinaryUserList(){
		return "user/ordinaryUserList";
	}
	
	/**
	 * 普通用户
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/ordinaryUserListJson",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ordinaryUserListJson(@ModelAttribute("pageBean") PageBean pageBean){
		//设置只能查询 普通类型的用户
		pageBean.getParams().put("queryUserTypes", GlobalConstants.getQueryOrdinaryUserTypes());
		Map<String,Object> map = selectUser4Page(pageBean);
		log.info("ordinaryUserList:"+map);
		return map;
	}
	
	@RequestMapping(value="/specialUserList",method=RequestMethod.GET)
	public String specialUserList(){
		return "user/specialUserList";
	}
	
	/**
	 * 特别用户
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/specialUserListJson",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> specialUserListJson(@ModelAttribute("pageBean") PageBean pageBean){
		//设置只能查询 特殊类型的用户
		pageBean.getParams().put("queryUserTypes", GlobalConstants.getQuerySpecialUserTypes());
		Map<String,Object> map = selectUser4Page(pageBean);
		log.info("specialUserList:"+map);
		return map;
	}
	
	@RequestMapping(value="/toSelectUser",method=RequestMethod.GET)
	public String chooseRecommender(Model model,@RequestParam("queryUserTypes") String queryUserTypes,@RequestParam("state") Integer state){
		model.addAttribute("queryUserTypes", queryUserTypes);
		model.addAttribute("state", state);
		return "user/selectUser";
	}
	
	@RequestMapping(value="/selectUser",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectUser(@ModelAttribute("pageBean") PageBean pageBean){
		Map<String,Object> map = selectUser4Page(pageBean);
		log.info("chooseUser:"+map);
		return map;
	}
	
	private Map<String,Object> selectUser4Page(PageBean pageBean){
		Page<User> page = userManageService.select4Page(pageBean);
		List<User> users = page.getResults();
		List<UserJson> userListJsons = new ArrayList<UserJson>();
		for(User user : users){
			UserAgreement usableAgreement = userAgreementService.getUsableAgreement(user.getId());
			UserJson json = new UserJson(user, usableAgreement);
			userListJsons.add(json);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", userListJsons);
		return map;
	}
	
	@RequestMapping(value="/toSaveOrUpdateUser",method=RequestMethod.GET)
	public String toSaveOrUpdateUser(Model model,@RequestParam("userId") Long userId,@RequestParam("createUserType") Long createUserType) throws Exception{
		if(userId != null){
			User user = userManageService.selectById(userId);
			model.addAttribute("user",user);
		}
		if(createUserType == null){
			throw new Exception("createUserType 为空");
		}
		model.addAttribute("createUserType", createUserType);
		return "user/saveOrUpdateUser";
	}
	
	@SystemLog(description="编辑用户")
	@RequestMapping(value="/saveOrUpdateUser",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveOrUpdateUser(@ModelAttribute("user") User user){
		log.info("开始保存用户：id："+user.getId()+" mobile："+user.getMobile());
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(user.getId() == null){
				//创建个人用户和账户
				userManageService.createUserInfo(user);
			}else{
				if(!StringUtils.isEmpty(user.getPassword())){
					user.setPassword(MD5.md5(user.getPassword()));
				}
				userManageService.update(user);
			}
			user = userManageService.selectById(user.getId());
			map.put("result", 0);
			map.put("message", "保存成功");
			map.put("userinfo", new UserJson(user,null));
		} catch (Exception e) {
			map.put("result", 1);
			map.put("message", "保存失败："+e.getMessage());
			map.put("userinfo", new UserJson(user,null));
			log.error(e);
			e.printStackTrace();
		}
		log.info("保存完成，返回信息："+map);
		return map;
	}
	
	@SystemLog(description="校验用户手机号")
	@RequestMapping(value="/checkMobile",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkMobile(@RequestParam(value="mobile") String mobile){ 
		Map<String,Object> map = new HashMap<String, Object>();
		
		UserCondition condition = new UserCondition();
		condition.setMobile(mobile);
		
		List<User> users = userManageService.selectList(condition);
		if(users == null || users.size() == 0){
			map.put("result", 0);
		}else{
			map.put("result", 1);
		}
		return map;
	}
	
	@RequestMapping(value="/userAgreementJson",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> userAgreementJson(@ModelAttribute("pageBean") PageBean pageBean){
		Map<String,Object> map = new HashMap<String, Object>();
		Object userId = pageBean.getParams().get("userId");
		if(userId == null || StringUtils.isEmpty(userId)){
			map.put("total", 0);
			map.put("rows", new ArrayList<UserAgreement>());
		}else{
			Page<UserAgreement> page = userAgreementService.select4Page(pageBean);
			List<UserAgreement> agreements = page.getResults();
			List<UserAgreementJson> agreementJsons = userAgreementService.buildUserAgreementJson(agreements);
			map.put("total", page.getTotalRecord());
			map.put("rows", agreementJsons);
			log.info("userAgreementJson:"+map);
		}
		return map;
	}
	
	@RequestMapping(value="/toAddUserAgreement",method=RequestMethod.GET)
	public String toAddAgreement(Model model,@RequestParam("userId") Long userId){
		model.addAttribute("userId", userId);
		return "user/addUserAgreement";
	}
	
	@SystemLog(description="新增用户分成协议")
	@RequestMapping(value="/addUserAgreement",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addUserAgreement(@ModelAttribute("userAgreement") UserAgreement agreement){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			agreement = userAgreementService.buildUserAgreement(agreement);
			userAgreementService.insert(agreement);
			map.put("result", 0);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("result", 1);
			map.put("message", "保存失败："+e.getMessage());
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}
	
	
	@RequestMapping(value="/toResetPassword",method=RequestMethod.GET)
	public String toResetPassword(Model model,@RequestParam("userId") Long userId){
		model.addAttribute("userId", userId);
		return "user/addUserAgreement";
	}
	
	@SystemLog(description="修改用户状态")
	@RequestMapping(value="/updateUserState",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateUserState(@RequestParam("userId") Long userId,@RequestParam("state") Integer state){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(userId == null){
			return ReturnMapUtil.getErrorMap("1", "userId为空.", map);
		}
		if(state == null){
			return ReturnMapUtil.getErrorMap("2", "state为空.", map);
		}
		User user = userManageService.selectById(userId);
		if(user == null){
			return ReturnMapUtil.getErrorMap("3", "该用户不存在.", map);
		}
		Integer userState = user.getState();
		
		if(state != 1 && state != 2){
			return ReturnMapUtil.getErrorMap("4", "操作类型错误.", map);
		}
		
		if(state == 1 && userState != 2){//改为生效操作，当前状态应该为失效
			return ReturnMapUtil.getErrorMap("5", "当前状态不为失效状态，不能进行生效操作.", map);
		}
		
		if(state == 2 && userState != 1){//改为生效操作，当前状态应该为失效
			return ReturnMapUtil.getErrorMap("6", "当前状态不为生效状态，不能进行失效操作.", map);
		}
		
		try {
			user.setState(state);
			userManageService.update(user);
			ReturnMapUtil.getErrorMap("0", "操作成功", map);
		} catch (Exception e) {
			ReturnMapUtil.getErrorMap("7", "操作失败："+e.getMessage(), map);
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}
	
	@SystemLog(description="重置密码")
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> resetPassword(@RequestParam("userId") Long userId){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(userId == null){
			return ReturnMapUtil.getErrorMap("1", "userId为空.", map);
		}
		
		User user = userManageService.selectById(userId);
		try {
			user.setPassword(MD5.md5(resetPassword));
			userManageService.update(user);
			return ReturnMapUtil.getErrorMap("0", "重置成功", map);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return ReturnMapUtil.getErrorMap("2", "重置失败："+e, map);
		}
	}
	
	
	@RequestMapping(value="/toSelectSpecialUserType",method=RequestMethod.GET)
	public String toSelectSpecialUserType(){
		return "user/selectSpecialUserType";
	}

	/**
	 * @param resetPassword the resetPassword to set
	 */
	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}
	
}
