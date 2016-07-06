<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>计划列表</title>
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript" src="/res/js/recharge/rechargeRequestList.js"></script>
	<style type="text/css">
		#searchRechargeForm .td_label{
			padding-left:20px;
		}
		
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="" id="searchRechargeForm" method="post">
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">冲值类型：</td>
						<td>
							<select class="easyui-combobox" id="paramsRequestType" name="params['requestType']" style="width:155px;" data-options="editable:false,panelHeight:'auto'">
								<option value="">全部</option>
								<option value="1">银行卡</option>
								<option value="2">微信</option>
								<option value="3">支付宝</option>
							</select>
						</td>
						<td class="td_label" align="right">申请时间：</td>
						<td colspan="4">
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateStart" name="params['createDateStart']"/>
							到
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateEnd" name="params['createDateEnd']"/>
						</td>
					</tr>
					<tr>
						<td class="td_label" align="right">姓名：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_username" name="params['userName']"/>
						</td>
						<td class="td_label" align="right">手机号：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_mobile" name="params['mobile']"/>
						</td>
						<td class="td_label" align="right">确认情况：</td>
						<td>
							<select class="easyui-combobox" id="paramsState" name="params['state']" style="width:155px;" data-options="editable:false,panelHeight:'auto'">
								<option value="">全部</option>
								<option value="1">未确认</option>
								<option value="2">已确认</option>
							</select>
						</td>
						<td class="td_label">
							<a href="javascript:searchRechargeForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetRechargeForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="recharge_request_list">
		</table>
	</div>
</body>
</html>