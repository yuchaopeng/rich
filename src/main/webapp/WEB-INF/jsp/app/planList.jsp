<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/appCommonInclude.jsp" %>
</head>
<body>
	<div class="mobile_content">
		<header class="theme-header">
			<div class="header_left">&nbsp;</div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<ul>
				<c:forEach items="${planForms }" var="planForm">
					<li class="datalist_view_li">
						<a href="/app/matchList?planId=${planForm.plan.id }">
							<table>
								<tr>
									<td style="width:95%">
										<div class="datalist_item">
											<h4>${planForm.plan.planName }</h4>
											<p>最近10期胜率：<span>${planForm.winRate }%</span></p>
										</div>
									</td>
									<td style="width:5%">
										<span style="color:gray;font-size:12px;">〉</span>
									</td>
								</tr>
								<tr>
									<td>
										<div class="datalist_item_bottom">
											<div class="list_left_div">${planForm.plan.firstStr }元起购</div>
											<div class="list_right_div">随时帮买</div>
										</div>
									</td>
									<td></td>
								</tr>
							</table>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="load_more_div">
			<form action="/app/planList" id="loadMoreForm" method="post">
				<input type="hidden" name="rows" id="rows" value="${rows }"/>
				<c:choose>
					<c:when test="${hasMore == true }">
						<a href="javascript:loadMorePlan();">加载更多</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:;">没有更多了</a>
					</c:otherwise>
				</c:choose>
			</form>
		</div>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param name="currentTab" value="tab_planList" />
		</jsp:include>
	</div>
	
	<script type="text/javascript">
		function loadMorePlan(){
			var rows = $("#rows").val();
			if(rows == ""){
				rows = 0;
			}
			rows = parseInt(rows);
			rows += 4;
			$("#rows").val(rows);
			$("#loadMoreForm").submit();
		}
	</script>
</body>
</html>