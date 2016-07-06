package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.UserHelpBuyRecord;

public interface UserHelpBuyService extends GenericService<UserHelpBuyRecord, Long> {
	
	UserHelpBuyRecord buildUserHelpBuy(UserHelpBuyRecord record);
}
