<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>注册</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/router?action=register" method="post">
			<table>
				<tr>
					<td>用户：</td>
					<td><input type="text" name="name" value="${requestScope.formBean.name}"/></td>
					<td><span>${requestScope.formBean.messages.name}</span></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" value="${requestScope.formBean.password}"/></td>
					<td><span>${requestScope.formBean.messages.password}</span></td>
				</tr>
				<tr>
					<td>确认：</td>
					<td><input type="password" name="password2" value="${requestScope.formBean.password2}"/></td>
					<td><span>${requestScope.formBean.messages.password2}</span></td>
				</tr>
				<tr>
					<td colspan="3"><span>${requestScope.formBean.messages.result}</span></td>
				</tr>
			</table>
			<input type="submit" value="注册"/>
		</form>
	</body>
</html>