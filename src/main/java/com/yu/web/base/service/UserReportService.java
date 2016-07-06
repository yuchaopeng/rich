package com.yu.web.base.service;

import java.text.ParseException;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.UserReport;

public interface UserReportService  extends GenericService<UserReport, Long> {
	
	void userReportTask() throws ParseException;
	
	void handleUserReport(String reportDate) throws ParseException;
}
