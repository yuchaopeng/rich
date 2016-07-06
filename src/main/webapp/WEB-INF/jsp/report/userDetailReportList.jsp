<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>计划明细列表</title>
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript" src="/res/js/report/userDetailReportList.js"></script>
	<style type="text/css">
		#searchUserDetailReportForm .td_label{
			padding-left:20px;
		}
		
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="" id="searchUserDetailReportForm" method="post">
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">手机：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_mobile" name="params['mobile']"/>
						</td>
						<td class="td_label" align="right">姓名：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_userName" name="params['userName']"/>
						</td>
						<td class="td_label" align="right">用户类型：</td>
						<td>
							<select class="easyui-combobox" id="paramsUserType" name="params['userType']" style="width:155px;" data-options="editable:false,panelHeight:'auto'">
								<option value="">全部</option>
								<option value="0">彩民用户</option>
								<option value="1">管理员</option>
								<option value="2">运营人员</option>
								<option value="3">门店职员</option>
								<option value="4">专家</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="td_label" align="right">日期：</td>
						<td colspan="3">
							<input class="easyui-datebox" data-options="editable:false" id="params_DateStart" name="params['dateStart']"/>
							到
							<input class="easyui-datebox" data-options="editable:false" id="params_DateEnd" name="params['dateEnd']"/>
						</td>
						<td class="td_label" align="right">推荐人：</td>
						<td>
							<input type="hidden" id="paramsRecommenderId" name="params['recommenderId']"/>
							<input class="easyui-textbox" type="text" id="paramsRecommenderUsername" name="params['recommenderUsername']" data-options="editable:false,buttonText:'选择',prompt:'选择推荐人',onClickButton:chooseRecommender" />
						</td>
						<td class="td_label">
							<a href="javascript:searchUserDetailReportForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetUserDetailReportForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
							<a href="javascript:downloadReport()" class="easyui-linkbutton" data-options="iconCls:'icon-download'" style="width:80px">下载报表</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="user_detail_report_list">
		</table>
	</div>
</body>
</html>