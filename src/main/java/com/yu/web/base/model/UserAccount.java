package com.yu.web.base.model;

import java.util.Date;

public class UserAccount{
	private String id;
	private User user;
	private Integer accountType;//账户类型：彩票账户。
	private Integer balance;//余额
	private Integer totalAdd;//总存入金额
	private Integer totalWin;//总中奖金额
	private Integer totalDivided;//总分成金额
	private Integer totalBetting;//投注中
	private Integer totalWithdraw;//提现中
	private Integer state;//账户状态
	private Date createDate;
	private Date lastUpdateDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getTotalAdd() {
		return totalAdd;
	}
	public void setTotalAdd(Integer totalAdd) {
		this.totalAdd = totalAdd;
	}
	public Integer getTotalWin() {
		return totalWin;
	}
	public void setTotalWin(Integer totalWin) {
		this.totalWin = totalWin;
	}
	public Integer getTotalDivided() {
		return totalDivided;
	}
	public void setTotalDivided(Integer totalDivided) {
		this.totalDivided = totalDivided;
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
	/**
	 * @return the totalBetting
	 */
	public Integer getTotalBetting() {
		return totalBetting;
	}
	/**
	 * @param totalBetting the totalBetting to set
	 */
	public void setTotalBetting(Integer totalBetting) {
		this.totalBetting = totalBetting;
	}
	/**
	 * @return the totalWithdraw
	 */
	public Integer getTotalWithdraw() {
		return totalWithdraw;
	}
	/**
	 * @param totalWithdraw the totalWithdraw to set
	 */
	public void setTotalWithdraw(Integer totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
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
	
	
}
