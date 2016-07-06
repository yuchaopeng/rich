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
<title><%=webSiteName %></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/res/css/app.css">
<script type="text/javascript" src="/res/js/zepto/zepto.min.js"></script>
<script type="text/javascript" src="/res/js/zepto/zepto.alert.js"></script>
<script type="text/javascript" src="/res/js/app/app.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/res/css/zepto.alert.css">
<meta name="baidu-tc-verification" content="b60bec82b31e57c1a6a5bcd7b8da902d" />