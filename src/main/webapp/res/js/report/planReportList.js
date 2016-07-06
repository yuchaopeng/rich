$(function(){
	$("#plan_report_list").datagrid({
		url:getRootPath()+"/report/planReportListJson",
		columns:[[
		          {field:'reportDateStr',title:'日期',width:$(this).width() * 0.15,align:'center'},
		          {field:'planName',title:'计划',width:$(this).width() * 0.25,align:'center'},
		          {field:'bettingAmountStr',title:'投注（元）',width:$(this).width() * 0.1,align:'center'},
		          {field:'winAmountStr',title:'中奖（元）',width:$(this).width() * 0.1,align:'center'},
		          {field:'commissionAmountStr',title:'佣金（元）',width:$(this).width() * 0.1,align:'center'},
		          {field:'recommenderAmountStr',title:'推荐人分成（元）',width:$(this).width() * 0.1,align:'center'},
		          {field:'expertAmountStr',title:'专家分成（元）',width:$(this).width() * 0.1,align:'center'},
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
	
	var page = $('#plan_report_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
});


function resetPlanReportForm(){
	$('#searchPlanReportForm').form('clear');
	$("#paramsState").combobox('setValue', ""); 
	$("#paramsRequestType").combobox('setValue', ""); 
}

function searchPlanReportForm(){
	$("[id^='params_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val))
	});
	
	$("#plan_report_list").datagrid("load", sy.serializeObject($("#searchPlanReportForm").form()));
}

function downloadReport(){
	$("#searchPlanReportForm").attr("action","/report/downloadPlanReport");
	$("#searchPlanReportForm").submit();
}