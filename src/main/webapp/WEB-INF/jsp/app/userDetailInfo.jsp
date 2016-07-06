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
	<div class="mobile_content">
		<header class="theme-header">
			<div class="header_left"><a href="/app/userInfo">〈个人信息</a></div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<div class="user_detail_info">
				<table>
					<tr>
						<td class="user_detail_info_td">姓名</td>
						<td align="right">${user.username }</td>
					</tr>
					<tr>
						<td class="user_detail_info_td">手机号</td>
						<td align="right">${user.mobile }</td>
					</tr>
					<tr>
						<td class="user_detail_info_td">我的邮箱</td>
						<td align="right">${user.email }</td>
					</tr>
				</table>
			</div>
			<div class="user_detail_update_password_info">
				<a href="javascript:;">
					<a href="/app/toUpdatePassword">
						<table>
							<tr>
								<td>
									修改密码
								</td>
								<td class="right_tag">
									〉
								</td>
							</tr>
						</table>
					</a>
				</a>
			</div>
		</div>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param value="tab_userInfo" name="currentTab"/>
		</jsp:include>
	</div>
</body>
</html>