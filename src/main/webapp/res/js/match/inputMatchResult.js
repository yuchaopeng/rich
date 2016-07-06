function cancelInputMatch(){
	$("#input_match_result").dialog("close");
}

function saveOrUpdateMatch(){
	
	var f = checkMatchForm();
	if(!f){
		return;
	}
	
	$("#inputMatchResultForm").form("submit",{
		success: function (data) {
			var json = eval("("+data+")");
			alertMessage("提示",json.message);
			$("#input_match_result").dialog("close");
		}
	});
}

function checkMatchForm(){
	$("[id^='detail_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val));
	});
	
	var first_winOption = $("#detail_winOption0").textbox("getValue");
	if(first_winOption == ''){
		alertMessage("提示","请输入第一场的“胜出选项”");
		return false;
	}
	var first_resultIsCheck=$("input:radio[name='matchDetails[0].matchResult']").is(":checked");
	if(!first_resultIsCheck){
		alertMessage("提示","请选择第一场的“赛果”");
		return false;
	}
	
	var matchDetailsLength = $("#matchDetailsLength").val();
	if(matchDetailsLength > 1){
		var second_winOption = $("#detail_winOption1").textbox("getValue");
		if(second_winOption == ''){
			alertMessage("提示","请输入第二场的“胜出选项”");
			return false;
		}
		var second_resultIsCheck=$("input:radio[name='matchDetails[1].matchResult']").is(":checked");
		if(!second_resultIsCheck){
			alertMessage("提示","请选择第二场的“赛果”");
			return false;
		}
	}
	var resultIsCheck = $("input:radio[name='match.matchResult']").is(":checked");
	if(!resultIsCheck){
		alertMessage("提示","请选择最终赛果");
		return false;
	}
	var detail_odds = $("#detail_odds").textbox("getValue");
	if(detail_odds == ''){
		alertMessage("提示","请输入“赔率”");
		return false;
	}
	
//	var first_matchName = $("#detail_matchName0").textbox("getValue");
//	var first_homeTeam = $("#detail_homeTeam0").textbox("getValue");
//	var first_visiteTeam = $("#detail_visiteTeam0").textbox("getValue");
//	var first_mbettingOption = $("#detail_bettingOption0").textbox("getValue");
//	
//	var second_matchName = $("#detail_matchName1").textbox("getValue");
//	
//	if(first_matchName == ''){
//		alertMessage("提示","请输入第一场的“赛事”");
//		return false;
//	}
//	if(first_homeTeam == ''){
//		alertMessage("提示","请输入第一场的“主队”");
//		return false;
//	}
//	if(first_visiteTeam == ''){
//		alertMessage("提示","请输入第一场的“客队”");
//		return false;
//	}
//	if(first_mbettingOption == ''){
//		alertMessage("提示","请输入第一场的“投注选项”");
//		return false;
//	}
//	
//	//如果第二场中的“赛事”字段不为空，则需要校验第二场相关字段必输
//	if(second_matchName != ''){
//		var second_homeTeam = $("#detail_homeTeam1").textbox("getValue");
//		var second_visiteTeam = $("#detail_visiteTeam1").textbox("getValue");
//		var second_mbettingOption = $("#detail_bettingOption1").textbox("getValue");
//		
//		if(second_homeTeam == ''){
//			alertMessage("提示","请输入第二场的“主队”");
//			return false;
//		}
//		if(second_visiteTeam == ''){
//			alertMessage("提示","请输入第二场的“客队”");
//			return false;
//		}
//		if(second_mbettingOption == ''){
//			alertMessage("提示","请输入第二场的“投注选项”");
//			return false;
//		}
//	}
	return true;
}

