package com.yu.web.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.base.annotation.SystemLog;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.util.ReturnMapUtil;
import com.yu.web.base.condition.PlanCondition;
import com.yu.web.base.json.PlanJson;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.Plan;
import com.yu.web.base.model.Role;
import com.yu.web.base.service.PlanService;
import com.yu.web.base.util.AmountUtil;
import com.yu.web.base.util.LoginUtil;

@Controller
@RequestMapping(value = "/plan")
public class PlanController {
	
    @Resource
	private PlanService planService;
	
	private Logger log = Logger.getLogger(PlanController.class);
	
	@RequestMapping(value="/planList",method=RequestMethod.GET)
	public String planList(){
		return "plan/planList";
	}
	
	@RequestMapping(value="/planListJson",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> planListJson(@ModelAttribute("pageBean") PageBean pageBean,HttpServletRequest request){
		CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
		List<Role> roles = userInfo.getRoles();
		boolean isAdmin = false;
		for(Role role : roles){
			if("ADMIN".equals(role.getRoleCode())){
				isAdmin = true;
				break;
			}
		}
		if(!isAdmin){
			pageBean.getParams().put("expertId", userInfo.getUser().getId());
		}
		
		Page<Plan> page = planService.select4Page(pageBean);
		List<Plan> plans = page.getResults();
		List<PlanJson> planJsons = new ArrayList<PlanJson>();
		for(Plan plan : plans){
			PlanJson json = new PlanJson(plan);
			planJsons.add(json);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", planJsons);
		log.info("planList:"+map);
		return map;
	}
	
	@RequestMapping(value="/toSaveOrUpdatePlan",method=RequestMethod.GET)
	public String toSaveOrUpdatePlan(Model model,@RequestParam("planId") Long planId){
		if(planId != null){
			Plan user = planService.selectById(planId);
			model.addAttribute("plan",user);
		}
		return "plan/saveOrUpdatePlan";
	}
	
	@SystemLog(description="编辑计划")
	@RequestMapping(value="/saveOrUpdatePlan",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveOrUpdatePlan(@ModelAttribute("plan") Plan plan,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			plan.setFirst(Integer.valueOf(AmountUtil.changeY2F(plan.getFirstStr())));
			plan.setSecond(Integer.valueOf(AmountUtil.changeY2F(plan.getSecondStr())));
			plan.setThird(Integer.valueOf(AmountUtil.changeY2F(plan.getThirdStr())));
			plan.setFourth(Integer.valueOf(AmountUtil.changeY2F(plan.getFourthStr())));
			if(plan.getId() == null){
				CurrentUserInfo userInfo = (CurrentUserInfo) request.getSession().getAttribute(LoginUtil.SESSION_CURRENT_USER);
				plan.setCreator(userInfo.getUser());
				plan = planService.buildPlan(plan);
				planService.insert(plan);
			}else{
				planService.update(plan);
			}
			plan = planService.selectById(plan.getId());
			map.put("result", 0);
			map.put("message", "保存成功");
			map.put("planInfo", new PlanJson(plan));
		} catch (Exception e) {
			map.put("result", 1);
			map.put("message", "保存失败："+e.getMessage());
			map.put("planInfo", new PlanJson(plan));
			log.error(e);
			e.printStackTrace();
		}
		log.info("保存完成，返回信息："+map);
		return map;
	}
	
	@SystemLog(description="修改计划状态")
	@RequestMapping(value="/updatePlanState",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePlanState(@RequestParam("planId") Long planId,@RequestParam("state") Integer state){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(planId == null){
			return ReturnMapUtil.getErrorMap("1", "planId为空.", map);
		}
		if(state == null){
			return ReturnMapUtil.getErrorMap("2", "state为空.", map);
		}
		
		Plan plan = planService.selectById(planId);
		if(plan == null){
			return ReturnMapUtil.getErrorMap("3", "该计划不存在.", map);
		}
		Integer planState = plan.getState();
		
		if(state != 1 && state != 2){
			return ReturnMapUtil.getErrorMap("4", "操作类型错误.", map);
		}
		
		if(state == 1 && planState != 2){
			return ReturnMapUtil.getErrorMap("5", "当前状态不为“生效”状态，不能进行“失效”操作.", map);
		}
		
		if(state == 2 && planState != 1){
			return ReturnMapUtil.getErrorMap("6", "当前状态不为“失效”状态，不能进行“生效”操作.", map);
		}
		
		try {
			plan.setState(state);
			planService.update(plan);
			ReturnMapUtil.getErrorMap("0", "操作成功", map);
		} catch (Exception e) {
			ReturnMapUtil.getErrorMap("7", "操作失败："+e.getMessage(), map);
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}
	
	@SystemLog(description="校验计划名称")
	@RequestMapping(value="/checkPlanName",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkPlanName(@RequestParam(value="planName") String planName){ 
		Map<String,Object> map = new HashMap<String, Object>();
		
		PlanCondition condition = new PlanCondition();
		condition.setPlanNameEquals(planName);
		List<Plan> plans = planService.selectList(condition);
		if(plans == null || plans.size() == 0){
			map.put("result", 0);
		}else{
			map.put("result", 1);
		}
		return map;
	}
}
