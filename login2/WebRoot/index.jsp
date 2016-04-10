<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<title>主页</title>
	</head>
	<body>
		<%
			Object validated = session.getAttribute("user");
			if (validated == null) {
				response.sendRedirect("/login2/login.jsp");
				return;
			}
		%>
		<strong>主页内容</strong><a href="/login2/logout">注销</a>	
	</body>
</html>