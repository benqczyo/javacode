<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<div id="clientShowCart">
	<c:if test="${empty requestScope.cart.items}">
		<span><fmt:message key="cart.empty" bundle="${bundle}"/></span>
	</c:if>
	<c:if test="${not empty requestScope.cart.items}">
		<ul id="items" class="clear">
			<c:forEach var="item" items="${requestScope.cart.items}">
				<c:set var="item" value="${item.value}"/>
				<li class="item" style="float: left; width: 212px;">
					<div class="clear">
						<div style="float: left; border: solid 1px gray;">
							<div>
								<img title="${item.book.description}" style="width: 100px; height: 130px;" src="${pageContext.request.contextPath}/router?action=showCover&pic=${item.book.pic}"/>
							</div>
							<div style="text-align:center;">
								<span><a href="">增加</a></span>
								<span><a href="">删除</a></span>
							</div>
						</div>
						<ul style="float: right; width: 100px;">
							<li>书名：${item.book.name}</li>
							<li>作者：${item.book.author}</li>
							<li>数量：<span>${item.numberOfBooks}</span></li>
							<li>总价：<span>${item.totalPrice}</span></li>
						</ul>
					</div>
				</li>
			</c:forEach>
		</ul>
		<div class="clear" style="margin-top: 10px;">
			<div style="float: left;">
				合计商品数：<span>${requestScope.cart.numberOfBooks}</span>
				总价：<span>￥${requestScope.cart.totalPrice}</span>
			</div>
			<div style="float: right;">
				<a href="">生成订单</a>
			</div>
		</div>
	</c:if>
</div>
<%@include file="footer.jsp"%>