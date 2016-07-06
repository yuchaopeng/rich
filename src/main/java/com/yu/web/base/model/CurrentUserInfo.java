package com.yu.web.base.model;

import java.util.List;

public class CurrentUserInfo {
	private User user;
	
	private List<Role> roles;
	
	private List<Menu> menus;
	
	private List<TreeMenu> treeMenus;
	
	
	public CurrentUserInfo(User user, List<Role> roles, List<Menu> menus, List<TreeMenu> treeMenus) {
		super();
		this.user = user;
		this.roles = roles;
		this.menus = menus;
		this.treeMenus = treeMenus;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<TreeMenu> getTreeMenus() {
		return treeMenus;
	}

	public void setTreeMenus(List<TreeMenu> treeMenus) {
		this.treeMenus = treeMenus;
	}
	
}
