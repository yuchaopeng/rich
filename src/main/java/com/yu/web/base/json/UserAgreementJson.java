package com.yu.web.base.json;

import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.UserAgreement;

public class UserAgreementJson {
	private Long id;

	private Long userId;

	private Integer agreementType;
	
	private String agreementTypeName;

	private Double agreementValue;

	private String createDate;

	public UserAgreementJson(Long id, Long userId, Integer agreementType,String agreementTypeName, Double agreementValue, String createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.agreementType = agreementType;
		this.agreementTypeName = agreementTypeName;
		this.agreementValue = agreementValue;
		this.createDate = createDate;
	}

	public UserAgreementJson(UserAgreement userAgreement){
		this(userAgreement.getId(),
				userAgreement.getUser().getId(),
				userAgreement.getAgreementType(),
				GlobalConstants.getUserAgreementTypeName(userAgreement.getAgreementType()),
				userAgreement.getAgreementValue(),
				DateUtils.getDateString(userAgreement.getCreateDate()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAgreementTypeName() {
		return agreementTypeName;
	}

	public void setAgreementTypeName(String agreementTypeName) {
		this.agreementTypeName = agreementTypeName;
	}

	
	
}
