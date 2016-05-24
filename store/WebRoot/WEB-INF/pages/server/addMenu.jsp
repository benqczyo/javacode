<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/server/header.jsp"%>
<div id="addMenu">
	<form action="${pathPrefix}?action=addmenu" method="post">
		<table>
			<tr>
				<td colspan="3"><input type="hidden" name="id"/></td>
			</tr>
			<tr>
				<td>标题：</td>
				<td><input type="input" name="title" value="${formBean.title}"/></td>
				<td><span>${formBean.messages.title}</span></td>
			</tr>
			<tr>
				<td>地址：</td>
				<td><input type="input" name="uri" value="${formBean.uri}"/></td>
				<td><span>${formBean.messages.uri}</span></td>
			</tr>
			<tr>
				<td>描述：</td>
				<td><input type="input" name="description" value="${formBean.description}"/></td>
				<td><span>${formBean.messages.description}</span></td>
			</tr>
			<tr>
				<td colspan="3"><span>${formBean.messages.result}</span></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="添加菜单"/></td>
			</tr>
		</table>
	</form>
</div>
<%@include file="/server/footer.jsp"%>