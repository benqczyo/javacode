<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="mgrRole">
	<form action="${uri}?action=delRoles" method="post" id="delRolesForm">
		<p>
			<a href="${pageContext.request.contextPath}/manager/addRole.jsp">[<fmt:message key="role.add" bundle="${bundle}" />]</a>
			<a href="javascript: delRoles();">[<fmt:message key="role.delRoles" bundle="${bundle}" />]</a>
		</p>
		<c:if test="${empty requestScope.page.records}">
			<strong><fmt:message key="role.empty" bundle="${bundle}" /></strong>
		</c:if>
		<c:if test="${not empty requestScope.page.records}">
			<table class="list">
				<tr>
					<th><fmt:message key="role.id" bundle="${bundle}" /></th>
					<th><fmt:message key="role.name" bundle="${bundle}" /></th>
					<th><fmt:message key="role.description" bundle="${bundle}" /></th>
					<th><fmt:message key="ops" bundle="${bundle}" /></th>
				</tr>
				<c:forEach var="role" items="${requestScope.page.records}" varStatus="vs">
					<tr>
						<td><input type="checkbox" name="ids" value="${role.id}" />${vs.count}</td>
						<td>${role.name}</td>
						<td>${role.description}</td>
						<td>
							<a href="${pageContext.request.contextPath}/router?action=changeRole&id=${role.id}"><fmt:message key="ops.update" bundle="${bundle}" /></a>
							<a href="javascript: delRole('${role.id}')"><fmt:message key="ops.delete" bundle="${bundle}" /></a>
							<a href="${uri}?action=show&view=mgrAssignMenu&id=${role.id}"><fmt:message key="ops.assignMenu" bundle="${bundle}" /></a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<p id="page">
				<c:if test="${requestScope.page.prevPageId eq -1}">
					<fmt:message key="prevPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.prevPageId ne -1}">
					<a href="${uri}?action=show&view=mgrRole&pageId=${requestScope.page.prevPageId}"><fmt:message key="prevPage" bundle="${bundle}"/></a>
				</c:if>
				<c:forEach begin="${requestScope.page.startPageId}" end="${requestScope.page.endPageId}" var="pageId">
					<a href="${uri}?action=show&view=mgrRole&pageId=${pageId}" ${requestScope.page.currentPageId eq pageId ? "class='selected'" : ""}>${pageId}</a>
				</c:forEach>
				<c:if test="${requestScope.page.nextPageId eq -1}">
					<fmt:message key="nextPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.nextPageId ne -1}">
					<a href="${uri}?action=show&view=mgrRole&pageId=${requestScope.page.nextPageId}"><fmt:message key="nextPage" bundle="${bundle}"/></a>
				</c:if>
			</p>
		</c:if>
	</form>
</div>
<%@include file="footer.jsp"%>