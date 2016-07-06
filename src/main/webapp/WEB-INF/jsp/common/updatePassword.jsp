<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
	<style type="text/css">
		#updatePasswordPanel {
			padding:5px;
		}
		#updatePasswordPanel table td{
			padding:5px;
			margin:10px;
		}
	</style>
	
	<script type="text/javascript">
		function updateBackUserPassword(){
			$("#updatePasswordForm input[type='text']").each(function(){
				$(this).val($.trim($(this).val()));
			});
			
			var sourcePassword = $("#sourcePassword").val();
			if(sourcePassword == ""){
				$.messager.alert("提示","请输入原密码.");
				return;
			}
			
			var newPassword = $("#newPassword").val();
			if(newPassword == ""){
				$.messager.alert("提示","请输入新密码.");
				return;
			}
			
			if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/.test(newPassword)){
				$.messager.alert("提示","新密码须为6位（含）以上数字与字符组合.");
				return;
			}
			
			if(newPassword == sourcePassword){
				$.messager.alert("提示","新密码不能与原密码相同.");
				return;
			}
			
			var confirmPassword = $("#confirmPassword").val();
			if(confirmPassword == ""){
				$.messager.alert("提示","请输入确认密码.");
				return;
			}
			
			if(newPassword != confirmPassword){
				$.messager.alert("提示","您输入的新密码和确认密码不一致，请重新输入.");
				return;
			}
			
			$("#updatePasswordForm").form("submit",{
				success: function (data) {
					var result = eval("("+data+")");
					alertMessage("提示",result.message);
					if(result.result == 0){
						$("#update_password").dialog("close");
					}
				}
			});
		}
		
		function cancelBackUserUpdatePassword(){
			$("#update_password").dialog("close");
		}
	</script>
	<div style="padding:10px;">
		<form action="<%=request.getContextPath() %>/user/updatePassword" id="updatePasswordForm" method="post">
			<div class="easyui-panel" id="updatePasswordPanel" style="padding:10px">
				<input type="hidden" name="userId" value="${user.id }"/>
				<table>
					<tr>
						<td align="right">
							手机号：
						</td>
						<td colspan="3">
							${user.mobile }
						</td>
					</tr>
					<tr>
						<td align="right">
							原密码：
						</td>
						<td>
							<input class="easyui-textbox" type="password" id="sourcePassword" name="sourcePassword" />
						</td>
					</tr>
					<tr>
						<td align="right">
							新密码：
						</td>
						<td>
							<input class="easyui-textbox" type="password" id="newPassword" name="newPassword"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							确认密码：
						</td>
						<td>
							<input class="easyui-textbox" type="password" id="confirmPassword" name="confirmPassword"/>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<a href="javascript:updateBackUserPassword()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
							<a href="javascript:cancelBackUserUpdatePassword()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
