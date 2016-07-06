package com.yu.web.base.form;

import com.yu.web.base.model.UserAccount;
import com.yu.web.base.util.AmountUtil;

public class UserAccountForm {
	private UserAccount userAccount;
	
	private String balance = "0";//余额
	private String totalAdd = "0";//总存入金额
	private String totalWin = "0";//总中奖金额
	private String totalDivided = "0";//总分成金额
	private String totalBetting = "0";//投注中
	private String totalWithdraw = "0";//提现中
	
	public UserAccountForm(UserAccount userAccount){
		this.userAccount = userAccount;
		if(userAccount != null){
			this.balance = AmountUtil.changeF2Y(userAccount.getBalance());
			this.totalAdd = AmountUtil.changeF2Y(userAccount.getTotalAdd());
			this.totalWin = AmountUtil.changeF2Y(userAccount.getTotalWin());
			this.totalDivided = AmountUtil.changeF2Y(userAccount.getTotalDivided());
			this.totalBetting = AmountUtil.changeF2Y(userAccount.getTotalBetting());
			this.totalWithdraw = AmountUtil.changeF2Y(userAccount.getTotalWithdraw());
		}
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTotalAdd() {
		return totalAdd;
	}

	public void setTotalAdd(String totalAdd) {
		this.totalAdd = totalAdd;
	}

	public String getTotalWin() {
		return totalWin;
	}

	public void setTotalWin(String totalWin) {
		this.totalWin = totalWin;
	}

	public String getTotalDivided() {
		return totalDivided;
	}

	public void setTotalDivided(String totalDivided) {
		this.totalDivided = totalDivided;
	}

	public String getTotalBetting() {
		return totalBetting;
	}

	public void setTotalBetting(String totalBetting) {
		this.totalBetting = totalBetting;
	}

	public String getTotalWithdraw() {
		return totalWithdraw;
	}

	public void setTotalWithdraw(String totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}
	
	
}
