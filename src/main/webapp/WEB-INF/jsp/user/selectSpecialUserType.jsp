<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/editUser.js"></script>
	<script type="text/javascript">
		function saveOrUpdateSpecialUser(createUserType){
			//parent.window.$("#special_user_type_choose").dialog("close");
			saveOrUpdateSpecialUser_('',createUserType);
		}
		
		function saveOrUpdateSpecialUser_(userId,createUserType){
			var url = getRootPath()+"userManage/toSaveOrUpdateUser?userId="+userId+"&createUserType="+createUserType;
			
			var title = "新建用户";
			if(userId != ''){
				title = "修改用户";
			}
			
			var height = 600;
			if(createUserType == 1 || createUserType == 2 || createUserType == 3){
				height = 350;
			}
			
			parent.window.$("#edit_user_dialog_div").dialog({
				title:title,
				href:url,
				width:580,
				height:height,
				modal:true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						parent.window.$("#edit_user_dialog_div").dialog('close');
					}
				}],
				onClose:function(){
					parent.window.$("#special_user_type_choose").dialog("close");
				}
			});
		}
	</script>
	<div style="padding:10px;">
		<a href="javascript:saveOrUpdateSpecialUser(1)" class="easyui-linkbutton" style="width:80px;">管理员</a>
		<a href="javascript:saveOrUpdateSpecialUser(2)" class="easyui-linkbutton" style="width:80px;">运营人员</a>
		<a href="javascript:saveOrUpdateSpecialUser(3)" class="easyui-linkbutton" style="width:80px;">门店职员</a>
		<a href="javascript:saveOrUpdateSpecialUser(4)" class="easyui-linkbutton" style="width:80px;">专家</a>
	</div>
