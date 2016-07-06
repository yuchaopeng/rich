package com.yu.web.base.json;

import com.yu.base.util.DateUtils;
import com.yu.web.base.model.MatchPlayMethod;

public class MatchPlayMethodJson {
	private Long id;
	private String methodName;
	private String description;
	private String createDateStr;
	private String lastUpdateDateStr;
	
	/**
	 * 是否被赛事所使用
	 */
	private boolean used;
	
	

	public MatchPlayMethodJson(MatchPlayMethod matchPlayMethod,boolean used) {
		this.id = matchPlayMethod.getId();
		this.methodName = matchPlayMethod.getMethodName();
		this.description = matchPlayMethod.getDescription();
		this.createDateStr = DateUtils.getDateString(matchPlayMethod.getCreateDate());
		this.lastUpdateDateStr = DateUtils.getDateString(matchPlayMethod.getLastUpdateDate());
		this.used = used;
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
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

	/**
	 * @return the used
	 */
	public boolean isUsed() {
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}


	
}
