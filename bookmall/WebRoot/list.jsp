<%@page pageEncoding="utf-8" contentType="text/html" %>
<%@page import="java.util.List, java.util.ArrayList, db.Book, configer.Constant" %>
<%@page errorPage="error.jsp" %>

<html>
	<head>
		<title>首页</title>
		<style>
			body, ul {margin: 0; padding: 0;}
			li {list-style: none; text-indent: 10px;}
		</style>
	</head>
	<body>
		<%
			List<Book> books = (ArrayList<Book>) request.getAttribute(Constant.ALL_BOOKS_ATTR);
			if (books == null) throw new ServletException(Constant.LIST_FAILED_ERROR);
		%>
		<h3>本店所有书籍：</h3>
		<ul>
			<%
				for (Book b : books) {	
			%>
			<li><%=b.getName()%>&nbsp;<a href="<%=String.format("%s/%s.do?id=%s", 
				application.getContextPath(), Constant.DETAIL_ACTION, b.getId())%>">详情</a>
				&nbsp;<a href="<%=String.format("%s/%s.do?id=%s", 
				application.getContextPath(), Constant.CART_ACTION, b.getId())%>">加入购物车</a></li>
			<%
				}
			%>
		</ul>
		<%
			List<Book> history = (ArrayList<Book>) request.getAttribute(Constant.HISTORY_ATTR);
			if (history != null) {
		%>
		<h3>最近浏览的商品</h3>
		<ul>
		<%
				for (Book b : history)	{	
		%>
			<li><%=b.getName()%></li>
				<%
					}
				%>
		</ul>
		<div><a href="<%=String.format("%s/%s.do", 
				application.getContextPath(), Constant.CLEAR_ACTION)%>">清空</a></div>
		<%
			}
		%>	
	</body>
</html>