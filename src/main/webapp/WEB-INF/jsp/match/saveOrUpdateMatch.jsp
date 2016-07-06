<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/res/js/match/saveOrUpdateMatch.js"></script>
	<style type="text/css">
		.matchDetailTable tr td{
			height:30px;
			line-height:30px;
		}
	</style>
	<div style="padding:10px;">
		<form action="<%=request.getContextPath() %>/match/saveOrUpdateMatch" id="saveOrUpdateMatchForm" method="post">
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
							<input type="hidden" id="matchPlanId" value="${match.plan.id }"/>
							<select class="easyui-combobox" id="planId" name="match.plan.id" style="width:300px;" data-options="editable:false,panelHeight:'auto',onLoadSuccess:matchPlanSelect">
								<c:forEach items="${plans }" var="p">
									<option value="${p.id }">${p.planName }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="hidden" id="matchForm_id" name="match.id" value="${match.id }"/>
						</td>
						<td align="right">
							<a href="javascript:saveOrUpdateMatch()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
							<a href="javascript:publishMatch()" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">发布</a>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<div class="easyui-panel" style="padding:10px;width:365px;height:200px;" data-options="title:'第一场'">
								<table class="matchDetailTable">
									<tr>
										<td width="60" align="right">
											赛事：
										</td>
										<td>
											<input type="hidden" id="matchDetail_id0" name="matchDetails[0].id" value="${matchDetails[0].id }"/>
											<input class="easyui-textbox" id="detail_matchName0" name="matchDetails[0].matchName" value="${matchDetails[0].matchName }" style="height:20px;width:110px;"/>
										</td>
										<td align="right">
											玩法：
										</td>
										<td>
											<input type="hidden" id="matchPlayMethodId_first"  value="${matchDetails[0].playMethod.id }"/>
											<select class="easyui-combobox" id="playMethod_first" name="matchDetails[0].playMethod.id"  style="width:110px;" data-options="editable:false,panelHeight:'auto',onLoadSuccess:matchPlayMethodSelect_first">
												<c:forEach items="${playMethods }" var="pm">
													<option value="${pm.id }">${pm.methodName }</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td width="60" align="right">
											主客队：
										</td>
										<td>
											<input class="easyui-textbox" id="detail_homeTeam0" name="matchDetails[0].homeTeam" value="${matchDetails[0].homeTeam }" style="height:20px;;width:110px;"/>
										</td>
										<td align="center">
											VS
										</td>
										<td>
											<input class="easyui-textbox" id="detail_visiteTeam0" name="matchDetails[0].visiteTeam" value="${matchDetails[0].visiteTeam }" style="height:20px;;width:110px;"/>
										</td>
									</tr>
									<tr>
										<td width="60">
											投注选项：
										</td>
										<td colspan="3">
											<input class="easyui-textbox" id="detail_bettingOption0" name="matchDetails[0].bettingOption" value="${matchDetails[0].bettingOption }" style="height:20px;;width:265px;"/>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td colspan="2">
							<div class="easyui-panel" class="matchDetailPanel" style="padding:10px;width:365px;height:200px;" data-options="title:'第二场'">
								<table class="matchDetailTable">
									<tr>
										<td width="60" align="right">
											赛事：
										</td>
										<td>
											<input type="hidden" id="matchDetail_id1" name="matchDetails[1].id" value="${matchDetails[1].id }"/>
											<input class="easyui-textbox" id="detail_matchName1" name="matchDetails[1].matchName" value="${matchDetails[1].matchName }" style="height:20px;width:110px;"/>
										</td>
										<td align="right">
											玩法：
										</td>
										<td>
											<input type="hidden" id="matchPlayMethodId_second" value="${matchDetails[1].playMethod.id }"/>
											<select class="easyui-combobox" id="playMethod_second" name="matchDetails[1].playMethod.id" value="${matchDetails[1].playMethod.id }" style="width:110px;" data-options="editable:false,panelHeight:'auto',onLoadSuccess:matchPlayMethodSelect_second">
												<c:forEach items="${playMethods }" var="pm">
													<option value="${pm.id }">${pm.methodName }</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td width="60" align="right">
											主客队：
										</td>
										<td>
											<input class="easyui-textbox" id="detail_homeTeam1" name="matchDetails[1].homeTeam" value="${matchDetails[1].homeTeam }" style="height:20px;;width:110px;"/>
										</td>
										<td align="center">
											VS
										</td>
										<td>
											<input class="easyui-textbox" id="detail_visiteTeam1" name="matchDetails[1].visiteTeam" value="${matchDetails[1].visiteTeam }" style="height:20px;;width:110px;"/>
										</td>
									</tr>
									<tr>
										<td width="60">
											投注选项：
										</td>
										<td colspan="3">
											<input class="easyui-textbox" id="detail_bettingOption1" name="matchDetails[1].bettingOption" value="${matchDetails[1].bettingOption }" style="height:20px;;width:265px;"/>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						</td>
						<td>
							<a href="javascript:deleteSecondMatch()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除第二场</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
