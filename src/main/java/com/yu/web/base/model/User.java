package com.yu.web.base.model;

import java.util.Date;

/**
 * 用户
 * @author yuchaopeng
 * @since 2015-11-08 12:49
 **/
public class User {
    private Long id;
    
    private String account;
    
    private String mobile;
    
    private String username;

    private String password;
    
    private String email;
    
    private String address;
    
    private Integer sex;
    
    private Integer userType;//0.普通用户，1.管理员，2.运营用户，3.门店职员，4.专家
    
    /**
     * 推荐人
     */
    private User recommender;
    
    private Integer state;//1.正常生效，2.失效，3.删除

    private Date createDate;
    
    private Date lastUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getRecommender() {
		return recommender;
	}

	public void setRecommender(User recommender) {
		this.recommender = recommender;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * @return the userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}