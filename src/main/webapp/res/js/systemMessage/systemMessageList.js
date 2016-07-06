$(function(){
	$("#system_message_list").datagrid({
		url:getRootPath()+"/systemMessage/systemMessageListJson",
		columns:[[
		          {field:'title',title:'标题',width:$(this).width() * 0.3,align:'center'},
		          {field:'stateVal',title:'类型',width:$(this).width() * 0.1,align:'center'},
		          {field:'userName',title:'创建人',width:$(this).width() * 0.1,align:'center'},
		          {field:'createDateStr',title:'创建时间',width:$(this).width() * 0.12,align:'center'},
		          {field:'lastUpdateDateStr',title:'修改时间',width:$(this).width() * 0.12,align:'center'},
		          {field:'id',title:'操作',width:$(this).width() * 0.2,align:'center',formatter:function(value,row,index){
		        	  var link = "";
		        	  if(row.id > 0){
			        	  if(row.state == 1){
			        		  link += "<a href=\"javascript:updateSystemMessageState('"+row.id+"',2,'发布')\">发布</a>";
			        		  link += " <a href=\"javascript:saveOrUpdateSystemMessage('"+row.id+"','')\">修改</a>";
			        		  link += " <a href=\"javascript:deleteSystemMessageState('"+row.id+"')\">删除</a>";
			        	  }else if(row.state == 2){
			        		  link = "<a href=\"javascript:updateSystemMessageState('"+row.id+"',1,'下线')\">下线</a>";
			        		  link += " <a href=\"javascript:saveOrUpdateSystemMessage('"+row.id+"','view')\">查看</a>";
			        	  }
		        	  }else{
		        		  link += " <a href=\"javascript:saveOrUpdateSystemMessage('"+row.id+"','')\">修改</a>";
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
		},
		toolbar:[{
			id:"add",
			text:"新增系统信息",
			iconCls: 'icon-add',
			handler:function(){
				saveOrUpdateSystemMessage('','');
			}
		}]
	});
	
	var page = $('#system_message_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
});


function resetSystemMessageForm(){
	$('#systemMessageForm').form('clear');
	$("#paramsState").combobox('setValue', ""); 
}

function searchSystemMessageForm(){
	$("[id^='params_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val))
	});
	
	$("#system_message_list").datagrid("load", sy.serializeObject($("#systemMessageForm").form()));
}

function saveOrUpdateSystemMessage(systemMessageId,handleType){
	window.location.href="/systemMessage/toSaveOrUpdateSystemMessage?systemMessageId="+systemMessageId+"&handleType="+handleType;
}

function updateSystemMessageState(systemMessageId,state,handleName){
	var message = "您确定进行“"+handleName+"”操作吗？";
	parent.window.$.messager.confirm("提示",message,function(data){
		 if (data) {  
			 $.ajax({
				url : '/systemMessage/updateState',
				data : {
					"systemMessageId":systemMessageId,
					"state":state
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					var result = data.result;
					if (result == 0) {
						$("#system_message_list").datagrid("reload");
					}
					alertMessage("提示",data.message);
				}
			});
       }  
	});
}

function deleteSystemMessageState(systemMessageId){
	var message = "您确定进行“删除”操作吗？";
	parent.window.$.messager.confirm("提示",message,function(data){
		 if (data) {  
			 $.ajax({
				url : '/systemMessage/delete',
				data : {
					"systemMessageId":systemMessageId
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					var result = data.result;
					if (result == 0) {
						$("#system_message_list").datagrid("reload");
					}
					alertMessage("提示",data.message);
				}
			});
       }  
	});
}

