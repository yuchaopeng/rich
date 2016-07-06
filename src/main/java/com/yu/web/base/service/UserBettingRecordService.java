package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.form.PayOrderCreateForm;
import com.yu.web.base.model.PayOrder;
import com.yu.web.base.model.UserBettingRecord;
import com.yu.web.base.util.IDBuildUtil;

public interface UserBettingRecordService extends GenericService<UserBettingRecord, Long> {
	void deleteBettingLottery(Long bettingId);
	
	void handleBetting(UserBettingRecord record);
}
