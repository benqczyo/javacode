<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/manager/header.jsp"%>
<div id="mgrCategory">
	<form action="${pageContext.request.contextPath}/router?action=delCategories" method="post" id="delCategoriesForm">
		<p>
			<a href="${pageContext.request.contextPath}/manager/addCategory.jsp">[<fmt:message key="category.add" bundle="${bundle}" />]</a>
			<a href="javascript: delCategories();">[<fmt:message key="category.delCategories" bundle="${bundle}" />]</a>
		</p>
		<c:if test="${empty requestScope.page.pageRecords}">
			<strong><fmt:message key="category.empty" bundle="${bundle}" /></strong>
		</c:if>
		<c:if test="${not empty requestScope.page.pageRecords}">
			<table class="list">
				<tr>
					<th><fmt:message key="category.order" bundle="${bundle}" /></th>
					<th><fmt:message key="category.name" bundle="${bundle}" /></th>
					<th><fmt:message key="category.description" bundle="${bundle}" /></th>
					<th><fmt:message key="ops" bundle="${bundle}" /></th>
				</tr>
				<c:forEach var="category" items="${requestScope.page.pageRecords}" varStatus="vs">
					<tr>
						<td><input type="checkbox" name="ids" value="${category.id}" />${vs.count}</td>
						<td>${category.name}</td>
						<td>${category.description}</td>
						<td>
							<a href=""><fmt:message key="ops.update" bundle="${bundle}" /></a>
							<a href="javascript: delCategory('${category.id}')"><fmt:message key="ops.delete" bundle="${bundle}" /></a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<p id="page">
				<c:if test="${requestScope.page.prevPageId eq -1}">
					<fmt:message key="prevPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.prevPageId ne -1}">
					<a href=""><fmt:message key="prevPage" bundle="${bundle}"/></a>
				</c:if>
				<c:forEach begin="${requestScope.page.startPageId}" end="${requestScope.page.endPageId}" var="pageId">
					<a href="">${pageId}</a>
				</c:forEach>
				<c:if test="${requestScope.page.nextPageId eq -1}">
					<fmt:message key="nextPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.nextPageId ne -1}">
					<a href=""><fmt:message key="nextPage" bundle="${bundle}"/></a>
				</c:if>
			</p>
		</c:if>
	</form>
</div>
<%@include file="/manager/footer.jsp"%>