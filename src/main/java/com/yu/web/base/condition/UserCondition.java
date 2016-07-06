package com.yu.web.base.condition;

import java.util.List;

import com.yu.base.condition.BaseCondition;

public class UserCondition extends BaseCondition{
	private Long id;
	private String account;
	private String password;
	private String username;
	private String mobile;
	/**
	 * 推荐人ID
	 */
	private List<Long> recommenderIds;
	
	private List<Integer>  userTypes;
	
	private String createDateStart;
	
	private String createDateEnd;
	
	private String state;
	
	private String userType;
	
	private String queryUserTypes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCreateDateStart() {
		return createDateStart;
	}
	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}
	public String getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public List<Long> getRecommenderIds() {
		return recommenderIds;
	}
	public void setRecommenderIds(List<Long> recommenderIds) {
		this.recommenderIds = recommenderIds;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the userTypes
	 */
	public List<Integer> getUserTypes() {
		return userTypes;
	}
	/**
	 * @param userTypes the userTypes to set
	 */
	public void setUserTypes(List<Integer> userTypes) {
		this.userTypes = userTypes;
	}
	public String getQueryUserTypes() {
		return queryUserTypes;
	}
	public void setQueryUserTypes(String queryUserTypes) {
		this.queryUserTypes = queryUserTypes;
	}
	
	
}
