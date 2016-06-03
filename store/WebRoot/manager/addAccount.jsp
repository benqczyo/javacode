<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="mgrAddAccount">
	<form action="${pageContext.request.contextPath}/router?action=addAccount" method="post">
		<table>
			<tr>
				<td colspan="3"><input type="hidden" name="id"/></td>
			</tr>
			<tr>
				<td>*<fmt:message key="account.name" bundle="${bundle}"/>:</td>
				<td><input type="input" name="name" value="${formBean.name}"/></td>
				<td><span>${formBean.messages.name}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="account.password" bundle="${bundle}"/>:</td>
				<td><input type="password" name="password" value="${formBean.password}"/></td>
				<td><span>${formBean.messages.password}</span></td>
			</tr>
			<tr>
				<td colspan="3"><span>${formBean.messages.result}</span></td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit" value="<fmt:message key='btn.add' bundle='${bundle}'/>"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="footer.jsp"%>