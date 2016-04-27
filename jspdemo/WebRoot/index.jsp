<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@page import="bean.User" %>

<!DOCTYPE html>
<html>
	<head>
		<title>JSP-DEMO</title>
	</head>
  <body>
  	<!--<jsp:useBean id="user" class="bean.User" scope="request"/>
  	<jsp:setProperty property="*" name="user"/>
  	--><%
  	pageContext.setAttribute("p", new User("benjamin"), pageContext.REQUEST_SCOPE);
  	%>
  	<strong>${requestScope.p}</strong>
  	<!--<strong>姓名：<jsp:getProperty property="name" name="user"/></strong>
  --></body>
</html>
