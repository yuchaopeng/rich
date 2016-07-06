package com.yu.web.base.model;

import java.util.Date;
import java.util.List;

public class PayOrder {
	private String orderId;
	private String originateOrderId;
	private String orderName;
	private Long planId;
	private Long matchId;
	private Long bettingRecordId;
	private String productType;
	private Integer amount;
	private String orderState;
	private String remark;
	private String comments;
	private String channel;
	private Date createDate;
	private Date lastUpdateDate;
	private List<PayOrderItem> orderItems;
	
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
	 * @return the originateOrderId
	 */
	public String getOriginateOrderId() {
		return originateOrderId;
	}
	/**
	 * @param originateOrderId the originateOrderId to set
	 */
	public void setOriginateOrderId(String originateOrderId) {
		this.originateOrderId = originateOrderId;
	}
	/**
	 * @return the orderName
	 */
	public String getOrderName() {
		return orderName;
	}
	/**
	 * @param orderName the orderName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
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
	 * @return the orderState
	 */
	public String getOrderState() {
		return orderState;
	}
	/**
	 * @param orderState the orderState to set
	 */
	public void setOrderState(String orderState) {
		this.orderState = orderState;
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
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
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
	 * @return the orderItems
	 */
	public List<PayOrderItem> getOrderItems() {
		return orderItems;
	}
	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<PayOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	/**
	 * @return the comment
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
}
