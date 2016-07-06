<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../common/backCommonInclude.jsp" %>
	<script type="text/javascript">
		function saveOrUpdateConfig(){
			$("#saveOrUpdateConfigForm input").each(function(){
				$(this).val($.trim($(this).val()));
			});
			
			
			var commRate = $("#commRate").val();
			if(!/^[1-9]\d*(\.\d{1})?$|^0(\.\d{1})$/.test(commRate)){
				alertMessage("提示","请输入正确的佣金比例（保留一位小数）.");
				return;
			}
			
			if(commRate >= 50){
				alertMessage("提示","佣金比例不能大于50%.");
				return;
			}
			
			$("#saveOrUpdateConfigForm").form("submit",{
				success: function (data) {
					var result = eval("("+data+")");
					if(result.result == 0){
						
					}
					alertMessage("提示",result.message);
				}
			});
		}
	</script>
	
	<style type="text/css">
		#saveOrUpdateConfigForm table td{padding:5px 5px;}
	</style>
</head>
<body>
	<div style="padding:5px;">
		<form action="/webSite/saveOrUpdateConfig" id="saveOrUpdateConfigForm" method="post">
			<input type="hidden" name="id" value="${webSiteConfig.id }"/>
			<div class="easyui-panel" title="网站设置" style="padding:10px">
				<table>
					<tr>
						<td class="td_label" align="right">网站名称：</td>
						<td>
							<input class="easyui-textbox" type="text" id="webSiteName" name="webSiteName" value="${webSiteConfig.webSiteName }"/>
						</td>
						<td class="td_label" align="right">门店佣金：</td>
						<td>
							<input class="easyui-textbox" type="text" id="commRate" name="commRate" style="width:50px;" value="${webSiteConfig.commRateStr }"/>%
						</td>
						<td>
							<a href="javascript:saveOrUpdateConfig()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>