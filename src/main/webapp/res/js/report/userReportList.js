$(function(){
	$("#user_report_list").datagrid({
		url:getRootPath()+"/report/userReportListJson",
		columns:[[
		          {field:'reportDateStr',title:'时间',width:$(this).width() * 0.15,align:'center'},
		          {field:'mobile',title:'手机号',width:$(this).width() * 0.2,align:'center'},
		          {field:'userName',title:'姓名',width:$(this).width() * 0.2,align:'center'},
		          {field:'rechargeAmountStr',title:'充值',width:$(this).width() * 0.1,align:'center'},
		          {field:'withdrawAmountStr',title:'提现',width:$(this).width() * 0.1,align:'center'},
		          {field:'winAmountStr',title:'中奖',width:$(this).width() * 0.1,align:'center'},
		          {field:'recommenderOtherAmountStr',title:'（推荐他人）分成',width:$(this).width() * 0.1,align:'center'}
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
	
	var page = $('#user_report_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
});


function resetReportForm(){
	$('#searchReportForm').form('clear');
}

function searchReportForm(){
	$("[id^='params_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val))
	});
	
	$("#user_report_list").datagrid("load", sy.serializeObject($("#searchReportForm").form()));
}

function downloadReport(){
	$("#searchReportForm").attr("action","/report/downloadUserReport");
	$("#searchReportForm").submit();
}