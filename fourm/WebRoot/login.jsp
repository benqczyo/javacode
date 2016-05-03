<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html>
  <head>
 	<title>登陆</title>
  	<link type="text/css" rel="stylesheet" href="css/css.css"/>
  </head>
  <body>
	<div>
		<form action="${pageContext.servletContext.contextPath}/controller?action=login" method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" value="${formBean.username}"/></td>
					<td><span>${formBean.errors.username}</span></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" value="${formBean.password}"/></td>
					<td><span>${formBean.errors.password}</span></td>
				</tr>
				<tr>
					<td colspan="3"><span>${formBean.errors.message}</span></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="登陆"/></td>
				</tr>
			</table>	
		</form>
	</div>  	
  </body>
</html>