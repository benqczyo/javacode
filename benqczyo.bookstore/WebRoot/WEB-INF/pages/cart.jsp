<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>购物车</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/css.css"/>
		<script type="text/javascript">
			function changeCount(id, obj, oldValue) {
				var newValue = obj.value;
				if (confirm("是否修改购买数量为" + newValue + "?")) {
					window.location = "${pageContext.servletContext.contextPath}/change.do?id=" + id + "&value=" + newValue;
				} else {
					obj.value = oldValue;
				}
			}
			function deleteThis(id) {
				if (confirm("是否从购物车中删除这个商品?")) 
					window.location = "${pageContext.servletContext.contextPath}/delete.do?id=" + id;
			}
		</script>
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
									<td><input type="text" value="${item.count}" onchange="changeCount(${item.book.id}, this, ${item.count})"/></td>
									<td>${item.price}</td>
									<td><a href="javascript:deleteThis(${item.book.id});">删除</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="2">
									<strong>商品数量总计：<span>${sessionScope.cart.count}</span></strong>
								</td>
								<td colspan="3">
									<strong>商品价格总计：<span>￥${sessionScope.cart.price}</span></strong>
								</td>
								<td>
									<a href="${pageContext.servletContext.contextPath}/clear.do">[清空购物车]</a>
								</td>
							</tr>
						</tbody>
					</table>
				</c:if>
				${requestScope.error}
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/list.do">继续购物</a>
			</div>
		</div>
	</body>
</html>