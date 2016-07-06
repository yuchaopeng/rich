package com.yu.web.base.json;

import java.util.List;

import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.Match;
import com.yu.web.base.model.MatchDetail;

public class MatchJson {
	private Long id;
	private String createDate;
	private String createDateSimple;
	private Long planId;
	private String planName;
	private Integer state;
	private String stateVal;
	private Integer firstResult;
	private String firstResultVal;
	private Integer secondResult;
	private String secondResultVal;
	private Integer matchResult;
	private String matchResultVal;
	
	public MatchJson(Long id, String createDate, String createDateSimple, Long planId, String planName, Integer state,
			String stateVal, Integer firstResult, String firstResultVal, Integer secondResult, String secondResultVal,
			Integer matchResult, String matchResultVal) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.createDateSimple = createDateSimple;
		this.planId = planId;
		this.planName = planName;
		this.state = state;
		this.stateVal = stateVal;
		this.firstResult = firstResult;
		this.firstResultVal = firstResultVal;
		this.secondResult = secondResult;
		this.secondResultVal = secondResultVal;
		this.matchResult = matchResult;
		this.matchResultVal = matchResultVal;
	}
	
	public MatchJson(Match match,List<MatchDetail> matchDetails){
		this(match.getId(), 
			DateUtils.getDateString(match.getCreateDate()),
			DateUtils.getSimpleDateString(match.getCreateDate()) ,
			match.getPlan() == null ? null : match.getPlan().getId(), 
			match.getPlan() == null ? null : match.getPlan().getPlanName(), 
			match.getState(), 
			GlobalConstants.getMatchStateVal(match.getState()), 
			matchDetails == null ? null : (matchDetails.size() > 0 ? matchDetails.get(0).getMatchResult() : null) , 
			matchDetails == null ? null : (matchDetails.size() > 0 ? GlobalConstants.getMatchDetailResultVal(matchDetails.get(0).getMatchResult()) : null), 
			matchDetails == null ? null : (matchDetails.size() > 1 ? matchDetails.get(1).getMatchResult() : null), 
			matchDetails == null ? null : (matchDetails.size() > 1 ? GlobalConstants.getMatchResultVal(matchDetails.get(1).getMatchResult()) : null),
			match.getMatchResult(), GlobalConstants.getMatchResultVal(match.getMatchResult()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateSimple() {
		return createDateSimple;
	}

	public void setCreateDateSimple(String createDateSimple) {
		this.createDateSimple = createDateSimple;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateVal() {
		return stateVal;
	}

	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public String getFirstResultVal() {
		return firstResultVal;
	}

	public void setFirstResultVal(String firstResultVal) {
		this.firstResultVal = firstResultVal;
	}

	public Integer getSecondResult() {
		return secondResult;
	}

	public void setSecondResult(Integer secondResult) {
		this.secondResult = secondResult;
	}

	public String getSecondResultVal() {
		return secondResultVal;
	}

	public void setSecondResultVal(String secondResultVal) {
		this.secondResultVal = secondResultVal;
	}

	public Integer getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(Integer matchResult) {
		this.matchResult = matchResult;
	}

	public String getMatchResultVal() {
		return matchResultVal;
	}

	public void setMatchResultVal(String matchResultVal) {
		this.matchResultVal = matchResultVal;
	}
}
