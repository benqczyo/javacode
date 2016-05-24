<!--header start-->
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="resource.ui" var="bundle"/>
<c:set var="uri" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="mgr.title" bundle="${bundle}"/></title>
	</head>
	<body>
		<div id="box">
			<div id="header" class="clear">
				<h3><fmt:message key="mgr.title" bundle="${bundle}"/></h3>
				<ul id="header_menus" class="clear">
					<li><a href=""><fmt:message key="mgr.home" bundle="${bundle}"/></a></li>
					<li><a href=""><fmt:message key="mgr.menu" bundle="${bundle}"/></a></li>
					<li><a href=""><fmt:message key="mgr.role" bundle="${bundle}"/></a></li>
					<li><a href=""><fmt:message key="mgr.account" bundle="${bundle}"/></a></li>
				</ul>	
			</div>
			<div id="content">