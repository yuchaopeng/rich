package com.yu.web.base.form;

import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.UserAccountDetail;
import com.yu.web.base.util.AmountUtil;

public class UserAccountDetailForm {
	private UserAccountDetail userAccountDetail;
	
	private String typeName;
	private String amount;
	private String balance;
	
	public UserAccountDetailForm(UserAccountDetail userAccountDetail){
		this.userAccountDetail = userAccountDetail;
		if(userAccountDetail != null){
			this.typeName = GlobalConstants.getTranTypeName(userAccountDetail.getType());
			this.amount = AmountUtil.changeF2Y(userAccountDetail.getAmount());
			this.balance = AmountUtil.changeF2Y(userAccountDetail.getBalance());
		}
	}
	
	public UserAccountDetail getUserAccountDetail() {
		return userAccountDetail;
	}
	public void setUserAccountDetail(UserAccountDetail userAccountDetail) {
		this.userAccountDetail = userAccountDetail;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
