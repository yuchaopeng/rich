package com.yu.web.base.model;

import java.util.Date;

import com.yu.web.base.util.AmountUtil;

public class UserBettingRecord {
	private Long id;
	private User user;
	private Plan plan;
	private Match match;
	private UserHelpBuyRecord userHelpBuyRecord;
	private Integer bettingAmount;
	private String bettingAmountStr;
	private Long recommenderUserId;
	private Integer recommenderAmount;
	private Integer state;
	private Integer lotteryUploadState;
	private ImageUpload imageUpload;
	private Integer winAmount;
	private String winAmountStr;
	private String orderId;
	private Date createDate;
	private Date lastUpdateDate;
	/**
	 * 备注
	 */
	private String remark;
	
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the plan
	 */
	public Plan getPlan() {
		return plan;
	}
	/**
	 * @param plan the plan to set
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	/**
	 * @return the userHelpBuyRecord
	 */
	public UserHelpBuyRecord getUserHelpBuyRecord() {
		return userHelpBuyRecord;
	}
	/**
	 * @param userHelpBuyRecord the userHelpBuyRecord to set
	 */
	public void setUserHelpBuyRecord(UserHelpBuyRecord userHelpBuyRecord) {
		this.userHelpBuyRecord = userHelpBuyRecord;
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
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the lotteryUploadState
	 */
	public Integer getLotteryUploadState() {
		return lotteryUploadState;
	}
	/**
	 * @param lotteryUploadState the lotteryUploadState to set
	 */
	public void setLotteryUploadState(Integer lotteryUploadState) {
		this.lotteryUploadState = lotteryUploadState;
	}

	public ImageUpload getImageUpload() {
		return imageUpload;
	}
	public void setImageUpload(ImageUpload imageUpload) {
		this.imageUpload = imageUpload;
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
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	/**
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}
	/**
	 * @param match the match to set
	 */
	public void setMatch(Match match) {
		this.match = match;
	}
	public String getBettingAmountStr() {
		if(this.bettingAmount != null){
			this.bettingAmountStr = AmountUtil.changeF2Y(this.bettingAmount);
		}
		return bettingAmountStr;
	}
	public void setBettingAmountStr(String bettingAmountStr) {
		this.bettingAmountStr = bettingAmountStr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getRecommenderUserId() {
		return recommenderUserId;
	}
	public void setRecommenderUserId(Long recommenderUserId) {
		this.recommenderUserId = recommenderUserId;
	}
	public Integer getRecommenderAmount() {
		return recommenderAmount;
	}
	public void setRecommenderAmount(Integer recommenderAmount) {
		this.recommenderAmount = recommenderAmount;
	}
	public String getWinAmountStr() {
		if(this.winAmount != null){
			this.winAmountStr = AmountUtil.changeF2Y(this.winAmount);
		}
		return winAmountStr;
	}
	public void setWinAmountStr(String winAmountStr) {
		this.winAmountStr = winAmountStr;
	}
	
}
