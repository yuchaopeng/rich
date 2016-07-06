package com.yu.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.mapper.AdminAccountInfoMapper;
import com.yu.web.base.mapper.WithdrawRequestMapper;
import com.yu.web.base.model.AdminAccountInfo;
import com.yu.web.base.model.PaymentMethodType;
import com.yu.web.base.model.WithdrawRequest;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.UserAccountService;
import com.yu.web.base.service.WithdrawRequestService;
import com.yu.web.base.util.AmountUtil;

@Service
public class WithdrawRequestServiceImpl extends GenericServiceImpl<WithdrawRequest, Long> implements WithdrawRequestService {

	@Resource
	private WithdrawRequestMapper withdrawRequestMapper;
	
	@Resource
	private AdminAccountInfoMapper adminAccountInfoMapper;
	
	@Resource
    private UserAccountService userAccountService;
	
	@Resource
	private SequenceService sequenceService;
	
	public WithdrawRequest buildWithdrawRequest(WithdrawRequest withdrawRequest){
		int id = sequenceService.get("recharge");
		withdrawRequest.setId(Long.valueOf(id));
		withdrawRequest.setActualAmount(null);
		withdrawRequest.setState(GlobalConstants.WITHDRAW_STATE_CREATE);
		String amount = AmountUtil.changeY2F(withdrawRequest.getAmountStr());
		withdrawRequest.setAmount(Integer.valueOf(amount));
		return withdrawRequest;
	}
	
	public WithdrawRequest buildWithdrawRequest(Long paymentMethodTypeId,AdminAccountInfo adminAccountInfo){
		WithdrawRequest withdrawRequest = new WithdrawRequest();
		PaymentMethodType paymentMethodType = adminAccountInfoMapper.selectPaymentMethodTypeById(paymentMethodTypeId);
		withdrawRequest.setRequestType(paymentMethodType);
		
		withdrawRequest.setPaymentUserName(adminAccountInfo.getUserName());
		withdrawRequest.setPaymentBank(adminAccountInfo.getBank());
		withdrawRequest.setPaymentAccount(adminAccountInfo.getAccount());
		withdrawRequest.setPaymentMobile(adminAccountInfo.getMobile());
		
		return withdrawRequest;
	}
	
	public void handleWithdrawRequest(WithdrawRequest request) throws Exception{
		//先保存提现记录
		withdrawRequestMapper.insert(request);
		
		userAccountService.userWithdraw(request.getUser().getId(), request.getAmount());
	}
	
	public void handleWithdrawResult(Long withdrawRequestId) throws Exception{
		WithdrawRequest request = withdrawRequestMapper.selectById(withdrawRequestId);
		
		Integer withdrawAmount = request.getAmount();
		Integer actualWithdrawAmount = request.getActualAmount();
		
		userAccountService.userWithdrawResult(request.getUser().getId(), withdrawAmount, actualWithdrawAmount);
		
		request.setState(GlobalConstants.WITHDRAW_STATE_COMPLATE);
		withdrawRequestMapper.update(request);
	}
	
	@Override
	public GenericMapper<WithdrawRequest, Long> getMapper() {
		return withdrawRequestMapper;
	}
}