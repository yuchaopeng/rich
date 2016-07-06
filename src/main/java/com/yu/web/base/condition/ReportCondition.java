package com.yu.web.base.condition;

import com.yu.base.condition.BaseCondition;

public class ReportCondition extends BaseCondition{
	private Long id;
	private Long planId;
	private String reportDate;

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
}
