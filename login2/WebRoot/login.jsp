<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.Random" %>

<html>
	<head>
		<title>登陆</title>
	</head>
	<body>
		<form action="/login2/validate" method="post">
			用户：<input type="text" name="user" /><br />
			密码：<input type="password" name="password" /><br />
			验证：<input type="text" name="captcha" /><br />
			<a href="javascript:;" title="点击切换"
				onclick="document.getElementById('captcha').src='/login2/captcha?tick=' + Math.random();">
					<img src="/login2/captcha" id="captcha" /></a><br />
			<input type="submit" />
		</form>
	</body>
</html>