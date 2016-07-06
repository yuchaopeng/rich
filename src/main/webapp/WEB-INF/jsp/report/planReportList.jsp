<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>计划报表</title>
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript" src="/res/js/report/planReportList.js"></script>
	<style type="text/css">
		searchPlanReportForm .td_label{
			padding-left:20px;
		}
		
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="" id="searchPlanReportForm" method="post">
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">计划：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_planName" name="params['planName']"/>
						</td>
						<td class="td_label" align="right">时间：</td>
						<td colspan="4">
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateStart" name="params['createDateStart']"/>
							到
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateEnd" name="params['createDateEnd']"/>
						</td>
						<td class="td_label">
							<a href="javascript:searchPlanReportForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetPlanReportForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
							<a href="javascript:downloadReport()" class="easyui-linkbutton" data-options="iconCls:'icon-download'" style="width:80px">下载报表</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="plan_report_list">
		</table>
	</div>
</body>
</html>