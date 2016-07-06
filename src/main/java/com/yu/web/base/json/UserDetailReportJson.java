package com.yu.web.base.json;

import java.util.Date;

import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.UserDetailReport;

public class UserDetailReportJson {
	private Long id;
	private Long userId;
	private String userName;
	private String mobile;
	private Integer userType;
	private String userTypeName;
	private Long recommenderId;
	private String recommenderName;
	private Long planId;
	private String planName;
	private Date reportDate;
	private String reportDateStr;
	private Integer bettingAmount;
	private String bettingAmountStr;
	private Integer commissionAmount;
	private String commissionAmountStr;
	private Integer recommenderAmount;
	private String recommenderAmountStr;
	private Integer recommenderOtherAmount;
	private String recommenderOtherAmountStr;
	private Integer expertAmount;
	private String expertAmountStr;
	private Integer winAmount;
	private String winAmountStr;
	private Date createDate;
	private String createDateStr;
	private Date lastUpdateDate;
	private String lastUpdateDateStr;
	
	public UserDetailReportJson(UserDetailReport report) {
		this(report.getId(),
				report.getUser() == null ? null : report.getUser().getId(),
				report.getUser() == null ? null : report.getUser().getUsername(),
				report.getUser() == null ? null : report.getUser().getMobile(),
				report.getUser() == null ? null : report.getUser().getUserType(),
				GlobalConstants.getUserTypeName(report.getUser() == null ? null : report.getUser().getUserType()),
				report.getRecommender() == null ? null : report.getRecommender().getId(),
				report.getRecommender() == null ? null : report.getRecommender().getUsername(),
				report.getPlan() == null ? null : report.getPlan().getId(),
				report.getPlan() == null ? null : report.getPlan().getPlanName(),
				report.getReportDate(),
				DateUtils.getSimpleDateString(report.getReportDate()),
				report.getBettingAmount(),
				report.getBettingAmountStr(),
				report.getCommissionAmount(),
				report.getCommissionAmountStr(),
				report.getRecommenderAmount(),
				report.getRecommenderAmountStr(),
				report.getRecommenderOtherAmount(),
				report.getRecommenderOtherAmountStr(),
				report.getExpertAmount(),
				report.getExpertAmountStr(),
				report.getWinAmount(),
				report.getWinAmountStr(),
				report.getCreateDate(),
				DateUtils.getDateString(report.getCreateDate()),
				report.getLastUpdateDate(),
				DateUtils.getDateString(report.getLastUpdateDate())
				);
	}
	
