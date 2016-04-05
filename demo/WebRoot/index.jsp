<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <% 
  	Cookie[] cookies = request.getCookies();
  	String user = "";
  	String password = "";
  	if (cookies != null) {
  		for (Cookie c : cookies) {
  			String cName = c.getName();
  			if ("user".equalsIgnoreCase(cName)) user = c.getValue();
  			if ("password".equalsIgnoreCase(cName)) password = c.getValue();
  		}
  	}
  %>
   <form action="/demo/login/login" method="post">
 		USER:<input type="text" name="user" value="<%=user%>" /><br />
 		PASS:<input type="text" name="password" value="<%=password%>" /><br />
 		<input type="checkbox" name="keep" />keep<br />
 		<input type="submit" />
   </form>
  </body>
</html>
