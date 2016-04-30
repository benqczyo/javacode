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
  				<a href="${pageContext.servletContext.contextPath}/register.do">注册</a>&nbsp;<a href="${pageContext.servletContext.contextPath}/login.do"">登陆</a>
  			</div>
  		</c:if>
  		<c:if test="${not empty sessionScope.user}">
  			<div>
  				<h2>欢迎您：<span>${sessionScope.user.name}</span></h2>
  			</div>
  		</c:if>
  	</div>
  </body>
</html>
