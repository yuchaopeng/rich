<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/res/js/userAgreement.js"></script>
<div style="padding:10px;">
	<form id="addUserAgreementForm" action="<%=request.getContextPath()%>/userManage/addUserAgreement" method="POST" target="SELF">
		<table>
			<tr>
				<td>分成类型：</td>
				<td>
					<select class="easyui-combobox" id="agreementType" name="agreementType" data-options="editable:false,panelHeight:'auto'" style="width:100px;">
						<option value="0">不分成</option>
						<option value="1">按比例</option>
						<option value="2">按次</option>
					</select>
				</td>
				<td>分成比例：</td>
				<td>
					<input class="easyui-textbox" name="agreementValue" id="agreementValue" style="width:100px;">
				</td>
				<td>
					<input type="hidden" name="user.id" value="${userId }"/>
					<a href="javascript:addUserAgreement();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>
		</table>
	</form>
</div>
