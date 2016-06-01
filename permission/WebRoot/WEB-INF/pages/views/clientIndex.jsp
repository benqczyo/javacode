<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resource.ui" var="bundle"/>

<!DOCTYPE html>
<html>
	<head>
		<title>主页</title>
	</head>
	<body>
		<c:if test="${sessionScope.account eq null}">
			<c:url value="/router?action=show&view=login" var="url"/>
			<a href="${url}">登陆</a>
		</c:if>
		<c:if test="${sessionScope.account ne null}">
			欢迎您  ${account.name}<a href="">注销</a>
		</c:if>
		<c:forEach var="menu" items="${menus}">
			<c:url value="${menu.uri}" var="url"/>
			<a href="${url}">${menu.title}</a>
		</c:forEach>
	</body>
</html>
