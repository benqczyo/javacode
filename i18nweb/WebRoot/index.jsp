<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.ResourceBundle, java.util.Locale"%>

<% 
	ResourceBundle rb = ResourceBundle.getBundle("com.benqcz.i18nweb.resource.ui", request.getLocale());
%>

<!DOCTYPE html>
<html>
	<head>
		<title><%=rb.getString("ui_login_title")%></title>
	</head>
	<body>
		<div id="box">
			<form action="" method="post">
				<table>
					<tr>
						<td><%=rb.getString("ui_login_name")%></td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td><%=rb.getString("ui_login_password")%></td>
						<td><input type="password"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="<%=rb.getString("ui_login_submit")%>"/>
						</td>
					</tr>
				</table>				
			</form>
		</div>
	</body>
</html>