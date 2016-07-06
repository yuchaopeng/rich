package com.yu.web.base.mapper;

import java.util.List;

import com.yu.base.mapper.GenericMapper;
import com.yu.web.base.condition.ReportTaskCondition;
import com.yu.web.base.model.ReportTask;
import com.yu.web.base.model.UserReport;

public interface UserReportMapper extends GenericMapper<UserReport, Long> {
	List<ReportTask> selectReportTask(ReportTaskCondition condition);
	
	void insertReportTask(ReportTask reportTask);
	
	void updateReportTask(ReportTask reportTask);
}