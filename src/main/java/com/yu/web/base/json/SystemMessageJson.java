package com.yu.web.base.json;

import java.util.Date;

import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.SystemMessage;
import com.yu.web.base.model.User;

public class SystemMessageJson {
	private Long id;
	private Long userId;
	private String userName;
	private String title;
	private String content;
	private Integer state;
	private String stateVal;
	private Date createDate;
	private Date publishDate;
	private Date lastUpdateDate;
	private String createDateStr;
	private String publishDateStr;
	private String lastUpdateDateStr;
	
	
	
	public SystemMessageJson(SystemMessage systemMessage) {
		this(systemMessage.getId(),
				systemMessage.getCreateUser() == null ? null : systemMessage.getCreateUser().getId(),
				systemMessage.getCreateUser() == null ? null : systemMessage.getCreateUser().getUsername(),
				systemMessage.getTitle(),
				systemMessage.getContent(),
				systemMessage.getState(),
				GlobalConstants.getSystemMessageStateVal(systemMessage.getState()),
				systemMessage.getCreateDate(),
				systemMessage.getPublishDate(),
				systemMessage.getLastUpdateDate(),
				DateUtils.getDateString(systemMessage.getCreateDate()),
				DateUtils.getDateString(systemMessage.getPublishDate()),
				DateUtils.getDateString(systemMessage.getLastUpdateDate())
		);
	}
	
	public SystemMessageJson(Long id, Long userId, String userName, String title, String content, Integer state,
			String stateVal, Date createDate, Date publishDate, Date lastUpdateDate, String createDateStr,
			String publishDateStr, String lastUpdateDateStr) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.title = title;
		this.content = content;
		this.state = state;
		this.stateVal = stateVal;
		this.createDate = createDate;
		this.publishDate = publishDate;
		this.lastUpdateDate = lastUpdateDate;
		this.createDateStr = createDateStr;
		this.publishDateStr = publishDateStr;
		this.lastUpdateDateStr = lastUpdateDateStr;
	}
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the stateVal
	 */
	public String getStateVal() {
		return stateVal;
	}
	/**
	 * @param stateVal the stateVal to set
	 */
	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the publishDate
	 */
	public Date getPublishDate() {
		return publishDate;
	}
	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * @return the createDateStr
	 */
	public String getCreateDateStr() {
		return createDateStr;
	}
	/**
	 * @param createDateStr the createDateStr to set
	 */
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	/**
	 * @return the publishDateStr
	 */
	public String getPublishDateStr() {
		return publishDateStr;
	}
	/**
	 * @param publishDateStr the publishDateStr to set
	 */
	public void setPublishDateStr(String publishDateStr) {
		this.publishDateStr = publishDateStr;
	}
	/**
	 * @return the lastUpdateDateStr
	 */
	public String getLastUpdateDateStr() {
		return lastUpdateDateStr;
	}
	/**
	 * @param lastUpdateDateStr the lastUpdateDateStr to set
	 */
	public void setLastUpdateDateStr(String lastUpdateDateStr) {
		this.lastUpdateDateStr = lastUpdateDateStr;
	}
	
	
}
