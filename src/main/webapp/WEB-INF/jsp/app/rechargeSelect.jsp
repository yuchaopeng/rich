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
			<div class="header_left"><a href="/app/userInfo">〈充值</a></div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<div class="recharge_remark_info">
				<p>当您通过银行卡/支付宝/微信存入资金到<%=webSiteName %>后，需要在这里通知<%=webSiteName %>查收款项。</p>
				<p>
					<a href="/app/viewSystemMessage?systemMessageId=-1&returnUrl=/app/toRechargeSelect">了解存入资金流程</a>
				</p>
			</div>
			<div class="user_recharge_select_info">
				<ul>
					<li>
						<a href="/app/toRecharge?paymentMethodTypeId=1">
							<table>
								<tr>
									<td style="width:80%">
										<div class="datalist_item">
											<h4>通过银行卡存入了现金</h4>
											<p>通知<%=webSiteName %>入账</p>
										</div>
									</td>
								</tr>
							</table>
						</a>
					</li>
					<li>
						<a href="/app/toRecharge?paymentMethodTypeId=3">
							<table>
								<tr>
									<td style="width:80%">
										<div class="datalist_item">
											<h4>通过支付宝存入了现金</h4>
											<p>通知<%=webSiteName %>入账</p>
										</div>
									</td>
								</tr>
							</table>
						</a>
					</li>
					<li>
						<a href="/app/toRecharge?paymentMethodTypeId=2">
							<table style="border-bottom:none;">
								<tr>
									<td style="width:80%">
										<div class="datalist_item">
											<h4>通过微信存入了现金</h4>
											<p>通知<%=webSiteName %>入账</p>
										</div>
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
</body>
</html>