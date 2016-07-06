package com.yu.web.base.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.base.util.DateUtils;
import com.yu.web.base.condition.ReportTaskCondition;
import com.yu.web.base.condition.UserAccountCondition;
import com.yu.web.base.condition.UserCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.mapper.UserAccountMapper;
import com.yu.web.base.mapper.UserMapper;
import com.yu.web.base.mapper.UserReportMapper;
import com.yu.web.base.model.ReportTask;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAccountDetail;
import com.yu.web.base.model.UserReport;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.UserReportService;
@Service
public class UserReportServiceImpl extends GenericServiceImpl<UserReport, Long> implements UserReportService {
	
	private Logger log = Logger.getLogger(UserReportServiceImpl.class);
	
	@Resource
	private UserReportMapper userReportMapper;
	
	@Resource
	private UserAccountMapper userAccountMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	public synchronized void userReportTask() throws ParseException{
		String reportDate = DateUtils.getPrevSimpleDateString();
		log.info("用户报表生成任务开始："+reportDate);
		handleUserReport(reportDate);
		log.info("用户报表生成任务完成："+reportDate);
	}
	
	public void handleUserReport(String reportDate) throws ParseException{
		log.info("开始处理“"+reportDate+"”用户报表.");
		ReportTaskCondition taskCondition = new ReportTaskCondition();
		taskCondition.setReportDate(reportDate);
		taskCondition.setReportType(GlobalConstants.REPORT_TASK_TYPE_USER_REPORT);
		List<ReportTask> reportTasks = userReportMapper.selectReportTask(taskCondition);
		ReportTask task = null;
		if(reportTasks != null && reportTasks.size() > 0){
			log.info("获取“"+reportDate+"”用户报表任务，有："+reportTasks.size()+"条");
			task = reportTasks.get(0);
			if(task.getState() == GlobalConstants.REPORT_TASK_STATE_FINISH){
				log.info("“"+reportDate+"”的用户报表任务已完成，不再继续执行.");
				return;
			}else{
				log.info("“"+reportDate+"”的用户报表任务状态为："+task.getState()+"，继续执行.");
			}
		}else{
			task = new ReportTask();
			log.info("“"+reportDate+"”的用户报表任务状态无记录，继续执行.");
		}
		log.info("“"+reportDate+"”的用户报表任务开始执行");
		if(task.getId() == null){
			int id = sequenceService.get("UserReport");
			task.setId(Long.valueOf(id));
			task.setReportDate(DateUtils.getSimpleDate(reportDate));
			task.setReportType(GlobalConstants.REPORT_TASK_TYPE_USER_REPORT);
			task.setState(GlobalConstants.REPORT_TASK_STATE_CREATE);
		}
		
		UserCondition userCondition = new UserCondition();
		userCondition.setQueryUserTypes(GlobalConstants.getQueryOrdinaryUserTypes());
		List<User> users = userMapper.selectList(userCondition);
		log.info("“"+reportDate+"”用户报表任务，彩民用户数量：“"+users == null ? 0 : users.size()+"”人");
		List<UserReport> reports = new ArrayList<UserReport>();
		for(User user : users){
			log.info("“"+reportDate+"”用户报表任务，处理用户：mobile:“"+user.getMobile()+"”，userName：“"+user.getUsername()+"”数据");
			UserReport report = new UserReport();
			report.setUser(user);
			report.setReportDate(DateUtils.getSimpleDate(reportDate));
			
			UserAccountCondition accountCondition = new UserAccountCondition();
			accountCondition.setUserId(user.getId());
			accountCondition.setReportDate(reportDate);
			List<UserAccountDetail> details = userAccountMapper.selectUserAccountDetailList(accountCondition);
			Integer rechargeAmount = 0;
			Integer withdrawAmount = 0;
			Integer winAmount = 0;
			Integer recommenderOtherAmount = 0;
			log.info("“"+reportDate+"”用户报表任务，处理用户：mobile:“"+user.getMobile()+"”，userName：“"+user.getUsername()+"”数据，交易数据总条数："+details.size());
			for(UserAccountDetail detail : details){
				if("RECHARGE_D".equals(detail.getType())){
					rechargeAmount += detail.getAmount();
				}
				
				if("WITHDRAW_R".equals(detail.getType())){
					withdrawAmount += detail.getAmount();
				}
				
//				if("WITHDRAW_RETURN_D".equals(detail.getType())){
//					withdrawAmount -= detail.getAmount();
//				}
				
				if("WIN_D".equals(detail.getType())){
					winAmount += detail.getAmount();
				}
				
				if("RECOMMENDER_SHARE_D".equals(detail.getType())){
					recommenderOtherAmount += detail.getAmount();
				}
			}
			report.setRechargeAmount(rechargeAmount);
			report.setWithdrawAmount(withdrawAmount);
			report.setWinAmount(winAmount);
			report.setRecommenderOtherAmount(recommenderOtherAmount);
			
			log.info("“"+reportDate+"”用户报表任务：rechargeAmount:“"+rechargeAmount+"”，withdrawAmount：“"+withdrawAmount+"”，winAmount：“"+winAmount+"”，recommenderOtherAmount：“"+recommenderOtherAmount+"”.");
			reports.add(report);
		}
		
		if(reports != null && reports.size() > 0){
			for(UserReport report : reports){
				int id = sequenceService.get("UserReport");
				report.setId(Long.valueOf(id));
				userReportMapper.insert(report);
			}
		}
		
		task.setState(GlobalConstants.REPORT_TASK_STATE_FINISH);
		userReportMapper.insertReportTask(task);
	}
	
	@Override
	public GenericMapper<UserReport, Long> getMapper() {
		return userReportMapper;
	}
}
