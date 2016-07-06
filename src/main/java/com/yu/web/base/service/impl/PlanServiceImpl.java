package com.yu.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.mapper.PlanMapper;
import com.yu.web.base.model.Plan;
import com.yu.web.base.service.PlanService;
import com.yu.web.base.service.SequenceService;

@Service
public class PlanServiceImpl extends GenericServiceImpl<Plan, Long> implements PlanService {

	@Resource
	PlanMapper planMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	public Plan buildPlan(Plan plan) throws Exception{
		int id = sequenceService.get("plan");
		plan.setId(Long.valueOf(id));
		plan.setState(GlobalConstants.PLAN_STATE_NORMAL);
		plan.setCreateDate(DateUtils.getNowTime());
		plan.setLastUpdateDate(DateUtils.getNowTime());
		return plan;
	}
	
	@Override
	public GenericMapper<Plan, Long> getMapper() {
		return planMapper;
	}

}