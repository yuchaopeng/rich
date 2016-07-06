package com.yu.web.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.base.annotation.SystemLog;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.util.DateUtils;
import com.yu.web.base.json.SystemMessageJson;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.SystemMessage;
import com.yu.web.base.service.PlanService;
import com.yu.web.base.service.SystemMessageService;
import com.yu.web.base.util.LoginUtil;

@Controller
@RequestMapping(value = "/systemMessage")
public class SystemMessageController {
	
    @Resource
	private PlanService planService;
    
    @Resource
    private SystemMessageService systemMessageService;
	
	private Logger log = Logger.getLogger(SystemMessageController.class);
	
	@RequestMapping(value="/systemMessageList",method=RequestMethod.GET)
	public String systemMessageList(){
		return "systemMessage/systemMessageList";
	}
	
	@RequestMapping(value="/systemMessageListJson",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> systemMessageListJson(@ModelAttribute("pageBean") PageBean pageBean){
		Page<SystemMessage> page = systemMessageService.select4Page(pageBean);
		List<SystemMessage> messages = page.getResults();
		List<SystemMessageJson> jsons = new ArrayList<>();
		for(SystemMessage message : messages){
			SystemMessageJson json = new SystemMessageJson(message);
			jsons.add(json);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", jsons);
		return map;
	}
	
	@RequestMapping(value="/toSaveOrUpdateSystemMessage",method=RequestMethod.GET)
	public String toSaveOrUpdateSystemMessage(Model model,@RequestParam("systemMessageId") Long systemMessageId,@RequestParam(value="handleType",required=false) String handleType){
		model.addAttribute("handleType", handleType);
		if(systemMessageId != null){
			SystemMessage systemMessage = systemMessageService.selectById(systemMessageId);
			model.addAttribute("systemMessage",systemMessage);
		}
		return "systemMessage/saveOrUpdateSystemMessage";
	}
	
	@SystemLog(description="保存系统信息")
	@RequestMapping(value="/saveOrUpdateSystemMessage",method=RequestMethod.POST)
	public String saveOrUpdateSystemMessage(Model model,@ModelAttribute("systemMessage") SystemMessage systemMessage,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(systemMessage.getId() == null){
				CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
				systemMessage.setCreateUser(userInfo.getUser());
				systemMessage = systemMessageService.buildSystemMessage(systemMessage);
				systemMessageService.insert(systemMessage);
			}else{
				systemMessageService.update(systemMessage);
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		log.info("保存完成，返回信息："+map);
		return toSaveOrUpdateSystemMessage(model, systemMessage.getId(),null);
	}
	
	@SystemLog(description="修改系统信息状态")
	@RequestMapping(value="/updateState",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateState(@RequestParam("systemMessageId") Long systemMessageId,@RequestParam("state") Integer state){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			SystemMessage systemMessage = systemMessageService.selectById(systemMessageId);
			
			if(systemMessage == null){
				map.put("result", 1);
				map.put("message", "系统消息不存在!");
			}else{
				if(state == 2){
					systemMessage.setPublishDate(DateUtils.getNowTime());
				}
				systemMessage.setState(state);
				systemMessageService.update(systemMessage);
				map.put("result", 0);
				map.put("message", "操作成功.");
			}
			
		} catch (Exception e) {
			map.put("result", 3);
			map.put("message", "操作失败："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	@SystemLog(description="删除系统信息")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> delete(@RequestParam(value="systemMessageId") Long systemMessageId){ 
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			SystemMessage systemMessage = systemMessageService.selectById(systemMessageId);
			if(systemMessage == null){
				map.put("result", 1);
				map.put("message", "系统消息不存在!");
			}else{
				if(systemMessage.getState() == 2){
					map.put("result", 2);
					map.put("message", "该系统消息已发布，不能删除.");
				}else{
					systemMessageService.deleteById(systemMessageId);
					map.put("result", 0);
					map.put("message", "操作成功.");
				}
			}
		} catch (Exception e) {
			map.put("result", 3);
			map.put("message", "操作失败："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
}
