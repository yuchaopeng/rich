$(function(){
	$("#user_detail_report_list").datagrid({
		url:getRootPath()+"/report/userDetailReportListJson",
		columns:[[
		          {field:'reportDateStr',title:'日期',width:$(this).width() * 0.05,align:'center'},
		          {field:'mobile',title:'手机',width:$(this).width() * 0.08,align:'center'},
		          {field:'userName',title:'姓名',width:$(this).width() * 0.08,align:'center'},
		          {field:'userTypeName',title:'用户类型',width:$(this).width() * 0.08,align:'center'},
		          {field:'planName',title:'计划',width:$(this).width() * 0.1,align:'center'},
		          {field:'bettingAmountStr',title:'投注金额',width:$(this).width() * 0.08,align:'center'},
		          {field:'commissionAmountStr',title:'佣金',width:$(this).width() * 0.08,align:'center'},
		          {field:'recommenderName',title:'推荐人',width:$(this).width() * 0.08,align:'center'},
		          {field:'recommenderAmountStr',title:'推荐人分成',width:$(this).width() * 0.08,align:'center'},
		          {field:'recommenderOtherAmountStr',title:'（推荐他人）分成',width:$(this).width() * 0.08,align:'center'},
		          {field:'expertAmountStr',title:'（作为专家）分成',width:$(this).width() * 0.08,align:'center'},
		          {field:'winAmountStr',title:'中奖金额',width:$(this).width() * 0.08,align:'center'}
		      ]],
		 onLoadSuccess:function(data){
		 },
		pagination:true,
		singleSelect: true,
		rownumbers:true,
		onLoadError:function(){
			parent.window.$.messager.alert("提示","系统出错，请联系管理员！");
		}
	});
	
	var page = $('#user_detail_report_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
});


function resetUserDetailReportForm(){
	$('#searchUserDetailReportForm').form('clear');
	$("#paramsState").combobox('setValue', ""); 
	$("#paramsUserType").combobox('setValue', ""); 
}

function searchUserDetailReportForm(){
	$("[id^='params_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val))
	});
	
	$("#user_detail_report_list").datagrid("load", sy.serializeObject($("#searchUserDetailReportForm").form()));
}

function toRechargeRequestDetail(rechargeRequestId,handleType){
	parent.window.$("#recharge_request_detail").dialog({
		title:"充值处理",
		href:"/rechargeRequest/toRechargeRequestDetail?rechargeRequestId="+rechargeRequestId+"&handleType="+handleType,
		width:500,
		height:570,
		modal:true,
		onClose:function(){
			$("#recharge_request_list").datagrid("reload");
		},
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				parent.window.$("#recharge_request_detail").dialog('close');
			}
		}]
	});
}


function chooseRecommender(){
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

function downloadReport(){
	$("#searchUserDetailReportForm").attr("action","/report/downloadUserDetailReport");
	$("#searchUserDetailReportForm").submit();
}