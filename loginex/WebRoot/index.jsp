<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="pathPrefix" value="${pageContext.request.contextPath}/servlet/router?action="/>
<fmt:setBundle basename="com.benqcz.loginex.resource.ui" var="bundle"/>
<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="main" bundle="${bundle}"/></title>
	</head>	
	<body>
		<c:if test="${sessionScope.user eq null}">
			<a href="${pathPrefix}showRegisterView">
				<fmt:message key="register" bundle="${bundle}"/>
			</a>
			<a href="${pathPrefix}showLoginView">
				<fmt:message key="login" bundle="${bundle}"/>
			</a>
		</c:if>
		<c:if test="${sessionScope.user ne null}">
			<p>
				<strong>
					<fmt:message key="welcome" bundle="${bundle}"/>&nbsp;${user.name}
				</strong>
				<a href="${pathPrefix}doLogout">
					<fmt:message key="logout" bundle="${bundle}"/>	
				</a>
			</p>
		</c:if>
	</body>
</html>