package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.PlanReport;

public interface PlanReportService  extends GenericService<PlanReport, Long> {
	
	void handlePlanReport(Long planId,String matchDate);
}
