package com.yu.web.base.form;

import java.util.List;

import com.yu.web.base.model.Match;
import com.yu.web.base.model.Plan;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserBettingRecord;

public class UserDetailReportForm {
	private User user;
	private Plan plan;
	private List<Match> matchs;
	private List<UserBettingRecord> bettingRecords;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public List<Match> getMatchs() {
		return matchs;
	}
	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}
	public List<UserBettingRecord> getBettingRecords() {
		return bettingRecords;
	}
	public void setBettingRecords(List<UserBettingRecord> bettingRecords) {
		this.bettingRecords = bettingRecords;
	}
	
}
