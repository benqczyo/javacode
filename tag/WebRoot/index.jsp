<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@page import="java.util.List, java.util.ArrayList" %>
<%@taglib uri="http://www.benqcz.com/servlet/jsp/jstl" prefix="benqcz" %>

<!DOCTYPE html>
<html>
	<head>
		<title>显示远程IP</title>
	</head>
	<body>
		<strong>远程IP地址： 
			<span style="color: red">
				<benqcz:remoteAddress />
			</span>
		</strong>
		<%
			List<String> studs = new ArrayList<String>();
			studs.add("benjamin");
			studs.add("jack");
			studs.add("sue");
			pageContext.setAttribute("studs", studs);
		%>
		<benqcz:forEach items="${studs}" var="stud">
			<strong>这是：<em>${stud}</em></strong> 
		</benqcz:forEach>
	</body>
</html>