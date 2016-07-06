<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>普通用户列表</title>
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript" src="/res/js/ordinaryUserList.js"></script>
	<script type="text/javascript" src="/res/js/editUser.js"></script>
	<style type="text/css">
		#searchForm .td_label{
			padding-left:20px;
		}
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="" id="searchForm" method="post">
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">生效状态：</td>
						<td>
							<select class="easyui-combobox" id="paramsState" name="params['state']" style="width:155px;" data-options="editable:false,panelHeight:'auto'">
								<option value="">全部</option>
								<option value="1">生效</option>
								<option value="2">未生效</option>
							</select>
						</td>
						<td class="td_label" align="right">创建时间：</td>
						<td colspan="4">
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateStart" name="params['createDateStart']"/>
							到
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateEnd" name="params['createDateEnd']"/>
						</td>
					</tr>
					<tr>
						<td class="td_label" align="right">姓名：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_username" name="params['username']"/>
						</td>
						<td class="td_label" align="right">手机号：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_mobile" name="params['mobile']"/>
						</td>
						<td class="td_label" align="right">推荐人：</td>
						<td>
							<input type="hidden" id = "paramsRecommenderId" name="params['recommenderId']"/>
							<input class="easyui-textbox" type="text" id="paramsRecommenderUsername" name="params['recommenderUsername']" data-options="editable:false,buttonText:'选择',prompt:'选择推荐人',onClickButton:chooseRecommender" />
						</td>
						<td class="td_label">
							<a href="javascript:searchForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="ordinary_user_list">
		</table>
	</div>
</body>
</html>