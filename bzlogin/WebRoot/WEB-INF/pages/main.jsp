<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>主页</title>
	</head>
	<body>
		<p>
			<strong>欢迎您：
				<span style="color: red">${requestScope.user}</span>
			</strong>
			<a href="${pageContext.servletContext.contextPath}/router?action=logout">注销</a>
		</p>
	</body>
</html>