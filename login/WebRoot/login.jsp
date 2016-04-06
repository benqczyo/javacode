<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<title>登陆</title>
		<meta charset="utf-8" />
		<style>
			#login {margin: 20px auto; padding: 10px; width: 200px;}
		</style>
	</head>
	<body>
		<%
			if (session.getAttribute("login") == null) {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
			
			String name = (String) session.getAttribute("name");
			name = name == null ? "" : name;
			String password = (String) session.getAttribute("password");
			password = password == null ? "" : password;
		%>
		<div id="login">
			<form action="/login/validate.do" method="post">
				用户：<input type="text" name="name" value="<%=name%>" /><br />
				密码：<input type="password" name="password" value="<%=password%>" /><br />
				<input type="submit" value="登陆" />	
			</form>
		</div>
	 </body>
</html>
