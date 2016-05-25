<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/pages/header.jsp"%>
<div id="mgrMenu">
	<p><a href="${uri}?router?action=addMenu">[<fmt:message key="menu.add" bundle="${bundle}"/>]</a></p>
	<table>
		<tr>
			<th><fmt:message key="menu.id" bundle="${bundle}"/></th>
			<th><fmt:message key="menu.title" bundle="${bundle}"/></th>
			<th><fmt:message key="menu.uri" bundle="${bundle}"/></th>
			<th><fmt:message key="menu.description" bundle="${bundle}"/></th>
			<th><fmt:message key="menu.ops" bundle="${bundle}"/></th>
		</tr>
		<c:forEach var="menu" items="${menus}" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${menu.title}</td>
				<td>${menu.uri}</td>
				<td>${menu.description}</td>
				<td>
					<a href="">删除</a>
					<a href="">修改</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@include file="/WEB-INF/pages/footer.jsp"%>