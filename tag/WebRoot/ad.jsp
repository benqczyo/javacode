<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@page import="java.util.Map, java.util.HashMap, domain.Person" %>
<%@taglib uri="http://www.benqcz.com/servlet/jsp/jstl" prefix="benqcz" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>广告页</title>
	</head>
	<body>
		<jsp:useBean id="person" class="domain.Person"/>
		<c:set target="${person}" property="name" value="benjamin"/>
		<strong>${person.name}</strong>
		<% 
			pageContext.setAttribute("map", new HashMap<String, Person>());
		%>
		<c:set target="${map}" property="benjamin" value="${person}"/>
		<strong>${map.benjamin.name}</strong>
		<c:forEach var="item" items="${map}">
			<strong><em>${item.key} = ${item.value}</em></strong>
		</c:forEach>
	</body>
</html>