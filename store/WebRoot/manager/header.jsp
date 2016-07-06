<!--header start-->
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="resource.ui" var="bundle"/>

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
			a.selected, a.selected:visited {color: red;}
			span {color: red;}
			body {font-family: "微软雅黑"; font-size: 14px;}
			#box {margin: 20px auto; width: 800px;}
			#header {font-size: 16px; border-bottom: solid 1px #000;}
			#header h3 {float: left;}
			#header ul {float: right;}
			#header li {float: left;}
			#header a {display: block; padding: 4px;}
			table.list {border-collapse: collapse; width: 100%; text-align: center;}
			table.list tr:hover {cursor: pointer;}
			table.list tr:nth-of-type(even) {background: #F7FAFF;}
			table.list th, table.list td {padding: 4px;}
			table.list th {background: #CEEBEF; color: #697279;}
			#page {text-align: center;}
		</style>
		<script>
			function delMenu(id) {
				if (confirm("确定删除所选菜单项？")) window.location = "${pageContext.request.contextPath}/router?action=delMenu&id=" + id;	
			}
			
			function delRole(id) {
				if (confirm("确定删除所选角色？")) window.location = "${pageContext.request.contextPath}/router?action=delRole&id=" + id;	
			}
			
			function delMenus() {
				var form = document.getElementById("delMenusForm"), ids = document.getElementsByName("ids"), isChecked = false;
				for (var i = 0; i < ids.length; i++) {
					if (ids[i].checked) {
						isChecked = true;
						break;
					}
				}
				if (isChecked == false) {
					alert("没有选择菜单项");
				} else {
					
					confirm("确定删除所选菜单项？") && form.submit();
				}
			}
			
			function delRoles() {
				var form = document.getElementById("delRolesForm"), ids = document.getElementsByName("ids"), isChecked = false;
				for (var i = 0; i < ids.length; i++) {
					if (ids[i].checked) {
						isChecked = true;
						break;
					}
				}
				if (isChecked == false) {
					alert("没有选择角色");
				} else {
					
					confirm("确定删除所选角色？") && form.submit();
				}
			}
			
			function doAssignMenu() {
				var form = document.getElementById("assignMenuForm"), menuIds = document.getElementsByName("menuId"), isChecked = false;
				for (var i = 0; i < menuIds.length; i++) {
					if (menuIds[i].checked) {
						isChecked = true;
						break;
					}
				}
				if (isChecked == false) {
					confirm("确定清空用户分配菜单？") && form.submit();
				} else {
					confirm("确定分配菜单？") && form.submit();
				}
			}
			
			function doAssignRole() {
				var form = document.getElementById("assignRoleForm"), menuIds = document.getElementsByName("roleId"), isChecked = false;
				for (var i = 0; i < menuIds.length; i++) {
					if (menuIds[i].checked) {
						isChecked = true;
						break;
					}
				}
				if (isChecked == false) {
					confirm("确定清空用户分配的角色？") && form.submit();
				} else {
					confirm("确定分配角色？") && form.submit();
				}
			}
		</script>
	</head>
	<body>
		<div id="box">
			<div id="header" class="clear">
				<h3><fmt:message key="mgr.title" bundle="${bundle}"/></h3>
				<ul id="header_menus" class="clear">
					<li><a href="${pageContext.request.contextPath}/manager/"><fmt:message key="mgr.home" bundle="${bundle}"/></a></li>
					<li><a href="${pageContext.request.contextPath}/router?action=listAllMenus"><fmt:message key="mgr.menu" bundle="${bundle}"/></a></li>
					<li><a href="${pageContext.request.contextPath}/router?action=listAllRoles"><fmt:message key="mgr.role" bundle="${bundle}"/></a></li>
					<li><a href="${pageContext.request.contextPath}/router?action=listAllAccounts"><fmt:message key="mgr.account" bundle="${bundle}"/></a></li>
				</ul>	
			</div>
			<div id="content">