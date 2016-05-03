<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<title>用户列表</title>
		<style>
			#box {margin: 0 auto; width: 90%;}
			div {margin-bottom: 10px;}
			#op a {display: inline-block; width: 100px; height: 20px; border: solid 1px grey; text-decoration: none; line-height: 20px; text-align: center; background: gainsboro; color: #fff;}
			#op a:hover {color: red;}
			table {width: 100%; border: none; border-collapse: collapse;}
			th, td {border: solid 1px gainsboro; text-align: center;}
		</style>
	</head>
	<body>
		<div id="box">
			<div id="op">
				<a href="${pageContext.request.contextPath}/delete.do">Delete</a>&nbsp;<a href="${pageContext.request.contextPath}/add.do"">Add</a>
			</div>
			<div id="content">
				<table>
					<tr>
						<th>SELECT</th>
						<th>ID</th>
						<th>NAME</th>
						<th>GENDER</th>
						<th>BIRTH</th>
						<th>CELLPHONE</th>
						<th>EMAIL</th>
						<th>PREFERENCE</th>
						<th>TYPE</th>
						<th>DESCRIPTION</th>
						<th>OPERATIONS</th>
					</tr>
					<c:forEach var="item" items="${customers}">
						<c:set var="customer" value="${item.value}"/>
						<tr>
							<td><input type="checkbox" name="id" value="${customer.id}"/></td>
							<td>${customer.id}</td>
							<td>${customer.name}</td>
							<td>${customer.gender}</td>
							<td>${customer.birthday}</td>
							<td>${customer.cellphone}</td>
							<td>${customer.email}</td>
							<td>${customer.preference}</td>
							<td>${customer.type}</td>
							<td>${customer.description}</td>
							<td><a href="">update</a>&nbsp;<a href="">delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html> 