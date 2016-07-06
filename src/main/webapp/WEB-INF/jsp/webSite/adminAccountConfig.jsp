<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<%@include file="../common/backCommonInclude.jsp" %>
	
	<style type="text/css">
		.admin_account_info{width:100%;}
		.admin_account_info .admin_account_info_title{width:200px;}
	</style>
	
	<script type="text/javascript">
		function updateConfig(){
			$("#updateAdminAccountConfigForm").form("submit",{
				success: function (data) {
					var result = eval("("+data+")");
					if(result.result == 0){
					}
					alertMessage("提示",result.message);
				}
			});
		}		
	</script>
</head>
<body>
	<div style="padding:5px;">
		<form action="/webSite/updateAdminAccountConfig" id="updateAdminAccountConfigForm" method="post">
			<div style="padding:10px;">
				<a href="javascript:updateConfig()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
			</div>
			<div class="easyui-panel" style="padding:10px" data-options="title:'银行卡'">
				<table class="admin_account_info">
					<tr>
						<td class="admin_account_info_title" align="right">
							收款人：
						</td>
						<td>
							<input type="hidden" name="bank.id" value="${bank.id }"/>
							<input class="easyui-validatebox textbox" data-options="required:true" name="bank.userName" value="${bank.userName }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td class="admin_account_info_title"  align="right">
							收款银行：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" name="bank.bank" value="${bank.bank }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td  class="admin_account_info_title" align="right">
							收款账户：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" name="bank.account" value="${bank.account }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td class="admin_account_info_title"  align="right">
							手机号：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" name="bank.mobile" value="${bank.mobile }" style="height:20px;"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="easyui-panel" style="padding:10px" data-options="title:'支付宝'">
				<table class="admin_account_info">
					<tr>
						<td  class="admin_account_info_title" align="right">
							收款人：
						</td>
						<td>
							<input type="hidden" name="alipay.id" value="${alipay.id }"/>
							<input class="easyui-validatebox textbox" data-options="required:true" name="alipay.userName" value="${alipay.userName }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td  class="admin_account_info_title" align="right">
							支付宝账户：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" name="alipay.account" value="${alipay.account }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td  class="admin_account_info_title" align="right">
							手机号：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" name="alipay.mobile" value="${alipay.mobile }" style="height:20px;"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="easyui-panel" style="padding:10px" data-options="title:'微信'">
				<table class="admin_account_info">
					<tr>
						<td class="admin_account_info_title"  align="right">
							收款人：
						</td>
						<td>
							<input type="hidden" name="wechat.id" value="${wechat.id }"/>
							<input class="easyui-validatebox textbox" data-options="required:true" name="wechat.userName" value="${wechat.userName }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td class="admin_account_info_title"  align="right">
							微信账户：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" name="wechat.account" value="${wechat.account }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td class="admin_account_info_title"  align="right">
							手机号：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" name="wechat.mobile" value="${wechat.mobile }" style="height:20px;"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>