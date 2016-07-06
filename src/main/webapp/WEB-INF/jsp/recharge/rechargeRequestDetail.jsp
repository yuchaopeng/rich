<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	#rechargeForm table{
		width:100%;
	}
	#rechargeForm table td{
		width:25%;
		height:25px;
		line-height:25px;
	}
</style>
<script type="text/javascript">
<!--
	function handleRechargeRequest(){
		var actualAmountStr = $("#actualAmountStr").textbox("getValue");
		if(!/^[1-9]\d*(\.\d{1,2})?$|^0(\.\d{1,2})$/.test(actualAmountStr)){
			alertMessage("提示","请输入正确的实收金额.");
			return;
		}
		
		parent.window.$.messager.confirm("提示","确定要处理这笔充值吗？",function(data){
			if (data) {  
				$("#rechargeForm").form("submit",{
					success: function (data) {
						var result = eval("("+data+")");
						alertMessage("提示",result.message);
						parent.window.$("#recharge_request_detail").dialog('close');
					}
				});
	        }  
		});
	}
	
//-->
</script>
<div style="padding:10px;">
	<form id="rechargeForm" action="<%=request.getContextPath()%>/rechargeRequest/handleRechargeRequest" method="POST" target="SELF">
		<div class="easyui-panel" style="padding:10px" data-options="title:'付款人信息'">
			<table>
				<tr>
					<td align="right" class="">付款人：</td>
					<td>${recharge.paymentUserName }</td>
					<td align="right">手机号：</td>
					<td>${recharge.paymentMobile }</td>
				</tr>
				<c:choose>
					<c:when test="${recharge.requestType.id == 1 }">
						<tr>
							<td align="right">付款银行：</td>
							<td>${recharge.paymentBank }</td>
							<td align="right">付款账号：</td>
							<td>${recharge.paymentAccount }</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td align="right">付款账号：</td>
							<td colspan="3">${recharge.paymentAccount }</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<td align="right">充值金额（元）：</td>
					<td>${recharge.rechargeAmountStr }</td>
				</tr>
			</table>
		</div>
		<div class="easyui-panel" style="padding:10px" data-options="title:'收款人信息'">
			<table>
				<tr>
					<td align="right">收款人：</td>
					<td>${recharge.reciverUserName }</td>
					<td align="right">手机号：</td>
					<td>${recharge.reciverMobile }</td>
				</tr>
				<c:choose>
					<c:when test="${recharge.requestType.id == 1 }">
						<tr>
							<td align="right">收款银行：</td>
							<td>${recharge.reciverBank }</td>
							<td align="right">收款账号：</td>
							<td>${recharge.reciverAccount }</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td align="right">收款账号：</td>
							<td colspan="3">${recharge.reciverAccount }</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="easyui-panel" style="padding:10px" data-options="title:'充值处理'">
			<table>
				<tr>
					<td align="right">姓名：</td>
					<td>${recharge.user.username }</td>
				</tr>
				<tr>
					<td align="right">手机号：</td>
					<td>${recharge.user.mobile }</td>
				</tr>
				<tr>
					<td align="right">实收金额（元）：</td>
					<td>
						<input type="hidden" name="id" value="${recharge.id }">
						<c:choose>
							<c:when test="${handleType == 'view' }">
								${recharge.actualAmountStr }
							</c:when>
							<c:otherwise>
								<input class="easyui-textbox" data-options="" id="actualAmountStr" name="actualAmountStr" value="${empty recharge.actualAmountStr ? recharge.rechargeAmountStr : recharge.actualAmountStr }" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<c:if test="${handleType != 'view' }">
					<tr>
						<td align="right"></td>
						<td>
							<a href="javascript:handleRechargeRequest()" class="easyui-linkbutton" style="line-height:24px;height:24px;width:175px;background:#F36F22;color:white">确认充值金额</a>
						</td>
					</tr>
					<tr>
						<td align="right"></td>
						<td>
							<p style="color:blue;">确认后不可再修改，请仔细确认。</p>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</form>
</div>
