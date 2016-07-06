package com.yu.web.base.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.form.PayOrderCreateForm;
import com.yu.web.base.mapper.AdminAccountInfoMapper;
import com.yu.web.base.mapper.RechargeRequestMapper;
import com.yu.web.base.model.AdminAccountInfo;
import com.yu.web.base.model.PayOrder;
import com.yu.web.base.model.PaymentMethodType;
import com.yu.web.base.model.RechargeRequest;
import com.yu.web.base.model.User;
import com.yu.web.base.service.PayOrderService;
import com.yu.web.base.service.RechargeRequestService;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.UserAccountService;
import com.yu.web.base.util.AmountUtil;
import com.yu.web.base.util.IDBuildUtil;

@Service
public class RechargeRequestServiceImpl extends GenericServiceImpl<RechargeRequest, Long> implements RechargeRequestService {

	@Resource
	private RechargeRequestMapper rechargeRequestMapper;
	
	@Resource
	private AdminAccountInfoMapper adminAccountInfoMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	@Resource
	private PayOrderService payOrderService;
	
	@Resource
	private UserAccountService userAccountService;
	
	public RechargeRequest buildRechargeRequest(RechargeRequest rechargeRequest){
		int id = sequenceService.get("recharge");
		rechargeRequest.setId(Long.valueOf(id));
		rechargeRequest.setActualAmount(null);
		rechargeRequest.setState(GlobalConstants.RECHARGE_STATE_CREATE);
		String amount = AmountUtil.changeY2F(rechargeRequest.getRechargeAmountStr());
		rechargeRequest.setAmount(Integer.valueOf(amount));
		return rechargeRequest;
	}
	
	public RechargeRequest buildRechargeRequest(Long paymentMethodTypeId,AdminAccountInfo adminAccountInfo){
		RechargeRequest rechargeRequest = new RechargeRequest();
		PaymentMethodType paymentMethodType = adminAccountInfoMapper.selectPaymentMethodTypeById(paymentMethodTypeId);
		rechargeRequest.setRequestType(paymentMethodType);
		
		rechargeRequest.setReciverUserName(adminAccountInfo.getUserName());
		rechargeRequest.setReciverBank(adminAccountInfo.getBank());
		rechargeRequest.setReciverAccount(adminAccountInfo.getAccount());
		rechargeRequest.setReciverMobile(adminAccountInfo.getMobile());
		
		return rechargeRequest;
	}
	
	@Override
	public GenericMapper<RechargeRequest, Long> getMapper() {
		return rechargeRequestMapper;
	}

	@Override
	public void handleUserRecharge(RechargeRequest rechargeRequest) throws Exception {
		User user = rechargeRequest.getUser();
		PayOrderCreateForm form = new PayOrderCreateForm();
		form.setOrderName("充值："+user.getUsername());
		form.setPayerUid(GlobalConstants.COMPANY_USER_ID);
		form.setPayerRelationUid(String.valueOf(user.getId()));
		form.setPayerOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
		form.setPayerOrderItemId(IDBuildUtil.getOrderItemId());
		form.setPayeeUid(String.valueOf(user.getId()));
		form.setPayeeRelationUid(GlobalConstants.COMPANY_USER_ID);
		form.setPayeeOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
		form.setPayeeOrderItemId(IDBuildUtil.getOrderItemId());
		form.setProductType(GlobalConstants.PAY_ORDER_PRODUCT_RECHARGE);
		form.setAmount(rechargeRequest.getActualAmount());
		form.setChannel(GlobalConstants.CHANNEL_BACK);
		PayOrder payOrder = payOrderService.createPayOrder(form);
		
		Map<String,Object> resultMap = payOrderService.orderPay(payOrder);
		boolean flag = Boolean.valueOf(resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG) == null ? "false" : resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG).toString());
		Object message = resultMap.get(GlobalConstants.PAY_ORDER_RESULT_MESSAGE);
		if(flag){
			userAccountService.handleUserTotalAdd(user.getId(), rechargeRequest.getActualAmount());
			
			rechargeRequest.setState(GlobalConstants.RECHARGE_STATE_COMPLATE);
			rechargeRequestMapper.update(rechargeRequest);
		}else{
			throw new Exception("充值失败："+message);
		}
	}
}