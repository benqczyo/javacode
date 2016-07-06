<!--header start-->
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="com.benqcz.ikanke.resource.ui" var="bundle"/>

<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="mgr.title" bundle="${bundle}"/></title>
		<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css"/>
		<script>
			function delCategory(id) {
				if (confirm("删除本条分类？"))
					window.location = "${pageContext.request.contextPath}/router?action=delCategory&id=" + id;
			}
			
			function delCategories() {
				var form = document.getElementById("delCategoriesForm"), 
					ids = document.getElementsByName("ids"),
					isChecked = false;
				for (var i = 0; i < ids.length; i++) {
					if (ids[i].checked) {
						isChecked = true;
						break;
					}
				}
				isChecked == false ? 
					alert("没有选择要删除的分类") : confirm("确定删除所选的分类？") && form.submit();
			}
			
			function delBook(id) {
				if (confirm("删除本书？"))
					window.location = "${pageContext.request.contextPath}/router?action=delBook&id=" + id;
			}
		</script>
	</head>
	<body>
		<div id="box">
			<div id="header" class="clear">
				<h3><fmt:message key="mgr.title" bundle="${bundle}"/></h3>
				<ul id="header_menus" class="clear">
					<li><a href="${pageContext.request.contextPath}/manager/"><fmt:message key="mgr.home" bundle="${bundle}"/></a></li>
					<li><a href="${pageContext.request.contextPath}/router?action=listAllCategories"><fmt:message key="mgr.category" bundle="${bundle}"/></a></li>
					<li><a href="${pageContext.request.contextPath}/router?action=listAllBooks"><fmt:message key="mgr.book" bundle="${bundle}"/></a></li>
					<li><a href="${pageContext.request.contextPath}/router?action=listAllOrders"><fmt:message key="mgr.order" bundle="${bundle}"/></a></li>
				</ul>	
			</div>
			<div id="content">