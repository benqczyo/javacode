<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/pages/header.jsp"%>
<div id="mgrUpdateMenu">
	<form action="${uri}?action=updateMenu" method="post">
		<table>
			<tr>
				<td colspan="3"><input type="hidden" name="id" value="${formBean.id}"/></td>
			</tr>
			<tr>
				<td>*<fmt:message key="menu.title" bundle="${bundle}"/>:</td>
				<td><input type="input" name="title" value="${formBean.title}"/></td>
				<td><span>${formBean.messages.title}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="menu.uri" bundle="${bundle}"/>:</td>
				<td><input type="input" name="uri" value="${formBean.uri}"/></td>
				<td><span>${formBean.messages.uri}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="menu.description" bundle="${bundle}"/>:</td>
				<td><input type="input" name="description" value="${formBean.description}"/></td>
				<td><span>${formBean.messages.description}</span></td>
			</tr>
			<tr>
				<td colspan="3"><span>${formBean.messages.result}</span></td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit" value="修改菜单"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="/WEB-INF/pages/footer.jsp"%>