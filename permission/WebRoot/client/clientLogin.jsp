<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resource.ui" var="bundle"/>
<!DOCTYPE html>
<html>
	<head>
		<title>登陆</title>
		<style>
			span {color: red;}
		</style>
	</head>
	<body>
		<c:url value="/router?action=login" var="url"/>
		<form action="${url}" method="post">
			<table>
				<tr>
					<td>账号：</td>
					<td><input type="text" name="name" value="${formBean.name}"/></td>
					<td><span>${formBean.messages.name}</span></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" value="${formBean.password}"/></td>
					<td><span>${formBean.messages.password}</span></td>
				</tr>
				<tr>
					<td colspan="3"><span>${formBean.messages.result}</span></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="登陆"/></td>
				</tr>
			</table>	
		</form>
	</body>
</html>