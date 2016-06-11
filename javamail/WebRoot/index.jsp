<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>主页</title>
	</head>
	<body>
		<c:if test="${sessionScope.account eq null}">
			<c:url var="url" value="/register.jsp"/>
			<a href="${url}">注册</a>
			<c:url var="url" value="/login.jsp"/>
			<a href="${url}">登陆</a>
		</c:if>
		<c:if test="${sessionScope.account ne null}">
			<p>
				<strong>欢迎您  ${sessionScope.account.name}</strong>
				<c:url var="url" value="/router">
					<c:param name="action" value="logout"/>
				</c:url>
				<a href="${url}">注销</a>	
			</p>
		</c:if>
	</body>
</html>
