$(function(){
	$("#betting_list").datagrid({
		url:getRootPath()+"/match/bettingListJson",
		columns:[[
		          {field:'createDate',title:'时间',width:$(this).width() * 0.1,align:'center'},
		          {field:'planName',title:'计划',width:$(this).width() * 0.15,align:'center',formatter: function(value, row, index) {
	                    var abValue = value;
	                    if (value.length>=40) {
	                        abValue = value.substring(0,19) + "...";
	                    }
	                    var content = '<a href="javascript:void(0)" title="' + value + '" class="plan_name_tooltip">' + abValue + '</a>';
	                    return content;
	                }
		          },
		          {field:'matchStateVal',title:'赛事状态',width:$(this).width() * 0.1,align:'center'},
		          {field:'stateVal',title:'投注状态',width:$(this).width() * 0.1,align:'center'},
		          {field:'username',title:'姓名',width:$(this).width() * 0.1,align:'center'},
		          {field:'bettingAmountStr',title:'本轮投注金额',width:$(this).width() * 0.1,align:'center'},
		          {field:'remark',title:'备注',width:$(this).width() * 0.1,align:'center'},
		          {field:'lotteryUploadStateVal',title:'照片上传情况',width:$(this).width() * 0.1,align:'center'},
		          {field:'id',title:'操作',width:$(this).width() * 0.1,align:'center',formatter:function(value,row,index){
		        	  
		        	  var canEdit = $("#canEdit").val();
		        	  if(canEdit == 'false'){
		        		  return "";
		        	  }else{
			        	  var uploadLink = "<a href=\"javascript:toUploadLottery('"+row.id+"')\">上传</a>";
			        	  var cancelLink = "<a href=\"javascript:bettingRollback('"+row.id+"')\">未生效投注</a>";
			        	  var viewLink = "<a href=\"javascript:toViewUploadLottery('"+row.id+"')\">查看</a>";
			        	  var updateLink = "<a href=\"javascript:toUploadLottery('"+row.id+"')\">修改</a>";
			        	  
			        	  var link = "";
			        	  var lotteryUploadState = row.lotteryUploadState;
			        	  var state = row.state;
			        	  if(state == 3){
				        	  if(lotteryUploadState == 1){
				        		  link = uploadLink + " " + cancelLink;
				        	  }else if(lotteryUploadState == 2){
				        		  link = viewLink + " " + updateLink;
				        	  }
			        	  }else if(state == 4){
			        		  link = "未生效投注";
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
		}
	});
	
	var page = $('#match_list').datagrid('getPager');
	$(page).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
});


function resetBettingForm(){
	$('#searchBettingForm').form('clear');
	$("#paramsMatchState").combobox('setValue', ""); 
	$("#paramsLotteryUploadState").combobox('setValue', "");
}

function searchBettingForm(){
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
	
	$("#betting_list").datagrid("load", sy.serializeObject($("#searchBettingForm").form()));
}

function bettingRollback(bettingRecordId){
	
	parent.window.$.messager.confirm("提示","确定要置为未生效投注？<br/>确定后，彩民的投注将退还，不再出票！！！",function(data){
		 if (data) {  
			 $.ajax({
				url : getRootPath()+'match/bettingRollback',
				data : {
					"bettingRecordId":bettingRecordId
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					var result = data.result;
					if (result == 0) {
						$("#betting_list").datagrid("reload");
					}
					alertMessage("提示",data.message);
				}
			});
        }  
	});
}

function toUploadLottery(bettingRecordId,type){
	if(typeof(type) == 'undefined'){
		type = '';
	}
	var url = getRootPath()+"match/toUploadLottery?bettingRecordId="+bettingRecordId+"&type="+type;
	parent.window.$("#upload_lottery").dialog({
		title:'上传彩票',
		href:url,
		width:795,
		height:490,
		modal:true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				parent.window.$("#upload_lottery").dialog('close');
			}
		}],
		onClose:function(){
			$("#betting_list").datagrid('reload');
		}
	});
}

function toViewUploadLottery(matchId){
	toUploadLottery(matchId,'view');
}