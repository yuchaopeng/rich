package com.yu.web.base.mapper;

import com.yu.base.mapper.GenericMapper;
import com.yu.web.base.model.UserAgreement;

public interface UserAgreementMapper extends GenericMapper<UserAgreement, Long> {
	/**
	 * 获取可用的分成协议
	 * @param userId
	 * @return
	 */
	UserAgreement getUsableAgreement(Long userId);
	
}