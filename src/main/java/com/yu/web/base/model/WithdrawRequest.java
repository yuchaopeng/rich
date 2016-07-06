package com.yu.web.base.model;

import java.util.Date;

import com.yu.web.base.util.AmountUtil;

public class WithdrawRequest {
	private Long id;
	private PaymentMethodType requestType;
	private User user;
	private Integer amount;
	private String amountStr;
	/**实际金额*/
	private Integer actualAmount;
	private String actualAmountStr;
	private Integer state;
	
	private String paymentUserName;
	private String paymentBank;
	private String paymentAccount;
	private String paymentMobile;
	
	private String reciverUserName;
	private String reciverBank;
	private String reciverAccount;
	private String reciverMobile;
	
	private String remark;
	
	private Date requestDate;
	private Date handleDate;
	private Date createDate;
	private Date lastUpdateDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAmountStr() {
		if(this.amount != null){
			this.amountStr =  AmountUtil.changeF2Y(this.amount);
		}
		return amountStr;
	}
	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}
	public String getActualAmountStr() {
		if(this.actualAmount != null){
			this.actualAmountStr = AmountUtil.changeF2Y(this.actualAmount);
		}
		return actualAmountStr;
	}
	public void setActualAmountStr(String actualAmountStr) {
		this.actualAmountStr = actualAmountStr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(Integer actualAmount) {
		this.actualAmount = actualAmount;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
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
	public String getPaymentUserName() {
		return paymentUserName;
	}
	public void setPaymentUserName(String paymentUserName) {
		this.paymentUserName = paymentUserName;
	}
	public String getPaymentBank() {
		return paymentBank;
	}
	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}
	public String getPaymentAccount() {
		return paymentAccount;
	}
	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}
	public String getPaymentMobile() {
		return paymentMobile;
	}
	public void setPaymentMobile(String paymentMobile) {
		this.paymentMobile = paymentMobile;
	}
	
	public String getReciverUserName() {
		return reciverUserName;
	}
	public void setReciverUserName(String reciverUserName) {
		this.reciverUserName = reciverUserName;
	}
	public String getReciverBank() {
		return reciverBank;
	}
	public void setReciverBank(String reciverBank) {
		this.reciverBank = reciverBank;
	}
	public String getReciverAccount() {
		return reciverAccount;
	}
	public void setReciverAccount(String reciverAccount) {
		this.reciverAccount = reciverAccount;
	}
	public String getReciverMobile() {
		return reciverMobile;
	}
	public void setReciverMobile(String reciverMobile) {
		this.reciverMobile = reciverMobile;
	}
	public PaymentMethodType getRequestType() {
		return requestType;
	}
	public void setRequestType(PaymentMethodType requestType) {
		this.requestType = requestType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
