package com.yu.web.base.condition;

import com.yu.base.condition.BaseCondition;

public class MatchCondition extends BaseCondition{
	private Long id;
	private Long matchId;
	private Long planId;
	private String planName;
	private String planNameEquals;
	private Long expertId;
	private Long creatorId;
	private Integer state;
	private Long playMethodId;
	
	private String matchDate;
	
	/**
	 * @return the playMethodId
	 */
	public Long getPlayMethodId() {
		return playMethodId;
	}
	/**
	 * @param playMethodId the playMethodId to set
	 */
	public void setPlayMethodId(Long playMethodId) {
		this.playMethodId = playMethodId;
	}
	public String getPlanNameEquals() {
		return planNameEquals;
	}
	public void setPlanNameEquals(String planNameEquals) {
		this.planNameEquals = planNameEquals;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Long getExpertId() {
		return expertId;
	}
	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
	
}
