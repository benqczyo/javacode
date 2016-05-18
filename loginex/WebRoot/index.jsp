<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="com.benqcz.loginex.resource.ui" var="bundle"/>
<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="login" bundle="${bundle}"/></title>
	</head>	
	<body>
		<form action="" method="POST">
			<table>
				<tr>
					<td><fmt:message key="name" bundle="${bundle}"/></td>
					<td><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td><fmt:message key="password" bundle="${bundle}"/></td>
					<td><input type="text" name="name"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>