<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
	<style type="text/css">
		#saveOrUpdatePlayMethodConfigPanel {
			padding:5px;
		}
		#saveOrUpdatePlayMethodConfigPanel table td{
			padding:5px;
			margin:10px;
		}
	</style>
	<div style="padding:10px;">
		<form action="<%=request.getContextPath() %>/webSite/saveOrUpdatePlayMethodConfig" id="saveOrUpdatePlayMethodForm" method="post">
			<div class="easyui-panel" id="saveOrUpdatePlayMethodConfigPanel" style="padding:10px">
				<table>
					<tr>
						<td align="right" style="width:120px;">
							玩法：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" id="form_description" name="methodName" value="${matchPlayMethod.methodName }">
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" id="form_description" name="description" value="${matchPlayMethod.description }"/>
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" id="userForm_id" name="id" value="${matchPlayMethod.id }"/>
						</td>
						<td align="right">
							<a href="javascript:saveOrUpdatePlayMethodConfig()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
<script type="text/javascript">
	function saveOrUpdatePlayMethodConfig(){
		$("[id^='form_']").each(function(){
			var val = $(this).val();
			$(this).val($.trim(val));
		});
		
		$("#saveOrUpdatePlayMethodForm").form("submit",{
			success: function (data) {
				var result = eval("("+data+")");
				if(result.result == 0){
					$("#saveOrUpdatePlayMethodForm input[name='id']").val(result.matchPlayMethod.id);
				}
				alertMessage("提示",result.message);
			}
		});
	}
</script>