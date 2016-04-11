<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<title>登陆</title>
	</head>
	<body>
		<form action="/login2/validate" method="post">
			用户：<input type="text" name="user" />
			密码：<input type="password" name="password" />
			<input type="submit" />
		</form>
	</body>
</html>