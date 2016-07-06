<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/appCommonInclude.jsp" %>
<link rel="stylesheet" href="/res/css/scale.css" type="text/css" />
</head>
<body>
	<div class="mobile_content">
		<header class="theme-header">
			<div class="header_left">&nbsp;</div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<div class="betting_plan_list">
				<c:forEach items="${helpBuyRecords }" var="buy">
					<a href="/app/bettingList?planId=${buy.plan.id }">${fn:length(buy.plan.planName) <= 8 ? buy.plan.planName : fn:substring(buy.plan.planName,0,8) }${fn:length(buy.plan.planName) <= 8 ? '' : '...' }</a>
				</c:forEach>
			</div>
			
			<div class="match_datalist">
				<c:forEach items="${matchForms }" var="form">
					<table class="match_table">
						<tr>
							<td style="width:35%">第一场</td>
							<td colspan="2" style="width:30%">
								<p><fmt:formatDate value="${form.match.createDate }" pattern="yyyy-MM-dd"/></p>
								<section>
									<c:choose>
										<c:when test="${form.userBettingRecord.lotteryUploadState == 2 }">
											<a class="betting_record_image_a" href="javascript:;" photoUrl="/image/getImage?imageId=${form.userBettingRecord.imageUpload.id }">查看彩票</a>
										</c:when>
										<c:otherwise>
											还未上传
										</c:otherwise>
									</c:choose>
								</section>
							</td>
							<td style="width:35%">第二场</td>
						</tr>
						<tr>
							<td colspan="2" style="width:50%;text-align:left;">
								<p>${form.matchDetails[0].matchName }  ${form.matchDetails[0].playMethod.methodName }</p>
								<p>${form.matchDetails[0].homeTeam } VS ${form.matchDetails[0].visiteTeam }</p>
								<c:choose>
									<c:when test="${form.matchDetails[0].matchResult == 1 }">
										胜（${form.matchDetails[0].winOption }）
									</c:when>
									<c:when test="${form.matchDetails[0].matchResult == 2 }">
										负（${form.matchDetails[0].winOption }）
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
													胜（${form.matchDetails[1].winOption }）
												</c:when>
												<c:when test="${form.matchDetails[1].matchResult == 2 }">
													负（${form.matchDetails[1].winOption }）
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
							<td style="width:35%">投注：${form.userBettingRecord.bettingAmountStr }</td>
							<td colspan="2" style="width:30%">
								<c:choose>
									<c:when test="${form.userBettingRecord.state == 4}">
										未生效投注
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${form.match.matchResult == 1 }">
												<span style="color:red;font-weight:bold;">赢</span>
											</c:when>
											<c:when test="${form.match.matchResult == 2 }">
												<span style="color:red;font-weight:bold;">输</span>
											</c:when>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="width:35%">彩金：${form.userBettingRecord.winAmountStr }</td>
						</tr>
					</table>
				</c:forEach>
			</div>
		</div>
		
		<div class="load_more_div">
			<form action="/app/bettingList" id="loadMoreForm" method="post">
				<input type="hidden" name="planId" id="planId" value="${buy.plan.id }"/>
				<input type="hidden" name="rows" id="rows" value="${rows }"/>
				<c:choose>
					<c:when test="${hasMore == true }">
						<a href="javascript:loadMoreBetting();">加载更多</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:;">没有更多了</a>
					</c:otherwise>
				</c:choose>
			</form>
		</div>
		
    	<section class="imgzoom_pack" style="display:none;">
    		<div class="imgzoom_x">X</div>
    		<div class="imgzoom_img"><img src=""/></div>
    	</section>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param value="tab_bettingList" name="currentTab"/>
		</jsp:include>
	</div>
	
	<script src="/res/js/app/scale.js"></script>
	<script type="text/javascript">
		function loadMoreBetting(){
			var rows = $("#rows").val();
			if(rows == ""){
				rows = 0;
			}
			rows = parseInt(rows);
			rows += 3;
			$("#rows").val(rows);
			$("#loadMoreForm").submit();
		}
	
		$(function(){
			ImagesZoom.init();
		});
	</script>
</body>
</html>