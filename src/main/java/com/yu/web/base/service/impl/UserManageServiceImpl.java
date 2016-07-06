package com.yu.web.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.base.util.DateUtils;
import com.yu.base.util.MD5;
import com.yu.web.base.condition.RoleCondition;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.mapper.RoleMapper;
import com.yu.web.base.mapper.UserAccountMapper;
import com.yu.web.base.mapper.UserMapper;
import com.yu.web.base.model.Role;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserAccount;
import com.yu.web.base.model.UserRole;
import com.yu.web.base.service.SequenceService;
import com.yu.web.base.service.UserManageService;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserManageServiceImpl extends GenericServiceImpl<User, Long> implements UserManageService {

	@Resource
    private UserMapper userMapper;
	
	@Resource
	private UserAccountMapper userAccountMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private SequenceService sequenceService;
	
	@Override
	public GenericMapper<User, Long> getMapper() {
		return userMapper;
	}
	
	public User createUserInfo(User user) throws Exception{
		//1.创建用户
		user = buildUser(user);
		insert(user);
		
		//2.创建账户
		UserAccount account = buildUserAccount(user);
		userAccountMapper.insert(account);
		
		//3.赋予每种类型的用户对应的权限
		List<String> userRoleType = GlobalConstants.getUserTypeRole(user.getUserType());
		for(String roleCode : userRoleType){
			RoleCondition condition = new RoleCondition();
			condition.setRoleCode(roleCode);
			List<Role> roles = roleMapper.selectList(condition);
			for(Role role : roles){
				UserRole userRole = new UserRole();
				int userRoleId = sequenceService.get("userRole");
				userRole.setId(Long.valueOf(userRoleId));
				userRole.setUser(user);
				userRole.setRole(role);
				userMapper.insertUserRole(userRole);
			}
		}
		return user;
	}

	private User buildUser(User user) throws Exception{
		int id = sequenceService.get("user");
		user.setId(Long.valueOf(id));
		if(!StringUtils.isEmpty(user.getPassword())){
			user.setPassword(MD5.md5(user.getPassword()));
		}
		user.setState(GlobalConstants.USER_STATE_INVALID);
		user.setCreateDate(DateUtils.getNowTime());
		user.setLastUpdateDate(DateUtils.getNowTime());
		return user;
	}
	
	private UserAccount buildUserAccount(User user){
		UserAccount account = new UserAccount();
		int id = sequenceService.get("userAccount");
		account.setId(String.valueOf(id));
		account.setUser(user);
		account.setBalance(0);
		account.setTotalAdd(0);
		account.setTotalBetting(0);
		account.setTotalDivided(0);
		account.setTotalWin(0);
		account.setTotalWithdraw(0);
		account.setAccountType(GlobalConstants.USER_ACCOUNT_TYPE_LOTTERY);
		account.setState(GlobalConstants.USER_ACCOUNT_STATE_NORMAL);
		return account;
	}
	
}
