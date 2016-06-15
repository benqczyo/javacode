<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="mgrBook">
	<form action="${pageContext.request.contextPath}/router?action=delBooks" method="post" id="delBooksForm">
		<p>
			<a href="${pageContext.request.contextPath}/router?action=addBook">[<fmt:message key="book.add" bundle="${bundle}" />]</a>
			<a href="javascript: delBooks();">[<fmt:message key="book.delBooks" bundle="${bundle}" />]</a>
		</p>
		<c:if test="${empty requestScope.page.pageRecords}">
			<strong><fmt:message key="book.empty" bundle="${bundle}" /></strong>
		</c:if>
		<c:if test="${not empty requestScope.page.pageRecords}">
			<table class="list">
				<tr>
					<th><fmt:message key="book.order" bundle="${bundle}"/></th>
					<th><fmt:message key="book.name" bundle="${bundle}"/></th>
					<th><fmt:message key="book.author" bundle="${bundle}"/></th>
					<th><fmt:message key="book.price" bundle="${bundle}"/></th>
					<th><fmt:message key="book.description" bundle="${bundle}"/></th>
					<th><fmt:message key="book.category" bundle="${bundle}"/></th>
					<th><fmt:message key="ops" bundle="${bundle}" /></th>
				</tr>
				<c:forEach var="book" items="${requestScope.page.pageRecords}" varStatus="vs">
					<tr>
						<td><input type="checkbox" name="ids" value="${book.id}" />${vs.count}</td>
						<td>${book.name}</td>
						<td>${book.author}</td>
						<td><span>${book.price}</span></td>
						<td>${book.description}</td>
						<td>${book.category.name}</td>
						<td>
							<a href=""><fmt:message key="ops.update" bundle="${bundle}" /></a>
							<a href="javascript: delRole('${role.id}')"><fmt:message key="ops.delete" bundle="${bundle}" /></a>
							<a href=""><fmt:message key="ops.assignCategory" bundle="${bundle}" /></a>
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
					<a href="" ${requestScope.page.currentPageId eq pageId ? "class='selected'" : ""}>${pageId}</a>
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
<%@include file="footer.jsp"%>