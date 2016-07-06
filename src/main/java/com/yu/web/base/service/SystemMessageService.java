package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.SystemMessage;

public interface SystemMessageService extends GenericService<SystemMessage, Long> {
	SystemMessage buildSystemMessage(SystemMessage systemMessage);
}
