package com.yu.web.base.service;

import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.service.GenericService;
import com.yu.web.base.model.UserAccount;
import com.yu.web.base.model.UserAccountDetail;

/**
 * 用户管理
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserAccountService extends GenericService<UserAccount, String> {
	public UserAccount getUserAccount(Long userId);
	
	Page<UserAccountDetail> selectUserAccountDetail4Page(PageBean pageBean);
	
	void userWithdraw(Long userId,Integer withdrawAmount) throws Exception;
	
	void userWithdrawResult(Long userId,Integer withdrawAmount,Integer actualWithdrawAmount) throws Exception;
	
	void handleUserBetting(Long userId,int flag,Integer amount);
	
	void handleUserTotalAdd(Long userId,Integer amount);
	
	void handleUserTotalDivided(Long userId,Integer amount);
	
	void handleUserTotalWin(Long userId,Integer amount);
}


