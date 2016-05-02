<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@page isErrorPage="true" %>

<!DOCTYPE html>
<html>
  <head>
  	<title>提示</title>  
  </head>
  <body>
  	<strong>${pageContext.exception.message}${message}</strong>&nbsp;<a href="${pageContext.servletContext.contextPath}">返回主页</a>
  </body>
</html>