package com.yu.web.base.task;

import java.text.ParseException;

import com.yu.base.util.SpringUtil;
import com.yu.web.base.service.UserDetailReportService;
import com.yu.web.base.service.UserReportService;

public class ReportTask {
	
	public void userReportTask(){
		try {
			UserReportService userReportService = (UserReportService) SpringUtil.getBean("userReportServiceImpl");
			userReportService.userReportTask();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void userDetailReportTask(){
		try {
			UserDetailReportService userDetailReportService = (UserDetailReportService) SpringUtil.getBean("userDetailReportServiceImpl");
			userDetailReportService.userDetailReportTask();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
