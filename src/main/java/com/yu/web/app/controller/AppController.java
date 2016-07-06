package com.yu.web.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.base.annotation.SystemLog;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.util.MD5;
import com.yu.web.base.condition.AdminAccountInfoCondition;
import com.yu.web.base.condition.MatchCondition;
import com.yu.web.base.condition.UserHelpBuyCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.form.AppPlanForm;
import com.yu.web.base.form.MatchForm;
import com.yu.web.base.form.UpdatePasswordForm;
import com.yu.web.base.form.UserAccountDetailForm;
import com.yu.web.base.form.UserAccountForm;
import com.yu.web.base.form.UserLoginForm;
import com.yu.web.base.model.AdminAccountInfo;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.Match;
import com.yu.web.base.model.MatchDetail;
import com.yu.web.base.model.Plan;
import com.yu.web.base.model.RechargeRequest;
import com.yu.web.base.model.SystemMessage;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAccount;
import com.yu.web.base.model.UserAccountDetail;
import com.yu.web.base.model.UserBettingRecord;
import com.yu.web.base.model.UserHelpBuyRecord;
import com.yu.web.base.model.WithdrawRequest;
import com.yu.web.base.service.AdminAccountInfoService;
import com.yu.web.base.service.MatchDetailService;
import com.yu.web.base.service.MatchService;
import com.yu.web.base.service.PlanService;
import com.yu.web.base.service.RechargeRequestService;
import com.yu.web.base.service.SystemMessageService;
import com.yu.web.base.service.UserAccountService;
import com.yu.web.base.service.UserBettingRecordService;
import com.yu.web.base.service.UserHelpBuyService;
import com.yu.web.base.service.UserService;
import com.yu.web.base.service.WithdrawRequestService;
import com.yu.web.base.util.AmountUtil;
import com.yu.web.base.util.LoginUtil;

@Controller
@RequestMapping(value = "/app")
public class AppController {
	
	private Logger log = Logger.getLogger(AppController.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private PlanService planService;
	
	@Resource
	private MatchService matchService;
	
	@Resource
	private MatchDetailService matchDetailService;
	
	@Resource
	private UserHelpBuyService userHelpBuyService;
	
	@Resource
	private UserBettingRecordService userBettingRecordService;
	
	@Resource
	private UserAccountService userAccountService;
	
	@Resource
	private AdminAccountInfoService adminAccountInfoService;
	
	@Resource
	private RechargeRequestService rechargeRequestService;
	
	@Resource
	private WithdrawRequestService withdrawRequestService;
	
	@Resource
	private SystemMessageService systemMessageService;
	
	@RequestMapping("")
    public String index(Model model,HttpServletRequest request) throws Exception {
		return planList(new PageBean(), model);
    }
	
	@RequestMapping("/toLogin")
    public String toLogin(Model model,HttpServletRequest request) throws Exception {
		return "app/login";
    }
	
	@SystemLog(description="APP用户登录")
	@RequestMapping("/login")
    public @ResponseBody Map<String, Object> login(@ModelAttribute("loginForm") UserLoginForm loginForm,HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if(loginForm == null){
			map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_NOT_INPUT);
			return map;
		}
		log.info("后台用户登录:"+loginForm.getMobile());
		
		boolean loginFlag = true;
		try {
			if(loginFlag && StringUtils.isEmpty(loginForm.getMobile())){
				map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_MOBILE_NULL);
				loginFlag = false;
			}
			if(loginFlag && StringUtils.isEmpty(loginForm.getPassword())){
				map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_PASSWORD_NULL);
				loginFlag = false;
			}
			
			if(loginFlag){
				CurrentUserInfo currentUserInfo = userService.login(loginForm.getMobile(), MD5.md5(loginForm.getPassword()));
				if(currentUserInfo == null || currentUserInfo.getUser() == null){
					map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_LOGIN_NOT_EXIST);
					loginFlag = false;
				}else{
					if(currentUserInfo.getUser().getState() == null){
						map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_ERROR);
						return map;
					}
					
