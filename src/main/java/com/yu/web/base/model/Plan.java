package com.yu.web.base.model;

import java.util.Date;

import com.yu.web.base.util.AmountUtil;

public class Plan {
	private Long id;
	private String planName;
	private User expert;//专家
	private Integer first;
	private String firstStr;
	private Integer second;
	private String secondStr;
	private Integer third;
	private String thirdStr;
	private Integer fourth;
	private String fourthStr;
	private Integer state;
	private User creator;
	private Date createDate;
	private Date lastUpdateDate;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the planName
	 */
	public String getPlanName() {
		return planName;
	}
	/**
	 * @param planName the planName to set
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	/**
	 * @return the expert
	 */
	public User getExpert() {
		return expert;
	}
	/**
	 * @param expert the expert to set
	 */
	public void setExpert(User expert) {
		this.expert = expert;
	}
	/**
	 * @return the first
	 */
	public Integer getFirst() {
		return first;
	}
	/**
	 * @param first the first to set
	 */
	public void setFirst(Integer first) {
		this.first = first;
	}
	/**
	 * @return the second
	 */
	public Integer getSecond() {
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}
	/**
	 * @return the third
	 */
	public Integer getThird() {
		return third;
	}
	/**
	 * @param third the third to set
	 */
	public void setThird(Integer third) {
		this.third = third;
	}
	/**
	 * @return the fourth
	 */
	public Integer getFourth() {
		return fourth;
	}
	/**
	 * @param fourth the fourth to set
	 */
	public void setFourth(Integer fourth) {
		this.fourth = fourth;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public String getFirstStr() {
		if(this.first != null){
			this.firstStr = AmountUtil.changeF2Y(this.first);
		}
		return firstStr;
	}
	public void setFirstStr(String firstStr) {
		this.firstStr = firstStr;
	}
	public String getSecondStr() {
		if(this.second != null){
			this.secondStr = AmountUtil.changeF2Y(this.second);
		}
		return secondStr;
	}
	public void setSecondStr(String secondStr) {
		this.secondStr = secondStr;
	}
	public String getThirdStr() {
		if(this.third != null){
			this.thirdStr = AmountUtil.changeF2Y(this.third);
		}
		return thirdStr;
	}
	public void setThirdStr(String thirdStr) {
		this.thirdStr = thirdStr;
	}
	public String getFourthStr() {
		if(this.fourth != null){
			this.fourthStr = AmountUtil.changeF2Y(this.fourth);
		}
		return fourthStr;
	}
	public void setFourthStr(String fourthStr) {
		this.fourthStr = fourthStr;
	}
	
	
}
