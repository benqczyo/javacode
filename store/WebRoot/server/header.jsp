<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="pathPrefix" value="${pageContext.request.contextPath}/servlet/router"/>
<!DOCTYPE html>
<html>
	<head>
		<title>后台管理</title>
		<style>
			/*清除样式*/
			body, ul, h3, th, td, input {margin: 0; padding: 0;}
			li {list-style: none;}
			a {text-decoration: none;}
			.clear {zoom: 1;}
			.clear:after {display: block; content: ""; clear: both;}
			/*样式设置*/
			body {font-family: "微软雅黑"; font-size: 14px;}
			#box {margin: 20px auto; width: 60%;}
			#header {margin-bottom: 10px; font-size: 18px; border-bottom: solid 2px #252525;}
			#header div:nth-of-type(1) {float: left;}
			#header div:nth-of-type(2) {float: right;}
			#header li {float: left;}
			#header a {display: block; padding: 4px;}
			#header a, a:visited {color: #000;}
			#header a:hover {color: #e11515;}
			#mgrMenu {margin-bottom: 10px;}
			#mgrMenu a, a:visited {color: #e11515; opacity: 0.6; filter: alpha(opacity=60);}
			#mgrMenu a:hover {opacity: 1; filter: alpha(opacity=100);}
			#mgrMenu div {margin-bottom: 4px;}
			#mgrMenu table {border-collapse: collapse; width: 100%; text-align: center;}
			#mgrMenu th, td {border: solid 1px #99b0da;}
			#mgrMenu th {background: #dbe3fa;}
			#addMenu th, td {border: none;}
			#addMenu span {color: #e11515;}
		</style>
	</head>
	<body>
		<div id="box">
			<div id="header" class="clear">
				<div>
					<h3>后台管理</h3>
				</div>
				<div>
					<ul class="clear">
						<li><a href="${pathPrefix}?action=show&view=bgindex">后台主页</a></li>
						<li><a href="${pathPrefix}?action=show&view=bgmenu">菜单管理</a></li>
						<li><a href="">角色管理</a></li>
						<li><a href="">用户管理</a></li>
					</ul>
				</div>
			</div>
