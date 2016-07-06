package com.yu.web.base.model;

public class UserRole {
	private Long id;
	private User user;
	private Role role;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
