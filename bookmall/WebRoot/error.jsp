<%@page pageEncoding="utf-8" contentType="text/html"%>
<%@page isErrorPage="true" %>
<html>
	<head>
		<title>错误</title>
	</head>
	<body>
		<h1>遇到一点问题：<%=exception.getMessage()%></h1>
	</body>
</html>