package com.yu.web.base.model;

import java.util.Date;

import com.yu.web.base.util.AmountUtil;

/**
 * 账户明细
 * @author Administrator
 *
 */
public class UserAccountDetail{
	private String id;
	
	private String userId;
	
	private String accountId;
	
	private String type;
	
	private Integer direction;//0增1减
	
	private String fromAccountId;
	private String fromUserId;
	
	private String toAccountId;
	private String toUserId;
	private Integer amount;
	private String amountStr;
	
	private Integer balance;
	private String balanceStr;
	
	private String remark;
	
	private String orderId;
	
	private Long planId;
	private Long matchId;
	private Long bettingRecordId;
	private Long relationUserId;
	
	private Date createDate;
	
	private Date lastUpdateDate;
	
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the direction
	 */
	public Integer getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * @return the balance
	 */
	public Integer getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	/**
	 * @return the fromAccountId
	 */
	public String getFromAccountId() {
		return fromAccountId;
	}

	/**
	 * @param fromAccountId the fromAccountId to set
	 */
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	/**
	 * @return the fromUserId
	 */
	public String getFromUserId() {
		return fromUserId;
	}

	/**
	 * @param fromUserId the fromUserId to set
	 */
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	/**
	 * @return the toAccountId
	 */
	public String getToAccountId() {
		return toAccountId;
	}

	/**
	 * @param toAccountId the toAccountId to set
	 */
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	/**
	 * @return the toUserId
	 */
	public String getToUserId() {
		return toUserId;
	}

	/**
	 * @param toUserId the toUserId to set
	 */
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
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

	public String getAmountStr() {
		if(this.amount != null){
			this.amountStr = AmountUtil.changeF2Y(this.amount);
		}
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}

	public String getBalanceStr() {
		if(this.balance != null){
			this.balanceStr = AmountUtil.changeF2Y(this.balance);
		}
		return balanceStr;
	}

	public void setBalanceStr(String balanceStr) {
		this.balanceStr = balanceStr;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getBettingRecordId() {
		return bettingRecordId;
	}

	public void setBettingRecordId(Long bettingRecordId) {
		this.bettingRecordId = bettingRecordId;
	}

	public Long getRelationUserId() {
		return relationUserId;
	}

	public void setRelationUserId(Long relationUserId) {
		this.relationUserId = relationUserId;
	}
	
}
