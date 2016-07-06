<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/appCommonInclude.jsp" %>
</head>
<body>
	<div class="mobile_match_content">
		<header class="theme-header">
			<div class="header_left"><a href="/app/planList">〈返回</a></div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<div class="match_plan_name">
				${plan.planName }
			</div>
			
			<div class="match_datalist">
				<c:forEach items="${matchForms }" var="form">
					<table class="match_table">
						<tr>
							<td style="width:35%">第一场</td>
							<td colspan="2" style="width:30%">${form.match.matchDate}</td>
							<td style="width:35%">第二场</td>
						</tr>
						<tr>
							<td colspan="2" style="width:50%;text-align:left;">
								<p>${form.matchDetails[0].matchName }  ${form.matchDetails[0].playMethod.methodName }</p>
								<p>${form.matchDetails[0].homeTeam } VS ${form.matchDetails[0].visiteTeam }</p>
								<c:choose>
									<c:when test="${form.matchDetails[0].matchResult == 1 }">
										'胜'（${form.matchDetails[0].winOption }）
									</c:when>
									<c:when test="${form.matchDetails[0].matchResult == 2 }">
										'胜'（${form.matchDetails[0].winOption }）
									</c:when>
								</c:choose>
							</td>
							<td colspan="2" style="width:50%;text-align:right;">
								<c:choose>
									<c:when test="${fn:length(form.matchDetails) > 1 }">
										<p>${form.matchDetails[1].matchName }  ${form.matchDetails[1].playMethod.methodName }</p>
										<p>${form.matchDetails[1].homeTeam } VS ${form.matchDetails[1].visiteTeam }</p>
										<p>
											<c:choose>
												<c:when test="${form.matchDetails[1].matchResult == 1 }">
													'胜'（${form.matchDetails[1].winOption }）
												</c:when>
												<c:when test="${form.matchDetails[1].matchResult == 2 }">
													'胜'（${form.matchDetails[1].winOption }）
												</c:when>
											</c:choose>
										</p>
									</c:when>
									<c:otherwise>
										<p>无第二场</p>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td style="width:35%">投注：${plan.firstStr }</td>
							<td colspan="2" style="width:30%">
								<c:choose>
									<c:when test="${form.match.matchResult == 1 }">
										<span style="color:red;font-weight:bold;">赢</span>
									</c:when>
									<c:when test="${form.match.matchResult == 2 }">
										<span style="color:red;font-weight:bold;">输</span>
									</c:when>
								</c:choose>
							</td>
							<td style="width:35%">彩金：${plan.firstStr*form.match.odds }</td>
						</tr>
					</table>
				</c:forEach>
			</div>
			<div class="load_more_match_div">
				<form action="/app/matchList" id="loadMoreForm" method="post">
					<input type="hidden" name="planId" id="planId" value="${plan.id }"/>
					<input type="hidden" name="rows" id="rows" value="${rows }"/>
					<c:choose>
						<c:when test="${hasMore == true }">
							<a href="javascript:loadMoreMatch();">加载更多</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;">没有更多了</a>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
			<div class="help_buy_div">
				<table>
					<c:choose>
						<c:when test="${!empty helpBuyRecord.id }">
							<tr>
								<td style="width:50%">
									<input class="linkbutton lightGrey" type="button" onclick="AppMatchList.cancelHelpBuy()" value="取消帮买"/>
								</td>
								<td style="width:50%">
									<input class="linkbutton yellow" type="button" value="帮买" onclick="AppMatchList.helpBuy()"/>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td style="width:100%">
									<input class="linkbutton yellow" type="button" value="帮买" onclick="AppMatchList.helpBuy()"/>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				
					
				</table>
			</div>
		</div>
		
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param name="currentTab" value="tab_planList" />
		</jsp:include>
	</div>
	
	<script type="text/javascript">
			
		function loadMoreMatch(){
			var rows = $("#rows").val();
			if(rows == ""){
				rows = 0;
			}
			rows = parseInt(rows);
			rows += 3;
			$("#rows").val(rows);
			$("#loadMoreForm").submit();
		}
	
		var AppMatchList = {
				helpBuy : function(){
					var planId = document.getElementById("planId").value;
					window.location.href='/app/helpBuy?planId='+planId;
				},
				cancelHelpBuy : function(){
					$.dialog({
	                    content : '您确定取消帮买吗？',
	                    ok : function() {
	                    	var planId = document.getElementById("planId").value;
	    					window.location.href='/app/cancelHelpBuy?planId='+planId;
	                        return true;
	                    },
	                    cancel : function(){
	                    	
	                    },
	                    lock : true
	                });
				}
		}
	</script>
</body>
</html>