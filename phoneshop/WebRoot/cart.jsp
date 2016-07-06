<%@ page import="java.util.List, java.lang.String, db.Product, cfg.Configer" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<meta charset="utf-8" />
		<title>手机列表</title>
	</head>
	<body>
		<div id="box">
			<%
				List<Product> list = (List<Product>) session.getAttribute(Configer.CART_ATTR);
				if (list != null) {
			%>
			<ul>
				<%
					for (Product p : list) {
				%>
				<li>
					<strong><%=p.getName()%></strong>
				</li>
				<%
					}
				%>
			</ul>
			<%
			} else {
			%>
			<strong>购物车为空 </strong>
			<%
			}
			%>
			<a href="/phoneshop/list.do">返回</a>
		</div>
	</body>
</html>