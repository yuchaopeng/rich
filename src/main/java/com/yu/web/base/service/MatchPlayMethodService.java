package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.MatchPlayMethod;

public interface MatchPlayMethodService extends GenericService<MatchPlayMethod, Long> {
	MatchPlayMethod buildMatchPlayMethod(MatchPlayMethod matchPlayMethod);
}
