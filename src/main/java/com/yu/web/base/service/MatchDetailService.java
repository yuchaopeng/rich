package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.Match;
import com.yu.web.base.model.MatchDetail;

public interface MatchDetailService extends GenericService<MatchDetail, Long> {
	MatchDetail buildMatchDetail(MatchDetail detail,Match match);
}
