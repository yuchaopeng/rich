package com.yu.web.base.model;

import java.util.Date;

public class UserAgreement {
	private Long id;
	private User user;
	private Integer agreementType;//协议类型。
	private Double agreementValue;
	private Date createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getAgreementType() {
		return agreementType;
	}
	public void setAgreementType(Integer agreementType) {
		this.agreementType = agreementType;
	}
	public Double getAgreementValue() {
		return agreementValue;
	}
	public void setAgreementValue(Double agreementValue) {
		this.agreementValue = agreementValue;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
