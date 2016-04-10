<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<title>主页</title>
	</head>
	<body>
		<%
			String user = (String) session.getAttribute("user");
			if (user == null) {
				response.sendRedirect("/login2/login.jsp");
				return;
			}
		%>
		<strong>欢迎<%=user%></strong>&nbsp;<a href="/login2/logout">注销</a>
	</body>
</html>