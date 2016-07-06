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
import com.yu.web.base.mapper.UserDetailReportMapper;
import com.yu.web.base.mapper.UserMapper;
import com.yu.web.base.mapper.UserReportMapper;
import com.yu.web.base.model.Plan;
import com.yu.web.base.model.ReportTask;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAccountDetail;
import com.yu.web.base.model.UserAccountDetailGroup;
import com.yu.web.base.model.UserDetailReport;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.UserBettingRecordService;
import com.yu.web.base.service.UserDetailReportService;
import com.yu.web.base.service.WebSiteConfigService;

@Service
public class UserDetailReportServiceImpl extends GenericServiceImpl<UserDetailReport, Long> implements UserDetailReportService {
	
	private Logger log = Logger.getLogger(UserDetailReportServiceImpl.class);
	
	@Resource
	private UserDetailReportMapper userDetailReportMapper;
	
	@Resource
	private UserReportMapper userReportMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserAccountMapper userAccountMapper;
	
	@Resource
	private WebSiteConfigService webSiteConfigService;
	
	@Resource
	private UserBettingRecordService userBettingRecordService;
	
	@Resource
	private SequenceService sequenceService;
	
	public synchronized void userDetailReportTask() throws ParseException{
		
		String reportDate = DateUtils.getPrevSimpleDateString();
		log.info("用户明细报表生成任务开始："+reportDate);
		handleUserDetailReport(reportDate);
		log.info("用户明细报表生成任务完成："+reportDate);
		
	}
	
