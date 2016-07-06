package com.yu.web.base.condition;

import com.yu.base.condition.BaseCondition;

public class UserAccountCondition extends BaseCondition{
	private Long userId;
	
	private Long planId;
	
	private String type;
	
	private Long relationUserId; 
	
	private String reportDate;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getRelationUserId() {
		return relationUserId;
	}

	public void setRelationUserId(Long relationUserId) {
		this.relationUserId = relationUserId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
}
