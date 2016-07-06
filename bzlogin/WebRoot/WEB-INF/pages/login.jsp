<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html>
	<head>
		<title>登陆</title>
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/router?action=check" method="post">
			<table>
				<tr>
					<td>姓名:</td>
					<td><input type="text" name="user"/></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input type="text" name="pass"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="auto"/>自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="登陆"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
