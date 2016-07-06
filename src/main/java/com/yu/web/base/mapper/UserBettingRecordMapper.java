package com.yu.web.base.mapper;

import com.yu.base.mapper.GenericMapper;
import com.yu.web.base.model.UserBettingRecord;

public interface UserBettingRecordMapper extends GenericMapper<UserBettingRecord, Long> {
	void deleteBettingLottery(Long bettingId);
}