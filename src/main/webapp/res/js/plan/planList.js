$(function(){
	$("#plan_list").datagrid({
		url:getRootPath()+"/plan/planListJson",
		columns:[[
		          {field:'planName',title:'计划名称',width:$(this).width() * 0.25,align:'center',formatter: function(value, row, index) {
	                    var abValue = value;
	                    if (value.length>=40) {
	                        abValue = value.substring(0,19) + "...";
	                    }
	                    var content = '<a href="javascript:void(0)" title="' + value + '" class="plan_name_tooltip">' + abValue + '</a>';
	                    return content;
	                }
		          },
		          {field:'expertName',title:'专家',width:$(this).width() * 0.1,align:'center'},
		          {field:'firstStr',title:'第一档',width:$(this).width() * 0.1,align:'center'},
		          {field:'secondStr',title:'第二档',width:$(this).width() * 0.1,align:'center'},
		          {field:'thirdStr',title:'第三档',width:$(this).width() * 0.1,align:'center'},
		          {field:'fourthStr',title:'第四档',width:$(this).width() * 0.1,align:'center'},
		          {field:'stateVal',title:'生效状态',width:$(this).width() * 0.1,align:'center'},
		          {field:'id',title:'操作',width:$(this).width() * 0.1,align:'center',formatter:function(value,row,index){
		        	  var planAction = "";
		        	  if(row.state == 2){
		        		  planAction = "<a href=\"javascript:updatePlanState('"+row.id+"','1')\">失效</a>";
		        	  }else{
		        		  planAction = "<a href=\"javascript:updatePlanState('"+row.id+"','2')\">生效</a>";
		        	  }
		        	  var updateLink = "<a href=\"javascript:saveOrUpdatePlan('"+row.id+"')\">修改</a>";
		        	  
		        	  return planAction + " "+ updateLink;
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
			id:"add",
			text:"新建计划",
			iconCls: 'icon-add',
			handler:function(){
				saveOrUpdatePlan('');
			}
		}]
	});
	
	var page = $('#plan_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
});


function resetPlanForm(){
	$('#searchPlanForm').form('clear');
	$("#paramsState").combobox('setValue', ""); 
}

function searchPlanForm(){
	$("[id^='params_']").each(function(){
		var val = $(this).textbox("getValue");
		$(this).textbox("setValue",$.trim(val))
	});
	
	/*var createDateStart = $("#paramsCreateDateStart").textbox("getValue");
	var createDateEnd = $("#paramsCreateDateEnd").textbox("getValue");
	
	if(createDateStart != "" && createDateEnd != ""){
		if(createDateEnd <= createDateStart){
			alertMessage("提示","结束日期必须大于开始日期");
		}
	}*/
	
	$("#plan_list").datagrid("load", sy.serializeObject($("#searchPlanForm").form()));
}

function saveOrUpdatePlan(planId){
	var url = getRootPath()+"plan/toSaveOrUpdatePlan?planId="+planId;
	parent.window.$("#save_or_update_plan").dialog({
		title:planId == '' ? '新建计划' : '修改计划',
		href:url,
		width:580,
		height:327,
		modal:true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				parent.window.$("#save_or_update_plan").dialog('close');
			}
		}],
		onClose:function(){
			$("#plan_list").datagrid('reload');
		}
	});
}

function selectExpert(){
	
	parent.window.$("#dialog_div").dialog({
		title:"选择专家",
		href:getRootPath()+"userManage/toSelectUser?queryUserTypes=4&state=1",
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
				$("#paramsExpertId").val(userId);
				$("#paramsExpertUsername").textbox("setValue",userName);
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


/*生效失效操作*/
function updatePlanState(planId,state){
	var stateUpdateName = "";
	
	if(state == 2){
		stateUpdateName = "生效";
	}else if(state == 1){
		stateUpdateName = "失效";
	}else{
		alertMessage("提示","无效的操作类型");
		return;
	}
	
	parent.window.$.messager.confirm("提示","确定要进行“"+stateUpdateName+"”操作吗?",function(data){
		 if (data) {  
			 $.ajax({
				url : getRootPath()+'plan/updatePlanState',
				data : {
					"planId":planId,
					"state":state
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					var result = data.result;
					if (result == 0) {
						$("#plan_list").datagrid("reload");
					}
					alertMessage("提示",data.message);
				}
			});
         }  
	});
}