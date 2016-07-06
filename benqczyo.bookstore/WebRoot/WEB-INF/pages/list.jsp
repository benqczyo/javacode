<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>书籍列表</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/css.css"/>
	</head>
	<body>
		<div id="box">
			<h2>库存书籍</h2>
			<div id="content">
				<table>
					<thead>
						<tr>
							<th>编号</th>
							<th>ISBN</th>
							<th>书名</th>
							<th>作者</th>
							<th>出版日期</th>
							<th>价格</th>
							<th>简介</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="entry" items="${books}" varStatus="status">
							<c:set var="book" value="${entry.value}"/>
							<tr>
								<td>${status.count}</td>	
								<td>ISBN-${book.isbn}</td>
								<td>${book.title}</td>
								<td>${book.author}</td>
								<td>${book.pub}</td>
								<td>${book.price}</td>
								<td>${book.description}</td>
								<td><a href="${pageContext.servletContext.contextPath}/buy.do?id=${book.id}">购买</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="op">
				<a href="${pageContext.servletContext.contextPath}/cart.do">[查看购物车]</a>	
			</div>
		</div>	
	</body>
</html>