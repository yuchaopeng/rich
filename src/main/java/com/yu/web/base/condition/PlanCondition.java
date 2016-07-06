package com.yu.web.base.condition;

import com.yu.base.condition.BaseCondition;

public class PlanCondition extends BaseCondition{
	private Long id;
	private String planName;
	private String planNameEquals;
	private Long expertId;
	private Long creatorId;
	private Integer state;
	
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
}
