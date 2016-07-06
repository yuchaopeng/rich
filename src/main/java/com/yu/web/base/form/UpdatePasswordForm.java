package com.yu.web.base.form;

public class UpdatePasswordForm {
	private Long userId;
	private String sourcePassword;
	private String newPassword;
	private String confirmPassword;
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the sourcePassword
	 */
	public String getSourcePassword() {
		return sourcePassword;
	}
	/**
	 * @param sourcePassword the sourcePassword to set
	 */
	public void setSourcePassword(String sourcePassword) {
		this.sourcePassword = sourcePassword;
	}
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
