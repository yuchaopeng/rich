package com.yu.web.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.base.annotation.SystemLog;
import com.yu.base.util.MD5;
import com.yu.web.base.form.UpdatePasswordForm;
import com.yu.web.base.form.UserLoginForm;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.User;
import com.yu.web.base.service.UserService;
import com.yu.web.base.util.LoginUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
    @Resource
	private UserService userService;
	
	private Logger log = Logger.getLogger(UserController.class);
	
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)
	public String toLogin() throws Exception{
		return "user/login";
	}
	
	@SystemLog(description="特殊用户登录")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(@ModelAttribute("loginForm") UserLoginForm loginForm,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		if(loginForm == null){
			map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_NOT_INPUT);
			return map;
		}
		log.info("后台用户登录:"+loginForm.getMobile());
		
		boolean loginFlag = true;
		try {
			if(loginFlag && StringUtils.isEmpty(loginForm.getMobile())){
				map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_MOBILE_NULL);
				loginFlag = false;
			}
			if(loginFlag && StringUtils.isEmpty(loginForm.getPassword())){
				map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_PASSWORD_NULL);
				loginFlag = false;
			}
			
			if(loginFlag){
				CurrentUserInfo currentUserInfo = userService.login(loginForm.getMobile(), MD5.md5(loginForm.getPassword()));
				if(currentUserInfo == null || currentUserInfo.getUser() == null){
					map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_LOGIN_NOT_EXIST);
					loginFlag = false;
				}else{
					if(currentUserInfo.getUser().getState() == null){
						map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_ERROR);
						return map;
					}
					
					if(currentUserInfo.getUser().getUserType() != 1 
							&& currentUserInfo.getUser().getUserType() != 2 
							&& currentUserInfo.getUser().getUserType() != 3 
							&& currentUserInfo.getUser().getUserType() != 4 
							&& currentUserInfo.getUser().getUserType() != 5){
						map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_NOT_BACK_USER);
						return map;
					}
					
					switch(currentUserInfo.getUser().getState()){
						case 1 : 
							map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_OK);
							request.getSession().setAttribute(LoginUtil.SESSION_CURRENT_USER, currentUserInfo);
							log.info("登录成功》id："+currentUserInfo.getUser().getId()+"，mobile："+currentUserInfo.getUser().getMobile()+"，name："+currentUserInfo.getUser().getUsername());
							break;
						case 2 : 
							map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_INVALID);
							break;
						case 3 : 
							map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_DELETE);
							break;
						default :
							map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_ERROR);
							break;
					}
				}
			}
		} catch (Exception e) {
			map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_LOGIN_ERROR);
			log.error("登录失败:", e);
			e.printStackTrace();
		}
		log.info("登录结束: "+map.get("result")+" : "+map.get("message"));
		return map;
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	@SystemLog(description="特殊用户登出")
	public String logout(HttpServletRequest request) throws Exception{
		
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		if(userInfo != null && userInfo.getUser() != null){
			log.info("退出登录》id："+userInfo.getUser().getId()+"，mobile："+userInfo.getUser().getMobile()+"，username："+userInfo.getUser().getUsername());
		}
		request.getSession().removeAttribute(LoginUtil.SESSION_CURRENT_USER);
		return toLogin();
	}
	
	@RequestMapping(value="/toUpdatePassword",method=RequestMethod.GET)
	public String toUpdatePassword(Model model,HttpServletRequest request){
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		User user = userService.selectById(userInfo.getUser().getId());
		model.addAttribute("user", user);
		return "common/updatePassword";
	}
	
	@RequestMapping("/updatePassword")
	@SystemLog(description="特殊用户修改密码")
	public @ResponseBody Map<String, Object> updatePassword(Model model,HttpServletRequest request,@ModelAttribute("updatePasswordForm") UpdatePasswordForm updatePasswordForm) throws Exception {
		String sourcePassword = updatePasswordForm.getSourcePassword();
		String newPassword = updatePasswordForm.getNewPassword();
		String confirmPassword = updatePasswordForm.getConfirmPassword();
		
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		User user = userService.selectById(userInfo.getUser().getId());
		
		Map<String,Object> result = new HashMap<String,Object>();
		if(!MD5.md5(sourcePassword).equals(user.getPassword())){
			result.put("result", "1");
			result.put("message", "您输入的原密码不正确，请重新输入.");
		}else if(!newPassword.equals(confirmPassword)){
			result.put("result", "2");
			result.put("message", "您输入的新密码和确认密码不一致，请重新输入.");
		}else{
			user.setPassword(MD5.md5(newPassword));
			userService.update(user);
			result.put("result", "0");
			result.put("message", "修改成功.");
		}
		
		return result;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
