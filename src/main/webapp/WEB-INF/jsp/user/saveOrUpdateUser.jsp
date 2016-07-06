<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/res/js/saveOrUpdateUser.js"></script>
	<style type="text/css">
		#saveOrUpdateUserPanel {
			padding:5px;
		}
		#saveOrUpdateUserPanel table td{
			padding:5px;
			margin:10px;
		}
	</style>
	<div style="padding:10px;">
		<form action="<%=request.getContextPath() %>/userManage/saveOrUpdateUser" id="saveOrUpdateUserForm" method="post">
			<div class="easyui-panel" id="saveOrUpdateUserPanel" style="padding:10px">
				<table>
					<tr>
						<td colspan="3">
							<input type="hidden" id="userForm_id" name="id" value="${user.id }"/>
						</td>
						<td align="right">
							<a href="javascript:saveOrUpdate()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
							<c:if test="${empty user.id }">
							<a href="javascript:cancelSaveOrUpdate()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
							</c:if>
						</td>
					</tr>
					<tr>
						<td align="right">
							手机号：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true,validType:'mobile'" value="${user.mobile }" onchange="setUserAccount(this)" onblur="checkMobile()" id="userForm_mobile" name="mobile" style="height:20px;">
						</td>
						<td align="right">
							账号：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" id="userForm_account" name="account" value="${user.account }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true" id="userForm_username" name="username" value="${user.username }" style="height:20px;"/>
						</td>
						<td align="right">
							邮箱：
						</td>
						<td>
							<input class="easyui-validatebox textbox" data-options="required:true,validType:'email'" id="userForm_email" name="email" value="${user.email }" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							密码：
						</td>
						<td>
							<input class="easyui-validatebox textbox" type="password" data-options="validType:'password'" id="password" name="password" style="height:20px;"/>
						</td>
						<td align="right">
							确认密码：
						</td>
						<td>
							<input class="easyui-validatebox textbox" type="password" name="repassword" id="repassword"  validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							地址：
						</td>
						<td colspan="3">
							<input class="easyui-validatebox textbox" data-options="required:true" id="userForm_address" name="address" value="${user.address }" style="height:20px;width:425px"/>
						</td>
					</tr>
					<tr>
						<c:choose>
							<c:when test="${empty user.id && createUserType == 0 || !empty user.id && user.userType == 0}">
								<td align="right">
									推荐人：
								</td>
								<td>
									<input type="hidden" name="userType" value="0"/>
									<input type="hidden" id = "paramsRecommenderId" name="recommender.id" value="${user.recommender.id }"/>
									<input class="easyui-textbox" type="text" id="paramsRecommenderUsername" value="${user.recommender.username }" data-options="editable:false,buttonText:'选择',prompt:'选择推荐人',onClickButton:chooseRecommenderDialog" />
								</td>
							</c:when>
							<c:otherwise>
								<td align="right">
									用户类别：
								</td>
								<td>
									<c:choose>
										<c:when test="${empty user.id}">
											<input type="hidden" name="userType" value="${createUserType }"/>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="userType" value="${user.userType }"/>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${createUserType == 1}">
											<input class="easyui-textbox" type="text" value="管理员" data-options="editable:false" />
										</c:when>
										<c:when test="${createUserType == 2}">
											<input class="easyui-textbox" type="text" value="运营用户" data-options="editable:false" />
										</c:when>
										<c:when test="${createUserType == 3}">
											<input class="easyui-textbox" type="text" value="门店职员" data-options="editable:false" />
										</c:when>
										<c:when test="${createUserType == 4}">
											<input class="easyui-textbox" type="text" value="专家" data-options="editable:false" />
										</c:when>
										<c:otherwise>
											未知状态用户
										</c:otherwise>
									</c:choose>
								</td>
							</c:otherwise>
						</c:choose>
						<td align="right">
							创建日期：
						</td>
						<td>
							<input class="easyui-textbox" type="text" id="createDate" data-options="editable:false" value='<fmt:formatDate value="${user.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>' <%-- value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>" --%>/>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="commission_model_data">
		</table>
	</div>
