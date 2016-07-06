<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<title>主页</title>
		<meta charset="utf-8" />
	</head>
	<body>
		<%
			if (session.getAttribute("name") == null)
				response.sendRedirect("/login/login.jsp");
		%>
		<strong>Main</strong>
	 </body>
</html>
