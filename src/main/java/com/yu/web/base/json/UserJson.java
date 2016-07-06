package com.yu.web.base.json;

import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAgreement;

public class UserJson {
	private Long id;

	private String account;

	private String mobile;

	private String username;

	private String email;

	private String address;

	private Integer sex;
	
	private String sexVal;
	
	private String createDate;
	
	private Integer userType;
	
	private String userTypeName;

	/**
	 * 推荐人
	 */
	private String recommenderMobile;
	
	private String recommenderUsername;

	private Integer state;// 1.正常生效，2.失效，3.删除

	private String stateVal;
	
	private Integer agreementType;
	
	private String agreementTypeName;
	
	private Double agreementValue;

	public UserJson(Long id, String account, String mobile, String username, String email,
			String address, Integer sex, String sexVal, String createDate, String recommenderMobile,
			String recommenderUsername, Integer state, String stateVal,Integer agreementType,String agreementTypeName,Double agreementValue,Integer userType,String userTypeName) {
		super();
		this.id = id;
		this.account = account;
		this.mobile = mobile;
		this.username = username;
		this.email = email;
		this.address = address;
		this.sex = sex;
		this.sexVal = sexVal;
		this.createDate = createDate;
		this.recommenderMobile = recommenderMobile;
		this.recommenderUsername = recommenderUsername;
		this.state = state;
		this.stateVal = stateVal;
		this.agreementType = agreementType;
		this.agreementTypeName = agreementTypeName;
		this.agreementValue = agreementValue;
		this.userType = userType;
		this.userTypeName = userTypeName;
	}
	
	public UserJson(User user,UserAgreement agreement){
		
		this(user.getId(), 
			user.getAccount(), 
			user.getMobile(), 
			user.getUsername(), 
			user.getEmail(),
			user.getAddress(), 
			user.getSex(), 
			GlobalConstants.getSexVal(user.getSex()), 
			DateUtils.getDateString(user.getCreateDate()),
			user.getRecommender() == null ? "" : user.getRecommender().getMobile(),
			user.getRecommender() == null ? "" : user.getRecommender().getUsername(), 
			user.getState(), 
			GlobalConstants.getUserStateVal(user.getState()),
			agreement == null ? null : agreement.getAgreementType(),
			GlobalConstants.getUserAgreementTypeName(agreement == null ? null : agreement.getAgreementType()),
			agreement == null ? null : agreement.getAgreementValue(),
			user.getUserType(),
			GlobalConstants.getUserTypeName(user.getUserType()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSexVal() {
		return sexVal;
	}

	public void setSexVal(String sexVal) {
		this.sexVal = sexVal;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRecommenderMobile() {
		return recommenderMobile;
	}

	public void setRecommenderMobile(String recommenderMobile) {
		this.recommenderMobile = recommenderMobile;
	}

	public String getRecommenderUsername() {
		return recommenderUsername;
	}

	public void setRecommenderUsername(String recommenderUsername) {
		this.recommenderUsername = recommenderUsername;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateVal() {
		return stateVal;
	}

	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
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

	/**
	 * @return the userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return the userTypeName
	 */
	public String getUserTypeName() {
		return userTypeName;
	}

	/**
	 * @param userTypeName the userTypeName to set
	 */
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	/**
	 * @return the agreementTypeName
	 */
	public String getAgreementTypeName() {
		return agreementTypeName;
	}

	/**
	 * @param agreementTypeName the agreementTypeName to set
	 */
	public void setAgreementTypeName(String agreementTypeName) {
		this.agreementTypeName = agreementTypeName;
	}
}
