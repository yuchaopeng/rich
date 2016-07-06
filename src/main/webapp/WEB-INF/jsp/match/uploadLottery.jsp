<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/match/uploadLottery.js"></script>
	<style type="text/css">
		.matchDetailTable tr td{
			height:30px;
			line-height:30px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			var actionType = '${type}';
			if(actionType == "view"){
				$("#action_div").hide();
				disableForm("uploadLotteryForm",true);
				$("#uploadFile").linkbutton({disabled: true});
				$("#deleteLotteryImageLink").hide();
			}
		});
	</script>
	<div style="padding:10px;">
		<form action="<%=request.getContextPath() %>/match/uploadLottery" id="uploadLotteryForm" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${bettingRecord.id }" name="bettingRecordId" id = "bettingRecordId"/>
			<div class="easyui-panel" id="uploadLotteryPanel" style="padding:10px">
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
							${match.plan.planName }
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="hidden" id="matchForm_id" name="match.id" value="${match.id }"/>
						</td>
						<td align="right">
							<div id="action_div">
								<!-- <a href="javascript:saveOrUpdateMatch()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
								<a href="javascript:cancelInputMatch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a> -->
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
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div class="easyui-panel" class="matchDetailPanel" style="padding:10px;width:725px;height:125px;" data-options="title:'上传'">
								<input class="easyui-filebox" name="lottery" id="lottery" data-options="prompt:'选择彩票',buttonText:'选择彩票'" style="width:85%">
								<a id="uploadFile" href="javascript:uploadLotteryPhoto()" class="easyui-linkbutton" style="width:80px;" data-options="iconCls:'icon-import'">上传</a>
								<div id="lotteryImageUploadDiv" style="padding:10px;" <c:if test="${!empty bettingRecord.imageUpload.id }">style="display:none;"</c:if>>									
									<input type="hidden" id="lotteryImageUploadId" value="${bettingRecord.imageUpload.id }">
									<a href="javascript:viewLotteryImage()" id="lotteryImageUploadName">${bettingRecord.imageUpload.realName}</a>
									<a href="javascript:deleteLotteryImage()" id="deleteLotteryImageLink" style="width:80px;" data-options="">删除</a>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
