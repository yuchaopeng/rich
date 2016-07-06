package com.yu.web.base.json;

import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.WithdrawRequest;

public class WithdrawRequestJson {
	private Long id;
	private Long requestTypeId;
	private String requestTypeName;
	private Long userId;
	private String userName;
	private String userMobile;

	private Integer amount;
	private String amountStr;
	/**实际金额*/
	private Integer actualAmount;
	private String actualAmountStr;
	private Integer state;
	private String stateVal;
	private String paymentUserName;
	private String paymentBank;
	private String paymentAccount;
	private String paymentMobile;
	private String reciverUserName;
	private String reciverBank;
	private String reciverAccount;
	private String reciverMobile;
	private String requestDate;
	private String handleDate;
	private String createDate;
	private String lastUpdateDate;
	
	public WithdrawRequestJson(WithdrawRequest request) {
		this(request.getId(), 
				request.getUser() == null ? null : request.getUser().getId(), 
				request.getUser() == null ? null : request.getUser().getUsername(), 
				request.getUser() == null ? null : request.getUser().getMobile(),
				request.getRequestType() == null ? null : request.getRequestType().getId(),
				request.getRequestType() == null ? null : request.getRequestType().getTypeName(),
				request.getAmount(), 
				request.getAmountStr(),
				request.getActualAmount(), 
				request.getActualAmountStr(),
				request.getState(), 
				GlobalConstants.getRechargeRequestStateVal(request.getState()), 
				request.getPaymentUserName(), 
				request.getPaymentBank(), 
				request.getPaymentAccount(), 
				request.getPaymentMobile(), 
				request.getReciverUserName(),
				request.getReciverBank(), 
				request.getReciverAccount(), 
				request.getReciverMobile(), 
				DateUtils.getDateString(request.getRequestDate()), 
				DateUtils.getDateString(request.getHandleDate()), 
				DateUtils.getDateString(request.getCreateDate()), 
				DateUtils.getDateString(request.getLastUpdateDate()));
	}
	
	public WithdrawRequestJson(Long id, Long userId, String userName,String userMobile,Long requestTypeId,String requestTypeName,
			Integer amount,String amountStr, Integer actualAmount,String actualAmountStr, Integer state, String stateVal, String paymentUserName,
			String paymentBank, String paymentAccount, String paymentMobile, String reciverUserName, String reciverBank,
			String reciverAccount, String reciverMobile, String requestDate, String handleDate, String createDate,
			String lastUpdateDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.userMobile = userMobile;
		this.requestTypeId = requestTypeId;
		this.requestTypeName = requestTypeName;
		this.amount = amount;
		this.amountStr = amountStr;
		this.actualAmount = actualAmount;
		this.actualAmountStr = actualAmountStr;
		this.state = state;
		this.stateVal = stateVal;
		this.paymentUserName = paymentUserName;
		this.paymentBank = paymentBank;
		this.paymentAccount = paymentAccount;
		this.paymentMobile = paymentMobile;
		this.reciverUserName = reciverUserName;
		this.reciverBank = reciverBank;
		this.reciverAccount = reciverAccount;
		this.reciverMobile = reciverMobile;
		this.requestDate = requestDate;
		this.handleDate = handleDate;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getStateVal() {
		return stateVal;
	}

	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
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

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getRequestTypeId() {
		return requestTypeId;
	}

	public void setRequestTypeId(Long requestTypeId) {
		this.requestTypeId = requestTypeId;
	}

	public String getRequestTypeName() {
		return requestTypeName;
	}

	public void setRequestTypeName(String requestTypeName) {
		this.requestTypeName = requestTypeName;
	}
	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}

	public String getActualAmountStr() {
		return actualAmountStr;
	}

	public void setActualAmountStr(String actualAmountStr) {
		this.actualAmountStr = actualAmountStr;
	}
	
}
