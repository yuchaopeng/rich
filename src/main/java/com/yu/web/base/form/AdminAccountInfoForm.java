package com.yu.web.base.form;

import com.yu.web.base.model.AdminAccountInfo;

public class AdminAccountInfoForm {
	private AdminAccountInfo bank;
	private AdminAccountInfo alipay;
	private AdminAccountInfo wechat;
	public AdminAccountInfo getBank() {
		return bank;
	}
	public void setBank(AdminAccountInfo bank) {
		this.bank = bank;
	}
	public AdminAccountInfo getAlipay() {
		return alipay;
	}
	public void setAlipay(AdminAccountInfo alipay) {
		this.alipay = alipay;
	}
	public AdminAccountInfo getWechat() {
		return wechat;
	}
	public void setWechat(AdminAccountInfo wechat) {
		this.wechat = wechat;
	}
	
}
