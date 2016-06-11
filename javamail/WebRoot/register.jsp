<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>注册</title>
	</head>
	<body>
		<c:url var="url" value="/router">
			<c:param name="action" value="register"/>	
		</c:url>
		<form action="${url}" method="post">
			<table>
				<tr>
					<td>*账号名称：</td>
					<td><input type="text" name="name" value="${formBean.name}"/></td>
					<td><span>${formBean.messages.name}</span></td>
				</tr>
				<tr>
					<td>*账号密码：</td>
					<td><input type="password" name="password" value="${formBean.password}"/></td>
					<td><span>${formBean.messages.password}</span></td>
				</tr>
				<tr>
					<td>*重复密码：</td>
					<td><input type="password" name="password2" value="${formBean.password2}"/></td>
					<td><span>${formBean.messages.password2}</span></td>
				</tr>
				<tr>
					<td>*电子邮件：</td>
					<td><input type="text" name="email" value="${formBean.email}"/></td>
					<td><span>${formBean.messages.email}</span></td>
				</tr>
				<tr>
					<td colspan="3"><span>${formBean.messages.result}</span></td>
				</tr>
			</table>
			<input type="submit" value="注册"/>
		</form>
	</body>
</html>
