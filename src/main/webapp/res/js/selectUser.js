function selectUser(title,queryUserTypes,state,callback){
	if(typeof(queryUserTypes) == undefined){
		queryUserTypes = '';
	}
	
	if(typeof(state) == undefined){
		state == '';
	}
	parent.window.$("#dialog_div").dialog({
		title:title,
		href:getRootPath()+"userManage/toSelectUser?queryUserTypes='"+queryUserTypes+"'&state="+state,
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
				
				callback(rows);
				
				/*var userId = rows[0].id;
				var userName = rows[0].username;
				$("#paramsRecommenderId").val(userId);
				$("#paramsRecommenderUsername").textbox("setValue",userName);
				parent.window.$("#dialog_div").dialog('close');*/
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