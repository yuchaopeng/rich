package com.yu.web.base.json;

import java.util.Date;

import com.yu.base.util.DateUtils;
import com.yu.web.base.model.UserReport;

public class UserReportJson {
	private Long id;
	private Long userId;
	private String userName;
	private String mobile;
	private String account;
	private Date reportDate;
	private String reportDateStr;
	private Integer rechargeAmount;
	private String rechargeAmountStr;
	private Integer withdrawAmount;
	private String withdrawAmountStr;
	private Integer winAmount;
	private String winAmountStr;
	private Integer recommenderOtherAmount;
	private String recommenderOtherAmountStr;
	private Date createDate;
	private String createDateStr;
	private Date lastUpdateDate;
	private String lastUpdateDateStr;
	
	public UserReportJson(UserReport report) {
		this(report.getId(),
				report.getUser() == null ? null : report.getUser().getId(),
				report.getUser() == null ? null : report.getUser().getUsername(),
				report.getUser() == null ? null : report.getUser().getMobile(),
				report.getUser() == null ? null : report.getUser().getAccount(),
				report.getReportDate(),
				DateUtils.getSimpleDateString(report.getReportDate()),
				report.getRechargeAmount(),
				report.getRechargeAmountStr(),
				report.getWithdrawAmount(),
				report.getWithdrawAmountStr(),
				report.getWinAmount(),
				report.getWinAmountStr(),
				report.getRecommenderOtherAmount(),
				report.getRecommenderOtherAmountStr(),
				report.getCreateDate(),
				DateUtils.getDateString(report.getCreateDate()),
				report.getLastUpdateDate(),
				DateUtils.getDateString(report.getLastUpdateDate())
				);
	}
	
	public UserReportJson(Long id, Long userId, String userName,String mobile,String account, Date reportDate, String reportDateStr,
			Integer rechargeAmount, String rechargeAmountStr, Integer withdrawAmount, String withdrawAmountStr,
			Integer winAmount, String winAmountStr, Integer recommenderOtherAmount, String recommenderOtherAmountStr,
			Date createDate, String createDateStr, Date lastUpdateDate, String lastUpdateDateStr) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.mobile = mobile;
		this.account = account;
		this.reportDate = reportDate;
		this.reportDateStr = reportDateStr;
		this.rechargeAmount = rechargeAmount;
		this.rechargeAmountStr = rechargeAmountStr;
		this.withdrawAmount = withdrawAmount;
		this.withdrawAmountStr = withdrawAmountStr;
		this.winAmount = winAmount;
		this.winAmountStr = winAmountStr;
		this.recommenderOtherAmount = recommenderOtherAmount;
		this.recommenderOtherAmountStr = recommenderOtherAmountStr;
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * @return the rechargeAmount
	 */
	public Integer getRechargeAmount() {
		return rechargeAmount;
	}
	/**
	 * @param rechargeAmount the rechargeAmount to set
	 */
	public void setRechargeAmount(Integer rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
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
	 * @return the withdrawAmount
	 */
	public Integer getWithdrawAmount() {
		return withdrawAmount;
	}
	/**
	 * @param withdrawAmount the withdrawAmount to set
	 */
	public void setWithdrawAmount(Integer withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
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
	 * @return the recommenderOtherAmount
	 */
	public Integer getRecommenderOtherAmount() {
		return recommenderOtherAmount;
	}
	/**
	 * @param recommenderOtherAmount the recommenderOtherAmount to set
	 */
	public void setRecommenderOtherAmount(Integer recommenderOtherAmount) {
		this.recommenderOtherAmount = recommenderOtherAmount;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
