package com.yu.web.base.json;

public class PlanReportExcelJson {
	private String reportDateStr;
	private String planName;
	private String bettingAmountStr;
	private String winAmountStr;
	private String commissionAmountStr;
	private String recommenderAmountStr;
	private String expertAmountStr;
	
	
	public PlanReportExcelJson(PlanReportJson json) {
		this.reportDateStr = json.getReportDateStr();
		this.planName = json.getPlanName();
		this.bettingAmountStr = json.getBettingAmountStr();
		this.winAmountStr = json.getWinAmountStr();
		this.commissionAmountStr = json.getCommissionAmountStr();
		this.recommenderAmountStr = json.getRecommenderAmountStr();
		this.expertAmountStr = json.getExpertAmountStr();
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
	
}
