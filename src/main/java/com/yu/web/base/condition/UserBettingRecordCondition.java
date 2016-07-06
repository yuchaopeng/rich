package com.yu.web.base.condition;

import com.yu.base.condition.BaseCondition;

public class UserBettingRecordCondition extends BaseCondition {
	private Long matchId;
	
	private Long planId;
	
	private Long userId;
	
	private String createDateStart;
	
	private String createDateEnd;
	
	private Long recommenderUserId;
	
	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public Long getRecommenderUserId() {
		return recommenderUserId;
	}

	public void setRecommenderUserId(Long recommenderUserId) {
		this.recommenderUserId = recommenderUserId;
	}

}
