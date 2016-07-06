<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>登陆</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/router?action=login" method="post">
			<table>
				<tr>
					<td>用户：</td>
					<td><input type="text" name="name" value="${requestScope.formBean.name}"/></td>
					<td><span>${requestScope.formBean.messages.name}</span></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" value="${formBean.password}"/></td>
					<td><span>${requestScope.formBean.messages.password}</span></td>
				</tr>
				<tr>
					<td colspan="3"><span>${requestScope.formBean.messages.result}</span></td>
				</tr>
			</table>
			<input type="submit" value="登陆"/>
		</form>
	</body>
</html>