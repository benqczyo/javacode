<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<title>客户列表</title>
		<style>
			body, h2 {margin: 0; padding: 0;}
			a {text-decoration: none;}
			#box {margin: 20px auto; width: 90%;}
			h2, div {margin-bottom: 10px;}
			h2 {text-align: center;}
			#op a {display: inline-block; border: solid 1px grey; width: 100px; height: 20px; color: #fff; font-size: 14px; line-height: 20px; text-align: center; background: gainsboro;}
			#op a:hover {color: red;}
			#content table {width: 100%; border: none; border-collapse: collapse;}
			#content th, td {border: solid 1px gainsboro; text-align: center;}
		</style>
	</head>
	<body>
		<div id="box">
			<h2>客户列表</h2>
			<div id="op">
				<a href="${pageContext.request.contextPath}/router?action=del">删除</a>&nbsp;<a href="${pageContext.request.contextPath}/router?action=showAddForm"">添加</a>
			</div>
			<c:if test="${requestScope.customers eq null}">
				<strong>还没有添加客户</strong>
			</c:if>
			<c:if test="${not empty requestScope.customers}">
				<div id="content">
					<table>
						<tr>
							<th>选择</th>
							<th>客户编号</th>
							<th>客户姓名</th>
							<th>客户性别</th>
							<th>出生日期</th>
							<th>移动电话</th>
							<th>电子邮箱</th>
							<th>爱好</th>
							<th>客户类型</th>
							<th>个人简介</th>
							<th>操作</th>
						</tr>
						<c:forEach var="item" items="${requestScope.customers}">
							<c:set var="customer" value="${item.value}"/>
							<tr>
								<td><input type="checkbox" name="id" value="${customer.id}"/></td>
								<td>${customer.id}</td>
								<td>${customer.name}</td>
								<td>${customer.gender eq '1' ? '男' : '女'}</td>
								<td>${customer.birthday}</td>
								<td>${customer.cellphone}</td>
								<td>${customer.email}</td>
								<td>${customer.preference}</td>
								<td>${customer.type eq 3 ? '普通客户' : '系统管理员'}</td>
								<td>${customer.description}</td>
								<td>
									<a href="${pageContext.request.contextPath}/router?action=showUpdateForm&id=${customer.id}">更新</a>&nbsp;<a href="${pageContext.request.contextPath}/router?action=delete&id=${customer.id}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
	</body>
</html> 