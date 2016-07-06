package com.yu.web.base.service;

import com.yu.base.service.GenericService;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserDefaultView;

/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserService extends GenericService<User, Long> {


    /**
     * 用户登录
     * @param account 登陆账号
     * @param password 登陆密码
     * @return
     */
    CurrentUserInfo login(String mobile,String password);
    
    UserDefaultView getUserDefaultView(Long userId);
}
