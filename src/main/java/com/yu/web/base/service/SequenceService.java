package com.yu.web.base.service;

import com.yu.web.base.model.Sequence;


public interface SequenceService {
	
	int get(String module);
	
	void update(Sequence seq);
	
	void insert(Sequence seq);
}
