package com.yu.web.base.mapper;

import java.util.List;

import com.yu.base.mapper.GenericMapper;
import com.yu.web.base.model.Menu;
import com.yu.web.base.model.Role;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserDefaultView;
import com.yu.web.base.model.UserRole;

public interface UserMapper extends GenericMapper<User, Long> {
	
	List<Role> getUserRole(Long userId);
	
	List<Menu> getUserMenu(Long userId);
	
	void insertUserRole(UserRole userRole);
	
	UserDefaultView getUserDefaultView(Long userId);
}