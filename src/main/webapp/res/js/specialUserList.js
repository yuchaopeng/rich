$(function(){
	$("#special_user_list").datagrid({
		url:getRootPath()+"/userManage/specialUserListJson",
		columns:[[
		          {field:'createDate',title:'创建时间',width:$(this).width() * 0.15,align:'center'},
		          {field:'userTypeName',title:'用户类别',width:$(this).width() * 0.1,align:'center'},
		          {field:'username',title:'姓名',width:$(this).width() * 0.1,align:'center'},
		          {field:'mobile',title:'手机号',width:$(this).width() * 0.1,align:'center'},
		          {field:'agreementTypeName',title:'提成方式',width:$(this).width() * 0.1,align:'center'},
		          {field:'agreementValue',title:'提成比（%）/值',width:$(this).width() * 0.1,align:'center'},
		          {field:'stateVal',title:'用户状态',width:$(this).width() * 0.1,align:'center'},
		          {field:'id',title:'结果',width:$(this).width() * 0.2,align:'center',formatter:function(value,row,index){
		        	  var resetPassword = "<a href=\"javascript:resetPassword("+row.id+")\">重置密码</a>";
		        	  
		        	  var userAction = "";
		        	  if(row.state == 1){
		        		  userAction = "<a href=\"javascript:updateUserState('"+row.id+"','2','special_user_list')\">失效</a>";
		        	  }else{
		        		  userAction = "<a href=\"javascript:updateUserState('"+row.id+"','1','special_user_list')\">生效</a>";
		        	  }
		        	  var updateLink = "<a href=\"javascript:saveOrUpdateUser('"+row.id+"','special_user_list','"+row.userType+"')\">修改</a>";
		        	  
		        	  return resetPassword + " " + userAction + " "+ updateLink;
		          }}
		      ]],
		pagination:true,
		singleSelect: true,
		rownumbers:true,
		onLoadError:function(){
			parent.window.$.messager.alert("提示","系统出错，请联系管理员！");
		},
		toolbar:[{
			id:"add",
			text:"新建特别用户",
			iconCls: 'icon-add',
			handler:toSelectSpecialUserType
		}]
	});
	
	var page = $('#special_user_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
});


function resetForm(){
	$('#searchForm').form('clear');
	$("#paramsState").combobox('setValue', ""); 
	$("#paramsUserType").combobox('setValue', ""); 
}

function searchForm(){
	$("[id^='params_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val))
	});
	
	var createDateStart = $("#paramsCreateDateStart").textbox("getValue");
	var createDateEnd = $("#paramsCreateDateEnd").textbox("getValue");
	
	if(createDateStart != "" && createDateEnd != ""){
		if(createDateEnd <= createDateStart){
			alertMessage("提示","结束日期必须大于开始日期");
		}
	}
	
	$("#special_user_list").datagrid("load", sy.serializeObject($("#searchForm").form()));
}


function toSelectSpecialUserType(){
	parent.window.$("#special_user_type_choose").dialog({
		width:380,
		height:120,
		href:getRootPath()+"userManage/toSelectSpecialUserType",
		title:"选择需要创建的类型",
		modal:true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				parent.window.$("#special_user_type_choose").dialog('close');
			}
		}],
		onClose:function(){
			$('#special_user_list').datagrid("reload");
		}
	});
}
