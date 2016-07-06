package com.yu.base.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.yu.base.annotation.SystemLog;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.util.LoginUtil;

@Component
@Aspect
public class SystemLogAop {

	//@Pointcut("within(@org.springframework.stereotype.Controller *)")
	@Pointcut("@annotation(com.yu.base.annotation.SystemLog)")
	public void cutController() {

	}

	@Around("cutController()")
	public Object recordSysLog(ProceedingJoinPoint joinPoint) throws Throwable {
		printLog(joinPoint);
		Object result = joinPoint.proceed();
		String resultJson = JSONObject.toJSONString(result);
		
		Log log = LogFactory.getLog(joinPoint.getTarget().getClass());
		String description = getMethodDescription(joinPoint);
		log.info("请求返回" + ("".equals(description) ? "" : "（"+description+"）") + "：" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
		log.info("请求返回值："+resultJson);
		return result;
	}
	@AfterThrowing(pointcut = "cutController()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		printLog(joinPoint,e);
	}
	
	private void printLog(JoinPoint joinPoint){
		printLog(joinPoint, null);
	}

	private void printLog(JoinPoint joinPoint,Throwable t){
		Log log = LogFactory.getLog(joinPoint.getTarget().getClass());
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			// 读取session中的用户
			CurrentUserInfo currentUserInfo = (CurrentUserInfo) session.getAttribute(LoginUtil.SESSION_CURRENT_USER);
			if (currentUserInfo == null) {
				currentUserInfo = (CurrentUserInfo) session.getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
			}
			
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					Object arg = joinPoint.getArgs()[i];
					if(arg instanceof HttpServletRequest || arg instanceof HttpServletResponse || arg instanceof Model){
						
					}else{
						params += JSONObject.toJSONString(arg) + ";";
					}
				}
			}
			
			String description = getMethodDescription(joinPoint);
			log.info("请求方法" + ("".equals(description) ? "" : "（"+description+"）") + "：" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
			log.info("请求参数："+params);
			if(currentUserInfo != null && currentUserInfo.getUser() != null){
				log.info("请求人：id："+currentUserInfo.getUser().getId()+"，mobile："+ currentUserInfo.getUser().getMobile() + "，username：" + currentUserInfo.getUser().getUsername());
			}
			log.info("请求IP："+getIpAddr(request));
			if(t != null){
				log.error("异常信息:" + t.getMessage(),t);
			}
		} catch (LogConfigurationException e) {
			// 记录本地异常日志
			e.printStackTrace();
			log.error("记录日志异常，异常信息:", e);
		} catch (Exception e) {
			// 记录本地异常日志
			e.printStackTrace();
			log.error("记录日志异常，异常信息:", e);
		}
	}

	
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				SystemLog systemLog = method.getAnnotation(SystemLog.class);
				if(systemLog != null){
					description = systemLog.description();
				}
				break;
			}
		}
		return description;
	}
}
