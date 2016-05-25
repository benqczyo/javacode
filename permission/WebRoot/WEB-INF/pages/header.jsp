<!--header start-->
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="resource.ui" var="bundle"/>
<c:set var="uri" value="${pageContext.request.contextPath}/router"/>

<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="mgr.title" bundle="${bundle}"/></title>
		<style>
			body, h3, ul {margin: 0; padding: 0;}
			li {list-style: none;}
			a {text-decoration: none;}
			.clear {zoom: 1;}
			.clear:after {display: block; content: ""; clear: both;}
			.l {float: left;}
			.r {float: right;}
			a, a:visited {color: #000;}
			a:hover {color: #e11515;}
			body {font-family: "微软雅黑"; font-size: 14px;}
			#box {margin: 20px auto; width: 800px;}
			#header {font-size: 16px; border-bottom: solid 1px #000;}
			#header h3 {float: left;}
			#header ul {float: right;}
			#header li {float: left;}
			#header a {display: block; padding: 4px;}
		</style>
	</head>
	<body>
		<div id="box">
			<div id="header" class="clear">
				<h3><fmt:message key="mgr.title" bundle="${bundle}"/></h3>
				<ul id="header_menus" class="clear">
					<li><a href="${uri}?action=show&view=mgrIndex"><fmt:message key="mgr.home" bundle="${bundle}"/></a></li>
					<li><a href="${uri}?action=show&view=mgrMenu"><fmt:message key="mgr.menu" bundle="${bundle}"/></a></li>
					<li><a href="${uri}?action=show&view=mgrRole"><fmt:message key="mgr.role" bundle="${bundle}"/></a></li>
					<li><a href="${uri}?action=show&view=mgrAccount"><fmt:message key="mgr.account" bundle="${bundle}"/></a></li>
				</ul>	
			</div>
			<div id="content">