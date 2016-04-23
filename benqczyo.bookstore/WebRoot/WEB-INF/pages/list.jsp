<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>书籍列表</title>
	</head>
	<body>
		<div id="box">
			<div id="content">
				<c:if test="${not empty allBooks}">
					<ul>
						<c:forEach var="entry" items="${allBooks}">
							<c:set var="book" value="${entry.value}"/>
							<li>${book.title}${book.author}${book.pub}</li>
						</c:forEach>
					</ul>
				</c:if>	
			</div>
		</div>	
	</body>
</html>