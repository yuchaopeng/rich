package com.yu.web.base.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yu.base.annotation.SystemLog;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.util.DateUtils;
import com.yu.web.base.condition.MatchCondition;
import com.yu.web.base.condition.MetchPlayMethodCondition;
import com.yu.web.base.condition.PlanCondition;
import com.yu.web.base.condition.UserBettingRecordCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.form.MatchForm;
import com.yu.web.base.form.PayOrderCreateForm;
import com.yu.web.base.json.MatchJson;
import com.yu.web.base.json.UserBettingRecordJson;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.ImageUpload;
import com.yu.web.base.model.Match;
import com.yu.web.base.model.MatchDetail;
import com.yu.web.base.model.MatchPlayMethod;
import com.yu.web.base.model.PayOrder;
import com.yu.web.base.model.Plan;
import com.yu.web.base.model.Role;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserBettingRecord;
import com.yu.web.base.model.UserHelpBuyRecord;
import com.yu.web.base.service.ImageUploadService;
import com.yu.web.base.service.MatchDetailService;
import com.yu.web.base.service.MatchPlayMethodService;
import com.yu.web.base.service.MatchService;
import com.yu.web.base.service.PayOrderService;
import com.yu.web.base.service.PlanReportService;
import com.yu.web.base.service.PlanService;
import com.yu.web.base.service.UserAccountService;
import com.yu.web.base.service.UserAgreementService;
import com.yu.web.base.service.UserBettingRecordService;
import com.yu.web.base.service.UserHelpBuyService;
import com.yu.web.base.util.IDBuildUtil;
import com.yu.web.base.util.LoginUtil;

@Controller
@RequestMapping("/match")
public class MatchController {
	
	@Resource
	private MatchService matchService;
	
	@Resource
	private MatchDetailService matchDetailService;
	
	@Resource
	private MatchPlayMethodService matchPlayMethodService;
	
	@Resource
	private PlanService planService ;
	
	@Resource
	private PayOrderService payOrderService;
	
	@Resource
	private UserBettingRecordService userBettingRecordService;
	
	@Resource
	private ImageUploadService imageUploadService;
	
	@Resource
	private UserAgreementService userAgreementService;
	
	@Resource
	private UserAccountService userAccountService;
	
	@Resource
	private UserHelpBuyService userHelpBuyService;
	
	@Resource
	private PlanReportService planReportService;
	
	//倍投，最大次数
	@Value("#{propertiesSetting['betting.multiModel.max.num']}")
	private String mutiModelMaxNum;
	
	private Logger log = Logger.getLogger(MatchController.class);
	
	@RequestMapping(value="/matchList",method=RequestMethod.GET)
	public String matchList(Model model,HttpServletRequest request){
		model.addAttribute("canEdit", true);
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		List<Role> roles = userInfo.getRoles();
		for(Role role : roles){
			if("OPERATE".equals(role.getRoleCode())){//如果是运营，则不让其进行操作
				model.addAttribute("canEdit", false);
				break;
			}
		}
		return "match/matchList";
	}
	
