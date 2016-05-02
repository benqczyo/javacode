<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html>
  <head>
 	<title>注册</title>
  	<link type="text/css" rel="stylesheet" href="css/css.css"/>
  </head>
  <body>
	<div>
		<form action="${pageContext.servletContext.contextPath}/controller?action=register" method="post">
			<table>
				<tr>
					<td>*请输入邮箱：</td>
					<td><input type="text" name="email" value="${formBean.email}"/></td>
					<td><span>${formBean.errors.email}</span></td>
				</tr>
				<tr>
					<td>*请输入用户名：</td>
					<td><input type="text" name="username" value="${formBean.username}"/></td>
					<td><span>${formBean.errors.username}</span></td>
				</tr>
				<tr>
					<td>*请输入密码：</td>
					<td><input type="password" name="password" value="${formBean.password}"/></td>
					<td><span>${formBean.errors.password}</span></td>
				</tr>
				<tr>
					<td>*重复密码：</td>
					<td><input type="password" name="password2" value="${formBean.password2}"/></td>
					<td><span>${formBean.errors.password2}</span></td>
				</tr>
				<tr>
					<td>*请输入出生日期：</td>
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