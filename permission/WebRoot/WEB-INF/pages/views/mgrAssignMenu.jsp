<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/pages/header.jsp"%>
<div id="mgrAssignMenu">
	<form action="${uri}?action=assignMenu" method="post" id="assignMenuForm">
		<input type="hidden" name="roleId" value="${role.id}" />
		<table class="list">
			<tr>
				<th>
					已有菜单项：
				</th>
				<th>
					所有菜单项：
				</th>
			</tr>
			<tr>
				<td>
					<ul>
						<c:forEach var="menu" items="${role.menus}">
							<li>
								${menu.title}
							</li>
						</c:forEach>
					</ul>
				</td>
				<td>
					<ul>
						<c:forEach var="menu" items="${menus}">
							<li>
								<input type="checkbox" name="menuId" value="${menu.id}" />
								${menu.title}
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button"
						value="<fmt:message key='btn.save' bundle='${bundle}'/>" 
						onclick="javascript: doAssign()"	
					/>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="/WEB-INF/pages/footer.jsp"%>