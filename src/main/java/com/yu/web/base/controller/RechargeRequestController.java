package com.yu.web.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.json.RechargeRequestJson;
import com.yu.web.base.model.RechargeRequest;
import com.yu.web.base.service.PayOrderService;
import com.yu.web.base.service.RechargeRequestService;
import com.yu.web.base.util.AmountUtil;

@Controller
@RequestMapping(value = "/rechargeRequest")
public class RechargeRequestController {
	
	private Logger log = Logger.getLogger(PlanController.class);
	
	@Resource
	private RechargeRequestService rechargeRequestService;
	
	@Resource
	private PayOrderService payOrderService;

	@RequestMapping(value="/rechargeRequestList")
	public String ordinaryUserList(){
		return "recharge/rechargeRequestList";
	}
	
	@RequestMapping(value="/rechargeRequestListJson")
	public @ResponseBody Map<String, Object> rechargeRequestListJson(@ModelAttribute("pageBean") PageBean pageBean){
		Page<RechargeRequest> page = rechargeRequestService.select4Page(pageBean);
		
		List<RechargeRequest> requests = page.getResults();
		
		List<RechargeRequestJson> requestJsons = new ArrayList<RechargeRequestJson>();
		for(RechargeRequest request : requests){
			RechargeRequestJson json = new RechargeRequestJson(request);
			requestJsons.add(json);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", requestJsons);
		log.info("planList:"+map);
		return map;
	}
	
	@RequestMapping(value="/toRechargeRequestDetail")
	public String toRechargeRequestDetail(Model model,@RequestParam("rechargeRequestId") Long rechargeRequestId,@RequestParam("handleType") String handleType){
		RechargeRequest recharge = rechargeRequestService.selectById(rechargeRequestId);
		model.addAttribute("recharge", recharge);
		model.addAttribute("handleType", handleType);
		return "recharge/rechargeRequestDetail";
	}
	
	@SystemLog(description="处理充值结果")
	@RequestMapping(value="/handleRechargeRequest",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> handleRechargeRequest(@ModelAttribute("rechargeRequest") RechargeRequest rechargeRequest){
		Map<String,Object> map = new HashMap<String, Object>();
		RechargeRequest r = null;
		try {
			String actualAmountStr = rechargeRequest.getActualAmountStr();
			rechargeRequest = rechargeRequestService.selectById(rechargeRequest.getId());
			if(rechargeRequest == null){
				map.put("result", "1");
				map.put("message", "充值记录未找到.");
				return map;
			}
			
			rechargeRequest.setActualAmountStr(actualAmountStr);
			Integer actualAmount = Integer.valueOf(AmountUtil.changeY2F(actualAmountStr));
			rechargeRequest.setActualAmount(actualAmount);
			rechargeRequest.setHandleDate(DateUtils.getNowTime());
			rechargeRequestService.update(rechargeRequest);
			
			r = rechargeRequest;
			
			if(rechargeRequest.getUser() != null){
				rechargeRequestService.handleUserRecharge(rechargeRequest);
				map.put("result", "0");
				map.put("message", "处理成功.");
			}else{
				map.put("result", "3");
				map.put("message", "该充值记录无用户信息.");
			}
			
		} catch (Exception e) {
			if(r != null){
				r.setRemark(e.getMessage());
				rechargeRequestService.update(r);
			}
			map.put("result", "2");
			map.put("message", "处理失败："+e.getMessage());
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}
	
}
