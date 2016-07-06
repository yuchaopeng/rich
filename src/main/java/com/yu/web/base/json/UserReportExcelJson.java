package com.yu.web.base.json;

public class UserReportExcelJson {
	private String reportDateStr;
	private String mobile;
	private String userName;
	private String rechargeAmountStr;
	private String withdrawAmountStr;
	private String winAmountStr;
	private String recommenderOtherAmountStr;
	
	public UserReportExcelJson(UserReportJson json) {
		this.reportDateStr = json.getReportDateStr();
		this.mobile = json.getMobile();
		this.userName = json.getUserName();
		this.rechargeAmountStr = json.getRechargeAmountStr();
		this.withdrawAmountStr = json.getWithdrawAmountStr();
		this.winAmountStr = json.getWinAmountStr();
		this.recommenderOtherAmountStr = json.getRecommenderOtherAmountStr();
	}

	/**
	 * @return the reportDateStr
	 */
	public String getReportDateStr() {
		return reportDateStr;
	}

	/**
	 * @param reportDateStr the reportDateStr to set
	 */
	public void setReportDateStr(String reportDateStr) {
		this.reportDateStr = reportDateStr;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the rechargeAmountStr
	 */
	public String getRechargeAmountStr() {
		return rechargeAmountStr;
	}

	/**
	 * @param rechargeAmountStr the rechargeAmountStr to set
	 */
	public void setRechargeAmountStr(String rechargeAmountStr) {
		this.rechargeAmountStr = rechargeAmountStr;
	}

	/**
	 * @return the withdrawAmountStr
	 */
	public String getWithdrawAmountStr() {
		return withdrawAmountStr;
	}

	/**
	 * @param withdrawAmountStr the withdrawAmountStr to set
	 */
	public void setWithdrawAmountStr(String withdrawAmountStr) {
		this.withdrawAmountStr = withdrawAmountStr;
	}

	/**
	 * @return the winAmountStr
	 */
	public String getWinAmountStr() {
		return winAmountStr;
	}

	/**
	 * @param winAmountStr the winAmountStr to set
	 */
	public void setWinAmountStr(String winAmountStr) {
		this.winAmountStr = winAmountStr;
	}

	/**
	 * @return the recommenderOtherAmountStr
	 */
	public String getRecommenderOtherAmountStr() {
		return recommenderOtherAmountStr;
	}

	/**
	 * @param recommenderOtherAmountStr the recommenderOtherAmountStr to set
	 */
	public void setRecommenderOtherAmountStr(String recommenderOtherAmountStr) {
		this.recommenderOtherAmountStr = recommenderOtherAmountStr;
	}

	
}
