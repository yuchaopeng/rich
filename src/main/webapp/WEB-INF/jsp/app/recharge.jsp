<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/appCommonInclude.jsp" %>
</head>
<body>
	<div class="mobile_content">
		<header class="theme-header">
			<div class="header_left"><a href="/app/toRechargeSelect">〈充值</a></div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<form action="/app/recharge" method="post" id="rechargeForm">
				<input type="hidden" name="id" value="${rechargeRequest.id }"/>
				<input type="hidden" name="requestType.id" value="${rechargeRequest.requestType.id }"/>
				<input type="hidden" id="rechargeResult" value="${rechargeResult }"/>
				
				<input type="hidden" name="reciverUserName" value="${rechargeRequest.reciverUserName }"/>
				<input type="hidden" name="reciverBank" value="${rechargeRequest.reciverBank }"/>
				<input type="hidden" name="reciverAccount" value="${rechargeRequest.reciverAccount }"/>
				<input type="hidden" name="reciverMobile" value="${rechargeRequest.reciverMobile }"/>
				
				<div style="padding:5px 15px;color:grey;line-height:20px;height:20px;">
					资金存入信息
				</div>
				<div class="user_recharge_select_info user_recharge_info">
					<ul>
						<li>
							<table>
								<tr>
									<td style="width:40%">
										付款人
									</td>
									<td style="width:60%">
										<input type="text" class="text_input" name="paymentUserName" value="${rechargeRequest.paymentUserName }" id="paymentUserName" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
						<li>
							<table>
								<tr>
									<td style="width:40%">
										手机号
									</td>
									<td style="width:60%">
										<input type="text" class="text_input" name="paymentMobile" id="paymentMobile" value="${rechargeRequest.paymentMobile }" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
						<c:if test="${rechargeRequest.requestType.id == 1 }">
							<li>
								<table>
									<tr>
										<td style="width:40%">
											付款银行
										</td>
										<td style="width:60%">
											<input type="text" class="text_input" name="paymentBank" id="paymentBank" value="${rechargeRequest.paymentBank }" style="width:100%"/>
										</td>
									</tr>
								</table>
							</li>
						</c:if>
						<li>
							<table>
								<tr>
									<td style="width:40%">
										<c:choose>
											<c:when test="${rechargeRequest.requestType.id == 1 }">
												付款账号
											</c:when>
											<c:when test="${rechargeRequest.requestType.id == 3 }">
												支付宝账号
											</c:when>
											<c:when test="${rechargeRequest.requestType.id == 2 }">
												微信账号
											</c:when>
											<c:otherwise>
												付款账号
											</c:otherwise>
										</c:choose>
									</td>
									<td style="width:60%">
										<input type="text" class="text_input" name="paymentAccount" id="paymentAccount" value="${rechargeRequest.paymentAccount }" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
						<li>
							<table style="border-bottom:none;">
								<tr>
									<td style="width:40%">
										存入金额（元）
									</td>
									<td style="width:60%">
										<input type="text" class="text_input" name="rechargeAmountStr" id="rechargeAmountStr" value="${rechargeRequest.rechargeAmountStr }" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
			</form>
			<div class="user_recharge_select_info user_recharge_info" style="margin-top:10px;">
				<ul>
					<li>
						<table>
							<tr>
								<td style="width:50%">
									收款人
								</td>
								<td style="width:50%" align="right">
									${rechargeRequest.reciverUserName}
								</td>
							</tr>
						</table>
					</li>
					<c:if test="${rechargeRequest.requestType.id == 1 }">
						<li>
							<table>
								<tr>
									<td style="width:50%">
										收款银行
									</td>
									<td style="width:50%" align="right">
										${rechargeRequest.reciverBank }
									</td>
								</tr>
							</table>
						</li>
					</c:if>
					<li>
						<table>
							<tr>
								<td style="width:50%">
									<c:choose>
										<c:when test="${rechargeRequest.requestType.id == 1 }">
											收款账号
										</c:when>
										<c:when test="${rechargeRequest.requestType.id == 3 }">
											支付宝账号
										</c:when>
										<c:when test="${rechargeRequest.requestType.id == 2 }">
											微信账号
										</c:when>
										<c:otherwise>
											收款账号
										</c:otherwise>
									</c:choose>
								</td>
								<td style="width:50%" align="right">
									${rechargeRequest.reciverAccount }
								</td>
							</tr>
						</table>
					</li>
					<li>
						<table style="border-bottom:none;">
							<tr>
								<td style="width:50%">
									手机号
								</td>
								<td style="width:50%" align="right">
									${rechargeRequest.reciverMobile}
								</td>
							</tr>
						</table>
					</li>
				</ul>
			</div>
			<div class="help_buy_button_div">
				<input class="linkbutton yellow" onclick="recharge()" type="button" value="通知帮富查收款项"/>
			</div>
		</div>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param value="tab_userInfo" name="currentTab"/>
		</jsp:include>
	</div>
	
	<script type="text/javascript">
		$(function(){
			var rechargeResult = $("#rechargeResult").val();
			if(rechargeResult == 'true'){
				$.dialog({
                    content : '您的充值申请已经提交，运营人员会在1-3个工作日处理完成您的申请.',
                    ok : function() {
                        window.location.href="/app/userInfo"
                    },
                    lock : true
                });
			}
		});
	
		function recharge(){
			$("#rechargeForm input[type='text']").each(function(){
				$(this).val($.trim($(this).val()));
			});
			
			var paymentUserName = $("#paymentUserName").val();
			if(paymentUserName == ""){
				$.dialog({
                    content : '请输入付款人.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var paymentMobile = $("#paymentMobile").val();
			if(paymentMobile == ""){
				$.dialog({
                    content : '请输入付款人手机号.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			if(!/^1\d{10}$/.test(paymentMobile)){
				$.dialog({
                    content : '请输入正确的手机号',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var paymentBank = $("#paymentBank").val();
			if(paymentBank == ""){
				$.dialog({
                    content : '请输入付款银行.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			var paymentAccount = $("#paymentAccount").val();
			if(paymentAccount == ""){
				$.dialog({
                    content : '请输入付款账号.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var rechargeAmountStr = $("#rechargeAmountStr").val();
			if(rechargeAmountStr == ""){
				$.dialog({
                    content : '请输入充值金额.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			if(!/^[1-9]\d*(\.\d{1,2})?$|^0(\.\d{1,2})$/.test(rechargeAmountStr)){
				$.dialog({
                    content : '请输入正确的充值金额.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			} 
			
			
			$("#rechargeForm").submit();
		}
	</script>
</body>
</html>