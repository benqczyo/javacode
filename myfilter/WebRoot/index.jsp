<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>过滤器</title>
	</head>
	<body>
		<form action="/myfilter/servlet/router?action=test" method="post">
			<textarea rows="10" cols="50" name="text"></textarea><br/>
			<input type="submit" value="提交"/>
		</form>
	</body>
</html>