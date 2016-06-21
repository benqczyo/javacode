<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="clientIndex">
	<ul id="books" class="clear">
		<c:forEach var="book" items="${requestScope.page.pageRecords}">
			<li class="book">
				<div>
					<img src="${pageContext.request.contextPath}/router?action=showCover&pic=${book.pic}"/>
					<p>${book.name}</p>
					<p>${book.author}</p>
					<p>${book.description}</p>
					<p><span>${book.price}</span>&nbsp;<a href="">购买</a></p>
				</div>
			</li>
		</c:forEach>
	</ul>
	<p id="page">
				<c:if test="${requestScope.page.prevPageId eq -1}">
					<fmt:message key="prevPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.prevPageId ne -1}">
					<a href="${pageContext.request.contextPath}/router?action=listAllBooks&pageId=${requestScope.page.prevPageId}"><fmt:message key="prevPage" bundle="${bundle}"/></a>
				</c:if>
				<c:forEach begin="${requestScope.page.startPageId}" end="${requestScope.page.endPageId}" var="pageId">
					<a href="${pageContext.request.contextPath}/router?action=listAllBooks&pageId=${pageId}" ${requestScope.page.currentPageId eq pageId ? "class='selected'" : ""}>${pageId}</a>
				</c:forEach>
				<c:if test="${requestScope.page.nextPageId eq -1}">
					<fmt:message key="nextPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.nextPageId ne -1}">
					<a href="${pageContext.request.contextPath}/router?action=listAllBooks&pageId=${requestScope.page.nextPageId}"><fmt:message key="nextPage" bundle="${bundle}"/></a>
				</c:if>
			</p>
</div>
<%@include file="footer.jsp"%>