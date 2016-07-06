//createUserType：0.普通用户，1.管理员，2.运营用户，3.门店职员，4.专家
function saveOrUpdateUser(userId,datagridId,createUserType){
	var url = getRootPath()+"userManage/toSaveOrUpdateUser?userId="+userId+"&createUserType="+createUserType;
	
	var title = "新建用户";
	if(userId != ''){
		title = "修改用户";
	}
	
	var height = 600;
	if(createUserType == 1 || createUserType == 2 || createUserType == 3){
		height = 350;
	}
	
	parent.window.$("#edit_user_dialog_div").dialog({
		title:title,
		href:url,
		width:580,
		height:height,
		modal:true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				parent.window.$("#edit_user_dialog_div").dialog('close');
			}
		}],
		onClose:function(){
			$("#"+datagridId).datagrid('reload');
		}
	});
}

/*生效失效操作*/
function updateUserState(userId,state,datagridId){
	var stateUpdateName = "";
	
	if(state == 1){
		stateUpdateName = "生效";
	}else if(state == 2){
		stateUpdateName = "失效";
	}else{
		alertMessage("提示","无效的操作类型");
		return;
	}
	
	parent.window.$.messager.confirm("提示","确定要进行“"+stateUpdateName+"”操作吗?",function(data){
		 if (data) {  
			 $.ajax({
				url : getRootPath()+'/userManage/updateUserState',
				data : {
					"userId":userId,
					"state":state
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					var result = data.result;
					if (result == 0) {
						$("#"+datagridId).datagrid("reload");
					}
					alertMessage("提示",data.message);
				}
			});
         }  
	});
}

function resetPassword(userId){
	parent.window.$.messager.confirm("提示","确定要进行“重置密码”操作吗?",function(data){
		 if (data) {  
			 $.ajax({
				url : getRootPath()+'/userManage/resetPassword',
				data : {
					"userId":userId
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					alertMessage("提示",data.message);
				}
			});
        }  
	});
}