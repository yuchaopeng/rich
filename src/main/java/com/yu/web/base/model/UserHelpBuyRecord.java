package com.yu.web.base.model;

import java.util.Date;

import com.yu.web.base.util.AmountUtil;

public class UserHelpBuyRecord {
	private Long id;
	private User user;
	private Plan plan;
	/**
	 * 起投金额
	 */
	private Integer startAmount;
	private String startAmountStr;
	/**
	 * 投注模式
	 */
	private Integer bettingModel;
	private Date createDate;
	private Date lastUpdateDate;
	private Integer state;
	/**
	 * 失败投注次数，成功后置为0
	 */
	private Integer continueLossNum;
	
	/**
	 * 总投注次数
	 */
	private Integer totalNum;
	
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the plan
	 */
	public Plan getPlan() {
		return plan;
	}
	/**
	 * @param plan the plan to set
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	/**
	 * @return the startAmount
	 */
	public Integer getStartAmount() {
		return startAmount;
	}
	/**
	 * @param startAmount the startAmount to set
	 */
	public void setStartAmount(Integer startAmount) {
		this.startAmount = startAmount;
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
	 * @return the continueLossNum
	 */
	public Integer getContinueLossNum() {
		return continueLossNum;
	}
	/**
	 * @param continueLossNum the continueLossNum to set
	 */
	public void setContinueLossNum(Integer continueLossNum) {
		this.continueLossNum = continueLossNum;
	}
	/**
	 * @return the totalNum
	 */
	public Integer getTotalNum() {
		return totalNum;
	}
	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * @return the bettingModel
	 */
	public Integer getBettingModel() {
		return bettingModel;
	}
	/**
	 * @param bettingModel the bettingModel to set
	 */
	public void setBettingModel(Integer bettingModel) {
		this.bettingModel = bettingModel;
	}
	public String getStartAmountStr() {
		if(this.startAmount != null){
			startAmountStr = AmountUtil.changeF2Y(this.startAmount);
		}
		return startAmountStr;
	}
	public void setStartAmountStr(String startAmountStr) {
		this.startAmountStr = startAmountStr;
	}
	
}
