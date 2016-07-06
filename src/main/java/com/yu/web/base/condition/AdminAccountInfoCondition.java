package com.yu.web.base.condition;

import com.yu.base.condition.BaseCondition;

public class AdminAccountInfoCondition extends BaseCondition{
	private Long paymentMethodType;

	public Long getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(Long paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}
	
}
