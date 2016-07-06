<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/res/js/plan/saveOrUpdatePlan.js"></script>
	<style type="text/css">
		#saveOrUpdatePlanPanel {
			padding:5px;
		}
		#saveOrUpdatePlanPanel table td{
			padding:5px;
			margin:10px;
		}
	</style>
	<div style="padding:10px;">
		<form action="<%=request.getContextPath() %>/plan/saveOrUpdatePlan" id="saveOrUpdatePlanForm" method="post">
			<div class="easyui-panel" id="saveOrUpdatePlanPanel" style="padding:10px">
				<table>
					<tr>
						<td colspan="3">
							<input type="hidden" id="planForm_id" name="id" value="${plan.id }"/>
						</td>
						<td align="right">
							<a href="javascript:saveOrUpdatePlan()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
							<c:if test="${empty plan.id }">
							<a href="javascript:cancelSaveOrUpdatePlan()" id="cancelButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
							</c:if>
						</td>
					</tr>
					<tr>
						<td align="right">
							计划名称：
						</td>
						<td colspan="3">
							<input class="easyui-validatebox textbox" value="${plan.planName }" onblur="checkPlanName()" data-options="required:true" id="planForm_planName" name="planName" style="height:20px;width:415px;">
						</td>
					</tr>
					<tr>
						<td align="right">
							专家：
						</td>
						<td>
							<input type="hidden" id = "paramsExpertId" name="expert.id" value="${plan.expert.id }"/>
							<input class="easyui-textbox" type="text" id="paramsExpertName" value="${plan.expert.username }" data-options="editable:false,buttonText:'选择',prompt:'选中专家',onClickButton:selectExpert" />
						</td>
						<td align="right">
							手机号：
						</td>
						<td>
							<input class="easyui-textbox" id="planForm_mobile" data-options="editable:false" value="${plan.expert.mobile }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							第一档：
						</td>
						<td>
							<input class="easyui-validatebox textbox" type="text" data-options="required:true,validType:'decimalNum'" value="${plan.firstStr }" id="planForm_first" name="firstStr" style="height:20px;"/>
						</td>
						<td align="right">
							第二档：
						</td>
						<td>
							<input class="easyui-validatebox textbox" type="text" data-options="required:true,validType:'decimalNum'" value="${plan.secondStr }" id="planForm_second" name="secondStr" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							第三档：
						</td>
						<td>
							<input class="easyui-validatebox textbox" type="text" data-options="required:true,validType:'decimalNum'" value="${plan.thirdStr }" id="planForm_third" name="thirdStr" style="height:20px;"/>
						</td>
						<td align="right">
							第四档：
						</td>
						<td>
							<input class="easyui-validatebox textbox" type="text" data-options="required:true,validType:'decimalNum'" value="${plan.fourthStr }" id="planForm_fourth" name="fourthStr" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							创建日期：
						</td>
						<td colspan="3">
							<input class="easyui-textbox" type="text" id="createDate" data-options="editable:false" value='<fmt:formatDate value="${plan.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>' <%-- value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>" --%>/>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
