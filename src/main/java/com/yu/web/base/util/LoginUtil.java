package com.yu.web.base.util;

import java.util.HashMap;
import java.util.Map;

public class LoginUtil {
	
	public static String SESSION_CURRENT_USER = "SESSION_CURRENT_USER";
	
	public static String SESSION_APP_CURRENT_USER = "SESSION_APP_CURRENT_USER";
	
	public static Map<String, Object> buildLoginResult(String errorCode){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", errorCode);
		map.put("message", LOGIN_STATE.getLoginErrorMsg(errorCode));
		return map;
	}
	
	public static class LOGIN_STATE{
		
		/**
		 * 请输入登录信息
		 */
		public static String STATE_NOT_INPUT = "00";
		
		/**
		 * 成功
		 */
		public static String STATE_OK = "0";
		
		/**
		 * 失效
		 */
		public static String STATE_INVALID = "1";
		
		/**
		 * 删除
		 */
		public static String STATE_DELETE = "2";
		
		/**
		 * 登录名为空
		 */
		public static String STATE_MOBILE_NULL = "6";
		
		/**
		 * 密码为空
		 */
		public static String STATE_PASSWORD_NULL = "7";
		
		/**
		 * 用户密码不存在
		 */
		public static String STATE_LOGIN_NOT_EXIST = "8";
		
		/**
		 * 用户状态异常
		 */
		public static String STATE_ERROR = "9";
		
		
		/**
		 * 登录异常
		 */
		public static String STATE_LOGIN_ERROR = "10";
		
		/**
		 * 用户不是APP用户
		 */
		public static String STATE_NOT_APP_USER = "11";
		
		/**
		 * 用户不是APP用户
		 */
		public static String STATE_NOT_BACK_USER = "12";
		
		/**
		 * 返回登陆异常码
		 * @param key
		 * @return
		 */
		private static Object getLoginErrorMsg(String key){
			return loginState.get(key);
		}
		
		private static Map<String,Object> loginState = new HashMap<String, Object>();
		
		static{
			loginState.put(STATE_OK, "登录成功");
			loginState.put(STATE_INVALID, "用户为失效状态");
			loginState.put(STATE_DELETE, "用户被删除");
			loginState.put(STATE_MOBILE_NULL, "手机号不能为空");
			loginState.put(STATE_PASSWORD_NULL, "密码不能为空");
			loginState.put(STATE_LOGIN_NOT_EXIST, "用户名或密码不正确");
			loginState.put(STATE_ERROR, "用户状态异常");
			loginState.put(STATE_LOGIN_ERROR, "登录异常");
			loginState.put(STATE_NOT_INPUT, "请输入登录信息");
			loginState.put(STATE_NOT_APP_USER, "当前用户不能登录手机版");
			loginState.put(STATE_NOT_BACK_USER, "当前用户不能登录管理系统");
		}
		
		
	}
}
