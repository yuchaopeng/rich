<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
		$(function(){
			$("#choose_user_list").datagrid({
				url:"<%=request.getContextPath()%>/userManage/selectUser?ramdom="+Math.random(),
				queryParams:{
					"params['queryUserTypes']":'${queryUserTypes}',
					"params['state']":'${state}'
				},
				columns:[[
				          {field:'createDate',title:'创建时间',width:180,align:'center'},
				          {field:'username',title:'姓名',width:180,align:'center'},
				          {field:'mobile',title:'手机号',width:180,align:'center'},
				          {field:'stateVal',title:'用户状态',width:180,align:'center'}
				      ]],
				pagination:true,
				rownumbers:true,
				singleSelect: true,
				onLoadError:function(){
					$.messager.alert("提示","系统出错，请联系管理员！");
				}
			});
			
			var page = $('#choose_user_list').datagrid('getPager');
			$(page).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		    }); 
		});
		
		
		function resetForm(){
			$('#search_re_Form').form('clear');
		}
		
		function searchForm(){
			$("[id^='params_']").each(function(){
				var val = $(this).textbox("getValue");
				$(this).textbox("setValue",$.trim(val))
			});
			$("#choose_user_list").datagrid("load", sy.serializeObject($("#search_re_Form").form()));
		}
	</script>
	
	<style type="text/css">
		#queryUser table {
			padding:5px;
		}
		#queryUser table td{
			padding:10px;
			margin:10px;
		}
	</style>
	<div style="padding:10px;">
		<form action="" id="search_re_Form" method="post">
			<input type="hidden" name="params['queryUserTypes']" value="${queryUserTypes }"/>
			<input type="hidden" name="params['state']" value="${state }"/>
			<div class="easyui-panel" title="查询条件" style="padding:10px">
				<table>
					<tr>
						<td align="right">姓名：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_username" name="params['username']"/>
						</td>
						<td align="right">手机号：</td>
						<td>
							<input class="easyui-textbox" type="text" id="params_mobile" name="params['mobile']"/>
						</td>
						<td>
							<a href="javascript:searchForm()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
							<a href="javascript:resetForm()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="choose_user_list">
		</table>
	</div>
