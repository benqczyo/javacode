<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>主页</title>
	</head>
	<body>
		<c:if test="${user eq null}">
			<a href="${pageContext.request.contextPath}/login.jsp">登陆</a>
		</c:if>	
		<c:if test="${user ne null}">
			<strong>欢迎 <span>${user.name}</span></strong>
			<ul>
			</ul>
		</c:if>
	</body>
</html>