<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户报表</title>
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript" src="/res/js/report/userReportList.js"></script>
	<style type="text/css">
		#searchRechargeForm .td_label{
			padding-left:20px;
		}
		
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="" id="searchReportForm" method="post">
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">姓名：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_username" name="params['userName']"/>
						</td>
						<td class="td_label" align="right">手机号：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_mobile" name="params['mobile']"/>
						</td>
					</tr>
					<tr>
						<td class="td_label" align="right">时间：</td>
						<td colspan="3">
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateStart" name="params['createDateStart']"/>
							到
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateEnd" name="params['createDateEnd']"/>
						</td>
						<td class="td_label">
							<a href="javascript:searchReportForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetReportForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
							<a href="javascript:downloadReport()" class="easyui-linkbutton" data-options="iconCls:'icon-download'" style="width:80px">下载报表</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="user_report_list">
		</table>
	</div>
</body>
</html>