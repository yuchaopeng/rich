package com.yu.web.base.mapper;

import java.util.List;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.page.Page;
import com.yu.web.base.condition.UserAccountCondition;
import com.yu.web.base.model.UserAccount;
import com.yu.web.base.model.UserAccountDetail;
import com.yu.web.base.model.UserAccountDetailGroup;

public interface UserAccountMapper extends GenericMapper<UserAccount, String> {
	UserAccount selectByIdForUpdate(String id);
	
	void insertUserAccountDetail(UserAccountDetail detail);
	
	List<UserAccountDetail> selectUserAccountDetail4Page(Page<UserAccountDetail> page);
	
	List<UserAccountDetail> selectUserAccountDetailList(UserAccountCondition condition);
	
	List<UserAccountDetailGroup> selectUserAccountDetailGroup(UserAccountCondition condition);
}