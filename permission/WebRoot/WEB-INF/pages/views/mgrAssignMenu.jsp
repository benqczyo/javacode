<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/pages/header.jsp"%>
<div id="mgrAssignMenu">
	<form action="${uri}?action=assignMenu" method="post">
		<table class="list">
			<tr>
				<th colspan="3">${role.name}</th>
			</tr>
			<tr>
				<th>已有菜单项：</th>
				<th>所有菜单项：</th>
			</tr>
			<tr>
				<td>
					<ul>
						<c:forEach var="menu" items="${menus}">
							<li>${menu.title}</li>
						</c:forEach>
					</ul>
				</td>
				<td>
					<ul>
						<c:forEach var="menu" items="${role.menus}">
							<li>${menu.title}</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="/WEB-INF/pages/footer.jsp"%>