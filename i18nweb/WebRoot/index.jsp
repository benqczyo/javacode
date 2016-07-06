<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="com.benqcz.i18nweb.resource.ui" scope="page" var="bundle"/>
${b}
<!DOCTYPE html>
<html>
	<head>
		<title>
			<fmt:message key="ui_login_title" bundle="${bundle}"/>
		</title>
	</head>
	<body>
		<div id="box">
			<form action="" method="post">
				<table>
					<tr>
						<td><fmt:message key="ui_login_name" bundle="${bundle}"/><br></td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td><fmt:message key="ui_login_password" bundle="${bundle}"/></td>
						<td><input type="password"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value='<fmt:message key="ui_login_submit" bundle="${bundle}"/>'/>
						</td>
					</tr>
				</table>				
			</form>
		</div>
	</body>
</html>