					if(currentUserInfo.getUser().getUserType() != 0){
						map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_NOT_APP_USER);
						return map;
					}
					
					switch(currentUserInfo.getUser().getState()){
						case 1 : 
							map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_OK);
							request.getSession().setAttribute(LoginUtil.SESSION_APP_CURRENT_USER, currentUserInfo);
							log.info("登录成功》id："+currentUserInfo.getUser().getId()+"，mobile："+currentUserInfo.getUser().getMobile()+"，name："+currentUserInfo.getUser().getUsername());
							break;
						case 2 : 
							map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_INVALID);
							break;
						case 3 : 
							map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_DELETE);
							break;
						default :
							map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_ERROR);
							break;
					}
				}
			}
		} catch (Exception e) {
			map = LoginUtil.buildLoginResult(LoginUtil.LOGIN_STATE.STATE_LOGIN_ERROR);
			log.error("登录失败:", e);
			e.printStackTrace();
		}
		log.info("登录结束: "+map.get("result")+" : "+map.get("message"));
		return map;
	}
	
	@RequestMapping("/planList")
    public String planList(PageBean pageBean,Model model) throws Exception {
		if(pageBean.getRows() == 0){
			pageBean.setRows(4);
		}
		pageBean.getParams().put("state", GlobalConstants.PLAN_STATE_INVALID);
		Page<Plan> page = planService.select4Page(pageBean);
		List<Plan> plans = page.getResults();
		List<AppPlanForm> planForms = new ArrayList<AppPlanForm>();
		for(Plan plan : plans){
			PageBean pb = new PageBean();
			pb.setRows(10);
			pb.getParams().put("planId", plan.getId());
			Page<Match> pageMatch = matchService.select4Page(pb);
			List<Match> matchs = pageMatch.getResults();
			String winRate = "0";
			
			int win = 0;
			int all = 0;
			for(Match match : matchs){
				all++;
				if(match.getMatchResult() != null && match.getMatchResult() == GlobalConstants.MATCH_RESULT_WIN){
					win++;
				}
			}
			
			if(all != 0){
				BigDecimal winb = new BigDecimal(win);
		        BigDecimal allb = new BigDecimal(all);
		        
		        BigDecimal winRateb = winb.divide(allb,2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP) ;
		        winRate = winRateb.toString();
			}
			AppPlanForm form = new AppPlanForm(plan, winRate);
			planForms.add(form);
		}
		model.addAttribute("planForms", planForms);
		model.addAttribute("rows", page.getPageSize());
		model.addAttribute("hasMore", page.getHasMore());
		return "app/planList";
    }
	
	@RequestMapping("/matchList")
	public String matchList(PageBean pageBean,Model model,HttpServletRequest request,@RequestParam("planId") Long planId) throws Exception {
		if(pageBean.getRows() == 0){
			pageBean.setRows(3);
		}
		pageBean.getParams().put("planId", planId);
		pageBean.getParams().put("stateList", GlobalConstants.MATCH_STATE_PUBLISH+","+GlobalConstants.MATCH_STATE_HAVE_RESULT+","+GlobalConstants.MATCH_STATE_HAVE_CAL_PRIZE);
		Page<Match> page = matchService.select4Page(pageBean);
		List<Match> matchs = page.getResults();
		List<MatchForm> matchForms = new ArrayList<>();
		for(Match match : matchs){
			MatchForm form = new MatchForm();
			MatchCondition condition = new MatchCondition();
			condition.setMatchId(match.getId());
			List<MatchDetail> details = matchDetailService.selectList(condition);
			
			form.setMatch(match);
			form.setMatchDetails(details);
			matchForms.add(form);
		}
		Plan plan = planService.selectById(planId);
		model.addAttribute("plan", plan);
		model.addAttribute("matchForms", matchForms);
		
		model.addAttribute("rows", page.getPageSize());
		model.addAttribute("hasMore", page.getHasMore());
		
		UserHelpBuyRecord record = getUserHelpBuyByUserIdAndPlanId(request,planId);
		model.addAttribute("helpBuyRecord", record);
		
		return "app/matchList";
	}
	
	@RequestMapping("/helpBuy")
	public String helpBuy(Model model,HttpServletRequest request,@RequestParam("planId") Long planId) throws Exception {
		Plan plan = planService.selectById(planId);
		model.addAttribute("plan", plan);
		
		UserHelpBuyRecord record = getUserHelpBuyByUserIdAndPlanId(request, planId);
		request.setAttribute("record", record);
		
		return "app/helpBuy";
	}
	
	@SystemLog(description="APP取消帮买")
	@RequestMapping("/cancelHelpBuy")
	public String cancelHelpBuy(Model model,HttpServletRequest request,@RequestParam("planId") Long planId) throws Exception {
		UserHelpBuyRecord record = getUserHelpBuyByUserIdAndPlanId(request, planId);
		if(record == null){
			throw new Exception("未帮买.");
		}
		record.setState(GlobalConstants.USER_HELP_BUY_CANCEL);
		userHelpBuyService.update(record);
		PageBean pageBean = new PageBean();
		return planList(pageBean, model);
	}
	
	@SystemLog(description="APP帮买")
	@RequestMapping("/saveOrUpdateHelpBuy")
	public String saveOrUpdateHelpBuy(Model model,HttpServletRequest request,UserHelpBuyRecord record) throws Exception {
		UserHelpBuyRecord r = getUserHelpBuyByUserIdAndPlanId(request, record.getPlan().getId());
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		if(r == null){
			log.info("用户["+userInfo.getUser().getId()+":"+userInfo.getUser().getUsername()+"]第一次帮买");
			record = userHelpBuyService.buildUserHelpBuy(record);
			record.setStartAmount(Integer.valueOf(AmountUtil.changeY2F(record.getStartAmountStr())));
			record.setUser(userInfo.getUser());
			userHelpBuyService.insert(record);
		}else{
			log.info("用户["+userInfo.getUser().getId()+":"+userInfo.getUser().getUsername()+"]已帮买过，修改");
			BeanUtils.copyProperties(record, r);
			r.setStartAmount(Integer.valueOf(AmountUtil.changeY2F(r.getStartAmountStr())));
			userHelpBuyService.update(r);
		}
		
		PageBean pageBean = new PageBean();
		return matchList(pageBean,model,request, record.getPlan().getId());
	}
	
	@RequestMapping("/bettingList")
	public String bettingList(PageBean pageBean,Model model,HttpServletRequest request,@RequestParam(value="planId",required=false) Long planId) throws Exception {
		if(pageBean.getRows() == 0){
			pageBean.setRows(3);
		}
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		UserHelpBuyCondition helpBuyCondition = new UserHelpBuyCondition();
		helpBuyCondition.setUserId(userInfo.getUser().getId());
		helpBuyCondition.setState(GlobalConstants.USER_HELP_BUY_OK);
		//查看当前人所有帮买的记录
		List<UserHelpBuyRecord> helpBuyRecords = userHelpBuyService.selectList(helpBuyCondition);
		model.addAttribute("helpBuyRecords", helpBuyRecords);
		
		if(planId == null){
			if(helpBuyRecords != null && helpBuyRecords.size() > 0){
				if(helpBuyRecords.get(0) != null && helpBuyRecords.get(0).getPlan() != null){
					planId = helpBuyRecords.get(0).getPlan().getId();
				}
			}
		}
		
		Page<UserBettingRecord> page = null;
		if(planId != null){
			pageBean.getParams().put("userId", userInfo.getUser().getId());
			pageBean.getParams().put("planId", planId);
			pageBean.getParams().put("bettingStateIn", 
					GlobalConstants.USER_BETTING_STATE_LOSS+","
							+GlobalConstants.USER_BETTING_STATE_ROLLBACK+","
							+GlobalConstants.USER_BETTING_STATE_SUCCESS+","
							+GlobalConstants.USER_BETTING_STATE_WIN+","
							+GlobalConstants.USER_BETTING_STATE_WIN_ERROR);
			page = userBettingRecordService.select4Page(pageBean);
			
			List<UserBettingRecord> bettingRecords = page.getResults();
			
			List<MatchForm> matchForms = new ArrayList<MatchForm>();
			for(UserBettingRecord record : bettingRecords){
				if(record.getMatch() == null){
					continue;
				}
				MatchForm form = new MatchForm();
				Match match = matchService.selectById(record.getMatch().getId());
				MatchCondition matchCondition = new MatchCondition();
				matchCondition.setMatchId(match.getId());
				List<MatchDetail> details = matchDetailService.selectList(matchCondition);
				form.setUserBettingRecord(record);
				form.setMatch(match);
				form.setMatchDetails(details);
				matchForms.add(form);
			}
			model.addAttribute("matchForms", matchForms);
		}
		
		model.addAttribute("rows", page == null ? 0 : page.getPageSize());
		model.addAttribute("hasMore",  page == null ? false : page.getHasMore());
		return "app/bettingList";
	}
	
	@RequestMapping("/userInfo")
	public String userInfo(Model model,HttpServletRequest request) throws Exception {
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		UserAccount userAccount = userAccountService.getUserAccount(userInfo.getUser().getId());
		UserAccountForm userAccountForm = new UserAccountForm(userAccount);
		model.addAttribute("userAccountForm", userAccountForm);
		return "/app/userInfo";
	}
	
	@RequestMapping("/userDetailInfo")
	public String userAccountDetail(Model model,HttpServletRequest request) throws Exception {
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		model.addAttribute("user", userInfo.getUser());
		return "/app/userDetailInfo";
	}
	
	@RequestMapping("/userAccountDetail")
	public String userDetailInfo(PageBean pageBean,Model model,HttpServletRequest request) throws Exception {
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		if(pageBean.getRows() == 0){
			pageBean.setRows(6);
		}
		pageBean.getParams().put("userId", userInfo.getUser().getId());
		Page<UserAccountDetail> page = userAccountService.selectUserAccountDetail4Page(pageBean);
		
		List<UserAccountDetail> details = page.getResults();
		List<UserAccountDetailForm> detailForms = new ArrayList<UserAccountDetailForm>();
		for(UserAccountDetail detail : details){
			UserAccountDetailForm from = new UserAccountDetailForm(detail);
			detailForms.add(from);
		}
		
		model.addAttribute("detailForms", detailForms);
		model.addAttribute("rows", page.getPageSize());
		model.addAttribute("hasMore", page.getHasMore());
		return "/app/userAccountDetail";
	}
	
	@RequestMapping("/toRechargeSelect")
	public String toRechargeSelect(Model model) throws Exception {
		return "/app/rechargeSelect";
	}
	
	@RequestMapping("/toRecharge")
	public String toRecharge(Model model,@RequestParam("paymentMethodTypeId") Long paymentMethodTypeId,@RequestParam(value="rechargeRequestId",required=false) Long rechargeRequestId) throws Exception {
		RechargeRequest rechargeRequest = null;
		if(rechargeRequestId != null){
			rechargeRequest = rechargeRequestService.selectById(rechargeRequestId);
		}else{
			AdminAccountInfoCondition condition = new AdminAccountInfoCondition();
			condition.setPaymentMethodType(paymentMethodTypeId);
			List<AdminAccountInfo> accountInfos = adminAccountInfoService.selectList(condition);
			AdminAccountInfo accountInfo = null;
			if(accountInfos != null && accountInfos.size() > 0){
				accountInfo = accountInfos.get(0);
			}
			
			rechargeRequest = rechargeRequestService.buildRechargeRequest(paymentMethodTypeId, accountInfo);
		}
		model.addAttribute("rechargeRequest", rechargeRequest);
		return "/app/recharge";
	}
	
	@SystemLog(description="APP充值")
	@RequestMapping("/recharge")
	public String recharge(Model model,HttpServletRequest request,@ModelAttribute("rechargeRequest") RechargeRequest rechargeRequest) throws Exception {
		if(rechargeRequest.getId() == null){
			rechargeRequest = rechargeRequestService.buildRechargeRequest(rechargeRequest);
			CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
			rechargeRequest.setUser(userInfo.getUser());
			rechargeRequestService.insert(rechargeRequest);
		}
		model.addAttribute("rechargeResult", true);
		return toRecharge(model, rechargeRequest.getRequestType() == null ? null : rechargeRequest.getRequestType().getId(),rechargeRequest.getId());
	}
	
	
	@RequestMapping("/toWithdrawSelect")
	public String toWithdrawSelect(Model model) throws Exception {
		return "/app/withdrawSelect";
	}
	
	@RequestMapping("/toWithdraw")
	public String toWithdraw(Model model,@RequestParam("paymentMethodTypeId") Long paymentMethodTypeId,@RequestParam(value="withdrawRequestId",required=false) Long withdrawRequestId,WithdrawRequest withdrawRequestInForm) throws Exception {
		WithdrawRequest withdrawRequest = null;
		if(withdrawRequestId != null){
			withdrawRequest = withdrawRequestService.selectById(withdrawRequestId);
		}else{
			AdminAccountInfoCondition condition = new AdminAccountInfoCondition();
			condition.setPaymentMethodType(paymentMethodTypeId);
			List<AdminAccountInfo> accountInfos = adminAccountInfoService.selectList(condition);
			AdminAccountInfo accountInfo = null;
			if(accountInfos != null && accountInfos.size() > 0){
				accountInfo = accountInfos.get(0);
			}
			
			withdrawRequest = withdrawRequestService.buildWithdrawRequest(paymentMethodTypeId, accountInfo);
			
			if(withdrawRequestInForm != null){
				withdrawRequest.setReciverAccount(withdrawRequestInForm.getReciverAccount());
				withdrawRequest.setReciverBank(withdrawRequestInForm.getReciverBank());
				withdrawRequest.setReciverMobile(withdrawRequestInForm.getReciverMobile());
				withdrawRequest.setReciverUserName(withdrawRequestInForm.getReciverUserName());
				withdrawRequest.setAmount(withdrawRequestInForm.getAmount());
				withdrawRequest.setAmountStr(withdrawRequestInForm.getAmountStr());
			}
		}
		model.addAttribute("withdrawRequest", withdrawRequest);
		return "/app/withdraw";
	}
	
	@SystemLog(description="APP提现")
	@RequestMapping("/withdraw")
	public String withdraw(Model model,HttpServletRequest request,@ModelAttribute("withdrawRequest") WithdrawRequest withdrawRequest,@RequestParam("loginPassword") String loginPassword) throws Exception {
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		User user = userService.selectById(userInfo.getUser().getId());
		if(user.getPassword() != null && !user.getPassword().equals(MD5.md5(loginPassword))){
			model.addAttribute("message", "登录密码输入错误！");
			model.addAttribute("withdrawResult", false);
		}else{
			try {
				if(withdrawRequest.getId() == null){
					withdrawRequest = withdrawRequestService.buildWithdrawRequest(withdrawRequest);
					
					withdrawRequest.setUser(userInfo.getUser());
					withdrawRequestService.handleWithdrawRequest(withdrawRequest);
					model.addAttribute("withdrawResult", true);
				}else{
					model.addAttribute("message", "提现出错：重复提交！");
					model.addAttribute("withdrawResult", false);
				}
			} catch (Exception e) {
				model.addAttribute("message", "提现出错："+e.getMessage());
				model.addAttribute("withdrawResult", false);
				e.printStackTrace();
			}
		}
		return toWithdraw(model, withdrawRequest.getRequestType() == null ? null : withdrawRequest.getRequestType().getId(),withdrawRequest.getId(),withdrawRequest);
	}
	
	@RequestMapping("/systemMessageList")
	public String systemMessageList(PageBean pageBean,Model model) throws Exception {
		if(pageBean.getRows() == 0){
			pageBean.setRows(6);
		}
		pageBean.getParams().put("state", GlobalConstants.SYSTEM_MESSAGE_PUBLISHED);
		Page<SystemMessage> page = systemMessageService.select4Page(pageBean);
		List<SystemMessage> systemMessages = page.getResults();
		
		model.addAttribute("systemMessages", systemMessages);
		model.addAttribute("rows", page.getPageSize());
		model.addAttribute("hasMore", page.getHasMore());
		return "/app/systemMessageList";
	}
	
	@RequestMapping("/viewSystemMessage")
	public String viewSystemMessage(Model model,@RequestParam("systemMessageId") Long systemMessageId,@RequestParam(value = "returnUrl",required = false) String returnUrl) throws Exception {
		SystemMessage systemMessage = systemMessageService.selectById(systemMessageId);
		model.addAttribute("systemMessage", systemMessage);
		model.addAttribute("returnUrl", returnUrl);
		return "/app/viewSystemMessage";
	}
	
	@RequestMapping("/toUpdatePassword")
	public String toUpdatePassword(Model model,HttpServletRequest request) throws Exception {
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		User user = userService.selectById(userInfo.getUser().getId());
		model.addAttribute("user", user);
		return "/app/updatePassword";
	}
	
	@SystemLog(description="APP修改密码")
	@RequestMapping("/updatePassword")
	public String updatePassword(Model model,HttpServletRequest request,@ModelAttribute("updatePasswordForm") UpdatePasswordForm updatePasswordForm) throws Exception {
		String sourcePassword = updatePasswordForm.getSourcePassword();
		String newPassword = updatePasswordForm.getNewPassword();
		String confirmPassword = updatePasswordForm.getConfirmPassword();
		
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		User user = userService.selectById(userInfo.getUser().getId());
		if(!MD5.md5(sourcePassword).equals(user.getPassword())){
			model.addAttribute("result", "1");
			model.addAttribute("message", "您输入的原密码不正确，请重新输入.");
		}else if(!newPassword.equals(confirmPassword)){
			model.addAttribute("result", "2");
			model.addAttribute("message", "您输入的新密码和确认密码不一致，请重新输入.");
		}else{
			user.setPassword(MD5.md5(newPassword));
			userService.update(user);
			model.addAttribute("result", "0");
			model.addAttribute("message", "修改成功.");
		}
		return toUpdatePassword(model, request);
	}
	
	@SystemLog(description="APP用户登出")
	@RequestMapping("/logout")
	public String logout(Model model,HttpServletRequest request) throws Exception {
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		if(userInfo != null && userInfo.getUser() != null){
			log.info("彩民用户退出登录》id："+userInfo.getUser().getId()+"，mobile："+userInfo.getUser().getMobile()+"，username："+userInfo.getUser().getUsername());
		}
		request.getSession().removeAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		return toLogin(model, request);
	}
	
	private UserHelpBuyRecord getUserHelpBuyByUserIdAndPlanId(HttpServletRequest request,Long planId) throws Exception {
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_APP_CURRENT_USER);
		UserHelpBuyCondition condition = new UserHelpBuyCondition();
		condition.setUserId(userInfo.getUser().getId());
		condition.setPlanId(planId);
		condition.setState(GlobalConstants.USER_HELP_BUY_OK);
		List<UserHelpBuyRecord> helpBuyList = userHelpBuyService.selectList(condition);
		UserHelpBuyRecord record = null;
		if(helpBuyList != null && helpBuyList.size() > 0){
			record = helpBuyList.get(0);
		}
		return record;
	}
}
