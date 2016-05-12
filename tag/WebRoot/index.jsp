<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib uri="http://www.benqcz.com/servlet/jsp/jstl" prefix="benqcz" %>

<!DOCTYPE html>
<html>
	<head>
		<title>显示远程IP</title>
	</head>
	<body>
		<strong>远程IP地址：<span style="color: red"><benqcz:remoteAddress/></span></strong>
	</body>
</html>