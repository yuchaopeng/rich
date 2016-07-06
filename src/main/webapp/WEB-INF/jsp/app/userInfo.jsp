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
			<div class="header_left">&nbsp;</div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<div class="user_amount_info">
				<table>
					<tr>
						<td align="right">总存入：</td>
						<td class="user_amount_info_td"><span class="user_amount">${userAccountForm.totalAdd }</span>元</td>
						<td align="right">备用金：</td>
						<td class="user_amount_info_td"><span class="user_amount">${userAccountForm.balance }</span>元</td>
					</tr>
					<tr>
						<td align="right">总中奖：</td>
						<td class="user_amount_info_td"><span class="user_amount">${userAccountForm.totalWin }</span>元</td>
						<td align="right">提现中：</td>
						<td class="user_amount_info_td"><span class="user_amount">${userAccountForm.totalWithdraw }</span>元</td>
					</tr>
					<tr>
						<td align="right">总分成：</td>
						<td class="user_amount_info_td"><span class="user_amount">${userAccountForm.totalDivided }</span>元</td>
						<td align="right">投注中：</td>
						<td class="user_amount_info_td"><span class="user_amount">${userAccountForm.totalBetting }</span>元</td>
					</tr>
				</table>
			</div>
			<div class="user_amount_info_handle">
				<div>
					<input class="linkbutton yellow" type="button" onclick="rechargeSelect()" value="充值"/>
				</div>
				<div>
					<input class="linkbutton lightGrey" type="button" onclick="withdrawSelect()" value="提现"/>
				</div>
			</div>
			
			<div class="user_info">
				<ul>
					<li>
						<a href="/app/userAccountDetail">
							<table>
								<tr>
									<td>
										资金明细
									</td>
									<td class="right_tag">
										〉
									</td>
								</tr>
							</table>
						</a>
					</li>
					<li>
						<a href="/app/userDetailInfo">
							<table>
								<tr>
									<td>
										个人信息
									</td>
									<td class="right_tag">
										〉
									</td>
								</tr>
							</table>
						</a>
					</li>
				</ul>
			</div>
			<div class="user_info about_website">
				<ul>
					<!-- <li>
						<a href="/app/viewSystemMessage?systemMessageId=-3&returnUrl=/app/userInfo">
							<table>
								<tr>
									<td>
										关于帮富
									</td>
									<td class="right_tag">
										〉
									</td>
								</tr>
							</table>
						</a>
					</li> -->
					<li>
						<a href="javascript:logout()">
							<table>
								<tr>
									<td>
										退出
									</td>
									<td class="right_tag">
										〉
									</td>
								</tr>
							</table>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param value="tab_userInfo" name="currentTab"/>
		</jsp:include>
	</div>
	
	<script type="text/javascript">
		function rechargeSelect(){
			window.location.href="/app/toRechargeSelect";
		}
		
		function withdrawSelect(){
			window.location.href="/app/toWithdrawSelect";
		}
		
		function logout(){
			$.dialog({
                content : '您确认要退出当前账号吗?',
                ok : function() {
                	window.location.href="/app/logout";
                    return true;
                },
                cancel:function(){
                },
                lock : true
            });
		}
	</script>
</body>
</html>