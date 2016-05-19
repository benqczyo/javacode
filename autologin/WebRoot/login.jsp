<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="pathPrefix" value="${pageContext.request.contextPath}/servlet/router?action="/>
<fmt:setBundle basename="com.benqcz.autologin.resource.ui" var="bundle"/>
<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="login" bundle="${bundle}"/></title>
		<style>
			span {color: red;}
		</style>
	</head>	
	<body>
		<form action="${pathPrefix}login" method="POST">
			<table>
				<tr>
					<td><fmt:message key="name" bundle="${bundle}"/></td>
					<td><input type="text" name="name" value="${formBean.name}"/></td>
					<td><span>${formBean.messages.name}</span></td>
				</tr>
				<tr>
					<td><fmt:message key="password" bundle="${bundle}"/></td>
					<td><input type="text" name="password" value="${formBean.password}"/></td>
					<td><span>${formBean.messages.password}</span></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="checkbox" name="auto" ${formBean.auto eq "on" ? "checked" : ""} /><fmt:message key="auto" bundle="${bundle}"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<strong><span>${formBean.messages.result}</span></strong>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="submit" value='<fmt:message key="login" bundle="${bundle}"/>'/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>