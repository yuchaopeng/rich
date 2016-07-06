<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.yu.web.base.model.WebSiteConfig"%>
<%@page import="com.yu.base.cache.CommonCache"%>
<%
	WebSiteConfig webSiteConfig = (WebSiteConfig)CommonCache.get(CommonCache.WEB_SITE_CONFIG_KEY);
	String webSiteName = "";
	if(webSiteConfig != null){
		webSiteName = webSiteConfig.getWebSiteName();
	}
%>
<!DOCTYPE html>
<html class="bg_login">
    <head>
        <meta charset="UTF-8">
        <title><%=webSiteName %></title>
        <%@include file="../common/backCommonInclude.jsp" %>
        <script type="text/javascript" src="<%=request.getContextPath() %>/res/js/backLogin.js"></script>
    </head>
    <body class="bg_login">
    	<div class="background_header">
    		<%=webSiteName %>
    	</div>
        <div id="login_div" style="position:absolute; left:40%;top:25%;">
			<form action="<%=request.getContextPath() %>/user/login" id="loginForm" method="post">
				<div class="easyui-panel" title="登录" style="width:400px;padding:30px 70px 20px 70px">
			    	<div style="margin-bottom:10px">
			            <input class="easyui-textbox" type="text" name="mobile" id="mobile" style="width:100%;height:40px;padding:12px" data-options="prompt:'手机号'" />
			        </div>
			        <div style="margin-bottom:20px">
			            <input class="easyui-textbox" type="password" name="password" id="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'password'" />
			        </div>
			        <div>
			            <a href="javascript:login()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
			                <span style="font-size:14px;">登录</span>
			            </a>
			        </div>
			        <div id="bootstrapAlert" style="display:none;height:25px;color:red;font-weight:bold;">
			        	<p></p>
			        </div>
			    </div>
			</form>
		</div>
    </body>
</html>