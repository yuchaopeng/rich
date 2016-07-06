package com.yu.web.base.util;

import com.yu.base.util.DateUtils;
import com.yu.base.util.SpringUtil;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.service.SequenceService;

public class IDBuildUtil {
	public static synchronized String getOrderId(String productType){
		SequenceService sequenceService = (SequenceService) SpringUtil.getBean("sequenceServiceImpl");
		int id = sequenceService.get("payOrder");
		String sid = String.valueOf(id);
		
		if(sid.length() == 1){
			sid = "00"+sid;
		}else if(sid.length() == 2){
			sid = "0"+sid;
		}else if(sid .length() == 3){
			
		}else if(sid.length() > 3){
			sid = sid.substring(sid.length()-3);
		}
		
		String prefix = getOrderIdPrefix(productType);
		String date = DateUtils.getSimpleNowDateString();
		return prefix+date+sid;
	}
	
	private static String getOrderIdPrefix(String productType){
		String prefix = "10";
		if(GlobalConstants.PAY_ORDER_PRODUCT_BETTING.equals(productType)){
			prefix = "20";
		}else if(GlobalConstants.PAY_ORDER_PRODUCT_RECOMMENDER_SHARE.equals(productType)){
			prefix = "30";
		}else if(GlobalConstants.PAY_ORDER_PRODUCT_BETTNG_BACK.equals(productType)){
			prefix = "40";
		}else if(GlobalConstants.PAY_ORDER_PRODUCT_EXPERT_SHARE.equals(productType)){
			prefix = "50";
		}else if(GlobalConstants.PAY_ORDER_PRODUCT_WIN.equals(productType)){
			prefix = "60";
		}
		return prefix;
	}
	
	public static synchronized String getOrderItemId(){
		SequenceService sequenceService = (SequenceService) SpringUtil.getBean("sequenceServiceImpl"); 
		int id = sequenceService.get("payOrderItemId");
		return id+"";
	}
	
	public static synchronized String getUserAccountDetailId(){
		SequenceService sequenceService = (SequenceService) SpringUtil.getBean("sequenceServiceImpl");
		int id = sequenceService.get("userAccountDetail");
		return id+"";
	}
}
