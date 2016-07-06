package com.yu.web.base.model;

public class PayOrderItem {
	private String orderItemId;
	private String orderId;
	private String orderType;
	private String orderMethodType;
	private String accountNum;
	private String userId;
	private String relationAccountNum;
	private String relationUserId;
	private String relationRealUserId;
	private Integer orderItemSort;
	private String createDate;
	private String lastUpdateDate;
	
	/**
	 * @return the orderItemId
	 */
	public String getOrderItemId() {
		return orderItemId;
	}
	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
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
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the orderMethodType
	 */
	public String getOrderMethodType() {
		return orderMethodType;
	}
	/**
	 * @param orderMethodType the orderMethodType to set
	 */
	public void setOrderMethodType(String orderMethodType) {
		this.orderMethodType = orderMethodType;
	}
	/**
	 * @return the accountNum
	 */
	public String getAccountNum() {
		return accountNum;
	}
	/**
	 * @param accountNum the accountNum to set
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
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
	 * @return the lastUpdateDate
	 */
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * @return the orderItemSort
	 */
	public Integer getOrderItemSort() {
		return orderItemSort;
	}
	/**
	 * @param orderItemSort the orderItemSort to set
	 */
	public void setOrderItemSort(Integer orderItemSort) {
		this.orderItemSort = orderItemSort;
	}
	/**
	 * @return the relationAccountNum
	 */
	public String getRelationAccountNum() {
		return relationAccountNum;
	}
	/**
	 * @param relationAccountNum the relationAccountNum to set
	 */
	public void setRelationAccountNum(String relationAccountNum) {
		this.relationAccountNum = relationAccountNum;
	}
	/**
	 * @return the relationUserId
	 */
	public String getRelationUserId() {
		return relationUserId;
	}
	/**
	 * @param relationUserId the relationUserId to set
	 */
	public void setRelationUserId(String relationUserId) {
		this.relationUserId = relationUserId;
	}
	public String getRelationRealUserId() {
		return relationRealUserId;
	}
	public void setRelationRealUserId(String relationRealUserId) {
		this.relationRealUserId = relationRealUserId;
	}
}
