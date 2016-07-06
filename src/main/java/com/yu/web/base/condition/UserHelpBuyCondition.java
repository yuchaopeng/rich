package com.yu.web.base.condition;

import com.yu.base.condition.BaseCondition;

public class UserHelpBuyCondition extends BaseCondition {
	private Long planId;
	private Long userId;
	private Integer state;
	/**
	 * @return the planId
	 */
	public Long getPlanId() {
		return planId;
	}
	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
