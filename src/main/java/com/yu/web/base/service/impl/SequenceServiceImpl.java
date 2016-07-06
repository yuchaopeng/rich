package com.yu.web.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.web.base.mapper.SequenceMapper;
import com.yu.web.base.model.Sequence;
import com.yu.web.base.service.SequenceService;

@Service
public class SequenceServiceImpl implements SequenceService {
	
	@Resource
	private SequenceMapper sequenceMapper;

	public int get(String module) {
		List<Sequence> seqs = sequenceMapper.get(module);
		int resultVal = 1;
		Sequence seq = null;
		if(seqs == null || seqs.size() == 0){
			seq = new Sequence();
			seq.setVal(resultVal);
			seq.setModule(module);
			insert(seq);
		}else{
			seq = seqs.get(0);
			resultVal = seq.getVal();
			seq.setVal(++resultVal);
			update(seq);
		}
		return resultVal;
	}
	
	public void update(Sequence seq){
		sequenceMapper.update(seq);
	}
	
	public void insert(Sequence seq){
		sequenceMapper.insert(seq);
	}

	public SequenceMapper getSequenceMapper() {
		return sequenceMapper;
	}

	public void setSequenceMapper(SequenceMapper sequenceMapper) {
		this.sequenceMapper = sequenceMapper;
	}
	
	
}
