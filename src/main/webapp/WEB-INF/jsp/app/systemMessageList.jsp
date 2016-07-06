<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<c:forEach items="${systemMessages }" var="systemMessage">
					<li class="datalist_view_li_account_detail">
						<a href="javascript:viewSystemMessage('${systemMessage.id }')">
							<table>
								<tr>
									<td style="width:90%">
										<div class="datalist_item">
											<h4>${systemMessage.title }</h4>
											<p><fmt:formatDate value="${systemMessage.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
										</div>
									</td>
									<td style="width:10%">
										〉
									</td>
								</tr>
							</table>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="load_more_div">
			<form action="/app/systemMessageList" id="loadMoreForm" method="post">
				<input type="hidden" name="rows" id="rows" value="${rows }"/>
				<c:choose>
					<c:when test="${hasMore == true }">
						<a href="javascript:loadMoreDetail();">加载更多</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:;">没有更多了</a>
					</c:otherwise>
				</c:choose>
			</form>
		</div>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param name="currentTab" value="tab_system" />
		</jsp:include>
	</div>
	
	<script type="text/javascript">
		function loadMoreDetail(){
			var rows = $("#rows").val();
			if(rows == ""){
				rows = 0;
			}
			rows = parseInt(rows);
			rows += 6;
			$("#rows").val(rows);
			$("#loadMoreForm").submit();
		}
		
		function viewSystemMessage(systemMessageId){
			window.location.href="/app/viewSystemMessage?systemMessageId="+systemMessageId;
		}
	</script>
</body>
</html>