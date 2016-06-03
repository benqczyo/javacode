<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="mgrUpdateRole">
	<form action="${uri}?action=updateRole" method="post">
		<table>
			<tr>
				<td colspan="2">
					<input type="hidden" name="id" value="${formBean.id}" />
				</td>
			</tr>
			<tr>
				<td>
					*
					<fmt:message key="role.name" bundle="${bundle}" />
					:
				</td>
				<td>
					<input type="input" name="name" value="${formBean.name}" />
				</td>
				<td>
					<span>${formBean.messages.name}</span>
				</td>
			</tr>
			<tr>
				<td>
					*
					<fmt:message key="role.description" bundle="${bundle}" />
					:
				</td>
				<td>
					<input type="input" name="description"
						value="${formBean.description}" />
				</td>
				<td>
					<span>${formBean.messages.description}</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<span>${formBean.messages.result}</span>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit"
						value="<fmt:message key='btn.update' bundle='${bundle}'/>" />
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="footer.jsp"%>