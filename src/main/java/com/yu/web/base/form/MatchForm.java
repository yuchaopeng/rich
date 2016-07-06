package com.yu.web.base.form;

import java.util.List;

import com.yu.web.base.model.Match;
import com.yu.web.base.model.MatchDetail;
import com.yu.web.base.model.UserBettingRecord;

public class MatchForm {
	private UserBettingRecord userBettingRecord;
	
	private Match match;
	
	private List<MatchDetail> matchDetails;

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public List<MatchDetail> getMatchDetails() {
		return matchDetails;
	}

	public void setMatchDetails(List<MatchDetail> matchDetails) {
		this.matchDetails = matchDetails;
	}

	public UserBettingRecord getUserBettingRecord() {
		return userBettingRecord;
	}

	public void setUserBettingRecord(UserBettingRecord userBettingRecord) {
		this.userBettingRecord = userBettingRecord;
	}
	
}
