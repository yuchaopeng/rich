package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.User;

/**
 * 用户管理
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserManageService extends GenericService<User, Long> {
	
	/**
	 * 创建用户信息，包含创建账户
	 * @param user
	 * @return
	 */
	User createUserInfo(User user) throws Exception;
	
}


