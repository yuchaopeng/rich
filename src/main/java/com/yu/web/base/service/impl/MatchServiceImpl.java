package com.yu.web.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.base.util.DateUtils;
import com.yu.web.base.condition.MatchCondition;
import com.yu.web.base.condition.UserHelpBuyCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.form.MatchForm;
import com.yu.web.base.mapper.MatchDetailMapper;
import com.yu.web.base.mapper.MatchMapper;
import com.yu.web.base.mapper.UserBettingRecordMapper;
import com.yu.web.base.mapper.UserHelpBuyMapper;
import com.yu.web.base.model.Match;
import com.yu.web.base.model.MatchDetail;
import com.yu.web.base.model.Plan;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserBettingRecord;
import com.yu.web.base.model.UserHelpBuyRecord;
import com.yu.web.base.service.MatchDetailService;
import com.yu.web.base.service.MatchService;
import com.yu.web.base.service.SequenceService;

@Service
public class MatchServiceImpl extends GenericServiceImpl<Match, Long> implements MatchService {

	@Resource
	private MatchMapper matchMapper;
	
	@Resource
	private UserHelpBuyMapper userHelpBuyMapper;
	
	@Resource
	private UserBettingRecordMapper userBettingRecordMapper;
	
	@Resource
	private MatchDetailMapper matchDetailMapper;
	
	@Resource
	private MatchDetailService matchDetailService;
	
	@Resource
	private SequenceService sequenceService;
	
	public Match buildMatch(Match match){
		int id = sequenceService.get("match");
		match.setId(Long.valueOf(id));
		match.setState(GlobalConstants.MATCH_STATE_DRAFT);
		Date date = DateUtils.getNowTime();
		match.setMatchDate(DateUtils.getSimpleDateString(date));
		match.setCreateDate(date);
		match.setLastUpdateDate(date);
		return match;
	}
	
	public Map<String,Object> saveOrUpdateMatch(MatchForm matchForm,User user) throws Exception{
		Match match = matchForm.getMatch();
		List<MatchDetail> detailResults = new ArrayList<>();
		List<MatchDetail> matchDetails = matchForm.getMatchDetails();
		
		if(match.getId() == null){
			if(match.getPlan() != null){
				Match m = getExistMatch(match.getPlan().getId());
				if(m != null){
					throw new Exception("该计划今天已经发布过赛事！");
				}
			}
			match = buildMatch(match);
			match.setCreator(user);
			matchMapper.insert(match);
		}else{
			matchMapper.update(match);
			match = matchMapper.selectById(match.getId());
		}
		
		for(MatchDetail detail : matchDetails){
			if(detail.getId() == null){
				if("".equalsIgnoreCase(detail.getMatchName())){//如果赛事名称为空则不保存，这样是为了有可能第二场赛事不填写的情况
					continue;
				}
				detail = matchDetailService.buildMatchDetail(detail,match);
				matchDetailMapper.insert(detail);
			}else{
				if("".equalsIgnoreCase(detail.getMatchName())){//如果赛事名称为空，则删除这条记录，说明已经明确要删除第二场赛事
					matchDetailMapper.deleteById(detail.getId());
					continue;
				}
				matchDetailMapper.update(detail);
				detail = matchDetailMapper.selectById(detail.getId());
			}
			detailResults.add(detail);
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("match", match);
		map.put("matchDetails", detailResults);
		return map;
	}
	
	
	public void inputMatchResult(MatchForm matchForm,User user) throws Exception{
		//先保存
		saveOrUpdateMatch(matchForm,user);
		Match match = matchForm.getMatch();
		match.setState(GlobalConstants.MATCH_STATE_HAVE_RESULT);
		matchMapper.update(match);
	}
	
	public List<UserBettingRecord> savePublishMatch(Long matchId){
		Match match = matchMapper.selectById(matchId);
		Plan plan = match.getPlan();
		UserHelpBuyCondition condition = new UserHelpBuyCondition();
		condition.setPlanId(plan.getId());
		List<UserHelpBuyRecord> helpBuyRecords = userHelpBuyMapper.selectList(condition);
		List<UserBettingRecord> bettingRecords = new ArrayList<UserBettingRecord>();
		for(UserHelpBuyRecord record : helpBuyRecords){
			//计算用户本次投注金额
			Integer bettingAmount = record.getStartAmount();
			
			int bettingMode = record.getBettingModel();
			if(bettingMode == GlobalConstants.BETTING_MODEL_MULTI){//如果是倍投，则计算失败次数，
				//获取连续失败次数
				int lossNum = record.getContinueLossNum();
				bettingAmount = bettingAmount * (int) Math.pow(2,lossNum);
			}
			
			//设置总投注次数
			int totalNum = record.getTotalNum();
			totalNum++;
			record.setTotalNum(totalNum);
			userHelpBuyMapper.update(record);
			
			UserBettingRecord bettingRecord = new UserBettingRecord();
			int id = sequenceService.get("userBettingRecord");
			bettingRecord.setId(Long.valueOf(id));
			bettingRecord.setUser(record.getUser());
			bettingRecord.setPlan(plan);
			bettingRecord.setMatch(match);
			bettingRecord.setUserHelpBuyRecord(record);
			//设置投注金额
			bettingRecord.setBettingAmount(bettingAmount);
			bettingRecord.setState(GlobalConstants.USER_BETTING_STATE_BETTING);
			bettingRecord.setLotteryUploadState(GlobalConstants.LOTTERY_PHOTO_UPLOAD_NOT);
			bettingRecord.setWinAmount(0);
			userBettingRecordMapper.insert(bettingRecord);
			bettingRecords.add(bettingRecord);
		}
		
		//发布完成之后，修改赛事状态为已发布
		match.setState(GlobalConstants.MATCH_STATE_PUBLISH);
		matchMapper.update(match);
		return bettingRecords;
	}
	
	private Match getExistMatch(Long planId){
		MatchCondition condition = new MatchCondition();
		condition.setMatchDate(DateUtils.getSimpleDateString(DateUtils.getNowTime()));
		condition.setPlanId(planId);
		List<Match> matchs = matchMapper.selectList(condition);
		if(matchs != null && matchs.size() > 0){
			return matchs.get(0);
		}
		return null;
	}
	
	@Override
	public GenericMapper<Match, Long> getMapper() {
		return matchMapper;
	}
}