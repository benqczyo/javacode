<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.UUID" %>

<html>
	<head>
		<title>注册</title>
	</head>
	<body>
		<form action="/register/register" method="post">
			<%
				String token = UUID.randomUUID().toString();
						session.setAttribute("token", token);
			%>
			<input type="hidden" name="token" value="<%=token%>"/>
			<table>
				<tr>
					<td>姓名:</td>
					<td><input type="text" name="user" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="提交" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