	public UserDetailReportJson(Long id, Long userId, String userName,String mobile,Integer userType,String userTypeName, Long recommenderId, String recommenderName,
			Long planId, String planName, Date reportDate, String reportDateStr, Integer bettingAmount,
			String bettingAmountStr, Integer commissionAmount, String commissionAmountStr, Integer recommenderAmount,
			String recommenderAmountStr, Integer recommenderOtherAmount, String recommenderOtherAmountStr,
			Integer expertAmount, String expertAmountStr,Integer winAmount,String winAmountStr, Date createDate, String createDateStr, Date lastUpdateDate,
			String lastUpdateDateStr) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.mobile = mobile;
		this.userType = userType;
		this.userTypeName = userTypeName;
		this.recommenderId = recommenderId;
		this.recommenderName = recommenderName;
		this.planId = planId;
		this.planName = planName;
		this.reportDate = reportDate;
		this.reportDateStr = reportDateStr;
		this.bettingAmount = bettingAmount;
		this.bettingAmountStr = bettingAmountStr;
		this.commissionAmount = commissionAmount;
		this.commissionAmountStr = commissionAmountStr;
		this.recommenderAmount = recommenderAmount;
		this.recommenderAmountStr = recommenderAmountStr;
		this.recommenderOtherAmount = recommenderOtherAmount;
		this.recommenderOtherAmountStr = recommenderOtherAmountStr;
		this.expertAmount = expertAmount;
		this.expertAmountStr = expertAmountStr;
		this.winAmount = winAmount;
		this.winAmountStr = winAmountStr;
		this.createDate = createDate;
		this.createDateStr = createDateStr;
		this.lastUpdateDate = lastUpdateDate;
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
	 * @return the recommenderId
	 */
	public Long getRecommenderId() {
		return recommenderId;
	}
	/**
	 * @param recommenderId the recommenderId to set
	 */
	public void setRecommenderId(Long recommenderId) {
		this.recommenderId = recommenderId;
	}
	/**
	 * @return the recommenderName
	 */
	public String getRecommenderName() {
		return recommenderName;
	}
	/**
	 * @param recommenderName the recommenderName to set
	 */
	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}
	/**
	 * @return the planId
	 */
	public Long getPlanId() {
		return planId;
	}
	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	/**
	 * @return the planName
	 */
	public String getPlanName() {
		return planName;
	}
	/**
	 * @param planName the planName to set
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}
	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	/**
	 * @return the reportDateStr
	 */
	public String getReportDateStr() {
		return reportDateStr;
	}
	/**
	 * @param reportDateStr the reportDateStr to set
	 */
	public void setReportDateStr(String reportDateStr) {
		this.reportDateStr = reportDateStr;
	}
	/**
	 * @return the bettingAmount
	 */
	public Integer getBettingAmount() {
		return bettingAmount;
	}
	/**
	 * @param bettingAmount the bettingAmount to set
	 */
	public void setBettingAmount(Integer bettingAmount) {
		this.bettingAmount = bettingAmount;
	}
	/**
	 * @return the bettingAmountStr
	 */
	public String getBettingAmountStr() {
		return bettingAmountStr;
	}
	/**
	 * @param bettingAmountStr the bettingAmountStr to set
	 */
	public void setBettingAmountStr(String bettingAmountStr) {
		this.bettingAmountStr = bettingAmountStr;
	}
	/**
	 * @return the commissionAmount
	 */
	public Integer getCommissionAmount() {
		return commissionAmount;
	}
	/**
	 * @param commissionAmount the commissionAmount to set
	 */
	public void setCommissionAmount(Integer commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	/**
	 * @return the commissionAmountStr
	 */
	public String getCommissionAmountStr() {
		return commissionAmountStr;
	}
	/**
	 * @param commissionAmountStr the commissionAmountStr to set
	 */
	public void setCommissionAmountStr(String commissionAmountStr) {
		this.commissionAmountStr = commissionAmountStr;
	}
	/**
	 * @return the recommenderAmount
	 */
	public Integer getRecommenderAmount() {
		return recommenderAmount;
	}
	/**
	 * @param recommenderAmount the recommenderAmount to set
	 */
	public void setRecommenderAmount(Integer recommenderAmount) {
		this.recommenderAmount = recommenderAmount;
	}
	/**
	 * @return the recommenderAmountStr
	 */
	public String getRecommenderAmountStr() {
		return recommenderAmountStr;
	}
	/**
	 * @param recommenderAmountStr the recommenderAmountStr to set
	 */
	public void setRecommenderAmountStr(String recommenderAmountStr) {
		this.recommenderAmountStr = recommenderAmountStr;
	}
	/**
	 * @return the recommenderOtherAmount
	 */
	public Integer getRecommenderOtherAmount() {
		return recommenderOtherAmount;
	}
	/**
	 * @param recommenderOtherAmount the recommenderOtherAmount to set
	 */
	public void setRecommenderOtherAmount(Integer recommenderOtherAmount) {
		this.recommenderOtherAmount = recommenderOtherAmount;
	}
	/**
	 * @return the recommenderOtherAmountStr
	 */
	public String getRecommenderOtherAmountStr() {
		return recommenderOtherAmountStr;
	}
	/**
	 * @param recommenderOtherAmountStr the recommenderOtherAmountStr to set
	 */
	public void setRecommenderOtherAmountStr(String recommenderOtherAmountStr) {
		this.recommenderOtherAmountStr = recommenderOtherAmountStr;
	}
	/**
	 * @return the expertAmount
	 */
	public Integer getExpertAmount() {
		return expertAmount;
	}
	/**
	 * @param expertAmount the expertAmount to set
	 */
	public void setExpertAmount(Integer expertAmount) {
		this.expertAmount = expertAmount;
	}
	/**
	 * @return the expertAmountStr
	 */
	public String getExpertAmountStr() {
		return expertAmountStr;
	}
	/**
	 * @param expertAmountStr the expertAmountStr to set
	 */
	public void setExpertAmountStr(String expertAmountStr) {
		this.expertAmountStr = expertAmountStr;
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

	public Integer getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(Integer winAmount) {
		this.winAmount = winAmount;
	}

	public String getWinAmountStr() {
		return winAmountStr;
	}

	public void setWinAmountStr(String winAmountStr) {
		this.winAmountStr = winAmountStr;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
