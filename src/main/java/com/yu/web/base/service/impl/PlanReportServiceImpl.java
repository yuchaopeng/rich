package com.yu.web.base.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.base.util.DateUtils;
import com.yu.web.base.condition.MatchCondition;
import com.yu.web.base.condition.ReportCondition;
import com.yu.web.base.condition.UserBettingRecordCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.mapper.MatchMapper;
import com.yu.web.base.mapper.PlanMapper;
import com.yu.web.base.mapper.PlanReportMapper;
import com.yu.web.base.model.Match;
import com.yu.web.base.model.Plan;
import com.yu.web.base.model.PlanReport;
import com.yu.web.base.model.UserBettingRecord;
import com.yu.web.base.service.PlanReportService;
import com.yu.web.base.service.UserBettingRecordService;
import com.yu.web.base.service.WebSiteConfigService;

@Service
public class PlanReportServiceImpl extends GenericServiceImpl<PlanReport, Long> implements PlanReportService {
	@Resource
	private PlanReportMapper planReportMapper;
	
	@Resource
	private MatchMapper matchMapper;
	
	@Resource
	private PlanMapper planMapper;
	
	@Resource
	private WebSiteConfigService webSiteConfigService;
	
	@Resource
	private UserBettingRecordService userBettingRecordService;
	
	public void handlePlanReport(Long planId,String matchDate){
		
		Plan plan = planMapper.selectById(planId);
		
		MatchCondition condition = new MatchCondition();
		condition.setPlanId(planId);
		condition.setMatchDate(matchDate);
		List<Match> matchs = matchMapper.selectList(condition);
		
		ReportCondition reportCondition = new ReportCondition();
		reportCondition.setReportDate(matchDate);
		
		PlanReport report = null;
		List<PlanReport> reports = planReportMapper.selectList(reportCondition);
		if(reports != null && reports.size() > 0){
			report = reports.get(0);
		}else{
			report = new PlanReport();
		}
		
		report.setPlan(plan);
		try {
			report.setReportDate(DateUtils.getSimpleDate(matchDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer bettingAmount = 0;
		Integer winAmount = 0;
		
		Integer recommenderAmount = 0;
		Integer expertAmount = 0;
		
		for(Match match :matchs){
			UserBettingRecordCondition bettingRecordCondition = new UserBettingRecordCondition();
			bettingRecordCondition.setMatchId(match.getId());
			List<UserBettingRecord> bettingRecords = userBettingRecordService.selectList(bettingRecordCondition);
			
			if(match.getExpertAmount() != null){
				expertAmount += match.getExpertAmount();
			}
			
			for(UserBettingRecord record : bettingRecords){
				if(record.getState() == GlobalConstants.USER_BETTING_STATE_SUCCESS 
						|| record.getState() == GlobalConstants.USER_BETTING_STATE_WIN
						|| record.getState() == GlobalConstants.USER_BETTING_STATE_LOSS
						|| record.getState() == GlobalConstants.USER_BETTING_STATE_WIN_ERROR){
					bettingAmount += record.getBettingAmount();
				}
				
				if(record.getState() == GlobalConstants.USER_BETTING_STATE_WIN){
					winAmount += record.getWinAmount();
				}
				
				if(record.getRecommenderAmount() != null){
					recommenderAmount += record.getRecommenderAmount();
				}
			}
		}
		
		Integer commissionAmount = webSiteConfigService.getCommAmount(bettingAmount);
		
		report.setBettingAmount(bettingAmount);
		report.setCommissionAmount(commissionAmount);
		report.setExpertAmount(expertAmount);
		report.setRecommenderAmount(recommenderAmount);
		report.setWinAmount(winAmount);
		
		if(report.getId() != null){
			planReportMapper.update(report);
		}else{
			planReportMapper.insert(report);
		}
	}
	
	@Override
	public GenericMapper<PlanReport, Long> getMapper() {
		return planReportMapper;
	}
}
