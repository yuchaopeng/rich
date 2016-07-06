<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>计划列表</title>
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/plan/planList.js"></script>
	<style type="text/css">
		#searchPlanForm .td_label{
			padding-left:20px;
		}
		
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="" id="searchPlanForm" method="post">
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">计划名称：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_planName" name="params['planName']"/>
						</td>
						<td class="td_label" align="right">计划状态：</td>
						<td>
							<select class="easyui-combobox" id="paramsState" name="params['state']" style="width:155px;" data-options="editable:false,panelHeight:'auto'">
								<option value="">全部</option>
								<option value="1">生效</option>
								<option value="2">未生效</option>
							</select>
						</td>
						<td class="td_label" align="right">专家：</td>
						<td>
							<input type="hidden" id = "paramsExpertId" name="params['expertId']"/>
							<input class="easyui-textbox" type="text" id="paramsExpertUsername" data-options="editable:false,buttonText:'选择',prompt:'选择专家',onClickButton:selectExpert" />
						</td>
						<td class="td_label">
							<a href="javascript:searchPlanForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetPlanForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="plan_list">
		</table>
	</div>
</body>
</html>