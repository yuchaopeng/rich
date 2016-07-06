package com.yu.web.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yu.base.mapper.GenericMapper;
import com.yu.base.service.impl.GenericServiceImpl;
import com.yu.web.base.condition.UserCondition;
import com.yu.web.base.mapper.UserMapper;
import com.yu.web.base.model.CurrentUserInfo;
import com.yu.web.base.model.Menu;
import com.yu.web.base.model.Role;
import com.yu.web.base.model.TreeMenu;
import com.yu.web.base.model.User;
import com.yu.web.base.model.UserDefaultView;
import com.yu.web.base.service.UserService;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public GenericMapper<User, Long> getMapper() {
        return userMapper;
    }

	@Override
	public CurrentUserInfo login(String mobile, String password) {
		UserCondition condition = new UserCondition();
		condition.setMobile(mobile);
		condition.setPassword(password);
		List<User> users = userMapper.selectList(condition);
		if(users == null || users.size() == 0){
			return null;
		}
		User user = users.get(0);
		List<Role> roles = userMapper.getUserRole(user.getId());
		List<Menu> menus = userMapper.getUserMenu(user.getId());
		List<TreeMenu> treeMenus = new ArrayList<TreeMenu>();
		for(Menu menu : menus){
			if(menu.getParent() == null){
				TreeMenu root = new TreeMenu();
				root.setId(menu.getId());
				root.setText(menu.getMenuName());
				root.setIconCls(menu.getIconCls());
				Map<String,Object> attributes = new HashMap<String,Object>();
				attributes.put("url", menu.getUrl());
				
				root.setAttributes(attributes);
				buildTreeMenu(root, menus);
				treeMenus.add(root);
			}
		}
		CurrentUserInfo userInfo = new CurrentUserInfo(user, roles, menus, treeMenus);
		return userInfo;
	}
	
	private void buildTreeMenu(TreeMenu parent,List<Menu> menus){
		for(Menu menu : menus){
			if(menu.getParent() != null && menu.getParent().getId().longValue() == parent.getId().longValue()){
				TreeMenu treeMenu = new TreeMenu();
				
				treeMenu.setId(menu.getId());
				treeMenu.setText(menu.getMenuName());
				treeMenu.setIconCls(menu.getIconCls());
				Map<String,Object> attributes = new HashMap<String,Object>();
				attributes.put("url", menu.getUrl());
				treeMenu.setAttributes(attributes);
				
				parent.getChildren().add(treeMenu);
				buildTreeMenu(treeMenu, menus);
			}
		}
	}

	@Override
	public UserDefaultView getUserDefaultView(Long userId) {
		return userMapper.getUserDefaultView(userId);
	}
}
