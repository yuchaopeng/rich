<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../common/appCommonInclude.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath() %>/res/js/app/appLogin.js"></script>
	<script type="text/javascript" src="/res/js/app/AppAjax.js"></script>
</head>
<body>
	<header class="theme-header">
		<div class="header_left">&nbsp;</div>
		<div class="header_center"><%=webSiteName %></div>
		<div class="header_right"></div>
	</header>
	
	<div class="login_div">
		<form method="post" id="loginForm" onsubmit="return false;">
			<table>
				<tr>
					<td>
						<input type="text" class="text_input" style="width:90%" placeholder="手机号" name="mobile" id="mobile" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" class="text_input" style="width:90%" placeholder="密　码" name="password" id="password" />
					</td>
				</tr>
				<tr>
					<td>
						<input class="linkbutton yellow" id="helpBuy_button" style="width:90%" type="button" onclick="appLogin()" value="登录"/>
					</td>
				</tr>
				<tr>
					<td>
						<p id="alert_msg" class="login_alert_message"></p>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
