<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<div style="text-align: center;">
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<c:set var="currentPageId" value="${page.currentPageId}" />
	<c:set var="prevPageId" value="${page.prevPageId}" />
	<c:set var="nextPageId" value="${page.nextPageId}" />
	<c:set var="totalPages" value="${page.totalPages}" />
	<p>
		<c:if test="${prevPageId eq -1}">&lt;&lt;</c:if>
		<c:if test="${prevPageId ne -1}">
			<a href="${path}/router?action=showPage&pageId=1">&lt;&lt;</a>
		</c:if>
		<c:forEach begin="${requestScope.page.startPageId}"
			end="${requestScope.page.endPageId}" var="pageId">
			<a
				style="display:inline-block; padding: 1px; border: solid 1px grey; text-align:center; color: ${page.currentPageId == pageId ? 'red' : 'black'};"
				href="${path}/router?action=showPage&pageId=${pageId}">${pageId}</a>
		</c:forEach>
		<select>
			<c:forEach begin="1" end="${requestScope.page.totalPages}"
				var="pageId">
				<option value="${pageId}" ${page.currentPageId==
					pageId ? 'selected="selected"' : ''}>
					${pageId}
				</option>
			</c:forEach>
		</select>
		<a href="javascript:jump()">跳转</a>
		<c:if test="${nextPageId eq -1}">&gt;&gt;</c:if>
		<c:if test="${nextPageId ne -1}">
			<a href="${path}/router?action=showPage&pageId=${totalPages}">&gt;&gt;</a>
		</c:if>
	</p>
</div>
