<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://www.benqcz.com/servlet/jsp/jstl" prefix="benqcz"%>

<benqcz:antiHotlinking site="http://localhost:8080/tag" redirect="/ad.jsp"></benqcz:antiHotlinking>
<!DOCTYPE html>
<html>
	<head>
		<title>显示远程IP</title>
	</head>
	<body>
		<%
			pageContext.setAttribute("count", 5);
		%>
		<benqcz:repeat count="${pageScope.count}">
			<strong>
				<benqcz:simpleUppercast>client address:</benqcz:simpleUppercast>
				<span style="color: red">
					<benqcz:simpleRemoteAddress /> 
				</span> 
			</strong>
		</benqcz:repeat>
		<benqcz:if test="false">hello</benqcz:if>
	</body>
</html>