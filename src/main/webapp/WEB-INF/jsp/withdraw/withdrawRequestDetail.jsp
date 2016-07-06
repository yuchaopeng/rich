<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	#withdrawForm table{
		width:100%;
	}
	#withdrawForm table td{
		width:25%;
		height:25px;
		line-height:25px;
	}
</style>
<script type="text/javascript">
<!--
	function handleWithdrawRequest(){
		var actualAmountStr = $("#actualAmountStr").textbox("getValue");
		if(!/^[1-9]\d*(\.\d{1,2})?$|^0(\.\d{1,2})$/.test(actualAmountStr)){
			alertMessage("提示","请输入正确的实提金额.");
			return;
		}
		var amountStr = $("#amountStr").val();
		if(actualAmountStr*1 > amountStr*1){
			alertMessage("提示","实提金额(元)必须小于等于提现金额(元).");
			return;
		}
		
		parent.window.$.messager.confirm("提示","确定要处理这笔提现吗？",function(data){
			if (data) {  
				$("#withdrawForm").form("submit",{
					success: function (data) {
						var result = eval("("+data+")");
						alertMessage("提示",result.message);
						parent.window.$("#withdraw_request_detail").dialog('close');
					}
				});
	        }  
		});
	}
	
//-->
</script>
<div style="padding:10px;">
	<form id="withdrawForm" action="<%=request.getContextPath()%>/withdrawRequest/handleWithdrawRequest" method="POST" target="SELF">
		<input type="hidden" id="amountStr" value="${withdraw.amountStr }">
		<div class="easyui-panel" style="padding:10px" data-options="title:'收款人信息'">
			<table>
				<tr>
					<td align="right">收款人：</td>
					<td>${withdraw.reciverUserName }</td>
					<td align="right">手机号：</td>
					<td>${withdraw.reciverMobile }</td>
				</tr>
				<c:choose>
					<c:when test="${withdraw.requestType.id == 1 }">
						<tr>
							<td align="right">收款银行：</td>
							<td>${withdraw.reciverBank }</td>
							<td align="right">收款账号：</td>
							<td>${withdraw.reciverAccount }</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td align="right">收款账号：</td>
							<td colspan="3">${withdraw.reciverAccount }</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<td align="right">提现金额：</td>
					<td colspan="3">${withdraw.amountStr }</td>
				</tr>
			</table>
		</div>
		<div class="easyui-panel" style="padding:10px" data-options="title:'提现处理'">
			<table>
				<tr>
					<td align="right">姓名：</td>
					<td>${withdraw.user.username }</td>
				</tr>
				<tr>
					<td align="right">手机号：</td>
					<td>${withdraw.user.mobile }</td>
				</tr>
				<tr>
					<td align="right">实提金额（元）：</td>
					<td>
						<input type="hidden" name="id" value="${withdraw.id }">
						<input type="hidden" id="amountStr" value="${withdraw.amountStr }"/>
						<c:choose>
							<c:when test="${handleType == 'view' }">
								${withdraw.actualAmountStr }
							</c:when>
							<c:otherwise>
								<input class="easyui-textbox" data-options="" id="actualAmountStr" name="actualAmountStr" value="${empty withdraw.actualAmountStr ? withdraw.amountStr : withdraw.actualAmountStr }" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<c:if test="${handleType != 'view' }">
					<tr>
						<td align="right"></td>
						<td>
							<a href="javascript:handleWithdrawRequest()" class="easyui-linkbutton" style="line-height:24px;height:24px;width:175px;background:#F36F22;color:white">确认提现金额</a>
						</td>
					</tr>
					<tr>
						<td align="right"></td>
						<td>
							<p style="color:blue;">确认后不可再修改，请仔细确认。</p>
							<p style="color:blue;">若实提金额小于提现金额，则多出的提现金额（未体现）退回彩民备用金。</p>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</form>
</div>
