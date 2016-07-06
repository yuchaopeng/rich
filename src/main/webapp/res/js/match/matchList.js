$(function(){
	$("#match_list").datagrid({
		url:getRootPath()+"/match/matchListJson",
		columns:[[
		          {field:'createDateSimple',title:'时间',width:$(this).width() * 0.15,align:'center'},
		          {field:'planName',title:'计划',width:$(this).width() * 0.2,align:'center'},
		          {field:'stateVal',title:'状态',width:$(this).width() * 0.1,align:'center'},
		          {field:'firstResultVal',title:'第一场赛果',width:$(this).width() * 0.1,align:'center'},
		          {field:'secondResultVal',title:'第二场赛果',width:$(this).width() * 0.1,align:'center'},
		          {field:'matchResultVal',title:'彩果',width:$(this).width() * 0.1,align:'center'},
		          {field:'id',title:'操作',width:$(this).width() * 0.2,align:'center',formatter:function(value,row,index){
		        	  
		        	  var canEdit = $("#canEdit").val();
		        	  if(canEdit == 'false'){
		        		  return "";
		        	  }else{
			        	  var publishMatchLink = "<a href=\"javascript:toPublishMatch('"+row.id+"')\">发布</a>";
			        	  var matchResultLinkName = "输入赛果";
			        	  if(row.state == 3){
			        		  matchResultLinkName = "修改赛果";
			        	  }
			        	  var inputMatchResultLink = "<a href=\"javascript:toInputMatchResult('"+row.id+"')\">"+matchResultLinkName+"</a>";
			        	  var viewMatchLink = "<a href=\"javascript:toViewMatch('"+row.id+"')\">查看</a>";
			        	  
			        	  var calPrizeLink = "<a href=\"javascript:calPrize('"+row.id+"')\">计奖</a>";
			        	  
			        	  var link = "";
			        	  if(row.state == 1){
			        		  link = publishMatchLink;
			        	  }else if(row.state == 2){
			        		  link = inputMatchResultLink;
			        	  }else if(row.state == 3){
			        		  link = inputMatchResultLink + " " + viewMatchLink+" "+calPrizeLink;
			        	  }else if(row.state == 4){
			        		  link = viewMatchLink;
			        	  }
			        	  return link;
		        	  }
		          }}
		      ]],
		 onLoadSuccess:function(data){
			$(".plan_name_tooltip").tooltip({
                onShow: function(){
                    $(this).tooltip('tip').css({ 
                        width:'250',
                        boxShadow: '1px 1px 3px #292929',
                    });
                }
            }); 
		 },
		pagination:true,
		singleSelect: true,
		rownumbers:true,
		onLoadError:function(){
			parent.window.$.messager.alert("提示","系统出错，请联系管理员！");
		},
		toolbar:[{
			id:"add_match",
			text:"新增赛事",
			iconCls: 'icon-add',
			handler:function(){
				saveOrUpdateMatchDetail('');
			}
		}]
	});
	
	var page = $('#match_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
	
	var canEdit = $("#canEdit").val();
	if(canEdit == 'false'){
		$("#add_match").linkbutton('disable');
	}
	  
});


function resetMatchForm(){
	$('#searchMatchForm').form('clear');
	$("#paramsState").combobox('setValue', ""); 
	$("#paramsMatchResult").combobox('setValue', "");
}

function searchMatchForm(){
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
	
	$("#match_list").datagrid("load", sy.serializeObject($("#searchMatchForm").form()));
}

function saveOrUpdateMatchDetail(matchId){
	var url = getRootPath()+"match/toSaveOrUpdateMatch?matchId="+matchId;
	parent.window.$("#save_or_update_match_detail").dialog({
		title:matchId == '' ? '新增赛事' : '发布赛事',
		href:url,
		width:800,
		height:500,
		modal:true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				parent.window.$("#save_or_update_match_detail").dialog('close');
			}
		}],
		onClose:function(){
			$("#match_list").datagrid('reload');
		}
	});
}

function toPublishMatch(matchId){
	saveOrUpdateMatchDetail(matchId);
}

function toInputMatchResult(matchId,actionType){
	if(typeof(actionType) == 'undefined'){
		actionType = '';
	}
	var url = getRootPath()+"match/toInputMatchResult?matchId="+matchId+"&actionType="+actionType;
	parent.window.$("#input_match_result").dialog({
		title:'输入赛果',
		href:url,
		width:795,
		height:490,
		modal:true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				parent.window.$("#input_match_result").dialog('close');
			}
		}],
		onClose:function(){
			$("#match_list").datagrid('reload');
		}
	});
}

function toViewMatch(matchId){
	toInputMatchResult(matchId,'view');
}

function calPrize(matchId){
	parent.window.$.messager.confirm("操作提示", "确定要进行计奖操作吗？", function (data) {  
        if (data) {  
        	var url = getRootPath()+"match/calPrize?matchId="+matchId;
        	$.ajax({
        		type : 'POST',
        		url : url,
        		success : function(data){
        			var json = eval("("+data+")");
        			alertMessage("提示",json.message);
        			$("#match_list").datagrid('reload');
        		},
        	}); 
        }
    });  
}