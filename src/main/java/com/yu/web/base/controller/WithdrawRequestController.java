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
import com.yu.web.base.json.WithdrawRequestJson;
import com.yu.web.base.model.WithdrawRequest;
import com.yu.web.base.service.WithdrawRequestService;
import com.yu.web.base.util.AmountUtil;

@Controller
@RequestMapping(value = "/withdrawRequest")
public class WithdrawRequestController {
	
	private Logger log = Logger.getLogger(PlanController.class);
	
	@Resource
	private WithdrawRequestService withdrawRequestService;
	
	@RequestMapping(value="/withdrawRequestList")
	public String ordinaryUserList(){
		return "withdraw/withdrawRequestList";
	}
	
	@RequestMapping(value="/withdrawRequestListJson")
	public @ResponseBody Map<String, Object> withdrawRequestListJson(@ModelAttribute("pageBean") PageBean pageBean){
		Page<WithdrawRequest> page = withdrawRequestService.select4Page(pageBean);
		
		List<WithdrawRequest> requests = page.getResults();
		
		List<WithdrawRequestJson> requestJsons = new ArrayList<WithdrawRequestJson>();
		for(WithdrawRequest request : requests){
			WithdrawRequestJson json = new WithdrawRequestJson(request);
			requestJsons.add(json);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", requestJsons);
		
		log.info("planList:"+map);
		return map;
	}
	
	@RequestMapping(value="/toWithdrawRequestDetail")
	public String toWithdrawRequestDetail(Model model,@RequestParam("withdrawRequestId") Long withdrawRequestId,@RequestParam("handleType") String handleType){
		WithdrawRequest withdraw = withdrawRequestService.selectById(withdrawRequestId);
		model.addAttribute("withdraw", withdraw);
		model.addAttribute("handleType", handleType);
		return "withdraw/withdrawRequestDetail";
	}
	
	@SystemLog(description="处理提现结果")
	@RequestMapping(value="/handleWithdrawRequest",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> handleWithdrawRequest(@ModelAttribute("withdrawRequest") WithdrawRequest withdrawRequest){
		Map<String,Object> map = new HashMap<String, Object>();
		WithdrawRequest w = null;
		try {
			String actualAmountStr = withdrawRequest.getActualAmountStr();
			withdrawRequest = withdrawRequestService.selectById(withdrawRequest.getId());
			if(withdrawRequest == null){
				map.put("result", "1");
				map.put("message", "充值记录未找到.");
				return map;
			}
			
			withdrawRequest.setActualAmountStr(actualAmountStr);
			Integer actualAmount = Integer.valueOf(AmountUtil.changeY2F(actualAmountStr));
			withdrawRequest.setActualAmount(actualAmount);
			withdrawRequest.setHandleDate(DateUtils.getNowTime());
			withdrawRequestService.update(withdrawRequest);
			w = withdrawRequest;
			withdrawRequestService.handleWithdrawResult(withdrawRequest.getId());
			
			map.put("result", "0");
			map.put("message", "处理成功.");
		} catch (Exception e) {
			if(w != null){
				w.setRemark(e.getMessage());
				withdrawRequestService.update(w);
			}
			map.put("result", "2");
			map.put("message", "处理失败："+e.getMessage());
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}
}
