package com.yu.web.base.json;

import java.util.Date;

import com.yu.base.util.DateUtils;
import com.yu.web.base.model.PlanReport;

public class PlanReportJson {
	private Long id;
	private Long planId;
	private String planName;
	private Date reportDate;
	private String reportDateStr;
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
	private String createDateStr;
	private Date lastUpdateDate;
	private String lastUpdateDateStr;
	
	public PlanReportJson(PlanReport report) {
		this(report.getId(),
				report.getPlan().getId(),
				report.getPlan().getPlanName(),
				report.getReportDate(),
				DateUtils.getSimpleDateString(report.getReportDate()),
				report.getBettingAmount(),
				report.getBettingAmountStr(),
				report.getWinAmount(),
				report.getWinAmountStr(),
				report.getCommissionAmount(),
				report.getCommissionAmountStr(),
				report.getRecommenderAmount(),
				report.getRecommenderAmountStr(),
				report.getExpertAmount(),
				report.getExpertAmountStr(),
				report.getCreateDate(),
				DateUtils.getDateString(report.getCreateDate()),
				report.getLastUpdateDate(),
				DateUtils.getDateString(report.getLastUpdateDate())
		);
	}
	
	public PlanReportJson(Long id, Long planId, String planName, Date reportDate, String reportDateStr,
			Integer bettingAmount, String bettingAmountStr, Integer winAmount, String winAmountStr,
			Integer commissionAmount, String commissionAmountStr, Integer recommenderAmount,
			String recommenderAmountStr, Integer expertAmount, String expertAmountStr, Date createDate,
			String createDateStr, Date lastUpdateDate, String lastUpdateDateStr) {
		super();
		this.id = id;
		this.planId = planId;
		this.planName = planName;
		this.reportDate = reportDate;
		this.reportDateStr = reportDateStr;
		this.bettingAmount = bettingAmount;
		this.bettingAmountStr = bettingAmountStr;
		this.winAmount = winAmount;
		this.winAmountStr = winAmountStr;
		this.commissionAmount = commissionAmount;
		this.commissionAmountStr = commissionAmountStr;
		this.recommenderAmount = recommenderAmount;
		this.recommenderAmountStr = recommenderAmountStr;
		this.expertAmount = expertAmount;
		this.expertAmountStr = expertAmountStr;
		this.createDate = createDate;
		this.createDateStr = createDateStr;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdateDateStr = lastUpdateDateStr;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the planId
	 */
	public Long getPlanId() {
		return planId;
	}
	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(Long planId) {
		this.planId = planId;
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
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}
	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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
	 * @return the bettingAmount
	 */
	public Integer getBettingAmount() {
		return bettingAmount;
	}
	/**
	 * @param bettingAmount the bettingAmount to set
	 */
	public void setBettingAmount(Integer bettingAmount) {
		this.bettingAmount = bettingAmount;
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
	 * @return the winAmount
	 */
	public Integer getWinAmount() {
		return winAmount;
	}
	/**
	 * @param winAmount the winAmount to set
	 */
	public void setWinAmount(Integer winAmount) {
		this.winAmount = winAmount;
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
	 * @return the commissionAmount
	 */
	public Integer getCommissionAmount() {
		return commissionAmount;
	}
	/**
	 * @param commissionAmount the commissionAmount to set
	 */
	public void setCommissionAmount(Integer commissionAmount) {
		this.commissionAmount = commissionAmount;
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
	 * @return the recommenderAmount
	 */
	public Integer getRecommenderAmount() {
		return recommenderAmount;
	}
	/**
	 * @param recommenderAmount the recommenderAmount to set
	 */
	public void setRecommenderAmount(Integer recommenderAmount) {
		this.recommenderAmount = recommenderAmount;
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
	 * @return the expertAmount
	 */
	public Integer getExpertAmount() {
		return expertAmount;
	}
	/**
	 * @param expertAmount the expertAmount to set
	 */
	public void setExpertAmount(Integer expertAmount) {
		this.expertAmount = expertAmount;
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
	 * @return the createDateStr
	 */
	public String getCreateDateStr() {
		return createDateStr;
	}
	/**
	 * @param createDateStr the createDateStr to set
	 */
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
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
	/**
	 * @return the lastUpdateDateStr
	 */
	public String getLastUpdateDateStr() {
		return lastUpdateDateStr;
	}
	/**
	 * @param lastUpdateDateStr the lastUpdateDateStr to set
	 */
	public void setLastUpdateDateStr(String lastUpdateDateStr) {
		this.lastUpdateDateStr = lastUpdateDateStr;
	}
	
	
}
