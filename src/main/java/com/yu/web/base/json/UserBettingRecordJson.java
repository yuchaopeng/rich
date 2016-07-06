package com.yu.web.base.json;

import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.UserBettingRecord;

public class UserBettingRecordJson {
	private Long id;
	private String createDate;
	private String planName;
	private Long planId;
	private Integer state;
	private String stateVal;
	private Integer matchState;
	private String matchStateVal;
	private String username;
	private Long userId;
	private String remark;
	private Integer bettingAmount;
	private String bettingAmountStr;
	private Integer lotteryUploadState;
	private String lotteryUploadStateVal;
	
	public UserBettingRecordJson(UserBettingRecord record){
		this(
			record.getId(),
			DateUtils.getSimpleDateString(record.getCreateDate()),
			record.getPlan() == null ? null : record.getPlan().getPlanName(),
					record.getPlan() == null ? null : record.getPlan().getId(),
			record.getState(),
			GlobalConstants.getBettingRecordStateVal(record.getState()),
			record.getMatch() == null ? null : record.getMatch().getState(),
			GlobalConstants.getMatchStateVal(record.getMatch() == null ? null : record.getMatch().getState()),
			record.getUser().getUsername(),
			record.getUser().getId(),
			record.getRemark(),
			record.getBettingAmount(),
			record.getBettingAmountStr(),
			record.getLotteryUploadState(),
			GlobalConstants.getLotteryUploadStateMapVal(record.getLotteryUploadState()));
	}
	
	public UserBettingRecordJson(Long id,String createDate, String planName, Long planId, Integer state, String stateVal,Integer matchState,String matchStateVal,
			String username, Long userId,String remark, Integer bettingAmount,String bettingAmountStr, Integer lotteryUploadState,
			String lotteryUploadStateVal) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.planName = planName;
		this.planId = planId;
		this.state = state;
		this.stateVal = stateVal;
		this.matchState = matchState;
		this.matchStateVal = matchStateVal;
		this.username = username;
		this.userId = userId;
		this.remark = remark;
		this.bettingAmount = bettingAmount;
		this.bettingAmountStr = bettingAmountStr;
		this.lotteryUploadState = lotteryUploadState;
		this.lotteryUploadStateVal = lotteryUploadStateVal;
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
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	 * @return the stateVal
	 */
	public String getStateVal() {
		return stateVal;
	}
	/**
	 * @param stateVal the stateVal to set
	 */
	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	/**
	 * @return the lotteryUploadStateVal
	 */
	public String getLotteryUploadStateVal() {
		return lotteryUploadStateVal;
	}
	/**
	 * @param lotteryUploadStateVal the lotteryUploadStateVal to set
	 */
	public void setLotteryUploadStateVal(String lotteryUploadStateVal) {
		this.lotteryUploadStateVal = lotteryUploadStateVal;
	}

	/**
	 * @return the matchState
	 */
	public Integer getMatchState() {
		return matchState;
	}

	/**
	 * @param matchState the matchState to set
	 */
	public void setMatchState(Integer matchState) {
		this.matchState = matchState;
	}

	/**
	 * @return the matchStateVal
	 */
	public String getMatchStateVal() {
		return matchStateVal;
	}

	/**
	 * @param matchStateVal the matchStateVal to set
	 */
	public void setMatchStateVal(String matchStateVal) {
		this.matchStateVal = matchStateVal;
	}

	public String getBettingAmountStr() {
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

	
}
