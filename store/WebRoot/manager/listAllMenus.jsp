<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/manager/header.jsp"%>
<div id="mgrMenu">
	<form action="${pageContext.request.contextPath}/router?action=delMenus" method="post" id="delMenusForm">
		<p>
			<a href="${pageContext.request.contextPath}/manager/addMenu.jsp">[<fmt:message key="menu.add" bundle="${bundle}" />]</a>
			<a href="javascript: delMenus();">[<fmt:message key="menu.delMenus" bundle="${bundle}" />]</a>
		</p>
		<c:if test="${empty requestScope.page.records}">
			<strong><fmt:message key="menu.empty" bundle="${bundle}" /></strong>
		</c:if>
		<c:if test="${not empty requestScope.page.records}">
			<table class="list">
				<tr>
					<th><fmt:message key="menu.id" bundle="${bundle}" /></th>
					<th><fmt:message key="menu.title" bundle="${bundle}" /></th>
					<th><fmt:message key="menu.uri" bundle="${bundle}" /></th>
					<th><fmt:message key="menu.description" bundle="${bundle}" /></th>
					<th><fmt:message key="ops" bundle="${bundle}" /></th>
				</tr>
				<c:forEach var="menu" items="${requestScope.page.records}" varStatus="vs">
					<tr>
						<td><input type="checkbox" name="ids" value="${menu.id}" />${vs.count}</td>
						<td>${menu.title}</td>
						<td>${menu.uri}</td>
						<td>${menu.description}</td>
						<td>
							<a href="${pageContext.request.contextPath}/router?action=changeMenu&id=${menu.id}"><fmt:message key="ops.update" bundle="${bundle}" /></a>
							<a href="javascript: delMenu('${menu.id}')"><fmt:message key="ops.delete" bundle="${bundle}" /></a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<p id="page">
				<c:if test="${requestScope.page.prevPageId eq -1}">
					<fmt:message key="prevPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.prevPageId ne -1}">
					<a href="${pageContext.request.contextPath}/router?action=listAllMenus&pageId=${requestScope.page.prevPageId}"><fmt:message key="prevPage" bundle="${bundle}"/></a>
				</c:if>
				<c:forEach begin="${requestScope.page.startPageId}" end="${requestScope.page.endPageId}" var="pageId">
					<a href="${pageContext.request.contextPath}/router?action=listAllMenus&pageId=${pageId}" ${requestScope.page.currentPageId eq pageId ? "class='selected'" : ""}>${pageId}</a>
				</c:forEach>
				<c:if test="${requestScope.page.nextPageId eq -1}">
					<fmt:message key="nextPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.nextPageId ne -1}">
					<a href="${pageContext.request.contextPath}/router?action=listAllMenus&pageId=${requestScope.page.nextPageId}"><fmt:message key="nextPage" bundle="${bundle}"/></a>
				</c:if>
			</p>
		</c:if>
	</form>
</div>
<%@include file="/manager/footer.jsp"%>