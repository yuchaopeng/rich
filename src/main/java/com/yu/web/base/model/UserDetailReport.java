package com.yu.web.base.model;

import java.util.Date;

import com.yu.web.base.util.AmountUtil;

public class UserDetailReport {
	private Long id;
	private User user;
	private User recommender;
	private Plan plan;
	private Date reportDate;
	private Integer bettingAmount;
	private String bettingAmountStr;
	private Integer commissionAmount;
	private String commissionAmountStr;
	private Integer recommenderAmount;
	private String recommenderAmountStr;
	private Integer recommenderOtherAmount;
	private String recommenderOtherAmountStr;
	private Integer expertAmount;
	private String expertAmountStr;
	private Integer winAmount;
	private String winAmountStr;
	private Date createDate;
	private Date lastUpdateDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getRecommender() {
		return recommender;
	}
	public void setRecommender(User recommender) {
		this.recommender = recommender;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getBettingAmount() {
		return bettingAmount;
	}
	public void setBettingAmount(Integer bettingAmount) {
		this.bettingAmount = bettingAmount;
	}
	public String getBettingAmountStr() {
		this.bettingAmountStr = this.bettingAmount == null ? null : AmountUtil.changeF2Y(this.bettingAmount);
		return bettingAmountStr;
	}
	public void setBettingAmountStr(String bettingAmountStr) {
		this.bettingAmountStr = bettingAmountStr;
	}
	public Integer getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(Integer commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public String getCommissionAmountStr() {
		this.commissionAmountStr = this.commissionAmount == null ? null : AmountUtil.changeF2Y(this.commissionAmount);
		return commissionAmountStr;
	}
	public void setCommissionAmountStr(String commissionAmountStr) {
		this.commissionAmountStr = commissionAmountStr;
	}
	public Integer getRecommenderAmount() {
		return recommenderAmount;
	}
	public void setRecommenderAmount(Integer recommenderAmount) {
		this.recommenderAmount = recommenderAmount;
	}
	public String getRecommenderAmountStr() {
		this.recommenderAmountStr = this.recommenderAmount == null ? null : AmountUtil.changeF2Y(this.recommenderAmount);
		return recommenderAmountStr;
	}
	public void setRecommenderAmountStr(String recommenderAmountStr) {
		this.recommenderAmountStr = recommenderAmountStr;
	}
	public Integer getRecommenderOtherAmount() {
		return recommenderOtherAmount;
	}
	public void setRecommenderOtherAmount(Integer recommenderOtherAmount) {
		this.recommenderOtherAmount = recommenderOtherAmount;
	}
	public String getRecommenderOtherAmountStr() {
		this.recommenderOtherAmountStr = this.recommenderOtherAmount == null ? null : AmountUtil.changeF2Y(this.recommenderOtherAmount);
		return recommenderOtherAmountStr;
	}
	public void setRecommenderOtherAmountStr(String recommenderOtherAmountStr) {
		this.recommenderOtherAmountStr = recommenderOtherAmountStr;
	}
	public Integer getExpertAmount() {
		return expertAmount;
	}
	public void setExpertAmount(Integer expertAmount) {
		this.expertAmount = expertAmount;
	}
	public String getExpertAmountStr() {
		this.expertAmountStr = this.expertAmount == null ? null : AmountUtil.changeF2Y(this.expertAmount);
		return expertAmountStr;
	}
	public void setExpertAmountStr(String expertAmountStr) {
		this.expertAmountStr = expertAmountStr;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Integer getWinAmount() {
		return winAmount;
	}
	public void setWinAmount(Integer winAmount) {
		this.winAmount = winAmount;
	}
	public String getWinAmountStr() {
		this.winAmountStr = this.winAmount == null ? null : AmountUtil.changeF2Y(this.winAmount);
		return winAmountStr;
	}
	public void setWinAmountStr(String winAmountStr) {
		this.winAmountStr = winAmountStr;
	}
}