	public void handleUserDetailReport(String reportDate) throws ParseException{
		log.info("开始处理“"+reportDate+"”用户明细报表.");
		ReportTaskCondition taskCondition = new ReportTaskCondition();	
		taskCondition.setReportDate(reportDate);
		taskCondition.setReportType(GlobalConstants.REPORT_TASK_TYPE_USER_DETAIL_REPORT);
		List<ReportTask> reportTasks = userReportMapper.selectReportTask(taskCondition);
		ReportTask task = null;
		if(reportTasks != null && reportTasks.size() > 0){
			log.info("获取“"+reportDate+"”用户明细报表任务，有："+reportTasks.size()+"条");
			task = reportTasks.get(0);
			if(task.getState() == GlobalConstants.REPORT_TASK_STATE_FINISH){
				log.info("“"+reportDate+"”的用户明细报表任务已完成，不再继续执行.");
				return;
			}else{
				log.info("“"+reportDate+"”的用户明细报表任务状态为："+task.getState()+"，继续执行.");
			}
		}else{
			task = new ReportTask();
			log.info("“"+reportDate+"”的用户明细报表任务状态无记录，继续执行.");
		}
		log.info("“"+reportDate+"”的用户明细报表任务开始执行");
		if(task.getId() == null){
			int id = sequenceService.get("UserReport");
			task.setId(Long.valueOf(id));
			task.setReportDate(DateUtils.getSimpleDate(reportDate));
			task.setReportType(GlobalConstants.REPORT_TASK_TYPE_USER_DETAIL_REPORT);
			task.setState(GlobalConstants.REPORT_TASK_STATE_CREATE);
		}
		
		UserCondition userCondition = new UserCondition();
		userCondition.setQueryUserTypes(GlobalConstants.getQueryAllUserTypes());
		List<User> users = userMapper.selectList(userCondition);
		log.info("“"+reportDate+"”用户明细报表任务，彩民用户数量：“"+users == null ? 0 : users.size()+"”人");
		List<UserDetailReport> reports = new ArrayList<UserDetailReport>();
		for(User user : users){
			if(user.getId() == 3){
				System.out.println("3333");
			}
			System.out.println(user.getUsername());
			Integer bettingAmount = 0;
			Integer commissionAmount = 0;
			Integer recommenderAmount = 0;
			Integer recommenderOtherAmount = 0;
			Integer expertAmount = 0;
			Integer winAmount = 0;
			
			UserAccountCondition condition = new UserAccountCondition();
			condition.setUserId(user.getId());
			condition.setReportDate(reportDate);
			List<UserAccountDetailGroup> detail = userAccountMapper.selectUserAccountDetailGroup(condition);
			
			
			UserDetailReport report = new UserDetailReport();
			
			if(detail == null || detail.size() == 0){
				report.setUser(user);
				User recommender = user.getRecommender();
				report.setRecommender(recommender);
				report.setReportDate(DateUtils.getSimpleDate(reportDate));
				
				report.setBettingAmount(bettingAmount);
				report.setCommissionAmount(commissionAmount);
				report.setRecommenderOtherAmount(recommenderOtherAmount);
				report.setExpertAmount(expertAmount);
				report.setWinAmount(winAmount);
				reports.add(report);
				if(recommender != null){
					report.setRecommenderAmount(recommenderAmount);
				}
				
				log.info("“"+reportDate+"”用户明细报表任务user（变更记录）:“"+user.getId()+ " "+user.getUsername()+"”：bettingAmount:“"+bettingAmount+"”，commissionAmount：“"+commissionAmount+"”，recommenderAmount：“"+recommenderAmount+"”，recommenderOtherAmount：“"+recommenderOtherAmount+"”，expertAmount：“"+expertAmount+"”");
				
			}else{
				for(int i = 0;i < detail.size(); i++){
					UserAccountDetailGroup d = detail.get(i);
					
					if("BETTING_R".equals(d.getType())){
						bettingAmount += d.getAmount();
					}
					
					if("BETTING_BACK_D".equals(d.getType())){
						bettingAmount -= d.getAmount();
					}
					
					if("RECOMMENDER_SHARE_D".equals(d.getType())){
						recommenderOtherAmount += d.getAmount();
					}
					
					if("EXPERT_SHARE_D".equals(d.getType())){
						expertAmount += d.getAmount();
					}
					
					if("WIN_D".equals(d.getType())){
						winAmount += d.getAmount();
					}
					
					if(i == detail.size() - 1 || d.getPlanId() != detail.get(i+1).getPlanId()){
						report.setBettingAmount(bettingAmount);
						
						report.setUser(user);
						User recommender = user.getRecommender();
						report.setRecommender(recommender);
						report.setReportDate(DateUtils.getSimpleDate(reportDate));
						
						commissionAmount = webSiteConfigService.getCommAmount(bettingAmount);
						report.setCommissionAmount(commissionAmount);
						report.setRecommenderOtherAmount(recommenderOtherAmount);
						report.setExpertAmount(expertAmount);
						report.setWinAmount(winAmount);
						
						//计算推荐人分成
						if(recommender != null){
							UserAccountCondition recommenderCondition = new UserAccountCondition();
							recommenderCondition.setUserId(recommender.getId());
							recommenderCondition.setPlanId(d.getPlanId());
							recommenderCondition.setRelationUserId(user.getId());
							recommenderCondition.setType("RECOMMENDER_SHARE_D");
							recommenderCondition.setReportDate(reportDate);
							List<UserAccountDetail> recommenderDetail = userAccountMapper.selectUserAccountDetailList(recommenderCondition);
							for(UserAccountDetail dd : recommenderDetail){
								recommenderAmount += dd.getAmount();
							}
							report.setRecommenderAmount(recommenderAmount);
						}
						
						log.info("“"+reportDate+"”用户明细报表任务user:“"+user.getId()+ " "+user.getUsername()+"”：bettingAmount:“"+bettingAmount+"”，commissionAmount：“"+commissionAmount+"”，recommenderAmount：“"+recommenderAmount+"”，recommenderOtherAmount：“"+recommenderOtherAmount+"”，expertAmount：“"+expertAmount+"”");
						
						Plan plan = new Plan();
						plan.setId(d.getPlanId());
						report.setPlan(plan);
						reports.add(report);
					}
					
					if(i < detail.size() - 1 && d.getPlanId() != detail.get(i+1).getPlanId()){
						report = new UserDetailReport();
						
						report.setUser(user);
						User recommender = user.getRecommender();
						report.setRecommender(recommender);
						report.setReportDate(DateUtils.getSimpleDate(reportDate));
						
						bettingAmount = 0;
						commissionAmount = 0;
						recommenderAmount = 0;
						recommenderOtherAmount = 0;
						expertAmount = 0;
						winAmount = 0;					}
				}	
			}
		}
		
		if(reports != null && reports.size() > 0){
			for(UserDetailReport report : reports){
				int id = sequenceService.get("UserDetailReport");
				report.setId(Long.valueOf(id));
				userDetailReportMapper.insert(report);
			}
		}
		
		task.setState(GlobalConstants.REPORT_TASK_STATE_FINISH);
		userReportMapper.insertReportTask(task);
	}
	
	@Override
	public GenericMapper<UserDetailReport, Long> getMapper() {
		return userDetailReportMapper;
	}
}
