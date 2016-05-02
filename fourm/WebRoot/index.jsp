<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
   <title>主页</title>
  </head>
  <body>
	<div>
		<div>
			<h1>论坛主页</h1>
		</div>
		<div>
			<c:if test="${sessionScope.user == null}">
				<p><a href="${pageContext.servletContext.contextPath}/register.jsp">注册</a>&nbsp;<a href="${pageContext.servletContext.contextPath}/login.jsp">登陆</a></p>
			</c:if>
			<c:if test="${sessionScope.user != null}">
				<p><strong>欢迎您：<span>${sessionScope.user.username}</span></strong>&nbsp;<a href="${pageContext.servletContext.contextPath}/controller?action=logout">注销</a></p>
			</c:if>
		</div>
	</div>  	
  </body>
</html>
