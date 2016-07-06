<%@page import="com.yu.web.base.model.WebSiteConfig"%>
<%@page import="com.yu.base.cache.CommonCache"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.yu.web.base.model.CurrentUserInfo,com.yu.web.base.util.LoginUtil" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	CurrentUserInfo userInfo = (CurrentUserInfo)session.getAttribute(LoginUtil.SESSION_CURRENT_USER);
	String userName = "";
	if(userInfo != null && userInfo.getUser() != null){
		userName = userInfo.getUser().getUsername();
	}
	
	WebSiteConfig webSiteConfig = (WebSiteConfig)CommonCache.get(CommonCache.WEB_SITE_CONFIG_KEY);
	String webSiteName = "";
	if(webSiteConfig != null){
		webSiteName = webSiteConfig.getWebSiteName();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=webSiteName %></title>
<%@include file="common/backCommonInclude.jsp" %>
<script type="text/javascript">
	$(function(){
		var defaultViewMenuId = $("#defaultViewMenuId").val();
		if(defaultViewMenuId != null && defaultViewMenuId != ''){
			var defaultViewMenuName = $("#defaultViewMenuName").val();
			var defaultViewMenuUrl = $("#defaultViewMenuUrl").val();
			if(defaultViewMenuUrl != null && defaultViewMenuUrl != ''){
				var url = getRootPath()+defaultViewMenuUrl;
				addTab(defaultViewMenuId,defaultViewMenuName,url);
			}
		}
	});
	
	function logout(){
		$.messager.confirm("提示","确定要退出本次登录吗？",function(r){
			if(r){
				window.location.href="<%=basePath%>user/logout";
			}
		});
	}
	
	function updatePassword(){
		$("#update_password").dialog({
			title:"修改密码",
			href:"/user/toUpdatePassword",
			width:400,
			height:260,
			modal:true
		});
	}
</script>
<style type="text/css">
	#current_user_info{
		width:250px;
		float:right;
		color:black;
		line-height:60px;
	}
</style>
</head>
<body class="easyui-layout">
	<input type="hidden" id="defaultViewMenuId" value="${defaultView.menuId }"/>
	<input type="hidden" id="defaultViewMenuName" value="${defaultView.menuName }"/>
	<input type="hidden" id="defaultViewMenuUrl" value="${defaultView.menuUrl }"/>
	<div data-options="region:'north',border:false" style="height:60px;line-height:60px;text-indent:30px;font-weight:bold;">
		<%=webSiteName %>
		<div id="current_user_info">
			<% 
				if(!"".equals(userName)){
			%>
				<span>
					您好 <%=userName %>
					<a href="javascript:updatePassword()">修改密码</a>
					<a href="javascript:logout()">退出登录</a>
				</span>
			<% 
				}
			%>
			<span id="current_time"></span>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单'" style="width:200px;padding:10px;">
		<ul id="treeMenu"></ul>
	</div>
	<div data-options="region:'south',border:false" style="height:40px;line-height:20px;background:rgb(230,238,248);padding:10px;text-align:center;font-family:arial">
		Copyright © 2015
	</div>
	<div data-options="region:'center'">
		<div class="easyui-tabs" id="centerTab" fit="true" border="false">  
            <div title="首页" style="padding:20px;overflow:hidden;">   
                <div style="margin-top:20px;">  
                    <h3>操作平台</h3>  
                </div>  
            </div>  
        </div>  
	</div>
	
	<div style="displan:none;">
		<div id="update_password"></div>
		<div id="dialog_div"></div>
		<div id="edit_user_dialog_div"></div>
		<div id="add_user_agreement_dialog_div"></div>
		<div id="update_user_dialog_div"></div>
		<div id="special_user_type_choose"></div>
		<div id="save_or_update_plan"></div>
		<div id="save_or_update_match_detail"></div>
		<div id="input_match_result"></div>
		<div id="upload_lottery"></div>
		<div id="view_lottery"></div>
		<div id="recharge_request_detail"></div>
		<div id="withdraw_request_detail"></div>
		<div id="web_site_config"></div>
		<div id="system_message"></div>
	</div>
	
</body>
</html>