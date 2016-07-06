package com.yu.web.base.form;

public class UserLoginForm {
	private String mobile;
	private String password;
	private String loginType;//登录类型
	
	public static String USER_LOGIN = "USER_LOGIN";//用户APP登录
	public static String BACKGROUND_LOGIN = "BACKGROUND_LOGIN";//后台登录
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	
}
