<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/appCommonInclude.jsp" %>

<script type="text/javascript">
	$(function(){
		
		$(".startAmount_button").click(function(){
			var tapId = $(this).attr("id");
			var classNames = $(this).attr("class");
			if(classNames.indexOf("yellow") != -1){
				$(this).removeClass("yellow");
				
				var inputStartAmount = $("#input_startAmount").val();
				if(inputStartAmount == ""){
					$("input[name='startAmountStr']").val("");
				}else{
					$("input[name='startAmountStr']").val(inputStartAmount);
				}
			}else{
				$(".startAmount_button").each(function(){
					if($(this).attr("id") == tapId){
						$(this).addClass("yellow");
						$("input[name='startAmountStr']").val($(this).val());
					}else{
						$(this).removeClass("yellow");
					}
				});
			}
		});
		
		$(".bettingModel_button").click(function(){
			var tapId = $(this).attr("id");
			$(".bettingModel_button").each(function(){
				if($(this).attr("id") == tapId){
					$(this).addClass("yellow");
					
					if($(this).attr("id") == 'juntou'){
						$("input[name='bettingModel']").val(1);
					}else if($(this).attr("id") == 'beitou'){
						$("input[name='bettingModel']").val(2);
					}
				}else{
					$(this).removeClass("yellow");
				}
			});
		});
		
		$("#helpBuy_button").click(function(){
			var startAmount = $("input[name='startAmountStr']").val();
			if(startAmount == ''){
				$.dialog({
                    content : '请选择起投金额金额.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var f = HelpBuy.checkInputAmount();
			if(!f){
				return;
			}
			
			var inputStartAmount = $("#input_startAmount").val();
			var clickFlag = false;
			$(".startAmount_button").each(function(){
				var classNames = $(this).attr("class");
				if(classNames.indexOf("yellow") != -1){
					clickFlag = true;//已经点击了投注金额
				}
			});
			if(inputStartAmount != "" && clickFlag){//同时输入了起投金额、并且选中了起投金额
				$.dialog({
                    content : '您同时选择、填写了投注金额.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			var bettingModel = $("input[name='bettingModel']").val();
			if(bettingModel == ''){
				$.dialog({
                    content : '请选择投注模式.',
                    ok : function() {
                        return true;
                    },
                    lock : true
                });
				return;
			}
			
			$("#saveOrUpdateHelpBuyForm").submit();
		});
		
		
		var start_amount = $("input[name='startAmountStr']").val();
		if(start_amount != ""){
			var f = false;
			$(".startAmount_button").each(function(){
				if($(this).val() == start_amount){
					$(this).addClass("yellow");
					f = true;
					return false;
				}
			});
			
			if(!f){
				$("#input_startAmount").val(start_amount);
			}
		}
		
		var betting_model = $("input[name='bettingModel']").val();
		if(betting_model != ""){
			if(betting_model == 1){
				$("#juntou").addClass("yellow");
			}else if(betting_model == 2){
				$("#beitou").addClass("yellow");
			}
		}
	});
	
	
	var HelpBuy = {
			inputStartAmount : function(){
				var startAmount = $("#input_startAmount").val();
				$("#input_startAmount").val($.trim(startAmount));
				startAmount = $("#input_startAmount").val();
				if(startAmount != ""){
					var f = this.checkInputAmount();
					if(!f){
						return;
					}
					$(".startAmount_button").each(function(){
						$(this).removeClass("yellow");
					});
					$("input[name='startAmountStr']").val(startAmount);
				}else{
					
					var clickFlag = false;
					var clickValue = "";
					$(".startAmount_button").each(function(){
						var classNames = $(this).attr("class");
						if(classNames.indexOf("yellow") != -1){
							clickFlag = true;//已经点击了投注金额
							clickValue = $(this).val();
						}
					});
					
					if(clickFlag){
						$("input[name='startAmountStr']").val(clickValue);
					}else{
						$("input[name='startAmountStr']").val("");
					}
				}
			},
			checkInputAmount : function(){
				var startAmount = $("#input_startAmount").val();
				$("#input_startAmount").val($.trim(startAmount));
				startAmount = $("#input_startAmount").val();
				if(startAmount != ""){
					var startAmount_first = $("#startAmount_first").val();
					if(!/^\d+$/.test(startAmount)){
						$.dialog({
		                    content : '输入的金额不为整数.',
		                    ok : function() {
		                    	$("#input_startAmount").focus().select();
		                        return true;
		                    },
		                    lock : true
		                });
						return false;
					}
					
					if(startAmount%100 != 0){
						$.dialog({
		                    content : '输入的金额不为100的倍数.',
		                    ok : function() {
		                    	$("#input_startAmount").focus().select();
		                        return true;
		                    },
		                    lock : true
		                });
						return false;
					}
					
					if(startAmount*1 < startAmount_first*1){
						$.dialog({
		                    content : '输入的金额不能小于最小投注金额.',
		                    ok : function() {
		                    	$("#input_startAmount").focus().select();
		                        return true;
		                    },
		                    lock : true
		                });
						$("#input_startAmount").val("");
						return false;
					}
					return true;
				}
				return true;
			}
	}
	
</script>
<title>帮买</title>
</head>
<body>
	<div class="mobile_content">
		<header class="theme-header">
			<div class="header_left"><a href="/app/matchList?planId=${plan.id }">〈返回</a></div>
			<div class="header_center"><%=webSiteName %></div>
			<div class="header_right"></div>
		</header>
		<div class="content">
			<form method="post" action="/app/saveOrUpdateHelpBuy" id="saveOrUpdateHelpBuyForm">
				<div class="match_plan_name">
					${plan.planName }
				</div>
				<div class="help_buy_content">
					帮买起投：
					<table style="width:100%">
						<tr>
							<td style="width:25%"><input class="linkbutton startAmount_button" type="button" id="startAmount_first" value="${plan.firstStr }"/></td>
							<td style="width:25%"><input class="linkbutton startAmount_button" type="button" id="startAmount_second" value="${plan.secondStr }"/></td>
							<td style="width:25%"><input class="linkbutton startAmount_button" type="button" id="startAmount_third" value="${plan.thirdStr }"/></td>
							<td style="width:25%"><input class="linkbutton startAmount_button" type="button" id="startAmount_fourth" value="${plan.fourthStr }"/></td>
						</tr>
						<tr>
							<td colspan="4">
								<input type="hidden" name="id" value="${record.id }"/>
								<input type="hidden" name="plan.id" value="${plan.id }"/>
								<input type="hidden" name="startAmountStr" value="${record.startAmountStr }"/>
								<input type="hidden" name="bettingModel" value="${record.bettingModel }"/>
								<input type="text" class="text_input" id="input_startAmount" placeholder="其他金额，100的整数倍" onblur="HelpBuy.inputStartAmount()" style="width:70%"/> 元
							</td>
						</tr>
					</table>
					投注模式：
					<table style="width:100%">
						<tr>
							<td style="width:50%"><input class="linkbutton bettingModel_button" id="juntou" type="button" value="均投"/></td>
							<td style="width:50%"><input class="linkbutton bettingModel_button" id="beitou" type="button" value="倍投"/></td>
						</tr>
					</table>
					<p><span style="font-weight:bold;">均投：</span>每次均按起投金额帮买。</p>
					<p><span style="font-weight:bold;">倍投：</span>初始按起投金额帮买，若未中，则第二次按初始起投金额的2倍投注；若还未中，则第三次按初始起投金额的4倍投注；以此类推，直至第四次投注，无论第四次中奖与否，第五次重新按起投金额帮买。</p>
					<p style="text-align:right;color:#87CEEB;">为不影响您的帮买，请准备充足备用金。</p>
					<p style="text-align:right;color:#87CEEB;">博彩有风险，投资需谨慎！</p>
				</div>
				<div class="help_buy_button_div">
					<input class="linkbutton yellow" id="helpBuy_button" type="button" value="立即帮买"/>
				</div>
			</form>
		</div>
		
		<jsp:include page="layout/footer.jsp" flush="true">
			<jsp:param name="currentTab" value="tab_planList" />
		</jsp:include>
	</div>
</body>
</html>