package com.yu.web.base.model;

import java.util.Date;

import com.yu.web.base.util.AmountUtil;

public class UserReport {
	private Long id;
	private User user;
	private Date reportDate;
	private Integer rechargeAmount;
	private String rechargeAmountStr;
	private Integer withdrawAmount;
	private String withdrawAmountStr;
	private Integer winAmount;
	private String winAmountStr;
	private Integer recommenderOtherAmount;
	private String recommenderOtherAmountStr;
	
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
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(Integer rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public String getRechargeAmountStr() {
		this.rechargeAmountStr = this.rechargeAmount == null ? null : AmountUtil.changeF2Y(this.rechargeAmount);
		return rechargeAmountStr;
	}
	public void setRechargeAmountStr(String rechargeAmountStr) {
		this.rechargeAmountStr = rechargeAmountStr;
	}
	public Integer getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Integer withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public String getWithdrawAmountStr() {
		this.withdrawAmountStr = this.withdrawAmount == null ? null : AmountUtil.changeF2Y(this.withdrawAmount);
		return withdrawAmountStr;
	}
	public void setWithdrawAmountStr(String withdrawAmountStr) {
		this.withdrawAmountStr = withdrawAmountStr;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
}
