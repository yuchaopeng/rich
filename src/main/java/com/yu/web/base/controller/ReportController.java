package com.yu.web.base.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.base.annotation.SystemLog;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.util.ExportExcelUtil;
import com.yu.web.base.json.PlanReportExcelJson;
import com.yu.web.base.json.PlanReportJson;
import com.yu.web.base.json.UserDetailReportExcelJson;
import com.yu.web.base.json.UserDetailReportJson;
import com.yu.web.base.json.UserReportExcelJson;
import com.yu.web.base.json.UserReportJson;
import com.yu.web.base.model.PlanReport;
import com.yu.web.base.model.UserDetailReport;
import com.yu.web.base.model.UserReport;
import com.yu.web.base.service.PlanReportService;
import com.yu.web.base.service.UserDetailReportService;
import com.yu.web.base.service.UserReportService;

@Controller
@RequestMapping(value = "/report")
public class ReportController{
	@Resource
	private PlanReportService planReportService;
	
	@Resource
	private UserReportService userReportService;
	
	@Resource
	private UserDetailReportService userDetailReportService;
	
	@RequestMapping(value="/planReportList")
	public String planReportList(){
		return "report/planReportList";
	}
	
	@RequestMapping(value="/planReportListJson")
	public @ResponseBody Map<String, Object> planReportListJson(@ModelAttribute("pageBean") PageBean pageBean){
		Page<PlanReport> page = planReportService.select4Page(pageBean);
		List<PlanReport> reports = page.getResults();
		
		List<PlanReportJson> reportJsons = new ArrayList<PlanReportJson>();
		for(PlanReport report : reports){
			PlanReportJson json = new PlanReportJson(report);
			reportJsons.add(json);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", reportJsons);
		return map;
	}
	
	@RequestMapping(value="/userReportList")
	public String userReportList(){
		return "report/userReportList";
	}
	
	@RequestMapping(value="/userReportListJson")
	public @ResponseBody Map<String, Object> userReportListJson(@ModelAttribute("pageBean") PageBean pageBean){
		Page<UserReport> page = userReportService.select4Page(pageBean);
		List<UserReport> reports = page.getResults();
		
		List<UserReportJson> reportJsons = new ArrayList<UserReportJson>();
		for(UserReport report : reports){
			UserReportJson json = new UserReportJson(report);
			reportJsons.add(json);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", reportJsons);
		return map;
	}
	
	@RequestMapping(value="/userReportTask")
	public void userReportTask(){
		try {
			userReportService.userReportTask();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/userDetailReportList")
	public String userDetailReportList(){
		return "report/userDetailReportList";
	}
	
	@RequestMapping(value="/userDetailReportListJson")
	public @ResponseBody Map<String, Object> userDetailReportListJson(@ModelAttribute("pageBean") PageBean pageBean){
		Page<UserDetailReport> page = userDetailReportService.select4Page(pageBean);
		List<UserDetailReport> reports = page.getResults();
		
		List<UserDetailReportJson> reportJsons = new ArrayList<UserDetailReportJson>();
		for(UserDetailReport report : reports){
			UserDetailReportJson json = new UserDetailReportJson(report);
			reportJsons.add(json);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRecord());
		map.put("rows", reportJsons);
		return map;
	}
	
	@RequestMapping(value="/userDetailReportTask")
	public void userDetailReportTask(){
		try {
			userDetailReportService.userDetailReportTask();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@SystemLog(description="下载计划报表")
	@RequestMapping(value="/downloadPlanReport")
	public void downloadPlanReport(PageBean pageBean,HttpServletRequest request,HttpServletResponse response){
		
		OutputStream out = null;
		try {
			int pageSize = 10000;
			pageBean.setPage(1);
			pageBean.setRows(pageSize);
			
			Page<PlanReport> page = planReportService.select4Page(pageBean);
			
			/*int totalRecord = page.getTotalRecord();
			int pageTotal = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
			
			if(pageTotal > 1){
				for(int i = 2;i <= pageTotal;i++){
					pageBean = new PageBean();
					pageBean.setPage(pageSize * i + 1);
					pageBean.setRows(pageSize);
				}
			}else{
				
			}*/
			
			List<PlanReport> reports = page.getResults();
			List<PlanReportExcelJson> reportExcelJsons = new ArrayList<PlanReportExcelJson>();
			
			
			String[] headers = {"日期","计划","投注（元）","中奖金额（元）","佣金（元）","推荐人分成（元）","专家分成（元）"};
			
			for(PlanReport report : reports){
				PlanReportJson json = new PlanReportJson(report);
				PlanReportExcelJson excelJson = new PlanReportExcelJson(json);
				reportExcelJsons.add(excelJson);
			}
			
			response.reset();  
			response.setContentType("application/vnd.ms-excel"); 
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("计划报表.xls").getBytes(), "iso-8859-1"));
	        out = response.getOutputStream();
	        
	    	ExportExcelUtil<UserDetailReportExcelJson> util = new ExportExcelUtil<UserDetailReportExcelJson>();
			util.exportExcel("计划报表",headers, reportExcelJsons, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				try {
					out.flush();  
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SystemLog(description="下载用户报表")
	@RequestMapping(value="/downloadUserReport")
	public void downloadUserReport(PageBean pageBean,HttpServletRequest request,HttpServletResponse response){
		
		OutputStream out = null;
		try {
			int pageSize = 10000;
			pageBean.setPage(1);
			pageBean.setRows(pageSize);
			
			Page<UserReport> page = userReportService.select4Page(pageBean);
			
			/*int totalRecord = page.getTotalRecord();
			int pageTotal = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
			
			if(pageTotal > 1){
				for(int i = 2;i <= pageTotal;i++){
					pageBean = new PageBean();
					pageBean.setPage(pageSize * i + 1);
					pageBean.setRows(pageSize);
				}
			}else{
				
			}*/
			
			List<UserReport> reports = page.getResults();
			List<UserReportExcelJson> reportExcelJsons = new ArrayList<UserReportExcelJson>();
			
			
			String[] headers = {"日期","手机号","姓名","充值","提现","中奖","（推荐他人）分成"};
			
			for(UserReport report : reports){
				UserReportJson json = new UserReportJson(report);
				UserReportExcelJson excelJson = new UserReportExcelJson(json);
				reportExcelJsons.add(excelJson);
			}
			
			response.reset();  
			response.setContentType("application/vnd.ms-excel"); 
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("用户报表.xls").getBytes(), "iso-8859-1"));
	        out = response.getOutputStream();
	        
	    	ExportExcelUtil<UserDetailReportExcelJson> util = new ExportExcelUtil<UserDetailReportExcelJson>();
			util.exportExcel("用户报表",headers, reportExcelJsons, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				try {
					out.flush();  
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SystemLog(description="下载用户明细报表")
	@RequestMapping(value="/downloadUserDetailReport")
	public void downloadUserDetailReport(PageBean pageBean,HttpServletRequest request,HttpServletResponse response){
		
		OutputStream out = null;
		try {
			int pageSize = 10000;
			pageBean.setPage(1);
			pageBean.setRows(pageSize);
			
			Page<UserDetailReport> page = userDetailReportService.select4Page(pageBean);
			
			/*int totalRecord = page.getTotalRecord();
			int pageTotal = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
			
			if(pageTotal > 1){
				for(int i = 2;i <= pageTotal;i++){
					pageBean = new PageBean();
					pageBean.setPage(pageSize * i + 1);
					pageBean.setRows(pageSize);
				}
			}else{
				
			}*/
			
			List<UserDetailReport> reports = page.getResults();
			List<UserDetailReportExcelJson> reportExcelJsons = new ArrayList<UserDetailReportExcelJson>();
			
			
			String[] headers = {"日期","手机号","姓名","用户类型","计划","投注","佣金","推荐人","推荐人分成","（推荐他人）分成","（作为专家）分成","中奖金额"};
			
			for(UserDetailReport report : reports){
				UserDetailReportJson json = new UserDetailReportJson(report);
				UserDetailReportExcelJson excelJson = new UserDetailReportExcelJson(json);
				reportExcelJsons.add(excelJson);
			}
			
			response.reset();  
			response.setContentType("application/vnd.ms-excel"); 
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("用户明细报表.xls").getBytes(), "iso-8859-1"));
	        out = response.getOutputStream();
	        
	    	ExportExcelUtil<UserDetailReportExcelJson> util = new ExportExcelUtil<UserDetailReportExcelJson>();
			util.exportExcel("用户明细报表",headers, reportExcelJsons, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				try {
					out.flush();  
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
