package com.yu.web.base.json;

public class UserDetailReportExcelJson {
	private String reportDateStr;
	private String userName;
	private String mobile;
	private String userTypeName;
	private String planName;
	private String bettingAmountStr;
	private String commissionAmountStr;
	private String recommenderName;
	private String recommenderAmountStr;
	private String recommenderOtherAmountStr;
	private String expertAmountStr;
	private String winAmountStr;
	
	
	public UserDetailReportExcelJson(UserDetailReportJson json) {
		this.reportDateStr = json.getReportDateStr();
		this.userName = json.getUserName();
		this.mobile = json.getMobile();
		this.userTypeName = json.getUserTypeName();
		this.planName = json.getPlanName();
		this.bettingAmountStr = json.getBettingAmountStr();
		this.commissionAmountStr = json.getCommissionAmountStr();
		this.recommenderName = json.getRecommenderName();
		this.recommenderAmountStr = json.getRecommenderAmountStr();
		this.recommenderOtherAmountStr = json.getRecommenderOtherAmountStr();
		this.expertAmountStr = json.getExpertAmountStr();
		this.winAmountStr = json.getWinAmountStr();
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
	 * @return the userTypeName
	 */
	public String getUserTypeName() {
		return userTypeName;
	}
	/**
	 * @param userTypeName the userTypeName to set
	 */
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	/**
	 * @return the planName
	 */
	public String getPlanName() {
		return planName;
	}
	/**
	 * @param planName the planName to set
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	/**
	 * @return the bettingAmountStr
	 */
	public String getBettingAmountStr() {
		return bettingAmountStr;
	}
	/**
	 * @param bettingAmountStr the bettingAmountStr to set
	 */
	public void setBettingAmountStr(String bettingAmountStr) {
		this.bettingAmountStr = bettingAmountStr;
	}
	/**
	 * @return the commissionAmountStr
	 */
	public String getCommissionAmountStr() {
		return commissionAmountStr;
	}
	/**
	 * @param commissionAmountStr the commissionAmountStr to set
	 */
	public void setCommissionAmountStr(String commissionAmountStr) {
		this.commissionAmountStr = commissionAmountStr;
	}
	/**
	 * @return the recommenderName
	 */
	public String getRecommenderName() {
		return recommenderName;
	}
	/**
	 * @param recommenderName the recommenderName to set
	 */
	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}
	/**
	 * @return the recommenderAmountStr
	 */
	public String getRecommenderAmountStr() {
		return recommenderAmountStr;
	}
	/**
	 * @param recommenderAmountStr the recommenderAmountStr to set
	 */
	public void setRecommenderAmountStr(String recommenderAmountStr) {
		this.recommenderAmountStr = recommenderAmountStr;
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
	/**
	 * @return the expertAmountStr
	 */
	public String getExpertAmountStr() {
		return expertAmountStr;
	}
	/**
	 * @param expertAmountStr the expertAmountStr to set
	 */
	public void setExpertAmountStr(String expertAmountStr) {
		this.expertAmountStr = expertAmountStr;
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
	
}
