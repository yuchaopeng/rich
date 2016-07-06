package com.yu.web.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yu.base.exception.BaseException;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.UserDefaultView;
import com.yu.web.base.service.UserService;
import com.yu.web.base.util.LoginUtil;

/**
 * 公共视图控制器
 * 
 * @author starzou
 * @since 2014年4月15日 下午4:16:34
 **/
@Controller
public class CommonController {
	
	@Resource
	private UserService userService;
	
    /**
     * 首页
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    @RequestMapping("index")
    public String index(Model model,HttpServletRequest request) throws Exception {
    	CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
    	if(userInfo == null || userInfo.getUser() == null){
    		throw new Exception("index 获取用户信息失败");
    	}
    	UserDefaultView defaultView = userService.getUserDefaultView(userInfo.getUser().getId());
		model.addAttribute("defaultView", defaultView);
        return "index";
    }
    
    @RequestMapping(value="/menu",method=RequestMethod.POST)
	public String menu(Model model,HttpServletRequest request) throws BaseException{
    	CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
    	if(userInfo == null || userInfo.getUser() == null){
			throw new BaseException("获取当前用户信息失败");
		}
    	model.addAttribute("treeMenus", userInfo.getTreeMenus());
		return "tiles/menu";
	}
    
}