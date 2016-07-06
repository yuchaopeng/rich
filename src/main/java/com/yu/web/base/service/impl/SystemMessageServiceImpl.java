package com.yu.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.mapper.SystemMessageMapper;
import com.yu.web.base.model.SystemMessage;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.SystemMessageService;

@Service
public class SystemMessageServiceImpl extends GenericServiceImpl<SystemMessage, Long> implements SystemMessageService {

	@Resource
	private SystemMessageMapper systemMessageMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	public SystemMessage buildSystemMessage(SystemMessage systemMessage){
		int id = sequenceService.get("systemMessage");
		systemMessage.setId(Long.valueOf(id));
		systemMessage.setState(GlobalConstants.SYSTEM_MESSAGE_DRAFT);
		return systemMessage;
	}
	
	@Override
	public GenericMapper<SystemMessage, Long> getMapper() {
		return systemMessageMapper;
	}
}