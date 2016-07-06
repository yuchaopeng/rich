package com.yu.web.base.model;

import java.util.Date;

public class WebSiteConfig {
	private Long id;
	private String webSiteName;
	private Double commRate;
	private String commRateStr;
	private Date createDate;
	private Date lastUpdateDate;
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
	 * @return the webSiteName
	 */
	public String getWebSiteName() {
		return webSiteName;
	}
	/**
	 * @param webSiteName the webSiteName to set
	 */
	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}
	/**
	 * @return the commRate
	 */
	public Double getCommRate() {
		return commRate;
	}
	/**
	 * @param commRate the commRate to set
	 */
	public void setCommRate(Double commRate) {
		this.commRate = commRate;
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
	 * @return the commRateStr
	 */
	public String getCommRateStr() {
		if(this.commRate != null){
			String commRateStr = String.valueOf(this.commRate);
			if("0".equals(commRateStr.substring(commRateStr.indexOf(".")+1))){
				this.commRateStr = commRateStr.substring(0, commRateStr.indexOf("."));
			}else{
				this.commRateStr = commRateStr;
			}
		}
		return commRateStr;
	}
	/**
	 * @param commRateStr the commRateStr to set
	 */
	public void setCommRateStr(String commRateStr) {
		this.commRateStr = commRateStr;
	}
	
}
