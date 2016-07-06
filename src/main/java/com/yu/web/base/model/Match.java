package com.yu.web.base.model;

import java.util.Date;

/**
 * 赛事
 * @author yu
 *
 */
public class Match {
	private Long id;
	private Plan plan;
	private User creator;
	/**
	 * 专家分成
	 */
	private Integer expertAmount;
	private Integer state;
	private String matchDate;
	private Integer matchResult;
	/**赔率*/
	private Double odds;
	private Date createDate;
	private Date lastUpdateDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(Integer matchResult) {
		this.matchResult = matchResult;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Double getOdds() {
		return odds;
	}
	public void setOdds(Double odds) {
		this.odds = odds;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Integer getExpertAmount() {
		return expertAmount;
	}
	public void setExpertAmount(Integer expertAmount) {
		this.expertAmount = expertAmount;
	}
	
}
