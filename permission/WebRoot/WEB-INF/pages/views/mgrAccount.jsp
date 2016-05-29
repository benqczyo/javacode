<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/pages/header.jsp"%>
<div id="mgrAccount">
	<form action="${uri}?action=delAccounts" method="post" id="delAccountForm">
		<p>
			<a href="${uri}?action=show&view=mgrAddAccount">[<fmt:message key="account.add" bundle="${bundle}" />]</a>
			<a href="javascript: delAccounts();">[<fmt:message key="account.delAccounts" bundle="${bundle}" />]</a>
		</p>
		<c:if test="${empty requestScope.page.records}">
			<strong><fmt:message key="role.empty" bundle="${bundle}" /></strong>
		</c:if>
		<c:if test="${not empty requestScope.page.records}">
			<table class="list">
				<tr>
					<th><fmt:message key="account.id" bundle="${bundle}" /></th>
					<th><fmt:message key="account.name" bundle="${bundle}" /></th>
					<th><fmt:message key="ops" bundle="${bundle}" /></th>
				</tr>
				<c:forEach var="account" items="${requestScope.page.records}" varStatus="vs">
					<tr>
						<td><input type="checkbox" name="ids" value="${account.id}" />${vs.count}</td>
						<td>${account.name}</td>
						<td>
							<a href="${uri}?action=show&view=mgrUpdateAccount&id=${account.id}"><fmt:message key="ops.update" bundle="${bundle}" /></a>
							<a href="javascript: delAccount('${account.id}')"><fmt:message key="ops.delete" bundle="${bundle}" /></a>
							<a href="">其他</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<p id="page">
				<c:if test="${requestScope.page.prevPageId eq -1}">
					<fmt:message key="prevPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.prevPageId ne -1}">
					<a href="${uri}?action=show&view=mgrAccount&pageId=${requestScope.page.prevPageId}"><fmt:message key="prevPage" bundle="${bundle}"/></a>
				</c:if>
				<c:forEach begin="${requestScope.page.startPageId}" end="${requestScope.page.endPageId}" var="pageId">
					<a href="${uri}?action=show&view=mgrAccount&pageId=${pageId}" ${requestScope.page.currentPageId eq pageId ? "class='selected'" : ""}>${pageId}</a>
				</c:forEach>
				<c:if test="${requestScope.page.nextPageId eq -1}">
					<fmt:message key="nextPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.nextPageId ne -1}">
					<a href="${uri}?action=show&view=mgrAccount&pageId=${requestScope.page.nextPageId}"><fmt:message key="nextPage" bundle="${bundle}"/></a>
				</c:if>
			</p>
		</c:if>
	</form>
</div>
<%@include file="/WEB-INF/pages/footer.jsp"%>