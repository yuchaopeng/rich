package com.yu.web.base.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.util.LoginUtil;

public class LoginInterceptor implements HandlerInterceptor {
	private List<String> excludeUrlPatterns;
	
	private PathMatcher pathMatcher = new AntPathMatcher(); 
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		String uri = request.getRequestURI();
		for(String urlPattern : excludeUrlPatterns){  
            if(pathMatcher.match(urlPattern,path)){  
                return true;  
            }  
        }  
		CurrentUserInfo userInfo = null;
		
		boolean isAppRequest = uri != null && uri.startsWith("/app");
		
		if(isAppRequest){
			userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		}else{
			userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		}
		
		/**
		 * 若未登录，则要求必须登录
		 */
		if(userInfo == null || userInfo.getUser() == null){
			if(isAppRequest){
				response.sendRedirect("/app/toLogin");
			}else{
				response.sendRedirect("/user/toLogin");
			}
			return false;
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void setExcludeUrlPatterns(List<String> excludeUrlPatterns) {
		this.excludeUrlPatterns = excludeUrlPatterns;
	}

}
