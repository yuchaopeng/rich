<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript" src="/res/js/systemMessage/systemMessageList.js"></script>
	<style type="text/css">
		#systemMessageForm .td_label{
			padding-left:20px;
		}
		
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="" id="systemMessageForm" method="post">
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">标题：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_title" name="params['title']"/>
						</td>
						<td class="td_label" align="right">确认情况：</td>
						<td>
							<select class="easyui-combobox" id="paramsState" name="params['state']" style="width:155px;" data-options="editable:false,panelHeight:'auto'">
								<option value="">全部</option>
								<option value="1">草稿</option>
								<option value="2">已发布</option>
							</select>
						</td>
						<td class="td_label" align="right">发布时间：</td>
						<td>
							<input class="easyui-datebox" data-options="editable:false" id="paramsPublishDateStart" name="params['publishDateStart']"/>
							到
							<input class="easyui-datebox" data-options="editable:false" id="paramsPublishDateEnd" name="params['publishDateEnd']"/>
						</td>
						<td class="td_label">
							<a href="javascript:searchSystemMessageForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetSystemMessageForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="system_message_list">
		</table>
	</div>
</body>
</html>