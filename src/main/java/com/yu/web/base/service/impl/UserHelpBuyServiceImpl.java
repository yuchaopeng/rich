package com.yu.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.mapper.UserHelpBuyMapper;
import com.yu.web.base.model.UserHelpBuyRecord;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.UserHelpBuyService;

@Service
public class UserHelpBuyServiceImpl extends GenericServiceImpl<UserHelpBuyRecord, Long> implements UserHelpBuyService {
	
	@Resource
	private UserHelpBuyMapper userHelpBuyMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	public UserHelpBuyRecord buildUserHelpBuy(UserHelpBuyRecord record){
		int id = sequenceService.get("userHelpBuyMapper");
		record.setId(Long.valueOf(id));
		record.setTotalNum(0);
		record.setContinueLossNum(0);
		record.setState(GlobalConstants.USER_HELP_BUY_OK);
		return record;
	}

	@Override
	public GenericMapper<UserHelpBuyRecord, Long> getMapper() {
		return userHelpBuyMapper;
	}
	
}
