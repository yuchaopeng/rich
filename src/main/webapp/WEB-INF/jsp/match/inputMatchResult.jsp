<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<script type="text/javascript" src="/res/js/match/inputMatchResult.js"></script>
	<style type="text/css">
		.matchDetailTable tr td{
			height:30px;
			line-height:30px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			var actionType = '${actionType }';
			if(actionType == "view"){
				$("#action_div").hide();
				disableForm("inputMatchResultForm",true);
			}
			
			$("input[type='radio'][name='match.matchResult']").change(function() {
				var val = $(this).val();
				if(val == 2){
					$("#detail_odds").textbox("setValue","0");
					$("#detail_odds").textbox("readonly",true);
				}else{
					$("#detail_odds").textbox("setValue","");
					$("#detail_odds").textbox("readonly",false);
				}
			});
		});
		
		
	</script>
	<div style="padding:10px;">
		<form action="<%=request.getContextPath() %>/match/inputMatchResult" id="inputMatchResultForm" method="post">
			<input type="hidden" id="matchDetailsLength" value="${fn:length(matchDetails)}"/>
			<div class="easyui-panel" id="saveOrUpdateMatchDetailPanel" style="padding:10px">
				<table>
					<tr>
						<td align="right" width="50">
							日期：
						</td>
						<td colspan="3">
							${createDate }
						</td>
					</tr>
					<tr>
						<td align="right">
							计划：
						</td>
						<td colspan="3">
							<input type="hidden" name="match.plan.id" id="matchPlanId" value="${match.plan.id }"/>
							${match.plan.planName }
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="hidden" id="matchForm_id" name="match.id" value="${match.id }"/>
						</td>
						<td align="right">
							<div id="action_div">
								<a href="javascript:saveOrUpdateMatch()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
								<a href="javascript:cancelInputMatch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
							</div>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<div class="easyui-panel" class="matchDetailPanel" style="padding:10px;width:360px;height:160px;" data-options="title:'第一场'">
								<table class="matchDetailTable">
									<tr>
										<td width="60" align="right">
											赛事：
										</td>
										<td width="110">
											${matchDetails[0].matchName }
										</td>
										<td align="right">
											玩法：
										</td>
										<td width="110">
											${matchDetails[0].playMethod.methodName }
										</td>
									</tr>
									<tr>
										<td width="60" align="right">
											主客队：
										</td>
										<td colspan="3">
											${matchDetails[0].homeTeam }
											&nbsp;&nbsp;VS&nbsp;&nbsp;
											${matchDetails[0].visiteTeam }
										</td>
									</tr>
									<tr>
										<td width="60">
											投注选项：
										</td>
										<td colspan="3">
											${matchDetails[0].bettingOption }
										</td>
									</tr>
								</table>
							</div>
							<div class="easyui-panel" class="matchDetailPanel" style="padding:10px;width:360px;height:100px;">
								<table class="matchDetailTable">
									<tr>
										<td width="60" align="right">
											胜出选项：
										</td>
										<td colspan="3">
											<input type="hidden" name="matchDetails[0].id" value="${matchDetails[0].id }"/>
											<input class="easyui-textbox" type="text" id="detail_winOption0" name="matchDetails[0].winOption"  value="${matchDetails[0].winOption }"/>
										</td>
									</tr>
									<tr>
										<td width="60" align="right">
											赛果：
										</td>
										<td colspan="3">
											<input type="radio" name="matchDetails[0].matchResult" value="1"  <c:if test="${matchDetails[0].matchResult == 1 }">checked="checked"</c:if> />胜 &nbsp;<input type="radio" name="matchDetails[0].matchResult" value="2"  <c:if test="${matchDetails[0].matchResult == 2 }">checked="checked"</c:if> />负 
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td colspan="2">
							<div class="easyui-panel" class="matchDetailPanel" style="padding:10px;width:360px;height:160px;" data-options="title:'第二场'">
								<c:choose>
									<c:when test="${fn:length(matchDetails) > 1 }">
										<table class="matchDetailTable">
											<tr>
												<td width="60" align="right">
													赛事：
												</td>
												<td width="110">
													${matchDetails[1].matchName }
												</td>
												<td align="right">
													玩法：
												</td>
												<td width="110">
													${matchDetails[1].playMethod.methodName }
												</td>
											</tr>
											<tr>
												<td width="60" align="right">
													主客队：
												</td>
												<td colspan="3">
													${matchDetails[1].homeTeam }
													&nbsp;&nbsp;VS&nbsp;&nbsp;
													${matchDetails[1].visiteTeam }
												</td>
											</tr>
											<tr>
												<td width="60">
													投注选项：
												</td>
												<td colspan="3">
													${matchDetails[1].bettingOption }
												</td>
											</tr>
										</table>
									</c:when>
									<c:otherwise>
										无第二场
									</c:otherwise>
								</c:choose>
							</div>
							<div class="easyui-panel" class="matchDetailPanel" style="padding:10px;width:360px;height:100px;">
								<c:choose>
									<c:when test="${fn:length(matchDetails) > 1 }">
										<table class="matchDetailTable">
											<tr>
												<td width="60" align="right">
													胜出选项：
												</td>
												<td width="110">
													<input type="hidden" name="matchDetails[1].id" value="${matchDetails[1].id }"/>
													<input class="easyui-textbox" type="text" id="detail_winOption1" name="matchDetails[1].winOption" value="${matchDetails[1].winOption }"/>
												</td>
											</tr>
											<tr>
												<td width="60" align="right">
													赛果：
												</td>
												<td width="110">
													<input type="radio" name="matchDetails[1].matchResult" value="1" <c:if test="${matchDetails[1].matchResult == 1 }">checked="checked"</c:if> />胜 &nbsp;<input type="radio" name="matchDetails[1].matchResult" value="2"  <c:if test="${matchDetails[1].matchResult == 2 }">checked="checked"</c:if> />负 
												</td>
											</tr>
										</table>
									</c:when>
									<c:otherwise>
										无第二场
									</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center" style="height:30px;line-height:30px;"> 
							彩果：<input type="radio" name="match.matchResult" value="1" <c:if test="${match.matchResult == 1 }">checked="checked"</c:if>/>赢 &nbsp;<input type="radio" name="match.matchResult" value="2" <c:if test="${match.matchResult == 2 }">checked="checked"</c:if>/>输 &nbsp;赔率：<input class="easyui-textbox" type="text" id="detail_odds" name="match.odds" value="${match.odds }"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
