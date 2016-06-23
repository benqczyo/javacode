<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="clientIndex">
	${requestScope.hint}
	<c:if test="${empty requestScope.page.pageRecords}">
		<span><fmt:message key="book.empty" bundle="${bundle}"/></span>
	</c:if>
	<c:if test="${not empty requestScope.page.pageRecords}">
		<ul id="books" class="clear">
			<c:forEach var="book" items="${requestScope.page.pageRecords}">
				<li class="book">
					<div>
						<div>
							<img src="${pageContext.request.contextPath}/router?action=showCover&pic=${book.pic}"/>
						</div>
						<ul>
							<li>${book.name}</li>
							<li>${book.author}</li>
							<li><span>${book.price}</span></li>
							<li><a href="${pageContext.request.contextPath}/router?action=buyBook&bookId=${book.id}">购买</a></li>
						</ul>
					</div>
				</li>
			</c:forEach>
		</ul>
		<p id="page">
				<c:if test="${requestScope.page.prevPageId eq -1}">
					<fmt:message key="prevPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.prevPageId ne -1}">
					<a href="${pageContext.request.contextPath}/router?action=listHomePage&pageId=${requestScope.page.prevPageId}"><fmt:message key="prevPage" bundle="${bundle}"/></a>
				</c:if>
				<c:forEach begin="${requestScope.page.startPageId}" end="${requestScope.page.endPageId}" var="pageId">
					<a href="${pageContext.request.contextPath}/router?action=listHomePage&pageId=${pageId}" ${requestScope.page.currentPageId eq pageId ? "class='selected'" : ""}>${pageId}</a>
				</c:forEach>
				<c:if test="${requestScope.page.nextPageId eq -1}">
					<fmt:message key="nextPage" bundle="${bundle}"/>
				</c:if>
				<c:if test="${requestScope.page.nextPageId ne -1}">
					<a href="${pageContext.request.contextPath}/router?action=listHomePage&pageId=${requestScope.page.nextPageId}"><fmt:message key="nextPage" bundle="${bundle}"/></a>
				</c:if>
		</p>	
	</c:if>
</div>
<%@include file="footer.jsp"%>