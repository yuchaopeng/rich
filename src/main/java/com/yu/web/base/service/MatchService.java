package com.yu.web.base.service;

import java.util.List;
import java.util.Map;

import com.yu.base.service.GenericService;
import com.yu.web.base.form.MatchForm;
import com.yu.web.base.model.Match;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserBettingRecord;

public interface MatchService extends GenericService<Match, Long> {
	Match buildMatch(Match match);
	
	/**
	 * 发布赛事，进行用户投注
	 * @param matchId
	 */
	List<UserBettingRecord> savePublishMatch(Long matchId);
	
	Map<String,Object> saveOrUpdateMatch(MatchForm matchForm,User user) throws Exception;
	
	void inputMatchResult(MatchForm matchForm,User user) throws Exception;
	
}
