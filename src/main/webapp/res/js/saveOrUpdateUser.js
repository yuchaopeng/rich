$(function(){
	var userType = $("input[type='hidden'][name='userType']").val();
	
	var flag = true;
	if(userType == 1 || userType == 2 || userType == 3){
		flag = false;
	}
	
	if(flag){
		$("#commission_model_data").hide();
		$("#commission_model_data").datagrid({
			url:getRootPath()+"userManage/userAgreementJson?params['userId']="+$("#userForm_id").val(),
			columns:[[
			          {field:'agreementTypeName',title:'提成方式',width:160,align:'center'},
			          {field:'agreementValue',title:'提成比（%）',width:160,align:'center'},
			          {field:'createDate',title:'时间',width:160,align:'center'}
			      ]],
			pagination:true,
			rownumbers:true,
			onLoadError:function(){
				$.messager.alert("提示","系统出错，请联系管理员！");
			},
			toolbar:[{
				id:"add",
				text:"更新",
				iconCls: 'icon-reload',
				handler:function(){
					var userId = $("#userForm_id").val();
					if(userId == ""){
						alertMessage("提示","请先保存用户基本信息.");
						return;
					}
					
					parent.window.$("#add_user_agreement_dialog_div").dialog({
						title:"添加分成方式",
						href:getRootPath()+"userManage/toAddUserAgreement?userId="+userId,
						width:440,
						height:140,
						modal:true,
						buttons:[{
							text:'关闭',
							iconCls:'icon-cancel',
							handler:function(){
								parent.window.$("#add_user_agreement_dialog_div").dialog('close');
							}
						}],
						onClose:function(){
							var url = getRootPath()+"userManage/userAgreementJson?params['userId']="+$("#userForm_id").val();
							$("#commission_model_data").datagrid({url:url});
						}
					});
				}
			}]
		});
		
		var page = $('#commission_model_data').datagrid('getPager');
		$(page).pagination({  
	        pageSize: 5,//每页显示的记录条数，默认为10  
	        pageList: [5,10],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    }); 
	}
});

function chooseRecommenderDialog(){
	parent.window.$("#dialog_div").dialog({
		title:"选择推荐人",
		href:getRootPath()+"userManage/toSelectUser?queryUserTypes=0&state=1",
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
				$("#paramsRecommenderId").val(userId);
				$("#paramsRecommenderUsername").textbox("setValue",userName);
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

function saveOrUpdate(){
	
	$("[id^='userForm_']").each(function(){
		var val = $(this).val();
		$(this).val($.trim(val));
	});
	
	var f = checkMobile();
	if(!f){
		return;
	}
	
	var id = $("#userForm_id").val();
	
	if(id == ""){
		var password = $("#password").val();
		if(password == ""){
			alertMessage("提示","请输入密码.");
			return;
		}
		var repassword = $("#repassword").val();
		if(password != "" && repassword == ""){
			alertMessage("提示","请输入确认密码.");
			return;
		}
	}
	
	$("#saveOrUpdateUserForm").form("submit",{
		success: function (data) {
			var result = eval("("+data+")");
			if(result.result == 0){
				$("#saveOrUpdateUserForm input[name='id']").val(result.userinfo.id);
				$("#saveOrUpdateUserForm #createDate").textbox("setValue",result.userinfo.createDate);
			}
			alertMessage("提示",result.message);
		}
	});
}

function cancelSaveOrUpdate(){
	$("#edit_user_dialog_div").dialog('close');
}

function checkMobile(){
	
	var id = $("#userForm_id").val();
	if(id != ""){//如果已经保存过，则不用校验手机号是否已经注册过
		return true;
	}
	
	var isValid = $("#userForm_mobile").validatebox('isValid');
	if(!isValid){
		return false;
	}
	
	var mobile =  $("#userForm_mobile").val();
	mobile = $.trim(mobile);
	 $("#userForm_mobile").val(mobile);
	
	var flag = true;
	$.ajax({// 数据库比对身份证号码是否重复  
        async : false,  
        type : 'post',  
        url : getRootPath()+'userManage/checkMobile',  
        data : {  
            'mobile' : mobile  
        },  
        success : function(data) {  
        	var result = eval("("+data+")");
        	if(result.result != 0){
        		flag = false;
        		alertMessage("提示","该手机用户已存在");
        	}
        }  
    });  
	return flag;
}

function setUserAccount(obj){
	var val = $(obj).val();
	$("#userForm_account").val(val);
}