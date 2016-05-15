<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List, java.util.ArrayList, java.util.Set, java.util.HashSet, java.util.Map, java.util.HashMap" %>
<%@taglib uri="http://www.benqcz.com/servlet/jsp/jstl" prefix="benqcz"%>

<benqcz:antiHotlinking site="http://localhost:8080/tag" redirect="/ad.jsp"></benqcz:antiHotlinking>
<!DOCTYPE html>
<html>
	<head>
		<title>显示远程IP</title>
	</head>
	<body>
		<benqcz:repeat count="5">
			<strong>
				<benqcz:simpleUppercast>client address:</benqcz:simpleUppercast>
				<span style="color: red">
					<benqcz:simpleRemoteAddress /> 
				</span> 
			</strong>
		</benqcz:repeat>
		<benqcz:if test="false">hello</benqcz:if>
		<%
			pageContext.setAttribute("var", "女");
		%>
		<benqcz:choose>
			<benqcz:when test="${var eq '男'}">
				男
			</benqcz:when>
			<benqcz:otherwise>
				女
			</benqcz:otherwise>
		</benqcz:choose>
		<%
			String[] names1 = new String[] {"jack", "sue", "ben"};
			pageContext.setAttribute("names1", names1);
			List<String> names2 = new ArrayList<String>();
			names2.add("jack");
			names2.add("sue");
			names2.add("ben");
			pageContext.setAttribute("names2", names2);
			Set<String> names3 = new HashSet<String>();
			names3.add("jack");
			names3.add("sue");
			names3.add("ben");
			pageContext.setAttribute("names3", names3);
			Map<String, String> names4 = new HashMap<String, String>();
			names4.put("jack", "11");
			names4.put("ben", "22");
			names4.put("sue", "33");
			pageContext.setAttribute("names4", names4);
			pageContext.setAttribute("names5", "String");
			int[] names6 = new int[] {1, 2, 3, 4, 5};
			pageContext.setAttribute("names6", names6);
		%>
		<benqcz:forEach items="${names1}" var="name">
			<strong>${name}</strong>
		</benqcz:forEach>
		<benqcz:htmlFilter>
			<!DOCTYPE html>
				<html>
					<head>
						<title>显示远程IP</title>
					</head>
					<body>
						<br/>
						<br/>
						<br/>
						<hr/>
						&nbsp;
					</body>
				</html>
		</benqcz:htmlFilter>
	</body>
</html>