package com.yu.web.base.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.form.PayOrderCreateForm;
import com.yu.web.base.mapper.UserBettingRecordMapper;
import com.yu.web.base.model.PayOrder;
import com.yu.web.base.model.UserBettingRecord;
import com.yu.web.base.service.PayOrderService;
import com.yu.web.base.service.UserAccountService;
import com.yu.web.base.service.UserBettingRecordService;
import com.yu.web.base.util.IDBuildUtil;

@Service
public class UserBettingRecordServiceImpl extends GenericServiceImpl<UserBettingRecord, Long> implements UserBettingRecordService {
	
	@Resource
	private UserBettingRecordMapper userBettingRecordMapper;
	
	@Resource
	private PayOrderService payOrderService;
	
	@Resource
	private UserAccountService userAccountService;

	@Override
	public void deleteBettingLottery(Long bettingId) {
		userBettingRecordMapper.deleteBettingLottery(bettingId);
	}

	@Override
	public GenericMapper<UserBettingRecord, Long> getMapper() {
		return userBettingRecordMapper;
	}

	@Override
	public void handleBetting(UserBettingRecord record) {
		PayOrderCreateForm form = new PayOrderCreateForm();
		form.setOrderName("投注："+record.getPlan().getPlanName());
		
		form.setPlanId(record.getPlan() == null ? null : record.getPlan().getId());
		form.setMatchId(record.getPlan() == null ? null : record.getMatch().getId());
		form.setBettingRecordId(record.getId());
		
		form.setPayerUid(String.valueOf(record.getUser().getId()));
		form.setPayerRelationUid(GlobalConstants.COMPANY_USER_ID);
		form.setPayerOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
		form.setPayerOrderItemId(IDBuildUtil.getOrderItemId());
		form.setPayeeUid(GlobalConstants.COMPANY_USER_ID);
		form.setPayeeRelationUid(String.valueOf(record.getUser().getId()));
		form.setPayeeOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
		form.setPayeeOrderItemId(IDBuildUtil.getOrderItemId());
		form.setProductType(GlobalConstants.PAY_ORDER_PRODUCT_BETTING);
		form.setAmount(record.getBettingAmount());
		form.setChannel(GlobalConstants.CHANNEL_AUTO);
		PayOrder payOrder = payOrderService.createPayOrder(form);
		
		Map<String,Object> resultMap = payOrderService.orderPay(payOrder);
		boolean flag = Boolean.valueOf(resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG) == null ? "false" : resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG).toString());
		
		if(payOrder != null){
			record.setOrderId(payOrder.getOrderId());
		}
		if(flag){
			record.setState(GlobalConstants.USER_BETTING_STATE_SUCCESS);
		}else{
			String resultMessage = (String) resultMap.get(GlobalConstants.PAY_ORDER_RESULT_MESSAGE);
			record.setRemark(resultMessage);
			record.setBettingAmount(0);
			record.setState(GlobalConstants.USER_BETTING_STATE_FAILED);
		}
		userBettingRecordMapper.update(record);
		
		userAccountService.handleUserBetting(record.getUser().getId(), 0, record.getBettingAmount());
		
	}
	
}
