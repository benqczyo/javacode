<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>主页</title>
	</head>
	<body>
		<c:if test="${sessionScope.user eq null}">
			<a href="${pageContext.request.contextPath}/login.jsp">登陆</a>
			<a href="${pageContext.request.contextPath}/register.jsp">注册</a>
		</c:if>	
		<c:if test="${user ne null}">
			<p>
				<strong>欢迎你
					<span>${sessionScope.user.name}</span>
				</strong>
				<a href="${pageContext.request.contextPath}/router?action=logout">注销</a>
				<ul>
					<c:forEach var="session" items="${applicationScope.sessions}">
						<c:if test="${session.key.name ne sessionScope.user.name}">
							<li>${session.key.name}<a href="${pageContext.request.contextPath}/router?action=kick&id=${session.key.id}">踢掉</a></li>
						</c:if>
					</c:forEach>
				</ul>
			</p>
		</c:if>
	</body>
</html>