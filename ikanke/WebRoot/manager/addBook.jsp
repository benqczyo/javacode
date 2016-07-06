<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="mgrAddBook">
	<form action="${pageContext.request.contextPath}/router?action=doAddBook" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>*<fmt:message key="book.name" bundle="${bundle}"/>:</td>
				<td><input type="input" name="name" value="${formBean.name}"/></td>
				<td><span>${formBean.messages.name}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="book.author" bundle="${bundle}"/>:</td>
				<td><input type="input" name="author" value="${formBean.author}"/></td>
				<td><span>${formBean.messages.author}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="book.price" bundle="${bundle}"/>:</td>
				<td><input type="input" name="price" value="${formBean.price}"/></td>
				<td><span>${formBean.messages.price}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="book.pic" bundle="${bundle}"/>:</td>
				<td><input type="file" name="pic"/></td>
				<td><span>${formBean.messages.pic}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="book.description" bundle="${bundle}"/>:</td>
				<td><input type="text" name="description" value="${formBean.description}"/></td>
				<td><span>${formBean.messages.description}</span></td>
			</tr>
			<tr>
				<td>*<fmt:message key="book.category" bundle="${bundle}"/>:</td>
				<td>
					<select name="categoryId">
						<c:forEach var="item" items="${formBean.categories}">
							<option value="${item.id}" ${item.id eq formBean.categoryId ? "selected='true'" : ""}>
								${item.name}
							</option>
						</c:forEach>
					</select>
				</td>
				<td><span>${formBean.messages.categoryId}</span></td>	
			</tr>
			<tr>
				<td colspan="3">
					<span>${formBean.messages.result}</span>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit" value="<fmt:message key='book.btn.add' bundle='${bundle}'/>"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="footer.jsp"%>