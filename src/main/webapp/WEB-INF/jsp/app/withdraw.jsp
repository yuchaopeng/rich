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
			<div class="header_left"><a href="/app/toWithdrawSelect">〈提现</a></div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<form action="/app/withdraw" method="post" id="withdrawForm">
				<input type="hidden" name="id" value="${withdrawRequest.id }"/>
				<input type="hidden" name="requestType.id" value="${withdrawRequest.requestType.id }"/>
				<input type="hidden" id="withdrawResult" value="${withdrawResult }"/>
				<input type="hidden" id="message" value="${message }"/>
				<input type="hidden" name="paymentUserName" value="${withdrawRequest.paymentUserName }"/>
				<input type="hidden" name="paymentBank" value="${withdrawRequest.paymentBank }"/>
				<input type="hidden" name="paymentAccount" value="${withdrawRequest.paymentAccount }"/>
				<input type="hidden" name="paymentMobile" value="${withdrawRequest.paymentMobile }"/>
				<div style="padding:5px 15px;color:grey;line-height:20px;height:20px;">
					资金提取信息
				</div>
				<div class="user_recharge_select_info user_recharge_info">
					<ul>
						<li>
							<table>
								<tr>
									<td style="width:40%">
										收款人
									</td>
									<td style="width:60%">
										<input type="text" class="text_input" name="reciverUserName" value="${withdrawRequest.reciverUserName }" id="reciverUserName" style="width:100%"/>
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
										<input type="text" class="text_input" name="reciverMobile" id="reciverMobile" value="${withdrawRequest.reciverMobile }" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
						<c:if test="${withdrawRequest.requestType.id == 1 }">
							<li>
								<table>
									<tr>
										<td style="width:40%">
											收款银行
										</td>
										<td style="width:60%">
											<input type="text" class="text_input" name="reciverBank" id="reciverBank" value="${withdrawRequest.reciverBank }" style="width:100%"/>
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
											<c:when test="${withdrawRequest.requestType.id == 1 }">
												收款账号
											</c:when>
											<c:when test="${withdrawRequest.requestType.id == 3 }">
												支付宝账号
											</c:when>
											<c:when test="${withdrawRequest.requestType.id == 2 }">
												微信账号
											</c:when>
											<c:otherwise>
												收款账号
											</c:otherwise>
										</c:choose>
									</td>
									<td style="width:60%">
										<input type="text" class="text_input" name="reciverAccount" id="reciverAccount" value="${withdrawRequest.reciverAccount }" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
						<li>
							<table style="border-bottom:none;">
								<tr>
									<td style="width:40%">
										提现金额（元）
									</td>
									<td style="width:60%">
										<input type="text" class="text_input" name="amountStr" id="amountStr" value="${withdrawRequest.amountStr }" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
				<div class="user_recharge_select_info user_recharge_info" style="margin-top:8px;">
					<ul>
						<li>
							<table style="border-bottom:none;">
								<tr>
									<td style="width:40%">
										登录密码
									</td>
									<td style="width:60%">
										<input type="password" class="text_input" name="loginPassword" id="loginPassword" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
				<div style="padding:10px 15px;color:grey;line-height:20px;height:20px;">
					资金申请提取后，<%=webSiteName %>会在两个工作日内完成资金转账，请您耐心等待。
				</div>
			</form>
			<div class="help_buy_button_div">
				<input class="linkbutton yellow" onclick="withdraw()" type="button" value="申请提现"/>
			</div>
		</div>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param value="tab_userInfo" name="currentTab"/>
		</jsp:include>
	</div>
	
	<script type="text/javascript">
		$(function(){
			var withdrawResult = $("#withdrawResult").val();
			if(withdrawResult == 'true'){
				$.dialog({
                    content : '您的提现申请已经提交，运营人员会在1-2个工作日处理完成您的申请.',
                    ok : function() {
                        window.location.href="/app/userInfo"
                    },
                    lock : true
                });
			}else if(withdrawResult == 'false'){
				var message = $("#message").val();
				$.dialog({
                    content : message,
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
			}
		});
	
		function withdraw(){
			$("#withdrawForm input[type='text']").each(function(){
				$(this).val($.trim($(this).val()));
			});
			
			var reciverUserName = $("#reciverUserName").val();
			if(reciverUserName == ""){
				$.dialog({
                    content : '请输入收款人.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var reciverMobile = $("#reciverMobile").val();
			if(reciverMobile == ""){
				$.dialog({
                    content : '请输入手机号.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			if(!/^1\d{10}$/.test(reciverMobile)){
				$.dialog({
                    content : '请输入正确的手机号',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var reciverBank = $("#reciverBank").val();
			if(reciverBank == ""){
				$.dialog({
                    content : '请输入收款银行.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			var reciverAccount = $("#reciverAccount").val();
			if(reciverAccount == ""){
				$.dialog({
                    content : '请输入收款账号.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var amountStr = $("#amountStr").val();
			if(amountStr == ""){
				$.dialog({
                    content : '请输入提现金额.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			if(!/^[1-9]\d*(\.\d{1,2})?$|^0(\.\d{1,2})$/.test(amountStr)){
				$.dialog({
                    content : '请输入正确的提现金额.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			} 
			
			if(amountStr < 100){
				$.dialog({
                    content : '提现金额最低100元.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var loginPassword = $("#loginPassword").val();
			if(loginPassword == ""){
				$.dialog({
                    content : '请输入登录密码.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			$("#withdrawForm").submit();
		}
	</script>
</body>
</html>