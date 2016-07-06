package com.yu.web.base.model;

import java.util.Date;

public class ReportTask {
	private Long id;
	private Date reportDate;
	private Integer reportType;
	private Integer state;
	private Date createDate;
	private Date lastUpdateDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getReportType() {
		return reportType;
	}
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
}
