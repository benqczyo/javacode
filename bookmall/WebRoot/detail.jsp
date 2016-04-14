<%@page pageEncoding="utf-8" contentType="text/html" %>
<%@page import="java.util.HashMap, java.util.LinkedHashMap, db.Book, configer.Constant" %>
<%@page errorPage="error.jsp" %>

<html>
	<head>
		<title>详情</title>
	</head>
	<body>
		<%
			Book book = (Book) request.getAttribute(Constant.DETAIL_ATTR);
			if (book == null) throw new ServletException();
		%>
		<h3><%=book.getName()%></h3>
	</body>
</html>