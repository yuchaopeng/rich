function selectExpert(){
	parent.window.$("#dialog_div").dialog({
		title:"选择推荐人",
		href:getRootPath()+"userManage/toSelectUser?queryUserTypes=4&state=1",
		width:800,
		height:570,
		modal:true,
		buttons:[{
			text:'确定',
			iconCls:'icon-ok',
			handler:function(){
				var rows = parent.window.$("#choose_user_list").datagrid('getSelections');
				var length = rows.length;
				if(length < 1){
					alertMessage("提示","请选择推荐人");
					return;
				}
				
				if(length > 1){
					alertMessage("提示","只能选择一个推荐人");
					return;
				}
				var userId = rows[0].id;
				var userName = rows[0].username;
				var mobile = rows[0].mobile;
				$("#paramsExpertId").val(userId);
				$("#paramsExpertName").textbox("setValue",userName);
				$("#planForm_mobile").textbox("setValue",mobile);
				parent.window.$("#dialog_div").dialog('close');
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				parent.window.$("#dialog_div").dialog('close');
			}
		}]
	});
}

function saveOrUpdatePlan(){
	
	$("[id^='planForm_']").each(function(){
		var val = $(this).val();
		$(this).val($.trim(val));
	});
	
	var f = checkPlanName();
	if(!f){
		return;
	}
	
	var expertId = $("#paramsExpertId").val();
	if(expertId == ''){
		alertMessage("提示","请选择专家.");
		return;
	}
	
	var first = $("input[name='firstStr']").val();
	var second = $("input[name='secondStr']").val();
	var third = $("input[name='thirdStr']").val();
	var fourthStr = $("input[name='fourthStr']").val();
	if(first != "" && second != "" && first*1 >= second*1){
		alertMessage("提示","“第二档”必须大于“第一档”.");
		return;
	}
	
	if(second != "" && third != "" && second*1 >= third*1){
		alertMessage("提示","“第三档”必须大于“第二档”.");
		return;
	}
	
	if(third != "" && fourthStr != "" && third*1 >= fourthStr*1){
		alertMessage("提示","“第四档”必须大于“第三档”.");
		return;
	}
	
	$("#saveOrUpdatePlanForm").form("submit",{
		success: function (data) {
			var result = eval("("+data+")");
			if(result.result == 0){
				$("#saveOrUpdatePlanForm input[name='id']").val(result.planInfo.id);
				$("#saveOrUpdatePlanForm #createDate").textbox("setValue",result.planInfo.createDate);
				$("#saveOrUpdatePlanForm #planForm_mobile").textbox("setValue",result.planInfo.expertMobile);
				$("#cancelButton").hide();
			}
			alertMessage("提示",result.message);
		}
	});
}

function cancelSaveOrUpdatePlan(){
	$("#save_or_update_plan").dialog('close');
}

function checkPlanName(){
	
	var id = $("#planForm_id").val();
	if(id != ""){//如果已经保存过，则不用校验计划名称是否重复
		return true;
	}
	
	var isValid = $("#planForm_planName").validatebox('isValid');
	if(!isValid){
		return false;
	}
	
	var planName = $("#planForm_planName").val();
	
	planName = $.trim(planName);
	 $("#planForm_planName").val(planName)
	var flag = true;
	$.ajax({// 数据库比对身份证号码是否重复  
        async : false,  
        type : 'post',  
        url : getRootPath()+'plan/checkPlanName',  
        data : {  
            'planName' : planName
        },  
        success : function(data) {  
        	var result = eval("("+data+")");
        	if(result.result != 0){
        		flag = false;
        		alertMessage("提示","该计划名称已存在.");
        	}
        }  
    });  
	return flag;
}