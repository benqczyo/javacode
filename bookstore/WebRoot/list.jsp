<%@page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="text">
<c:set var="prefix" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="home_title" /></title>
    </head>
    <body>
    	<c:choose>
    		<c:when test="${books != null}">
    			<h3><fmt:message key="home_introduce" /></h3>
    			<ul>
	    			<c:forEach var="book" items="${books}">
	    				<c:set var="detail" value="${prefix}/detail.do?id=${book.id}"></c:set>
	    				<li>${book.name}&nbsp;<a href="${detail}"><fmt:message key="a_detail_text" /></a></li>	
	    			</c:forEach>
	    		</ul>
	    	</c:when>
    		<c:otherwise>
    			<c:set var="errorPage" value="${prefix}/error.jsp"></c:set>
    			<c:redirect url="${errorPage}" />	
    		</c:otherwise>
   		</c:choose>	
   		<c:if test="${history != null}">
   			<h3><fmt:message key="home_history" /></h3>
	    	<ul>
	    		<c:forEach var="book" items="${history}">
	    			<li>${book.name}</li>	
	    		</c:forEach>		
	    	</ul>
	    	<c:set var="clear" value="${prefix}/clear.do"></c:set>
	    	<div><a href="${clear}">清空</a></div>
	    </c:if>	
 	</body>
</html>
</fmt:bundle>