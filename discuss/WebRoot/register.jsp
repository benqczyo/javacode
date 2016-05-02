<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
  	<title>注册</title>
  	<style>
  		span {color: red;}
  	</style>
  </head>
  <body>
  	<div>
  		<form action="${pageContext.servletContext.contextPath}/router?action=register" method="post">
  			<table>
  				<tr>
  					<td>*注册邮箱：</td>
  					<td><input type="text" name="email" value="${formBean.email}"/></td>
  					<td><span>${formBean.errors.email}</span></td>
  				</tr>
  				<tr>
  					<td>*用户名：</td>
  					<td><input type="text" name="username" value="${formBean.username}"/></td>
  					<td><span>${formBean.errors.username}</span></td>
  				</tr>
  				<tr>
  					<td>*密码：</td>
  					<td><input type="text" name="password" value="${formBean.password}"/></td>
  					<td><span>${formBean.errors.password}</span></td>
  				</tr>
  				<tr>
  					<td>出生日期：</td>
  					<td><input type="text" name="birthday" value="${formBean.birthday}"/></td>
  					<td><span>${formBean.errors.birthday}</span></td>
  				</tr>
  				<tr>
  					<td colspan="3"><input type="submit" value="注册"/></td>
  				</tr>
  			</table>
  		</form>
  	</div>
  </body>
</html>
