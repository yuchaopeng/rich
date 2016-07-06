package com.yu.web.base.form;

public class PayOrderCreateForm {
	private String orderName;
	
	private Long planId;
	private Long matchId;
	private Long bettingRecordId;
	
	/**付款用户，账户*/
	private String payerUid;
	
	private String payerRelationUid;
	
	/**付款方式*/
	private String payerOrderMethodType;
	
	private String payerOrderItemId;
	
	/**收款用户，账户*/
	private String payeeUid;
	
	private String payeeRelationUid;
	
	/**收款方式*/
	private String payeeOrderMethodType;
	
	private String payeeOrderItemId;
	
	/**订单产品类型*/
	private String productType;
	
	/**订单金额*/
	private Integer amount;
	
	/**渠道*/
	private String channel;
	
	private String remark;

	/**
	 * @return the payerUid
	 */
	public String getPayerUid() {
		return payerUid;
	}

	/**
	 * @param payerUid the payerUid to set
	 */
	public void setPayerUid(String payerUid) {
		this.payerUid = payerUid;
	}

	/**
	 * @return the payeeUid
	 */
	public String getPayeeUid() {
		return payeeUid;
	}

	/**
	 * @param payeeUid the payeeUid to set
	 */
	public void setPayeeUid(String payeeUid) {
		this.payeeUid = payeeUid;
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
	 * @return the payerOrderMethodType
	 */
	public String getPayerOrderMethodType() {
		return payerOrderMethodType;
	}

	/**
	 * @param payerOrderMethodType the payerOrderMethodType to set
	 */
	public void setPayerOrderMethodType(String payerOrderMethodType) {
		this.payerOrderMethodType = payerOrderMethodType;
	}

	/**
	 * @return the payeeOrderMethodType
	 */
	public String getPayeeOrderMethodType() {
		return payeeOrderMethodType;
	}

	/**
	 * @param payeeOrderMethodType the payeeOrderMethodType to set
	 */
	public void setPayeeOrderMethodType(String payeeOrderMethodType) {
		this.payeeOrderMethodType = payeeOrderMethodType;
	}

	/**
	 * @return the payerOrderItemId
	 */
	public String getPayerOrderItemId() {
		return payerOrderItemId;
	}

	/**
	 * @param payerOrderItemId the payerOrderItemId to set
	 */
	public void setPayerOrderItemId(String payerOrderItemId) {
		this.payerOrderItemId = payerOrderItemId;
	}

	/**
	 * @return the payeeOrderItemId
	 */
	public String getPayeeOrderItemId() {
		return payeeOrderItemId;
	}

	/**
	 * @param payeeOrderItemId the payeeOrderItemId to set
	 */
	public void setPayeeOrderItemId(String payeeOrderItemId) {
		this.payeeOrderItemId = payeeOrderItemId;
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

	public String getPayerRelationUid() {
		return payerRelationUid;
	}

	public void setPayerRelationUid(String payerRelationUid) {
		this.payerRelationUid = payerRelationUid;
	}

	public String getPayeeRelationUid() {
		return payeeRelationUid;
	}

	public void setPayeeRelationUid(String payeeRelationUid) {
		this.payeeRelationUid = payeeRelationUid;
	}
	
}
