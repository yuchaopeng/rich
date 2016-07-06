package com.yu.web.base.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.base.exception.BaseException;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.TreeMenu;
import com.yu.web.base.service.UserService;
import com.yu.web.base.util.LoginUtil;

/**
 * 公共视图控制器
 * 
 * @author starzou
 * @since 2014年4月15日 下午4:16:34
 **/
@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource
	private UserService userService;
	
    @RequestMapping(value="/getTreeMenus",method=RequestMethod.POST)
	public @ResponseBody List<TreeMenu> menu(HttpServletRequest request) throws Exception{
    	
    	//CurrentUserInfo userInfo = userService.login("18500219968", MD5.md5("123456"));
    	//request.getSession().setAttribute(LoginUtil.SESSION_CURRENT_USER, userInfo);
    	CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
    	
    	if(userInfo == null || userInfo.getUser() == null){
			throw new BaseException("menu 获取当前用户信息失败");
		}
    	return userInfo.getTreeMenus();
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
}