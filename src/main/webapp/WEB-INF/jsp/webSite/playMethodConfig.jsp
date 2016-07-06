<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<%@include file="../common/backCommonInclude.jsp" %>
</head>
<body>
	<div style="padding:5px;">
		<table id="play_method_list">
		</table>
	</div>
	
	
	<script type="text/javascript">
		$(function(){
			$("#play_method_list").datagrid({
				url:"/webSite/playMethodConfigListJson",
				columns:[[
				          {field:'methodName',title:'玩法',width:$(this).width() * 0.2,align:'center'},
				          {field:'description',title:'备注',width:$(this).width() * 0.2,align:'center'},
				          {field:'used',title:'是否已用',width:$(this).width() * 0.2,align:'center',formatter:function(value,row,index){
				        	  if(row.used){
				        		  return "是";
				        	  }else{
				        		  return "否";
				        	  }
				          }},
				          {field:'id',title:'操作',width:$(this).width() * 0.35,align:'center',formatter:function(value,row,index){
				        	  var link = "";
				        	  link += "<a href=\"javascript:saveOrUpdatePlayMethodConfig('"+row.id+"','')\">修改</a>";
				        	  if(!row.used){
				              	link += " <a href=\"javascript:deletePlayMethodConfig('"+row.id+"','view')\">删除</a>";
				        	  }
				        	  return link;
				          }}
				      ]],
				 onLoadSuccess:function(data){
				},
				toolbar:[{
					id:"add",
					text:"新增玩法",
					iconCls: 'icon-add',
					handler:function(){
						saveOrUpdatePlayMethodConfig('');
					}
				}],
				pagination:true,
				singleSelect: true,
				rownumbers:true,
				onLoadError:function(){
					parent.window.$.messager.alert("提示","系统出错，请联系管理员！");
				}
			});
			
			var page = $('#play_method_list').datagrid('getPager');
			$(page).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		    }); 
		});
		
		
		function saveOrUpdatePlayMethodConfig(playMethodId){
			var url = getRootPath()+"webSite/toSaveOrUpdatePlayMethodConfig?playMethodId="+playMethodId;
			
			var title = "新增玩法";
			if(playMethodId != ''){
				title = "修改玩法";
			}
			
			parent.window.$("#web_site_config").dialog({
				title:title,
				href:url,
				width:400,
				height:220,
				modal:true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						parent.window.$("#web_site_config").dialog('close');
					}
				}],
				onClose:function(){
					$("#play_method_list").datagrid('reload');
				}
			});
		}
		
		function deletePlayMethodConfig(playMethodId){
			parent.window.$.messager.confirm("提示","您确定要删除玩法吗？",function(data){
				 if (data) {  
					 $.ajax({
						url : '/webSite/deletePlayMethodConfig',
						data : {
							"playMethodId":playMethodId
						},
						type : 'post',
						dataType : 'json',
						success : function(data) {
							var result = data.result;
							if (result == 0) {
								$("#play_method_list").datagrid("reload");
							}
							alertMessage("提示",data.message);
						}
					});
		        }  
			});
		}
	</script>
</body>
</html>