	@RequestMapping(value="/matchListJson",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> matchListJson(Model model,@ModelAttribute("pageBean") PageBean pageBean,HttpServletRequest request){
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		List<Role> roles = userInfo.getRoles();
		boolean isAdmin = false;
		for(Role role : roles){
			if("OPERATE".equals(role.getRoleCode()) || "ADMIN".equals(role.getRoleCode())){
				isAdmin = true;
				break;
			}
		}
		if(!isAdmin){
			pageBean.getParams().put("expertId", userInfo.getUser().getId());
		}
		
		
		Page<Match> page = matchService.select4Page(pageBean);
		List<Match> matchs = page.getResults();
		List<MatchJson> jsons = new ArrayList<MatchJson>();
		
		for(Match match : matchs){
			MatchCondition condition = new MatchCondition();
			condition.setMatchId(match.getId());
			List<MatchDetail> matchDetails = matchDetailService.selectList(condition);
			MatchJson json = new MatchJson(match, matchDetails);
			jsons.add(json);
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("total", page.getTotalRecord());
		map.put("rows", jsons);
		return map;
	}
	
	@RequestMapping(value="/toSaveOrUpdateMatch",method=RequestMethod.GET)
	public String toSaveOrUpdateMatch(Model model,@RequestParam("matchId") Long matchId,HttpServletRequest request) throws Exception{
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		
		Long userId = userInfo.getUser().getId();
		//当前登录人必须为专家
		//专家发布计划时，只能选到自己的计划
		PlanCondition planCondition = new PlanCondition();
		planCondition.setExpertId(userId);
		planCondition.setState(GlobalConstants.PLAN_STATE_INVALID);
		List<Plan> plans = planService.selectList(planCondition);
		model.addAttribute("plans",plans);
		
		//获取玩法：
		MetchPlayMethodCondition playMethodCondition = new MetchPlayMethodCondition();
		List<MatchPlayMethod> playMethods = matchPlayMethodService.selectList(playMethodCondition);
		model.addAttribute("playMethods", playMethods);
		
		String createDate = "";
		if(matchId == null){
			createDate = DateUtils.getSimpleDateString(DateUtils.getNowTime());
		}else{
			Match match = matchService.selectById(matchId);
			createDate = DateUtils.getSimpleDateString(match.getCreateDate());
			MatchCondition matchCondition = new MatchCondition();
			matchCondition.setMatchId(matchId);
			List<MatchDetail> matchDetails = matchDetailService.selectList(matchCondition);
			
			model.addAttribute("match", match);
			model.addAttribute("matchDetails", matchDetails);
		}
		model.addAttribute("createDate", createDate);
		return "match/saveOrUpdateMatch";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/saveOrUpdateMatch")
	@SystemLog(description="编辑赛事")
	public @ResponseBody Map<String, Object> saveOrUpdateMatch(@ModelAttribute("matchForm") MatchForm matchForm,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		List<MatchDetail> matchDetails = new ArrayList<>();
		Match match = null;
		try{
			CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
			Map<String,Object> resultMap = matchService.saveOrUpdateMatch(matchForm,userInfo.getUser());
			match = (Match) resultMap.get("match");
			matchDetails = (List<MatchDetail>) resultMap.get("matchDetails");
			map.put("result", "0");
			map.put("message", "保存成功");
			map.put("match", match);
			map.put("matchDetails", matchDetails);
		} catch (Exception e) {
			map.put("result", "1");
			map.put("message", "保存失败："+e.getMessage());
			map.put("match", match);
			map.put("matchDetails", matchDetails);
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="/publishMatch")
	@SystemLog(description="发布赛事")
	public @ResponseBody Map<String, Object> publishMatch(@RequestParam("matchId") Long matchId){
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			//发布赛事，生成投注记录
			List<UserBettingRecord> bettingRecords = matchService.savePublishMatch(matchId);
			for(UserBettingRecord record : bettingRecords){//创建订单，并进行支付
				/*PayOrderCreateForm form = new PayOrderCreateForm();
				form.setOrderName("投注："+record.getPlan().getPlanName());
				form.setPayerUid(String.valueOf(record.getUser().getId()));
				form.setPayerOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
				form.setPayerOrderItemId(IDBuildUtil.getOrderItemId());
				form.setPayeeUid(GlobalConstants.COMPANY_USER_ID);
				form.setPayeeOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
				form.setPayeeOrderItemId(IDBuildUtil.getOrderItemId());
				form.setProductType(GlobalConstants.PAY_ORDER_PRODUCT_BETTING);
				form.setAmount(record.getBettingAmount());
				form.setChannel(GlobalConstants.CHANNEL_AUTO);
				PayOrder payOrder = payOrderService.createPayOrder(form);
				boolean flag = payOrderService.orderPay(payOrder);
				if(flag){
					record.setState(GlobalConstants.USER_BETTING_STATE_SUCCESS);
				}else{
					record.setState(GlobalConstants.USER_BETTING_STATE_FAILED);
				}
				userBettingRecordService.update(record);*/
				userBettingRecordService.handleBetting(record);
			}
			
			map.put("result", "0");
			map.put("message", "发布成功");
		} catch (Exception e) {
			log.error(e);
			map.put("result", "1");
			map.put("message", "发布失败："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="/toInputMatchResult",method=RequestMethod.GET)
	public String toInputMatchResult(Model model,@RequestParam("matchId") Long matchId,@RequestParam("actionType") Object actionType) throws Exception{
		Match match = matchService.selectById(matchId);
		if(match == null){
			throw new Exception("没找到对应的赛事");
		}
		
		MatchCondition condition = new MatchCondition();
		condition.setMatchId(matchId);
		List<MatchDetail> matchDetails = matchDetailService.selectList(condition);
		
		String createDate = DateUtils.getSimpleDateString(match.getCreateDate());
		
		model.addAttribute("createDate", createDate);
		model.addAttribute("match",match);
		model.addAttribute("matchDetails",matchDetails);
		model.addAttribute("actionType", actionType);
		
		return "match/inputMatchResult";
	}
	
	@SystemLog(description="输入赛事结果")
	@RequestMapping(value="/inputMatchResult")
	public @ResponseBody Map<String, Object> inputMatchResult(@ModelAttribute("matchForm") MatchForm matchForm,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
			matchService.inputMatchResult(matchForm,userInfo.getUser());
			map.put("result", "0");
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("result", "1");
			map.put("message", "保存失败："+e.getMessage());
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="/bettingList",method=RequestMethod.GET)
	public String bettingList(Model model,HttpServletRequest request) throws Exception{
		model.addAttribute("canEdit", true);
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		List<Role> roles = userInfo.getRoles();
		for(Role role : roles){
			if("OPERATE".equals(role.getRoleCode())){//如果是运营，则不让其进行操作
				model.addAttribute("canEdit", false);
				break;
			}
		}
		return "match/bettingList";
	}
	
	@RequestMapping(value="/bettingListJson",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> bettingListJson(@ModelAttribute("pageBean") PageBean pageBean){
		Page<UserBettingRecord> page = userBettingRecordService.select4Page(pageBean);
		List<UserBettingRecord> bettingRecords = page.getResults();
		List<UserBettingRecordJson> jsons = new ArrayList<UserBettingRecordJson>();
		
		for(UserBettingRecord record : bettingRecords){
			UserBettingRecordJson json = new UserBettingRecordJson(record);
			jsons.add(json);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("total", page.getTotalRecord());
		map.put("rows", jsons);
		return map;
	}
	
	@SystemLog(description="未生效投注")
	@RequestMapping(value="/bettingRollback")
	public @ResponseBody Map<String, Object> bettingRollback(@RequestParam("bettingRecordId") Long bettingRecordId){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			UserBettingRecord record = userBettingRecordService.selectById(bettingRecordId);
			
			PayOrderCreateForm form = new PayOrderCreateForm();
			form.setOrderName("无效投注退回："+record.getPlan().getPlanName());
			
			form.setPlanId(record.getPlan() == null ? null : record.getPlan().getId());
			form.setMatchId(record.getPlan() == null ? null : record.getMatch().getId());
			form.setBettingRecordId(record.getId());
			
			form.setPayerUid(GlobalConstants.COMPANY_USER_ID);
			form.setPayerRelationUid(String.valueOf(record.getUser().getId()));
			form.setPayerOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
			form.setPayerOrderItemId(IDBuildUtil.getOrderItemId());
			form.setPayeeUid(String.valueOf(record.getUser().getId()));
			form.setPayeeRelationUid(GlobalConstants.COMPANY_USER_ID);
			form.setPayeeOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
			form.setPayeeOrderItemId(IDBuildUtil.getOrderItemId());
			form.setProductType(GlobalConstants.PAY_ORDER_PRODUCT_BETTNG_BACK);
			form.setAmount(record.getBettingAmount());
			form.setChannel(GlobalConstants.CHANNEL_BACK);
			PayOrder payOrder = payOrderService.createPayOrder(form);
			
			Map<String,Object> resultMap = payOrderService.orderPay(payOrder);
			boolean flag = Boolean.valueOf(resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG) == null ? "false" : resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG).toString());
			
			if(flag){
				//减去投注中的金额
				userAccountService.handleUserBetting(record.getUser().getId(), 1, record.getBettingAmount());
				record.setState(GlobalConstants.USER_BETTING_STATE_ROLLBACK);
				map.put("result", "0");
				map.put("message", "操作成功");
			}else{
				map.put("result", "2");
				map.put("message", "操作失败");
			}
			userBettingRecordService.update(record);
		} catch (Exception e) {
			map.put("result", "1");
			map.put("message", "操作失败："+e.getMessage());
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="/toUploadLottery",method=RequestMethod.GET)
	public String toUploadLottery(Model model,@RequestParam("bettingRecordId") Long bettingRecordId,@RequestParam("type") Object type,HttpServletRequest request) throws Exception{
		
		UserBettingRecord record = userBettingRecordService.selectById(bettingRecordId);
		if(record == null){
			throw new Exception("没找到对应的投注");
		}
		
		Match match = null;
		if(record.getMatch() != null){
			match = matchService.selectById(record.getMatch().getId());
		}
		
		if(match == null){
			throw new Exception("没找到对应的赛事");
		}
		
		MatchCondition condition = new MatchCondition();
		condition.setMatchId(match.getId());
		List<MatchDetail> matchDetails = matchDetailService.selectList(condition);
		
		String createDate = DateUtils.getSimpleDateString(match.getCreateDate());
		
		model.addAttribute("createDate", createDate);
		model.addAttribute("match",match);
		model.addAttribute("matchDetails",matchDetails);
		model.addAttribute("type", type);
		model.addAttribute("bettingRecord", record);
		return "match/uploadLottery";
	}
	
	@SystemLog(description="上传彩票")
	@RequestMapping(value="/uploadLottery",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> uploadLottery(@RequestParam("bettingRecordId") Long bettingRecordId,@RequestParam(value = "lottery") MultipartFile lottery) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ImageUpload imageUpload = imageUploadService.imageUpload(lottery);
			if(imageUpload != null){
				UserBettingRecord record = userBettingRecordService.selectById(bettingRecordId);
				if(record == null){
					map.put("result", "1");
					map.put("message", "上传失败：投注记录不存在.");
				}else{
					record.setLotteryUploadState(GlobalConstants.LOTTERY_PHOTO_UPLOAD_FINISH);
					record.setImageUpload(imageUpload);
					userBettingRecordService.update(record);
					map.put("result", "0");
					map.put("message", "上传成功");
					record = userBettingRecordService.selectById(bettingRecordId);
					map.put("bettingRecord", record);
				}
			}else{
				map.put("result", "2");
				map.put("message", "上传失败");
			}
		}catch(Exception e){
			map.put("result", "3");
			map.put("message", "上传失败："+e.getMessage());
			log.error(e);
			e.printStackTrace();
		}
		log.info("map:"+map);
		return map;
	}
	
	@RequestMapping(value="/toViewLottery",method=RequestMethod.GET)
	public String toViewLottery(Model model,@RequestParam("bettingRecordId") Long bettingRecordId) throws Exception{
		UserBettingRecord record = userBettingRecordService.selectById(bettingRecordId);
		model.addAttribute("bettingRecord", record);
		return "match/viewLottery";
	}
	
	@SystemLog(description="删除彩票图片")
	@RequestMapping(value="/deleteLottery",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteLottery(@RequestParam("bettingRecordId") Long bettingRecordId) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//删除当前记录已经上传的图片
			userBettingRecordService.deleteBettingLottery(bettingRecordId);
			
			UserBettingRecord record = userBettingRecordService.selectById(bettingRecordId);
			//修改状态为未上传
			record.setLotteryUploadState(GlobalConstants.LOTTERY_PHOTO_UPLOAD_NOT);
			userBettingRecordService.update(record);
			map.put("result", "0");
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("result", "1");
			map.put("message", "删除失败："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	@SystemLog(description="计奖")
	@RequestMapping(value="/calPrize",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> calPrize(@RequestParam("matchId") Long matchId) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		Match match = matchService.selectById(matchId);
		
		if(match.getState() != GlobalConstants.MATCH_STATE_HAVE_RESULT){
			map.put("result", "1");
			map.put("message", "赛事不为“有彩果”状态，不能计奖.");
			return map;
		}
		
		//计算此赛事专家的分成
		Plan plan = match.getPlan();
		
		//保存为已计奖
		match.setState(GlobalConstants.MATCH_STATE_HAVE_CAL_PRIZE);
		matchService.update(match);
		
		UserBettingRecordCondition condition = new UserBettingRecordCondition();
		condition.setMatchId(matchId);
		List<UserBettingRecord> bettingRecords = userBettingRecordService.selectList(condition);
		Integer effectBettingAmount = 0;
		for(UserBettingRecord record : bettingRecords){
			
			
			if(record.getState() != GlobalConstants.USER_BETTING_STATE_SUCCESS){//如果不为投注成功，则不计奖
				continue;
			}
			
			//将用户投注中的金额减掉
			userAccountService.handleUserBetting(record.getUser().getId(), 1, record.getBettingAmount());
			
			effectBettingAmount += record.getBettingAmount();
			
			if(match.getMatchResult() == GlobalConstants.MATCH_RESULT_LOSS){//如果未中奖
				record.setState(GlobalConstants.USER_BETTING_STATE_LOSS);
				userBettingRecordService.update(record);
				
				//如果未中奖，将失败次数+1
				UserHelpBuyRecord helpBuyRecord = userHelpBuyService.selectById(record.getUserHelpBuyRecord().getId());
				Integer continueLossNum = helpBuyRecord.getContinueLossNum();
				if(continueLossNum == null){
					continueLossNum = 0;
				}
				
				if(mutiModelMaxNum == null){
					mutiModelMaxNum = "4";//默认值4
				}
			
				int lossNum = helpBuyRecord.getContinueLossNum();
				//如果超过了倍投的次数，则重置为0
				int maxNum = Integer.valueOf(mutiModelMaxNum);
				if(lossNum == maxNum - 1){
					continueLossNum = 0;
				}else{
					continueLossNum++;
				}
				
				helpBuyRecord.setContinueLossNum(continueLossNum);
				userHelpBuyService.update(helpBuyRecord);
			}
			
			User user = record.getUser();
			if(user == null){
				log.info("投注记录中的用户不存在："+record.getId());
				continue;
			}
			
			if(match.getMatchResult() == GlobalConstants.MATCH_RESULT_WIN){//如果中奖
				Integer bettingAmount = record.getBettingAmount();//获取投注金额
				Double odds = match.getOdds();//获取赔率
				
				//计算中奖金额
				Integer winAmount = userAgreementService.calWinAmount(bettingAmount, odds);
				
				//给用户的中奖彩金
				PayOrderCreateForm form = new PayOrderCreateForm();
				
				form.setPlanId(plan.getId());
				form.setMatchId(match.getId());
				form.setBettingRecordId(record.getId());
				
				form.setOrderName("中奖："+record.getPlan().getPlanName());
				form.setPayerUid(GlobalConstants.COMPANY_USER_ID);
				form.setPayerRelationUid(String.valueOf(record.getUser().getId()));
				form.setPayerOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
				form.setPayerOrderItemId(IDBuildUtil.getOrderItemId());
				form.setPayeeUid(String.valueOf(record.getUser().getId()));
				form.setPayeeRelationUid(String.valueOf(GlobalConstants.COMPANY_USER_ID));
				form.setPayeeOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
				form.setPayeeOrderItemId(IDBuildUtil.getOrderItemId());
				form.setProductType(GlobalConstants.PAY_ORDER_PRODUCT_WIN);
				form.setAmount(winAmount);
				form.setChannel(GlobalConstants.CHANNEL_BACK);
				PayOrder payOrder = payOrderService.createPayOrder(form);
				
				Map<String,Object> resultMap = payOrderService.orderPay(payOrder);
				boolean flag = Boolean.valueOf(resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG) == null ? "false" : resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG).toString());
				if(flag){
					userAccountService.handleUserTotalWin(record.getUser().getId(), winAmount);
					record.setState(GlobalConstants.USER_BETTING_STATE_WIN);
					record.setWinAmount(winAmount);
					userBettingRecordService.update(record);
				}else{
					record.setState(GlobalConstants.USER_BETTING_STATE_WIN_ERROR);
					String resultMessage = (String) resultMap.get(GlobalConstants.PAY_ORDER_RESULT_MESSAGE);
					record.setRemark(resultMessage);
					record.setWinAmount(winAmount);
					userBettingRecordService.update(record);
				}
				
				//如果中奖，将失败次数归0
				UserHelpBuyRecord helpBuyRecord = userHelpBuyService.selectById(record.getUserHelpBuyRecord().getId());
				helpBuyRecord.setContinueLossNum(0);
				userHelpBuyService.update(helpBuyRecord);
			}
			
			//给推荐人的分成
			//获取用户推荐人
			User recommender = user.getRecommender();
			if(recommender != null){//如果存在推荐人
				//获取用户的协议
				Integer bettingAmount = record.getBettingAmount();
				Integer userAmount = userAgreementService.calUserAmount(recommender, bettingAmount);
				PayOrderCreateForm recommenderForm = new PayOrderCreateForm();
				
				recommenderForm.setPlanId(plan.getId());
				recommenderForm.setMatchId(match.getId());
				recommenderForm.setBettingRecordId(record.getId());
				
				recommenderForm.setOrderName("推荐人分成："+record.getPlan().getPlanName());
				recommenderForm.setPayerUid(GlobalConstants.COMPANY_USER_ID);
				recommenderForm.setPayerRelationUid(String.valueOf(recommender.getId()));
				recommenderForm.setPayerOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
				recommenderForm.setPayerOrderItemId(IDBuildUtil.getOrderItemId());
				recommenderForm.setPayeeUid(String.valueOf(recommender.getId()));
				recommenderForm.setPayeeRelationUid(String.valueOf(user.getId()));
				recommenderForm.setPayeeOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
				recommenderForm.setPayeeOrderItemId(IDBuildUtil.getOrderItemId());
				recommenderForm.setProductType(GlobalConstants.PAY_ORDER_PRODUCT_RECOMMENDER_SHARE);
				recommenderForm.setAmount(userAmount);
				recommenderForm.setChannel(GlobalConstants.CHANNEL_BACK);
				
				PayOrder payOrderRecommender = payOrderService.createPayOrder(recommenderForm);
				Map<String,Object> resultMap = payOrderService.orderPay(payOrderRecommender);
				boolean flag = Boolean.valueOf(resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG) == null ? "false" : resultMap.get(GlobalConstants.PAY_ORDER_RESULT_FLAG).toString());
				if(flag){
					userAccountService.handleUserTotalDivided(recommender.getId(), userAmount);
					//设置该投注的推荐人分成金额
					record.setRecommenderUserId(recommender.getId());
					record.setRecommenderAmount(userAmount);
					userBettingRecordService.update(record);
					log.info("为用户【"+user.getId()+"】的推荐人【"+recommender.getId()+"】分成成功");
				}else{
					log.info("为用户【"+user.getId()+"】的推荐人【"+recommender.getId()+"】分成失败");
				}
			}
		}
		
		if(plan == null){
			log.info("赛事【"+match.getId()+"】的投注计划为空");
		}else{
			User expert = plan.getExpert();
			if(expert == null){
				
			}else{
				Integer userAmount = userAgreementService.calUserAmount(expert, effectBettingAmount);
				PayOrderCreateForm recommenderForm = new PayOrderCreateForm();
				
				recommenderForm.setPlanId(plan.getId());
				recommenderForm.setMatchId(match.getId());
				
				recommenderForm.setOrderName("专家分成："+plan.getPlanName());
				recommenderForm.setPayerUid(GlobalConstants.COMPANY_USER_ID);
				recommenderForm.setPayerRelationUid(String.valueOf(expert.getId()));
				recommenderForm.setPayerOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
				recommenderForm.setPayerOrderItemId(IDBuildUtil.getOrderItemId());
				recommenderForm.setPayeeUid(String.valueOf(expert.getId()));
				recommenderForm.setPayeeRelationUid(GlobalConstants.COMPANY_USER_ID);
				recommenderForm.setPayeeOrderMethodType(GlobalConstants.PAY_ORDER_METHOD_TYPE_ACCOUNT);
				recommenderForm.setPayeeOrderItemId(IDBuildUtil.getOrderItemId());
				recommenderForm.setProductType(GlobalConstants.PAY_ORDER_PRODUCT_EXPERT_SHARE);
				recommenderForm.setAmount(userAmount);
				recommenderForm.setChannel(GlobalConstants.CHANNEL_BACK);
				PayOrder payOrderRecommender = payOrderService.createPayOrder(recommenderForm);
				
				Map<String,Object> resultMap = payOrderService.orderPay(payOrderRecommender);
				boolean flag = Boolean.valueOf(resultMap.get("resultFlag") == null ? "false" : resultMap.get("resultFlag").toString());
				
				if(flag){
					userAccountService.handleUserTotalDivided(expert.getId(), userAmount);
					match.setExpertAmount(userAmount);
					matchService.update(match);
					log.info("计划【"+plan.getPlanName()+"】的专家【"+expert.getId()+"】分成成功");
				}else{
					log.info("计划【"+plan.getPlanName()+"】的专家【"+expert.getId()+"】分成失败");
				}
			}
		}
		
		final Long planId = match.getPlan() == null ? null : match.getPlan().getId();
		final String matchDate = match.getMatchDate();
		planReportService.handlePlanReport(planId, matchDate);
//		if(planId != null){//生成计划报表
//			ExecutorService pool = Executors.newSingleThreadExecutor();
//				pool.submit(new Runnable() {
//					@Override
//					public void run() {
//						planReportService.handlePlanReport(planId, matchDate);
//					}
//			});
//		}
		map.put("result", "0");
		map.put("message", "计奖成功");
		return map;
	}
}
