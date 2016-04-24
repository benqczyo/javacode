<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>书籍列表</title>
		<style type="text/css">
			body, h2 {margin: 0; padding: 0;}
			a {text-decoration: none;}
			#box {margin: 120px auto; width: 80%; text-align: center;}
			#box h2 {margin-bottom: 10px;}
			#content {margin-bottom: 10px;}
			#content table {border: none; border-collapse: collapse; width: 100%; color: #fff;}
			#content table a {color: #ac0c14;}
			#content table thead tr {background: #ac0c14;}
			#content table tbody tr:nth-of-type(even) {background: #C9E8C6;}
			#content table tbody tr:nth-of-type(odd) {background: #6CC072;}
			#content table th, #content table td {padding: 4px; border: solid 2px #fff;}
		</style>
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
				<a href="">[查看购物车]</a>	
			</div>
		</div>	
	</body>
</html>