package com.yu.web.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.web.base.json.PlanJson;
import com.yu.web.base.model.Plan;
import com.yu.web.base.service.PlanService;

@Controller
@RequestMapping(value = "/interface")
public class WebInterfaceController {
	
	@Resource
	private PlanService planService;
	
	private Logger log = Logger.getLogger(WebInterfaceController.class);
	
	@RequestMapping(value="/planList")
	public void planList(@ModelAttribute("pageBean") PageBean pageBean,@RequestParam("latestId") String latestId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/json");
		if(!StringUtils.isEmpty(latestId)){
			pageBean.getParams().put("latestId", latestId);
		}
		Page<Plan> page = planService.select4Page(pageBean);
		
		List<Plan> plans = page.getResults();
		List<PlanJson> planJsons = new ArrayList<PlanJson>();
		for(Plan plan : plans){
			PlanJson json = new PlanJson(plan);
			json.setWinRate(Double.valueOf(50));
			planJsons.add(json);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", planJsons);
		
		String json = JSON.toJSONString(map);
		
		response.getWriter().write(json);
		response.getWriter().close();
	}
	
	@RequestMapping(value="/matchList")
	public void matchList(@ModelAttribute("pageBean") PageBean pageBean,HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/json");
		
		String callback = request.getParameter("callback");
		
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("id", "1");
		map1.put("name", "name1");
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("id", "2");
		map2.put("name", "name2");
		
		List<Map<String,Object>> list = new ArrayList<>();
		list.add(map1);
		list.add(map2);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", "2");
		map.put("rows", list);
		
		String json = JSON.toJSONString(map);
		
		String jsonp =  callback+"("+json+")";
		response.getWriter().write(jsonp);
		response.getWriter().close();
	}
	
}
