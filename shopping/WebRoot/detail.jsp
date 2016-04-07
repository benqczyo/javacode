<%@ page import="java.util.List, db.Product" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<meta charset="utf-8" />
		<title>手机列表</title>
	</head>
	<body>
		<div id="box">
			<%
				Product p = (Product) request.getAttribute("product");
				if (p == null) {
					response.sendRedirect("/shopping/error.jsp");	
					return;
				}
			%>
			<strong><%=p.getDescription()%></strong>
		</div>
	</body>
</html>