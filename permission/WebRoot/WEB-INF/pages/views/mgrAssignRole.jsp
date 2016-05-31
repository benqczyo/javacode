<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/pages/header.jsp"%>
<div id="mgrAssignRole">
	<form action="${uri}?action=assignRole" method="post" id="assignRoleForm">
		<input type="hidden" name="accountId" value="${account.id}" />
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
						<c:forEach var="role" items="${account.roles}">
							<li>
								${role.name}
							</li>
						</c:forEach>
					</ul>
				</td>
				<td>
					<ul>
						<c:forEach var="role" items="${roles}">
							<li>
								<input type="checkbox" name="roleId" value="${role.id}" />
								${role.name}
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button"
						value="<fmt:message key='btn.save' bundle='${bundle}'/>" 
						onclick="javascript: doAssignRole()"	
					/>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="/WEB-INF/pages/footer.jsp"%>