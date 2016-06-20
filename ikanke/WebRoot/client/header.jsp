<!--header start-->
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="com.benqcz.ikanke.resource.ui" var="bundle"/>

<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="client.title" bundle="${bundle}"/></title>
		<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<div id="box">
			<div id="header" class="clear">
				<h3><fmt:message key="client.title" bundle="${bundle}"/></h3>
				<ul id="header_menus" class="clear">
					<c:forEach var="category" items="${sessionScope.categories}">
						<li><a href="">${category.name}</a></li>
					</c:forEach>
				</ul>	
			</div>
			<div id="content">