<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="com.benqcz.filter.resource.ui" var="bundle" scope="page"/>
<!DOCTYPE html>
<html>
	<head>
		<title>
			<fmt:message key="ui_login_title" bundle="${bundle}"/>
		</title>	
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/service/router?action=login" method="get">
			<table>
				<tr>
					<td>
						<fmt:message key="ui_login_name" bundle="${bundle}"/>
					</td>
					<td>
						<input type="text" name="name"/>
					</td>
				</tr>
				<tr>
					<td>
						<fmt:message key="ui_login_password" bundle="${bundle}"/>
					</td>
					<td>
						<input type="text" name="password"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value='<fmt:message key="ui_login_submit" bundle="${bundle}"/>'/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>