package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.AdminAccountInfo;
import com.yu.web.base.model.RechargeRequest;

public interface RechargeRequestService extends GenericService<RechargeRequest, Long> {
	
	RechargeRequest buildRechargeRequest(RechargeRequest rechargeRequest);
	
	RechargeRequest buildRechargeRequest(Long paymentMethodTypeId,AdminAccountInfo adminAccountInfo);
	
	void handleUserRecharge(RechargeRequest rechargeRequest) throws Exception;
}
