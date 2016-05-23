<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<title>后台管理</title>
	</head>
	<body>
		<div id="box">
			<div id="header">
				<ul>
					<li><a href="${pageContext.request.contextPath}/servlet/router?action=show&view=menu">菜单管理</a></li>
					<li><a href="">角色管理</a></li>
					<li><a href="">管理权限</a></li>
				</ul>
			</div>
