package com.yu.web.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.page.Page;
import com.yu.base.page.PageBean;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.condition.UserAccountCondition;
import com.yu.web.base.mapper.UserAccountMapper;
import com.yu.web.base.model.UserAccount;
import com.yu.web.base.model.UserAccountDetail;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.UserAccountService;
import com.yu.web.base.util.IDBuildUtil;

@Service
public class UserAccountServiceImpl extends GenericServiceImpl<UserAccount, String> implements UserAccountService {

	@Resource
    private UserAccountMapper userAccountMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	public UserAccount getUserAccount(Long userId){
		UserAccountCondition condition = new UserAccountCondition();
		condition.setUserId(userId);
		List<UserAccount> userAccounts = userAccountMapper.selectList(condition);
		if(userAccounts != null && userAccounts.size() > 0){
			return userAccounts.get(0);
		}
		return null;
	}
	
	public Page<UserAccountDetail> selectUserAccountDetail4Page(PageBean pageBean){
		Page<UserAccountDetail> page = new Page<UserAccountDetail>(pageBean);
		List<UserAccountDetail> results = userAccountMapper.selectUserAccountDetail4Page(page);
		page.setResults(results);
		return page;
	}
	
	@Override
	public void userWithdraw(Long userId, Integer withdrawAmount) throws Exception {
		UserAccount userAccount = getUserAccount(userId);
		if(userAccount == null){
			throw new Exception("用户账户不存在.");
		}
		userAccount = userAccountMapper.selectByIdForUpdate(userAccount.getId());
		Integer balance = userAccount.getBalance();
		if(balance < withdrawAmount){
			throw new Exception("您的余额不足."); 
		}
		balance -= withdrawAmount;
		userAccount.setBalance(balance);
		
		Integer totalWithdraw = userAccount.getTotalWithdraw();
		totalWithdraw += withdrawAmount;
		userAccount.setTotalWithdraw(totalWithdraw);
		userAccountMapper.update(userAccount);
	}
	
	public void userWithdrawResult(Long userId,Integer withdrawAmount,Integer actualWithdrawAmount) throws Exception{
		UserAccount userAccount = getUserAccount(userId);
		userAccount = userAccountMapper.selectByIdForUpdate(userAccount.getId());
		Integer totalWithdraw = userAccount.getTotalWithdraw();
		totalWithdraw -= withdrawAmount;
		userAccount.setTotalWithdraw(totalWithdraw);
		
		//创建用户提现记录
		UserAccountDetail detail = new UserAccountDetail();
		String detailId = IDBuildUtil.getUserAccountDetailId();
		detail.setId(detailId);
		detail.setUserId(String.valueOf(userId));
		detail.setAccountId(String.valueOf(userAccount.getId()));
		String type = "WITHDRAW_R";
		detail.setType(type);
		detail.setDirection(1);
		detail.setToAccountId("");
		detail.setToUserId("");
		detail.setAmount(actualWithdrawAmount);
		detail.setBalance(userAccount.getBalance());
		detail.setRemark("");
		userAccountMapper.insertUserAccountDetail(detail);
		
		if(actualWithdrawAmount < withdrawAmount){
			Integer balance = userAccount.getBalance();
			balance += (withdrawAmount - actualWithdrawAmount);
			userAccount.setBalance(balance);
			
			//创建用户提现记录
			UserAccountDetail d = new UserAccountDetail();
			String dId = IDBuildUtil.getUserAccountDetailId();
			d.setId(dId);
			d.setUserId(String.valueOf(userId));
			d.setAccountId(String.valueOf(userAccount.getId()));
			String dtype = "WITHDRAW_RETURN_D";
			d.setType(dtype);
			d.setDirection(0);
			d.setFromAccountId("");
			d.setFromUserId("");
			d.setAmount(withdrawAmount - actualWithdrawAmount);
			d.setBalance(balance);
			d.setRemark("");
			userAccountMapper.insertUserAccountDetail(d);
		}
		userAccountMapper.update(userAccount);
	}
	
	/**
	 * 处理投注时，投注中字段
	 * @param userId
	 * @param flag 0 增，1 减
	 * @param amount
	 */
	public void handleUserBetting(Long userId,int flag,Integer amount){
		UserAccount userAccount = getUserAccount(userId);
		userAccount = userAccountMapper.selectByIdForUpdate(userAccount.getId());
		Integer bettingAmount = userAccount.getTotalBetting();
		if(flag == 0){
			bettingAmount += amount;
		}else if(flag == 1){
			bettingAmount -= amount;
		}
		userAccount.setTotalBetting(bettingAmount);
		userAccountMapper.update(userAccount);
		
	}
	
	public void handleUserTotalAdd(Long userId,Integer amount){
		UserAccount userAccount = getUserAccount(userId);
		userAccount = userAccountMapper.selectByIdForUpdate(userAccount.getId());
		Integer totalAdd = userAccount.getTotalAdd();
		totalAdd += amount;
		userAccount.setTotalAdd(totalAdd);
		userAccountMapper.update(userAccount);
	}
	
	public void handleUserTotalDivided(Long userId,Integer amount){
		UserAccount userAccount = getUserAccount(userId);
		userAccount = userAccountMapper.selectByIdForUpdate(userAccount.getId());
		Integer totalDivided = userAccount.getTotalDivided();
		totalDivided += amount;
		userAccount.setTotalDivided(totalDivided);
		userAccountMapper.update(userAccount);
	}

	public void handleUserTotalWin(Long userId,Integer amount){
		UserAccount userAccount = getUserAccount(userId);
		userAccount = userAccountMapper.selectByIdForUpdate(userAccount.getId());
		Integer totalWin = userAccount.getTotalWin();
		totalWin += amount;
		userAccount.setTotalWin(totalWin);
		userAccountMapper.update(userAccount);
	}
	
	@Override
	public GenericMapper<UserAccount, String> getMapper() {
		return userAccountMapper;
	}


}
