$(function(){
	$("#recharge_request_list").datagrid({
		url:getRootPath()+"/rechargeRequest/rechargeRequestListJson",
		columns:[[
		          {field:'requestDate',title:'申请时间',width:$(this).width() * 0.1,align:'center'},
		          {field:'requestTypeName',title:'充值类型',width:$(this).width() * 0.1,align:'center'},
		          {field:'userName',title:'姓名',width:$(this).width() * 0.1,align:'center'},
		          {field:'userMobile',title:'手机号',width:$(this).width() * 0.1,align:'center'},
		          {field:'amountStr',title:'充值金额',width:$(this).width() * 0.1,align:'center'},
		          {field:'handleDate',title:'处理时间',width:$(this).width() * 0.1,align:'center'},
		          {field:'actualAmountStr',title:'实际金额',width:$(this).width() * 0.1,align:'center'},
		          {field:'stateVal',title:'确认情况',width:$(this).width() * 0.1,align:'center'},
		          {field:'id',title:'操作',width:$(this).width() * 0.15,align:'center',formatter:function(value,row,index){
		        	  var link = "";
		        	  if(row.state == 1){
		        		  link = "<a href=\"javascript:toRechargeRequestDetail('"+row.id+"','')\">处理</a>";
		        	  }else{
		        		  link = "<a href=\"javascript:toRechargeRequestDetail('"+row.id+"','view')\">查看</a>";
		        	  }
		        	  
		        	  return link;
		          }}
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
	
	var page = $('#recharge_request_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
});


function resetRechargeForm(){
	$('#searchRechargeForm').form('clear');
	$("#paramsState").combobox('setValue', ""); 
	$("#paramsRequestType").combobox('setValue', ""); 
}

function searchRechargeForm(){
	$("[id^='params_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val))
	});
	
	$("#recharge_request_list").datagrid("load", sy.serializeObject($("#searchRechargeForm").form()));
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