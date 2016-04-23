<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<title>登陆</title>
		<meta charset="utf-8" />
		<style>
			#login {margin: 20px auto; padding: 10px; width: 500px;}
		</style>
	</head>
	<body>
		<%
			if (session.getAttribute("name") != null)
				response.sendRedirect("/login/main.jsp");
				
			String error = (String) session.getAttribute("error");
			error = error == null ? "" : error;
;		%>
		<div id="login">
			<form action="/login/validate.do" method="post">
				用户：<input type="text" name="name" /><br />
				密码：<input type="password" name="password" /><br />
				验证：<input type="text" name="captcha" /><img src="/login/captcha" onclick="this.src='/login/captcha?token=' + new Date().getTime();"/>
				<span><%=error%></span><br />
				<input type="submit" value="登陆" />	
			</form>
		</div>
	 </body>
</html>
