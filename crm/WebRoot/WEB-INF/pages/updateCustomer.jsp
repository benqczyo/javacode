<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<title>修改客户</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/router?action=validateUpdateFormBean" method="post">
			<table>
				<tr>
					<td colspan="3">
						<input type = "hidden" name="id" value="${formBean.id}"/>
					</td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input type="text" name="name" value="${formBean.name}"/></td>
					<td><strong>${formBean.errors.name}</strong></td>
				</tr>
				<tr>
					<td>性别:</td>
					<td>
						<input type="radio" name="gender" value="1" 
							<c:if test="${formBean.gender eq null or formBean.gender eq '1'}">
								checked = "checked"
							</c:if>
						/>男
						<input type="radio" name="gender" value="0" 
							<c:if test="${formBean.gender eq '0'}">
								checked = "checked"
							</c:if>
						/>女
					</td>
					<td><strong>${formBean.errors.gender}</strong></td>
				</tr>
				<tr>
					<td>出生日期:</td>
					<td><input type="text" name="birthday" value="${formBean.birthday}"/></td>
					<td><strong>${formBean.errors.birthday}</strong></td>
				</tr>
				<tr>
					<td>移动电话:</td>
					<td><input type="text" name="cellphone" value="${formBean.cellphone}"/></td>
					<td><strong>${formBean.errors.cellphone}</strong></td>
				</tr>
				<tr>
					<td>电子邮箱:</td>
					<td><input type="text" name="email" value="${formBean.email}"/></td>
					<td><strong>${formBean.errors.email}</strong></td>
				</tr>
				<tr>
					<td>爱好:</td>
					<td><input type="text" name="preference" value="${formBean.preference}"/></td>
					<td><strong>${formBean.errors.preference}</strong></td>
				</tr>
				<tr>
					<td>类型:</td>
					<td>
						<input type="radio" name="type" value="4" 
							<c:if test="${formBean.type eq null or formBean.type eq '4'}">
								checked = "checked"
							</c:if>
						/>普通客户
						<input type="radio" name="type" value="3"
							<c:if test="${formBean.type eq '3'}">
								checked = "checked"
							</c:if>
						/>系统管理员
 					</td>
 					<td><strong>${formBean.errors.type}</strong></td>
				</tr>
				<tr>
					<td>个人简介:</td>
					<td><input type="text" name="description" value="${formBean.description}"/></td>
					<td><strong>${formBean.errors.description}</strong></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="submit" value="更新"/>
					</td>
				</tr>
			</table>
			
		</form>
	</body>
</html>
