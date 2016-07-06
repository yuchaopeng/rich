package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.Plan;

public interface PlanService extends GenericService<Plan, Long> {
	
	Plan buildPlan(Plan plan) throws Exception;
}
