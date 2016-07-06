<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="mgrUpdateAccount">
	<form action="${pageContext.request.contextPath}/router?action=updateAccount" method="post">
		<input type="hidden" name="id" value="${formBean.id}"/>
		<table>
			<tr>
				<td>旧密码：</td>
				<td><input type="password" name="oldPassword" value="${formBean.oldPassword}"/></td>
				<td><span>${formBean.messages.oldPassword}</span></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" name="newPassword" value="${formBean.newPassword}"/></td>
				<td><span>${formBean.messages.newPassword}</span></td>
			</tr>
			<tr>
				<td colspan="3"><span>${formBean.messages.result}</span></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="修改"/></td>
			</tr>
		</table>
	</form>
</div>
<%@include file="footer.jsp"%>