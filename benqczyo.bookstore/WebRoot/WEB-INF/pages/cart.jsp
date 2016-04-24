<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>购物车</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/css.css"/>
	</head>
	<body>
		<div id="box">
			<div id="content">
				<c:if test="${empty sessionScope.cart.items}">
					<h2>购物车为空，您还没有购买商品</h2>		
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<h2>您所购买的商品如下:</h2>
					<table>
						<thead>
							<tr>
								<th>编号</th>
								<th>书名</th>
								<th>单价</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="entry" items="${sessionScope.cart.items}" varStatus="status">
								<c:set var="item" value="${entry.value}"/>
								<tr>
									<td>${status.count}</td>	
									<td>${item.book.title}</td>
									<td>${item.book.price}</td>
									<td>${item.count}</td>
									<td>${item.price}</td>
									<td><a href="${pageContext.servletContext.contextPath}/delete.do?id=${book.id}">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/list.do">返回主页</a>
			</div>
		</div>
	</body>
</html>