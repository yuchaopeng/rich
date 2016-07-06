package com.yu.web.base.form;

import com.yu.web.base.model.Plan;

public class AppPlanForm {
	private Plan plan;
	private String winRate;
	
	
	public AppPlanForm(Plan plan, String winRate) {
		super();
		this.plan = plan;
		this.winRate = winRate;
	}
	/**
	 * @return the plan
	 */
	public Plan getPlan() {
		return plan;
	}
	/**
	 * @param plan the plan to set
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	/**
	 * @return the winRate
	 */
	public String getWinRate() {
		return winRate;
	}
	/**
	 * @param winRate the winRate to set
	 */
	public void setWinRate(String winRate) {
		this.winRate = winRate;
	}
	
}
