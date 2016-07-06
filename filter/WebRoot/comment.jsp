<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>评论</title>	
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/service/router?action=comment" method="post">
			<textarea rows="10" cols="50" name="comment"></textarea><br/>
			<input type="submit" value="comment"/>
		</form>
	</body>
</html>