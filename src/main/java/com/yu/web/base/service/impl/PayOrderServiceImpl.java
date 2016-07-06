package com.yu.web.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.condition.UserAccountCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.form.PayOrderCreateForm;
import com.yu.web.base.mapper.PayOrderMapper;
import com.yu.web.base.mapper.UserAccountMapper;
import com.yu.web.base.mapper.UserMapper;
import com.yu.web.base.model.PayOrder;
import com.yu.web.base.model.PayOrderItem;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAccount;
import com.yu.web.base.model.UserAccountDetail;
import com.yu.web.base.service.PayOrderService;
import com.yu.web.base.util.IDBuildUtil;

@Service
public class PayOrderServiceImpl extends GenericServiceImpl<PayOrder, String> implements PayOrderService {

	@Resource
	private PayOrderMapper payOrderMapper;
	
	@Resource
	private UserAccountMapper userAccountMapper;
	
	@Resource
	private UserMapper userMapper;
	
	public PayOrder createPayOrder(PayOrderCreateForm orderCreateForm){
		PayOrder payOrder = null;
		try {
			if(orderCreateForm == null){
				throw new Exception("PayOrderCreateForm为空.");
			}
			
			if(StringUtils.isEmpty(orderCreateForm.getPayerUid())){
				throw new Exception("付款人payerId为空.");
			}
			
			if(StringUtils.isEmpty(orderCreateForm.getPayeeUid())){
				throw new Exception("收款人payeeId为空.");
			}
			
			if(orderCreateForm.getAmount() == null){
				throw new Exception("订单金额amount为空.");
			}
			
			if(StringUtils.isEmpty(orderCreateForm.getChannel())){
				throw new Exception("渠道channel为空.");
			}
			
			payOrder = new PayOrder();
			String orderId = IDBuildUtil.getOrderId(orderCreateForm.getProductType());
			payOrder.setOrderId(orderId);
			
			payOrder.setPlanId(orderCreateForm.getPlanId());
			payOrder.setMatchId(orderCreateForm.getMatchId());
			payOrder.setBettingRecordId(orderCreateForm.getBettingRecordId());
			
			payOrder.setOriginateOrderId(null);
			payOrder.setOrderName(orderCreateForm.getOrderName());
			payOrder.setProductType(orderCreateForm.getProductType());
			payOrder.setAmount(orderCreateForm.getAmount());
			payOrder.setRemark(orderCreateForm.getRemark());
			payOrder.setOrderState(GlobalConstants.PAY_ORDER_STATE_UNPAID);
			payOrder.setChannel(orderCreateForm.getChannel());
			
			boolean isSuccess = true;
			
			StringBuffer comments = new StringBuffer();
			String payerUidStr = orderCreateForm.getPayerUid();
			String payerAccountNum = orderCreateForm.getPayerUid();
			String payerOrderMethodType = orderCreateForm.getPayerOrderMethodType();
			if(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT.equals(payerOrderMethodType)){
				Long payerUid = Long.valueOf(payerUidStr);
				User payerUser = userMapper.selectById(payerUid);
				if(payerUser == null){
					comments.append("付款人["+payerUid+"]不存在.");
					isSuccess = false;
				}else{
					UserAccountCondition accountCondition = new UserAccountCondition();
					accountCondition.setUserId(payerUid);
					List<UserAccount> payerUserAccounts = userAccountMapper.selectList(accountCondition);
					
					if(CollectionUtils.isEmpty(payerUserAccounts)){
						comments.append("付款人["+payerUser.getMobile()+"("+payerUser.getUsername()+")]找不到账户.");
						isSuccess = false;
					}else{
						UserAccount payerUserAccount = payerUserAccounts.get(0);
						payerAccountNum = payerUserAccount.getId();
					}
				}
			}
			
			String payeeUidStr = orderCreateForm.getPayeeUid();
			String payeeOrderMethodType = orderCreateForm.getPayeeOrderMethodType();
			String payeeAccountNum = orderCreateForm.getPayeeUid();
			if(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT.equals(payeeOrderMethodType)){
				Long payeeUid = Long.valueOf(payeeUidStr);
				User payeeUser = userMapper.selectById(payeeUid);
				if(payeeUser == null){
					comments.append("收款人["+payeeUid+"]不存在.");
					isSuccess = false;
				}else{
					UserAccountCondition accountCondition = new UserAccountCondition();
					accountCondition.setUserId(payeeUid);
					List<UserAccount> payeeUserAccounts = userAccountMapper.selectList(accountCondition);
					
					if(CollectionUtils.isEmpty(payeeUserAccounts)){
						comments.append("收款人["+payeeUser.getMobile()+"("+payeeUser.getUsername()+")]找不到账户.");
						isSuccess = false;
					}else{
						UserAccount payeeUserAccount = payeeUserAccounts.get(0);
						payeeAccountNum = payeeUserAccount.getId();
					}
				}
			}
			
			PayOrderItem payOrderItemR = new PayOrderItem();
			payOrderItemR.setOrderItemId(orderCreateForm.getPayerOrderItemId());
			payOrderItemR.setOrderId(orderId);
			payOrderItemR.setOrderType(GlobalConstants.PAY_ORDER_TYPE_R);
			payOrderItemR.setOrderMethodType(payerOrderMethodType);
			payOrderItemR.setAccountNum(payerAccountNum);
			payOrderItemR.setUserId(payerUidStr);
			payOrderItemR.setRelationRealUserId(orderCreateForm.getPayerRelationUid());
			payOrderItemR.setRelationAccountNum(payeeUidStr);
			payOrderItemR.setRelationUserId(payeeAccountNum);
			payOrderItemR.setOrderItemSort(1);
			
			PayOrderItem payOrderItemD = new PayOrderItem();
			payOrderItemD.setOrderItemId(orderCreateForm.getPayeeOrderItemId());
			payOrderItemD.setOrderId(orderId);
			payOrderItemD.setOrderType(GlobalConstants.PAY_ORDER_TYPE_D);
			payOrderItemD.setOrderMethodType(payeeOrderMethodType);
			payOrderItemD.setAccountNum(payeeAccountNum);
			payOrderItemD.setUserId(payeeUidStr);
			payOrderItemD.setRelationRealUserId(orderCreateForm.getPayeeRelationUid());
			payOrderItemD.setRelationAccountNum(payerUidStr);
			payOrderItemD.setRelationUserId(payerAccountNum);
			payOrderItemD.setOrderItemSort(2);
			
			payOrder.setComments(comments.toString());
			if(!isSuccess){
				payOrder.setOrderState(GlobalConstants.PAY_ORDER_STATE_ERROR);
			}
			
			payOrderMapper.insert(payOrder);
			payOrderMapper.insertOrderItem(payOrderItemR);
			payOrderMapper.insertOrderItem(payOrderItemD);
			
			List<PayOrderItem> orderItems = new ArrayList<>();
			orderItems.add(payOrderItemR);
			orderItems.add(payOrderItemD);
			payOrder.setOrderItems(orderItems);
		} catch (NumberFormatException e) {
			payOrder = null;
			e.printStackTrace();
		} catch (Exception e) {
			payOrder = null;
			e.printStackTrace();
		}
		return payOrder;
	}
	
	
	public Map<String,Object> orderPay(PayOrder payOrder){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		boolean isSuccess = true;;
		try {
			if(!GlobalConstants.PAY_ORDER_STATE_UNPAID.equals(payOrder.getOrderState())){
				resultMap.put(GlobalConstants.PAY_ORDER_RESULT_FLAG, false);
				resultMap.put(GlobalConstants.PAY_ORDER_RESULT_MESSAGE, "订单不为待付款状态");
				return resultMap;
			}
			List<PayOrderItem> orderItems = payOrder.getOrderItems();
			StringBuffer comments = new StringBuffer();
			Integer amount = payOrder.getAmount();
			isSuccess = true;
			for(PayOrderItem orderItem : orderItems){
				if(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT.equals(orderItem.getOrderMethodType())){//如果是账户支付
					UserAccount userAccount = userAccountMapper.selectByIdForUpdate(orderItem.getAccountNum());
					Integer balance = userAccount.getBalance();
					Integer direction = null;
					if(GlobalConstants.PAY_ORDER_TYPE_R.equals(orderItem.getOrderType())){//如果是收款
						if(balance < amount){
							comments.append("账户余额不足");
							isSuccess = false;
							payOrder.setOrderState(GlobalConstants.PAY_ORDER_STATE_ERROR);
							break;
						}
						balance = balance - amount;	
						if(balance == 0){
							balance = new Integer(0);
						}
						payOrder.setOrderState(GlobalConstants.PAY_ORDER_STATE_PAING);
						direction = 1;
					}
					if(GlobalConstants.PAY_ORDER_TYPE_D.equals(orderItem.getOrderType())){//如果是收款
						balance = balance + amount;	
						payOrder.setOrderState(GlobalConstants.PAY_ORDER_STATE_PAID);
						direction = 0;
					}
					userAccount.setBalance(balance);
					
					UserAccountDetail detail = new UserAccountDetail();
					String detailId = IDBuildUtil.getUserAccountDetailId();
					detail.setId(detailId);
					detail.setUserId(orderItem.getUserId());
					detail.setAccountId(orderItem.getAccountNum());
					detail.setRelationUserId(Long.valueOf(orderItem.getRelationRealUserId()));
					String type = GlobalConstants.getUserAccountDetailType(payOrder.getProductType()+"_"+orderItem.getOrderType());
					detail.setType(type);
					detail.setDirection(direction);
					if(direction == 1){
						detail.setToAccountId(orderItem.getRelationAccountNum());
						detail.setToUserId(orderItem.getRelationUserId());
						
					}else if(direction == 0){
						detail.setFromAccountId(orderItem.getRelationAccountNum());
						detail.setFromUserId(orderItem.getRelationUserId());
					}
					detail.setAmount(amount);
					detail.setBalance(balance);
					
					detail.setPlanId(payOrder.getPlanId());
					detail.setMatchId(payOrder.getMatchId());
					detail.setBettingRecordId(payOrder.getBettingRecordId());
					
					detail.setRemark("");
					
					userAccountMapper.update(userAccount);
					userAccountMapper.insertUserAccountDetail(detail);
				}else if(GlobalConstants.PAY_ORDER_METHOD_TYPE_ALIPAY.equals(orderItem.getOrderMethodType())){
					payOrder.setOrderState(GlobalConstants.PAY_ORDER_STATE_HANG);//设置为挂账状态
					break;
				}else if(GlobalConstants.PAY_ORDER_METHOD_TYPE_BANK_CARD.equals(orderItem.getOrderMethodType())){
					payOrder.setOrderState(GlobalConstants.PAY_ORDER_STATE_HANG);//设置为挂账状态
					break;
				}else if(GlobalConstants.PAY_ORDER_METHOD_TYPE_WECHAT.equals(orderItem.getOrderMethodType())){
					payOrder.setOrderState(GlobalConstants.PAY_ORDER_STATE_HANG);//设置为挂账状态
					break;
				}else{
					isSuccess = false;
				}
			}
			payOrder.setComments(comments.toString());
			payOrderMapper.update(payOrder);
			
			resultMap.put(GlobalConstants.PAY_ORDER_RESULT_FLAG, isSuccess);
			resultMap.put(GlobalConstants.PAY_ORDER_RESULT_MESSAGE, comments.toString());
		} catch (Exception e) {
			resultMap.put(GlobalConstants.PAY_ORDER_RESULT_FLAG, false);
			resultMap.put(GlobalConstants.PAY_ORDER_RESULT_MESSAGE, e.getMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@Override
	public GenericMapper<PayOrder, String> getMapper() {
		return payOrderMapper;
	}
}