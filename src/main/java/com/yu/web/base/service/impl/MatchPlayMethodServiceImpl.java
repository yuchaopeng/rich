package com.yu.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.mapper.MatchPlayMethodMapper;
import com.yu.web.base.model.MatchPlayMethod;
import com.yu.web.base.service.MatchPlayMethodService;
import com.yu.web.base.service.SequenceService;

@Service
public class MatchPlayMethodServiceImpl extends GenericServiceImpl<MatchPlayMethod, Long> implements MatchPlayMethodService {

	@Resource
	private MatchPlayMethodMapper matchPlayMethodMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	
	public MatchPlayMethod buildMatchPlayMethod(MatchPlayMethod matchPlayMethod){
		int id = sequenceService.get("matchPlayMethod");
		matchPlayMethod.setId(Long.valueOf(id));
		return matchPlayMethod;
	}
	
	@Override
	public GenericMapper<MatchPlayMethod, Long> getMapper() {
		return matchPlayMethodMapper;
	}
}