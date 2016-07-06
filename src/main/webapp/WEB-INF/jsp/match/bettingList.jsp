<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>投注列表</title>
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript" src="/res/js/match/bettingList.js"></script>
	<style type="text/css">
		#searchBettingForm .td_label{
			padding-left:20px;
		}
		
		.match_name_tooltip{
			color:black;
			text-decoration: none;
		}
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="" id="searchBettingForm" method="post">
			<input type="hidden" id="canEdit" value="${canEdit }"/>
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">创建时间：</td>
						<td colspan="3">
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateStart" name="params['createDateStart']"/>
							到
							<input class="easyui-datebox" data-options="editable:false" id="paramsCreateDateEnd" name="params['createDateEnd']"/>
						</td>
						<td class="td_label" align="right">状态：</td>
						<td>
							<select class="easyui-combobox" id="paramsMatchState" name="params['matchState']" style="width:155px;" data-options="editable:false,panelHeight:'auto'">
								<option value="">全部</option>
								<option value="2">已发布</option>
								<option value="3">有彩果</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="td_label" align="right">计划：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_planName" name="params['planName']"/>
						</td>
						<td class="td_label" align="right">姓名：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_userName" name="params['userName']"/>
						</td>
						<td class="td_label" align="right">彩票照片：</td>
						<td>
							<select class="easyui-combobox" id="paramsLotteryUploadState" name="params['lotteryUploadState']" style="width:155px;" data-options="editable:false,panelHeight:'auto'">
								<option value="">全部</option>
								<option value="1">未上传</option>
								<option value="2">已上传</option>
							</select>
						</td>
						<td class="td_label">
							<a href="javascript:searchBettingForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetBettingForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
						</td>
					</tr>
					
				</table>
			</div>
		</form>
		<table id="betting_list">
		</table>
	</div>
</body>
</html>