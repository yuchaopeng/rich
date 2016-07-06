<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/backCommonInclude.jsp" %>
<style type="text/css">
	.system_message_info{width:100%;}
	.system_message_info tr td{padding:10px;font-size:14px;}
</style>
</head>
<body>
	<div>
		<form action="/systemMessage/saveOrUpdateSystemMessage" id="saveSystemMessageForm" method="post">
			<table class="system_message_info">
				<tr>
					<td align="right" style="width:100px;">
						标题：
					</td>
					<td>
						<input type="hidden" id="handleType" value="${handleType }"/>
						<input type="hidden" name="id" value="${systemMessage.id }"/>
						<input class="easyui-textbox" data-options="" id="title" name="title" value="${systemMessage.title }" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						内容：
					</td>
					<td>
						<!-- 加载编辑器的容器 -->
		    			<script id="container" name="content" type="text/plain" style="width:90%">${systemMessage.content}</script>
		    			<!-- 配置文件 -->
					    <script type="text/javascript" src="/res/ueditor/ueditor.config.js"></script>
					    <!-- 编辑器源码文件 -->
					    <script type="text/javascript" src="/res/ueditor/ueditor.all.js"></script>
					    <!-- 实例化编辑器 -->
					    <script type="text/javascript">
					        var ue = UE.getEditor('container');
					        
					        ue.addListener( 'ready', function( editor ) {
					        	var handleType = $("#handleType").val();
						        if("view" == handleType){
						        	UE.getEditor('container').setDisabled();
						        }
					        });
					    </script>
					</td>
				</tr>
				<tr id="saveOrUpdateTd">
					<td align="right">
					</td>
					<td>
						<a href="javascript:saveOrUpdate()" id="saveButton" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>
						<a href="javascript:returnSystemMessageList()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<script type="text/javascript">
		$(function(){
			var handleType = $("#handleType").val();
			if("view" == handleType){
				$("#title").textbox({"readonly":true});
				$("#saveButton").hide();
			}
		});
		
		function saveOrUpdate(){
			$("#title").textbox("setValue",$.trim($("#title").textbox("getValue")));
			var title = $("#title").textbox("getValue");
			if(title == ""){
				alertMessage("提示","请输入标题.");
				return;
			}
			
			var hasContent = ue.hasContents();
			if(!hasContent){
				alertMessage("提示","请填写内容.");
				return;
			}
			
			$("#saveSystemMessageForm").submit();
		}
		
		function returnSystemMessageList(){
			window.location.href="/systemMessage/systemMessageList"
		}
		
		
	</script>
</body>
</html>