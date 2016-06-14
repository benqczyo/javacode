<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="mgrAddCategory">
	<form action="${pageContext.request.contextPath}/router?action=addCategory" method="post">
		<table>
			<tr>
				<td>*<fmt:message key="category.name" bundle="${bundle}"/>:</td>
				<td><input type="input" name="name" value="${formBean.name}"/></td>
				<td><span>${formBean.messages.name}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="category.description" bundle="${bundle}"/>:</td>
				<td><input type="input" name="description" value="${formBean.description}"/></td>
				<td><span>${formBean.messages.description}</span></td>
			</tr>
			<tr><td colspan="3"><span>${formBean.messages.result}</span></td></tr>
			<tr><td colspan="3"><input type="submit" value="<fmt:message key='category.btn.add' bundle='${bundle}'/>"/></td></tr>
		</table>
	</form>
</div>
<%@include file="footer.jsp"%>