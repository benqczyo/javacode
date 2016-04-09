<%@ page import="java.util.List, db.Product, cfg.Configer" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<meta charset="utf-8" />
		<title>手机列表</title>
		<style>
			img {width: 300px; height: 300px}
		</style>
	</head>
	<body>
		<div id="box">
			<%
				Product p = (Product) request.getAttribute(Configer.PRODUCT_DETAIL_ATTR);
				if (p == null) {
					response.sendRedirect(getServletContext().getContextPath() + Configer.ERROR_PAGE);	
					return;
				}
			%>
			<img src="<%=getServletContext().getContextPath() + "/" + p.getImage()%>" />
			<strong><%=p.getDescription()%></strong>
		</div>
	</body>
</html>