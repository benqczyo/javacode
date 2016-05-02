<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
  	<title>主页</title>  
  </head>
  <body>
  	<div>
  		<h1>论坛主页</h1>
  		<c:if test="${empty sessionScope.user}">
  			<div>
  				<a href="${pageContext.servletContext.contextPath}/register.jsp">注册</a>&nbsp;<a href="${pageContext.servletContext.contextPath}/login.jsp">登陆</a>
  			</div>
  		</c:if>
  		<c:if test="${not empty sessionScope.user}">
  			<div>
  				<p>欢迎您：<span>${sessionScope.user.name}</span>&nbsp;<a href="">注销</a></p>
  			</div>
  		</c:if>
  	</div>
  </body>
</html>
