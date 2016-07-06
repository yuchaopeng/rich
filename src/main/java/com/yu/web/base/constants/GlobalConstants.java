package com.yu.web.base.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalConstants {
	public static int SEX_MAN = 1;
	public static int SEX_WOMAN = 2;
	
	public static String COMPANY_USER_ID = "0";
	
	public static Long WEB_SITE_CONFIG_ID = 1L;
	
	/**用户状态正常生效*/
	public static int USER_STATE_NORMAL = 1;
	/**用户状态未生效*/
	public static int USER_STATE_INVALID = 2;
	/**用户状态删除*/
	public static int USER_STATE_DELETED = 3;
	
	/**用户账户类型*/
	public static int USER_ACCOUNT_TYPE_LOTTERY = 1;
	
	/**用户账户状态：正常*/
	public static int USER_ACCOUNT_STATE_NORMAL = 1;
	/**用户账户状态：冻结*/
	public static int USER_ACCOUNT_STATE_FREEZE = 2;
	
	/**提成类型：不分成*/
	public static int USER_AGREEMENT_NOTHING = 0;
	/**提成类型：按比例分成*/
	public static int USER_AGREEMENT_PROP = 1;
	/**提成类型：按次分成*/
	public static int USER_AGREEMENT_NUM = 2;
	
	/**0.管理员，1.普通用户，2.运营用户，3.门店职员，4.专家*/
	/**用户类型：普通用户*/
	public static int USER_TYPE_NORMAL = 0;
	/**用户类型：管理员*/
	public static int USER_TYPE_ADMIN = 1;
	/**用户类型：运营用户*/
	public static int USER_TYPE_OPERATIONS = 2;
	/**用户类型：门店职员*/
	public static int USER_TYPE_WORKER = 3;
	/**用户类型：专家*/
	public static int USER_TYPE_EXPERT = 4;
	/**用户类型：公司级别用户，目前只有一条*/
	public static int USER_TYPE_COMPANY = 5;
	
	/**计划状态：未生效*/
	public static int PLAN_STATE_NORMAL = 1;
	/**计划状态：已生效*/
	public static int PLAN_STATE_INVALID = 2;
	
	/**赛事状态：草稿*/
	public static int MATCH_STATE_DRAFT = 1;
	/**赛事状态：已发布*/
	public static int MATCH_STATE_PUBLISH = 2;
	/**赛事状态：有彩果*/
	public static int MATCH_STATE_HAVE_RESULT = 3;
	/**赛事状态：已计奖*/
	public static int MATCH_STATE_HAVE_CAL_PRIZE = 4;
	
	/**用户投注状态：投注中*/
	public static int USER_BETTING_STATE_BETTING = 1;
	/**用户投注状态：投注失败*/
	public static int USER_BETTING_STATE_FAILED = 2;
	/**用户投注状态：投注成功*/
	public static int USER_BETTING_STATE_SUCCESS = 3;
	/**用户投注状态：未生效投注*/
	public static int USER_BETTING_STATE_ROLLBACK = 4;
	/**用户投注状态：中奖*/
	public static int USER_BETTING_STATE_WIN = 5;
	/**用户投注状态：计奖失败*/
	public static int USER_BETTING_STATE_WIN_ERROR = 6;
	/**用户投注状态：未中奖*/
	public static int USER_BETTING_STATE_LOSS = 7;
	
	/**彩票未上传*/
	public static int LOTTERY_PHOTO_UPLOAD_NOT = 1;
	/**彩票已上传*/
	public static int LOTTERY_PHOTO_UPLOAD_FINISH = 2;
	
	/**投注方式：均投*/
	public static int BETTING_MODEL_EVEN = 1;
	/**投注方式：倍投*/
	public static int BETTING_MODEL_MULTI = 2;
	
	/**yibangmai*/
	public static int USER_HELP_BUY_OK = 1;
	/***/
	public static int USER_HELP_BUY_CANCEL = 2;
	
	/**赛事结果：胜*/
	public static int MATCH_RESULT_WIN = 1;
	/**赛事结果：负*/
	public static int MATCH_RESULT_LOSS = 2;
	
	/**赛事明细结果：胜*/
	public static int MATCH_DETAIL_RESULT_WIN = 1;
	/**赛事明细结果：负*/
	public static int MATCH_DETAIL_RESULT_LOSS = 2;
	
	/**订单产品类型：投注*/
	public static String PAY_ORDER_PRODUCT_BETTING = "BETTING";
	/**订单产品类型：投注退回*/
	public static String PAY_ORDER_PRODUCT_BETTNG_BACK = "BETTING_BACK";
	/**订单产品类型：中奖*/
	public static String PAY_ORDER_PRODUCT_WIN = "WIN";
	/**订单产品类型：推荐人分成*/
	public static String PAY_ORDER_PRODUCT_RECOMMENDER_SHARE = "RECOMMENDER_SHARE";
	/**订单产品类型：专家分成*/
	public static String PAY_ORDER_PRODUCT_EXPERT_SHARE = "EXPERT_SHARE";
	/**订单产品类型：充值*/
	public static String PAY_ORDER_PRODUCT_RECHARGE = "RECHARGE";
	/**订单产品类型：提现*/
	public static String PAY_ORDER_PRODUCT_WITHDRAW  = "WITHDRAW";
	
	/**订单状态：创建订单失败*/
	public static String PAY_ORDER_STATE_ERROR  = "ERROR";
	/**订单状态：待付款*/
	public static String PAY_ORDER_STATE_UNPAID  = "UNPAID";
	/**订单状态：付款中*/
	public static String PAY_ORDER_STATE_PAING  = "PAING";
	/**订单状态：挂起*/
	public static String PAY_ORDER_STATE_HANG = "HANG";
	/**订单状态：已付款*/
	public static String PAY_ORDER_STATE_PAID  = "PAID";
	
	/**订单项类型：收款项*/
	public static String PAY_ORDER_TYPE_R = "R";
	/**订单项类型：付款项*/
	public static String PAY_ORDER_TYPE_D = "D";
	
	/**订单项支付方式：账户支付*/
	public static String PAY_ORDER_METHOD_TYPE_ACCOUNT = "ACCOUNT";
	/**订单项支付方式：银行卡*/
	public static String PAY_ORDER_METHOD_TYPE_BANK_CARD = "BANK_CARD";
	/**订单项支付方式：支付宝*/
	public static String PAY_ORDER_METHOD_TYPE_ALIPAY = "ALIPAY";
	/**订单项支付方式：微信*/
	public static String PAY_ORDER_METHOD_TYPE_WECHAT = "WECHAT";
	
	/**
	 * 订单支付返回时判断成功的标识：boolean型
	 */
	public static String PAY_ORDER_RESULT_FLAG = "resultFlag";
	/**
	 * 订单支付返回的结果信息：字符串型
	 */
	public static String PAY_ORDER_RESULT_MESSAGE = "resultMessage";
	
	/**渠道：自动发起*/
	public static String CHANNEL_AUTO = "00";
	/**渠道：后台管理系统发起*/
	public static String CHANNEL_BACK = "01";
	/**渠道：手机端发起*/
	public static String CHANNEL_MOBILE = "02";
	
	/**充值状态：未确认*/
	public static Integer RECHARGE_STATE_CREATE = 1;
	/**充值状态：已确认*/
	public static Integer RECHARGE_STATE_COMPLATE = 2;
	
	/**提现状态：未确认*/
	public static Integer WITHDRAW_STATE_CREATE = 1;
	/**提现状态：已确认*/
	public static Integer WITHDRAW_STATE_COMPLATE = 2;
	
	/**系统信息状态：草稿*/
	public static Integer SYSTEM_MESSAGE_DRAFT = 1;
	/**系统信息状态：已发布*/
	public static Integer SYSTEM_MESSAGE_PUBLISHED = 2;
	
	/**报表任务类型：用户报表任务*/
	public static Integer REPORT_TASK_TYPE_USER_REPORT = 1;
	/**报表任务类型：用户明细报表任务*/
	public static Integer REPORT_TASK_TYPE_USER_DETAIL_REPORT = 2;
	
	/**报表任务状态：已创建*/
	public static Integer REPORT_TASK_STATE_CREATE = 1;
	/**报表任务状态：处理完成*/
	public static Integer REPORT_TASK_STATE_FINISH = 2;
	/**报表任务状态：处理失败*/
	public static Integer REPORT_TASK_STATE_ERROR = 3;
	
	private static Map<Integer,String> sexMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> userStateMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> agreementTypeMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> userTypeMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> planStateMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> matchStateMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> matchResultMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> matchDetailResultMap = new HashMap<Integer,String>();
	
	/**用户类型角色关系*/
	private static Map<Integer,List<String>> userTypeRole = new HashMap<Integer,List<String>>();
	
	
	private static Map<String,String> userAccountDetailTypes = new HashMap<String,String>();
	
	private static Map<Integer,String> bettingRecordStateMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> lotteryUploadStateMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> rechargeRequestStateMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> withdrawRequestStateMap = new HashMap<Integer,String>();
	
	private static Map<Integer,String> systemMessageStateMap = new HashMap<Integer,String>();
	
	private static Map<String,String> tranTypeMap = new HashMap<String,String>();
	
	private static String queryOrdinaryUserTypes = USER_TYPE_NORMAL+"";

	private static String querySpecialUserTypes = USER_TYPE_ADMIN+","+USER_TYPE_OPERATIONS+","+USER_TYPE_WORKER+","+USER_TYPE_EXPERT;
	
	private static String queryAllUserTypes = USER_TYPE_NORMAL+","+USER_TYPE_ADMIN+","+USER_TYPE_OPERATIONS+","+USER_TYPE_WORKER+","+USER_TYPE_EXPERT;
	
	static{
		sexMap.put(SEX_MAN, "男");
		sexMap.put(SEX_WOMAN, "女");
		
		userStateMap.put(USER_STATE_NORMAL, "生效");
		userStateMap.put(USER_STATE_INVALID, "未生效");
		userStateMap.put(USER_STATE_DELETED, "已删除");
		
		agreementTypeMap.put(USER_AGREEMENT_NOTHING, "不分成");
		agreementTypeMap.put(USER_AGREEMENT_PROP, "按比例");
		agreementTypeMap.put(USER_AGREEMENT_NUM, "按次");
		
		userTypeMap.put(USER_TYPE_NORMAL, "普通用户");
		userTypeMap.put(USER_TYPE_ADMIN, "管理员");
		userTypeMap.put(USER_TYPE_OPERATIONS, "运营人员");
		userTypeMap.put(USER_TYPE_WORKER, "门店职员");
		userTypeMap.put(USER_TYPE_EXPERT, "专家");
		
		planStateMap.put(PLAN_STATE_NORMAL, "未生效");
		planStateMap.put(PLAN_STATE_INVALID, "生效");
		
		matchStateMap.put(MATCH_STATE_DRAFT, "草稿");
		matchStateMap.put(MATCH_STATE_PUBLISH, "已发布");
		matchStateMap.put(MATCH_STATE_HAVE_RESULT, "有彩果");
		matchStateMap.put(MATCH_STATE_HAVE_CAL_PRIZE, "已计奖");
		
		matchResultMap.put(MATCH_RESULT_WIN, "胜");
		matchResultMap.put(MATCH_RESULT_LOSS, "负");
		
		matchDetailResultMap.put(MATCH_DETAIL_RESULT_WIN, "胜");
		matchDetailResultMap.put(MATCH_DETAIL_RESULT_LOSS, "负");
		
		
		bettingRecordStateMap.put(USER_BETTING_STATE_BETTING, "投注中");
		bettingRecordStateMap.put(USER_BETTING_STATE_FAILED, "投注失败");
		bettingRecordStateMap.put(USER_BETTING_STATE_SUCCESS, "投注成功");
		bettingRecordStateMap.put(USER_BETTING_STATE_ROLLBACK, "未生效投注");
		bettingRecordStateMap.put(USER_BETTING_STATE_WIN, "中奖");
		bettingRecordStateMap.put(USER_BETTING_STATE_WIN_ERROR, "计奖失败");
		bettingRecordStateMap.put(USER_BETTING_STATE_LOSS, "未中奖");
		
		lotteryUploadStateMap.put(LOTTERY_PHOTO_UPLOAD_NOT, "未上传");
		lotteryUploadStateMap.put(LOTTERY_PHOTO_UPLOAD_FINISH, "已上传");
		
		rechargeRequestStateMap.put(1, "未确认");
		rechargeRequestStateMap.put(2, "已确认");
		
		withdrawRequestStateMap.put(1, "未确认");
		withdrawRequestStateMap.put(2, "已确认");
		
		systemMessageStateMap.put(1, "草稿");
		systemMessageStateMap.put(2, "已发布");
		
		List<String> normalUserRoles = new ArrayList<String>();
		List<String> adminUserRoles	= new ArrayList<String>();
		List<String> operationsUserRoles = new ArrayList<String>();
		List<String> workerUserRoles = new ArrayList<String>();
		List<String> expertUserRoles = new ArrayList<String>();
		
		normalUserRoles.add("NORMAL");
		adminUserRoles.add("ADMIN");
		operationsUserRoles.add("OPERATE");
		workerUserRoles.add("WORKER");
		expertUserRoles.add("EXPERT");
		
		userTypeRole.put(USER_TYPE_NORMAL, normalUserRoles);
		userTypeRole.put(USER_TYPE_ADMIN, adminUserRoles);
		userTypeRole.put(USER_TYPE_OPERATIONS, operationsUserRoles);
		userTypeRole.put(USER_TYPE_WORKER, workerUserRoles);
		userTypeRole.put(USER_TYPE_EXPERT, expertUserRoles);
		
		
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_BETTING+"_"+PAY_ORDER_TYPE_R, "BETTING_R");
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_BETTING+"_"+PAY_ORDER_TYPE_D, "BETTING_D");
		
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_BETTNG_BACK+"_"+PAY_ORDER_TYPE_R, "BETTING_BACK_R");
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_BETTNG_BACK+"_"+PAY_ORDER_TYPE_D, "BETTING_BACK_D");
		
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_WIN+"_"+PAY_ORDER_TYPE_R, "WIN_R");
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_WIN+"_"+PAY_ORDER_TYPE_D, "WIN_D");
		
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_RECOMMENDER_SHARE+"_"+PAY_ORDER_TYPE_R, "RECOMMENDER_SHARE_R");
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_RECOMMENDER_SHARE+"_"+PAY_ORDER_TYPE_D, "RECOMMENDER_SHARE_D");
		
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_EXPERT_SHARE+"_"+PAY_ORDER_TYPE_R, "EXPERT_SHARE_R");
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_EXPERT_SHARE+"_"+PAY_ORDER_TYPE_D, "EXPERT_SHARE_D");
		
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_RECHARGE+"_"+PAY_ORDER_TYPE_R, "RECHARGE_R");
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_RECHARGE+"_"+PAY_ORDER_TYPE_D, "RECHARGE_D");
		
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_WITHDRAW+"_"+PAY_ORDER_TYPE_R, "WITHDRAW_R");
		userAccountDetailTypes.put(PAY_ORDER_PRODUCT_WITHDRAW+"_"+PAY_ORDER_TYPE_D, "WITHDRAW_D");
		
		tranTypeMap.put("BETTING_R", "投注");
		tranTypeMap.put("BETTING_D", "投注_收入");
		tranTypeMap.put("BETTING_BACK_R", "投注退回_支出");
		tranTypeMap.put("BETTING_BACK_D", "投注退回");
		tranTypeMap.put("WIN_R", "中奖_支出");
		tranTypeMap.put("WIN_D", "中奖");
		tranTypeMap.put("RECOMMENDER_SHARE_R", "推荐人分成_支出");
		tranTypeMap.put("RECOMMENDER_SHARE_D", "推荐人分成");
		tranTypeMap.put("EXPERT_SHARE_R", "专家分成_支出");
		tranTypeMap.put("EXPERT_SHARE_D", "专家分成");
		tranTypeMap.put("RECHARGE_R", "充值_收入");
		tranTypeMap.put("RECHARGE_D", "充值");
		tranTypeMap.put("WITHDRAW_R", "提现");
		tranTypeMap.put("WITHDRAW_D", "提现_支出");
		tranTypeMap.put("WITHDRAW_RETURN_R", "提现退回—支出");
		tranTypeMap.put("WITHDRAW_RETURN_D", "提现退回");
	}
	
	
	public static String getSexVal(Integer sex){
		if(sex == null) return null;
		return sexMap.get(sex);
	}
	
	public static String getUserStateVal(Integer state){
		if(state == null) return null;
		return userStateMap.get(state);
	}
	
	public static String getUserAgreementTypeName(Integer agreementType){
		if(agreementType == null) return null;
		return agreementTypeMap.get(agreementType);
	}
	
	public static String getUserTypeName(Integer userType){
		if(userType == null) return null;
		return userTypeMap.get(userType);
	}
	
	public static String getQueryOrdinaryUserTypes(){
		return queryOrdinaryUserTypes;
	}
	
	public static String getQuerySpecialUserTypes(){
		return querySpecialUserTypes;
	}
	
	public static String getQueryAllUserTypes(){
		return queryAllUserTypes;
	}
	
	public static String getPlanStateVal(Integer state){
		if(state == null) return null;
		return planStateMap.get(state);
	}
	
	public static String getMatchStateVal(Integer state){
		if(state == null) return null;
		return matchStateMap.get(state);
	}
	
	public static String getMatchResultVal(Integer state){
		if(state == null) return null;
		return matchResultMap.get(state);
	}
	
	public static String getMatchDetailResultVal(Integer state){
		if(state == null) return null;
		return matchDetailResultMap.get(state);
	}
	
	public static List<String> getUserTypeRole(Integer userType){
		if(userType == null) return null;
		return userTypeRole.get(userType);
	}
	
	public static String getUserAccountDetailType(String key){
		if(key == null) return null;
		return userAccountDetailTypes.get(key);
	}
	
	public static String getBettingRecordStateVal(Integer state){
		if(state == null) return null;
		return bettingRecordStateMap.get(state);
	}
	
	public static String getLotteryUploadStateMapVal(Integer state){
		if(state == null) return null;
		return lotteryUploadStateMap.get(state);
	}
	
	public static String getRechargeRequestStateVal(Integer state){
		if(state == null) return null;
		return rechargeRequestStateMap.get(state);
	}
	public static String getWithdrawRequestStateVal(Integer state){
		if(state == null) return null;
		return withdrawRequestStateMap.get(state);
	}
	
	public static String getSystemMessageStateVal(Integer state){
		if(state == null) return null;
		return systemMessageStateMap.get(state);
	}
	
	
	public static String getTranTypeName(String tranType){
		if(tranType == null) return null;
		return tranTypeMap.get(tranType);
	}
}
