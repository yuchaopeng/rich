package com.yu.web.base.model;

import java.util.Date;

public class MatchDetail {
	private Long id;
	private Match match;
	/**赛事名称*/
	private String matchName;
	/**玩法*/
	private MatchPlayMethod playMethod;
	/**主队*/
	private String homeTeam;
	/**客队*/
	private String visiteTeam;
	/**投注选项*/
	private String bettingOption;
	/**胜出选项*/
	private String winOption;
	/**赛果*/
	private Integer matchResult;
	
	private Date createDate;
	private Date lastUpdateDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public MatchPlayMethod getPlayMethod() {
		return playMethod;
	}
	public void setPlayMethod(MatchPlayMethod playMethod) {
		this.playMethod = playMethod;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getVisiteTeam() {
		return visiteTeam;
	}
	public void setVisiteTeam(String visiteTeam) {
		this.visiteTeam = visiteTeam;
	}
	public String getBettingOption() {
		return bettingOption;
	}
	public void setBettingOption(String bettingOption) {
		this.bettingOption = bettingOption;
	}
	public String getWinOption() {
		return winOption;
	}
	public void setWinOption(String winOption) {
		this.winOption = winOption;
	}
	public Integer getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(Integer matchResult) {
		this.matchResult = matchResult;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
}
