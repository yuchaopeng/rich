package com.yu.web.base.model;

import java.util.Date;

import com.yu.web.base.util.AmountUtil;

public class PlanReport {
	private Long id;
	private Plan plan;
	private Date reportDate;
	private Integer bettingAmount;
	private String bettingAmountStr;
	private Integer winAmount;
	private String winAmountStr;
	private Integer commissionAmount;
	private String commissionAmountStr;
	private Integer recommenderAmount;
	private String recommenderAmountStr;
	private Integer expertAmount;
	private String expertAmountStr;
	private Date createDate;
	private Date lastUpdateDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Integer getBettingAmount() {
		return bettingAmount;
	}
	public void setBettingAmount(Integer bettingAmount) {
		this.bettingAmount = bettingAmount;
	}
	public String getBettingAmountStr() {
		if(bettingAmount != null){
			this.bettingAmountStr = AmountUtil.changeF2Y(bettingAmount);
		}
		return bettingAmountStr;
	}
	public void setBettingAmountStr(String bettingAmountStr) {
		this.bettingAmountStr = bettingAmountStr;
	}
	public Integer getWinAmount() {
		return winAmount;
	}
	public void setWinAmount(Integer winAmount) {
		this.winAmount = winAmount;
	}
	public String getWinAmountStr() {
		if(winAmount != null){
			this.winAmountStr = AmountUtil.changeF2Y(winAmount);
		}
		return winAmountStr;
	}
	public void setWinAmountStr(String winAmountStr) {
		this.winAmountStr = winAmountStr;
	}
	public Integer getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(Integer commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public String getCommissionAmountStr() {
		if(commissionAmount != null){
			this.commissionAmountStr = AmountUtil.changeF2Y(commissionAmount);
		}
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
		if(recommenderAmount != null){
			this.recommenderAmountStr = AmountUtil.changeF2Y(recommenderAmount);
		}
		return recommenderAmountStr;
	}
	public void setRecommenderAmountStr(String recommenderAmountStr) {
		this.recommenderAmountStr = recommenderAmountStr;
	}
	public Integer getExpertAmount() {
		return expertAmount;
	}
	public void setExpertAmount(Integer expertAmount) {
		this.expertAmount = expertAmount;
	}
	public String getExpertAmountStr() {
		if(expertAmount != null){
			this.expertAmountStr = AmountUtil.changeF2Y(expertAmount);
		}
		return expertAmountStr;
	}
	public void setExpertAmountStr(String expertAmountStr) {
		this.expertAmountStr = expertAmountStr;
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
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
}
