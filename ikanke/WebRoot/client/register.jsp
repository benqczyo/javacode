<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="register">
	<form action="${pageContext.request.contextPath}/router?action=doRegister" method="post">
		<table>
			<tr>
				<td>账户名：</td>
				<td><input type="text" name="name" value="${formBean.name}"/></td>
				<td><span>${formBean.messages.name}</span></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" value="${formBean.password}"/></td>
				<td><span>${formBean.messages.password}</span></td>
			</tr>
			<tr>
				<td>手机：</td>
				<td><input type="text" name="cellphone" value="${formBean.cellphone}"/></td>
				<td><span>${formBean.messages.cellphone}</span></td>
			</tr>
			<tr>
				<td>电邮：</td>
				<td><input type="text" name="email" value="${formBean.email}"/></td>
				<td><span>${formBean.messages.email}</span></td>
			</tr>
			<tr>
				<td>地址：</td>
				<td><input type="text" name="address" value="${formBean.address}"/></td>
				<td><span>${formBean.messages.address}</span></td>
			</tr>
			<tr>
				<td colspan="3"><span>${formBean.messages.result}</span></td>
			</tr>
		</table>
		<input type="submit" value='<fmt:message key="register" bundle="${bundle}"/>'/>
	</form>
</div>
<%@include file="footer.jsp"%>
