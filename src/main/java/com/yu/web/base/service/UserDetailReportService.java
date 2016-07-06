package com.yu.web.base.service;

import java.text.ParseException;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.UserDetailReport;

public interface UserDetailReportService  extends GenericService<UserDetailReport, Long> {
	void userDetailReportTask() throws ParseException;
}
