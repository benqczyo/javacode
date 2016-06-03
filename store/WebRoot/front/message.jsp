<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resource.ui" var="bundle"/>
<!DOCTYPE html>
<html>
	<head>
		<title>提示</title>
		<style>
			span {color: red;}
		</style>
	</head>
	<body>
		<p><span>${message}</span><a href="${pageContext.request.contextPath}/router?action=defaultPage">返回主页</a></p>
	</body>
</html>