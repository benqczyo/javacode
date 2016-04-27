<%@page pageEncoding="utf-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="text">
<html>
	<head>
		<title><fmt:message key="detail_title" /></title>
	</head>
	<body>
		<c:set var="back" value="${pageContext.servletContext.contextPath}/list.do"></c:set>
		<strong>${book.name}</strong>&nbsp;<a href="${back}"><fmt:message key="a_back_text" /></a>
	</body>
</html>
</fmt:bundle>