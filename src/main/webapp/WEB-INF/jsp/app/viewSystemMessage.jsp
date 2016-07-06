<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/appCommonInclude.jsp" %>
<style type="text/css">
	.systemMessageContent{width:100%;}
</style>
</head>
<body>
	<div class="mobile_content">
		<header class="theme-header">
			<div class="header_left">
				<c:choose>
					<c:when test="${!empty returnUrl }">
						<a href="${returnUrl }">〈返回</a>
					</c:when>
					<c:otherwise>
						<a href="/app/systemMessageList">〈返回</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="systemMessageContent">
			<div style="padding:10px;">
				<h4>标题：${systemMessage.title }</h4>
				<span style="color:grey;">发布时间：<fmt:formatDate value="${systemMessage.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
			<div style="padding:10px;overflow:auto;">
				${systemMessage.content }
			</div>
		</div>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param name="currentTab" value="tab_system" />
		</jsp:include>
	</div>
</body>
</html>