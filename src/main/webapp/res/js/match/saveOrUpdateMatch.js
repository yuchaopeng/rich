function saveOrUpdateMatch(){
	
	var f = checkMatchForm();
	if(!f){
		return;
	}
	
	$("#saveOrUpdateMatchForm").form("submit",{
		success: function (data) {
			var json = eval("("+data+")");
			setMatchSaveSuccess(json);
			alertMessage("提示",json.message);
		}
	});
}

function checkMatchForm(){
	$("[id^='detail_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val));
	});
	
	var planId = $("#planId").combobox("getValue");
	if(planId == ""){
		alertMessage("提示","请选择计划.");
		return false;
	}
	
	var first_matchName = $("#detail_matchName0").textbox("getValue");
	var first_homeTeam = $("#detail_homeTeam0").textbox("getValue");
	var first_visiteTeam = $("#detail_visiteTeam0").textbox("getValue");
	var first_mbettingOption = $("#detail_bettingOption0").textbox("getValue");
	
	var second_matchName = $("#detail_matchName1").textbox("getValue");
	
	if(first_matchName == ''){
		alertMessage("提示","请输入第一场的“赛事”");
		return false;
	}
	if(first_homeTeam == ''){
		alertMessage("提示","请输入第一场的“主队”");
		return false;
	}
	if(first_visiteTeam == ''){
		alertMessage("提示","请输入第一场的“客队”");
		return false;
	}
	if(first_mbettingOption == ''){
		alertMessage("提示","请输入第一场的“投注选项”");
		return false;
	}
	
	//如果第二场中的“赛事”字段不为空，则需要校验第二场相关字段必输
	if(second_matchName != ''){
		var second_homeTeam = $("#detail_homeTeam1").textbox("getValue");
		var second_visiteTeam = $("#detail_visiteTeam1").textbox("getValue");
		var second_mbettingOption = $("#detail_bettingOption1").textbox("getValue");
		
		if(second_homeTeam == ''){
			alertMessage("提示","请输入第二场的“主队”");
			return false;
		}
		if(second_visiteTeam == ''){
			alertMessage("提示","请输入第二场的“客队”");
			return false;
		}
		if(second_mbettingOption == ''){
			alertMessage("提示","请输入第二场的“投注选项”");
			return false;
		}
	}
	return true;
}

function setMatchSaveSuccess(json){
	if(json.result == 0){
		var matchId = json.match.id;
		$("#matchForm_id").val(matchId);
		if(json.matchDetails != null && typeof(json.matchDetails) != 'undefined'){
			if(typeof(json.matchDetails[0]) != 'undefined'){
				var matchDetailId0 = json.matchDetails[0].id;
				$("#matchDetail_id0").val(matchDetailId0);
			}
			if(typeof(json.matchDetails[1]) != 'undefined'){
				var matchDetailId1 = json.matchDetails[1].id;
				$("#matchDetail_id1").val(matchDetailId1);
			}
		}
	}
}

function deleteSecondMatch(){
	var second_id = $("#matchDetail_id1").val();
	if(second_id == ''){
		alertMessage("提示","您还未保存第二场赛事.");
		return;
	}
	
	parent.window.$.messager.confirm("提示","确定要删除“第二场赛事”吗?<br/>删除后需要点击“保存”才会生效.",function(data){
		 if (data) {  
			$("#matchDetail_id1").val('')
			$("#detail_matchName1").textbox("setValue","");
			$("#detail_homeTeam1").textbox("setValue","");
			$("#detail_visiteTeam1").textbox("setValue","");
			$("#detail_bettingOption1").textbox("setValue","");
			alertMessage("提示","已删除，“保存”后生效.");
		 }
	});
}

function publishMatch(){
	var f = checkMatchForm();
	if(!f){
		return;
	}
	
	parent.window.$.messager.confirm("提示","确定要发布赛事吗？",function(data){
		if (data) {  
			//先保存，再发布
			$("#saveOrUpdateMatchForm").form("submit",{
				success: function (data) {
					var json = eval("("+data+")");
					setMatchSaveSuccess(json);
					if(json.result == 0){
						//保存成功，调用后台发布方法：
						var matchId = $("#matchForm_id").val();
						$.ajax({
							url : getRootPath()+'match/publishMatch',
							data : {
								"matchId":matchId
							},
							type : 'post',
							dataType : 'json',
							success : function(json_result) {
								if(json_result.result == 0){
									
								}
								alertMessage("提示",json_result.message);
								$("#save_or_update_match_detail").dialog("close");
							}
						});
					}else{
						alertMessage("提示",json.message);
					}
				}
			});
		}
	});
}

function matchPlanSelect(data){
	var matchPlanId = $("#matchPlanId").val();
	if(matchPlanId != ''){
		$('#planId').combobox('setValue',matchPlanId);
	}
}

function matchPlayMethodSelect_first(data){
	var playMethodId = $("#matchPlayMethodId_first").val();
	if(playMethodId != ''){
		$('#playMethod_first').combobox('setValue',playMethodId);
	}
}


function matchPlayMethodSelect_second(data){
	var playMethodId = $("#matchPlayMethodId_second").val();
	if(playMethodId != ''){
		$('#playMethod_second').combobox('setValue',playMethodId);
	}
}