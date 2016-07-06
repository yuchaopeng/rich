package com.yu.web.base.json;

import com.yu.base.util.DateUtils;
import com.yu.web.base.constants.GlobalConstants;
import com.yu.web.base.model.Plan;

public class PlanJson {
	private Long id;
	private String planName;
	private Long expertId;
	private String expertName;
	private String expertMobile;
	private Integer first;
	private Integer second;
	private Integer third;
	private Integer fourth;
	private String firstStr;
	private String secondStr;
	private String thirdStr;
	private String fourthStr;
	private Double winRate;
	private Integer state;
	private String stateVal;
	private Long creatorId;
	private String creatorName;
	private String createDate;
	private String lastUpdateDate;
	
	public PlanJson(Long id, String planName, Long expertId, String expertName,String expertMobile, Integer first, Integer second,
			Integer third, Integer fourth,String firstStr, String secondStr, String thirdStr, String fourthStr, Integer state,String stateVal, Long creatorId, String creatorName, String createDate,
			String lastUpdateDate) {
		super();
		this.id = id;
		this.planName = planName;
		this.expertId = expertId;
		this.expertName = expertName;
		this.expertMobile = expertMobile;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.firstStr = firstStr;
		this.secondStr = secondStr;
		this.thirdStr = thirdStr;
		this.fourthStr = fourthStr;
		this.state = state;
		this.stateVal = stateVal;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public PlanJson(Plan plan){
		this(plan.getId(),
				plan.getPlanName(),
				plan.getExpert() == null ? null : plan.getExpert().getId(),
				plan.getExpert() == null ? null : plan.getExpert().getUsername(),
				plan.getExpert() == null ? null : plan.getExpert().getMobile(),
				plan.getFirst(),
				plan.getSecond(),
				plan.getThird(),
				plan.getFourth(),
				plan.getFirstStr(),
				plan.getSecondStr(),
				plan.getThirdStr(),
				plan.getFourthStr(),
				plan.getState(),
				GlobalConstants.getPlanStateVal(plan.getState()),
				plan.getCreator() == null ? null : plan.getCreator().getId(),
				plan.getCreator() == null ? null : plan.getCreator().getUsername(),
				DateUtils.getDateString(plan.getCreateDate()),
				DateUtils.getDateString(plan.getLastUpdateDate()));
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Long getExpertId() {
		return expertId;
	}
	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	/**
	 * @return the first
	 */
	public Integer getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(Integer first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public Integer getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}

	/**
	 * @return the third
	 */
	public Integer getThird() {
		return third;
	}

	/**
	 * @param third the third to set
	 */
	public void setThird(Integer third) {
		this.third = third;
	}

	/**
	 * @return the fourth
	 */
	public Integer getFourth() {
		return fourth;
	}

	/**
	 * @param fourth the fourth to set
	 */
	public void setFourth(Integer fourth) {
		this.fourth = fourth;
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getStateVal() {
		return stateVal;
	}

	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
	}

	public String getExpertMobile() {
		return expertMobile;
	}

	public void setExpertMobile(String expertMobile) {
		this.expertMobile = expertMobile;
	}

	public Double getWinRate() {
		return winRate;
	}

	public void setWinRate(Double winRate) {
		this.winRate = winRate;
	}

	public String getFirstStr() {
		return firstStr;
	}

	public void setFirstStr(String firstStr) {
		this.firstStr = firstStr;
	}

	public String getSecondStr() {
		return secondStr;
	}

	public void setSecondStr(String secondStr) {
		this.secondStr = secondStr;
	}

	public String getThirdStr() {
		return thirdStr;
	}

	public void setThirdStr(String thirdStr) {
		this.thirdStr = thirdStr;
	}

	public String getFourthStr() {
		return fourthStr;
	}

	public void setFourthStr(String fourthStr) {
		this.fourthStr = fourthStr;
	}
	
}
