<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://www.benqcz.com/jsp/jstl" prefix="benqcz"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<form action="/token/servlet/router?action=login" method="post">
			<input type="hidden" name="token" value="<benqcz:token/>"/>
			Name:<input type="text" name="name"/><br/>
			<input type="submit" value="login"/>
		</form>
	</body>
</html>