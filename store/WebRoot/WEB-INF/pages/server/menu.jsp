<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/server/header.jsp"%>
<div id=mgrMenu>
	<h3>菜单列表</h3>
	<p><a href="">添加</a></p>
	<table>
		<tr>
			<th>序号</th>
			<th>菜单名</th>
			<th>地址</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
		<c:forEach var="menu" items="${menus}" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${menu.title}</td>
				<td>${menu.uri}</td>
				<td>${menu.description}</td>
				<td>
					<a href="">删除</a>
					<a href="">修改</a>
				</td>
			</tr>
		</c:forEach>	
	</table>
</div>
<%@include file="/server/footer.jsp"%>