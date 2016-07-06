package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.AdminAccountInfo;
import com.yu.web.base.model.WithdrawRequest;

public interface WithdrawRequestService extends GenericService<WithdrawRequest, Long> {
	
	WithdrawRequest buildWithdrawRequest(WithdrawRequest withdrawRequest);
	
	WithdrawRequest buildWithdrawRequest(Long paymentMethodTypeId,AdminAccountInfo adminAccountInfo);
	
	void handleWithdrawRequest(WithdrawRequest request) throws Exception;
	
	void handleWithdrawResult(Long withdrawRequestId) throws Exception;
}
