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
			<div class="header_left"><a href="/app/userInfo">〈修改密码</a></div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"><a href="javascript:updatePassword()">保存&nbsp;&nbsp;</a></div>
		</header>
		<div class="content" style="padding-top:10px;">
			<form action="/app/updatePassword" method="post" id="updatePasswordForm">
				<input type="hidden" name="userId" value="${user.id }"/>
				<input type="hidden" id="resultFlag" value="${result }"/>
				<input type="hidden" id="resultMessage" value="${message }"/>
				<div class="user_recharge_select_info user_recharge_info">
					<ul>
						<li>
							<table>
								<tr>
									<td style="width:40%">
										手机号
									</td>
									<td style="width:60%;height:30px;">
										${user.mobile }
									</td>
								</tr>
							</table>
						</li>
						<li>
							<table>
								<tr>
									<td style="width:40%">
										原密码
									</td>
									<td style="width:60%">
										<input type="password" class="text_input" name="sourcePassword" id="sourcePassword" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
						<li>
							<table>
								<tr>
									<td style="width:40%">
										新密码
									</td>
									<td style="width:60%">
										<input type="password" class="text_input" name="newPassword" id="newPassword" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
						<li>
							<table style="border-bottom:none;">
								<tr>
									<td style="width:40%">
										确认密码
									</td>
									<td style="width:60%">
										<input type="password" class="text_input" name="confirmPassword" id="confirmPassword" style="width:100%"/>
									</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
			</form>
		</div>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param value="tab_userInfo" name="currentTab"/>
		</jsp:include>
	</div>
	
	<script type="text/javascript">
		$(function(){
			var resultFlag = $("#resultFlag").val();
			var resultMessage = $("#resultMessage").val();
			
			if(resultFlag != null && resultFlag != ""){
				$.dialog({
	                content : resultMessage,
	                ok : function() {
	                	if(resultFlag != 0){
	                		return true;
	                	}else{
	                		window.location.href="/app/userInfo";
	                	}
	                },
	                lock : true
	            });
			}
		});
		
		
	
		function updatePassword(){
			$("#updatePasswordForm input[type='text']").each(function(){
				$(this).val($.trim($(this).val()));
			});
			
			var sourcePassword = $("#sourcePassword").val();
			if(sourcePassword == ""){
				$.dialog({
                    content : '请输入原密码.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var newPassword = $("#newPassword").val();
			if(newPassword == ""){
				$.dialog({
                    content : '请输入新密码.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/.test(newPassword)){
				$.dialog({
                    content : '新密码须为6位（含）以上数字与字符组合.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			if(newPassword == sourcePassword){
				$.dialog({
                    content : '新密码不能与原密码相同.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var confirmPassword = $("#confirmPassword").val();
			if(confirmPassword == ""){
				$.dialog({
                    content : '请输入确认密码.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			if(newPassword != confirmPassword){
				$.dialog({
                    content : '您输入的新密码和确认密码不一致，请重新输入.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			$("#updatePasswordForm").submit();
		}
	</script>
</body>
</html>