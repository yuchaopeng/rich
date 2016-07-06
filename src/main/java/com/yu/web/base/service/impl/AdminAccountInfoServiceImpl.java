package com.yu.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.mapper.AdminAccountInfoMapper;
import com.yu.web.base.model.AdminAccountInfo;
import com.yu.web.base.service.AdminAccountInfoService;

@Service
public class AdminAccountInfoServiceImpl extends GenericServiceImpl<AdminAccountInfo, Long> implements AdminAccountInfoService {

	@Resource
	private AdminAccountInfoMapper adminAccountInfoMapper;
	
	
	@Override
	public GenericMapper<AdminAccountInfo, Long> getMapper() {
		return adminAccountInfoMapper;
	}
}