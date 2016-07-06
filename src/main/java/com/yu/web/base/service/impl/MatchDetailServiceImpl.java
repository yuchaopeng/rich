package com.yu.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.base.util.DateUtils;
import com.yu.web.base.mapper.MatchDetailMapper;
import com.yu.web.base.model.Match;
import com.yu.web.base.model.MatchDetail;
import com.yu.web.base.service.MatchDetailService;
import com.yu.web.base.service.SequenceService;

@Service
public class MatchDetailServiceImpl extends GenericServiceImpl<MatchDetail, Long> implements MatchDetailService {

	@Resource
	private MatchDetailMapper matchDetailMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	
	public MatchDetail buildMatchDetail(MatchDetail detail,Match match){
		int id = sequenceService.get("matchDetail");
		detail.setId(Long.valueOf(id));
		detail.setMatch(match);
		detail.setCreateDate(DateUtils.getNowTime());
		detail.setLastUpdateDate(DateUtils.getNowTime());
		return detail;
	}
	
	@Override
	public GenericMapper<MatchDetail, Long> getMapper() {
		return matchDetailMapper;
	}
}