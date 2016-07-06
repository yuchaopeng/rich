package com.yu.web.base.mapper;

import java.util.List;

import com.yu.web.base.model.Sequence;

public interface SequenceMapper {
	List<Sequence> get(String module);
	
	void update(Sequence seq);
	
	void insert(Sequence seq);
}
