<%@ page import="java.util.List, db.Product, cfg.Configer" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<meta charset="utf-8" />
		<title>手机列表</title>
		<style>
			.clear {zoom: 1;}
			.clear:after {display: block; content: ""; clear: both;}
			body, ul, p {margin: 0; padding: 0;}
			li {list-style: none;}
			img {border: none; vertical-align: top;}
			a {text-decoration: none;}
			#box {margin: 100px auto; padding: 20px; border: solid 1px #ddd; width: 644px;}
			#box ul {}
			#box ul li a {opacity: 0.8; filter: alpha(opacity=80);}
			#box ul li a:hover {opacity: 1.0; filter: alpha(opacity=100);}
			#box ul li {position: relative; float: left; padding: 0 10px; width: 140px; border-left: solid 1px #dddddd;}
			#box ul li:nth-of-type(1) {border-left: none;}
			#box ul li img {width: 120px; height: 120px;}
			#box ul li p {margin-top: 11px;}
			#box ul li p strong {font-size: 14px; font-style: normal; color: #c61822; line-height: 22px;}
			#box ul li p a {font-size: 12px; color: #858585; line-height: 19px;}
			#box ul li p a:hover {color: #c81623; text-decoration: underline;}
		</style>
	</head>
	<body>
		<div id="box">
			<ul class="clear">
			<% 
				List<Product> products = (List<Product>) request.getAttribute(Configer.ALL_PROCUCTS_ATTR);
				if (products == null) {
					response.sendRedirect(getServletContext().getContextPath() + Configer.ERROR_PAGE);	
					return;
				}
				
				for (Product p : products) {
			%>
			<li>
				<div>
					<a href="detail.do?id=<%=p.getId()%>"><img src="<%=String.format("%s/%s", 
						getServletContext().getContextPath(),
						p.getImage())%>"  /></a>
				</div>
				<p><a href=""><%=p.getDescription()%></a></p>
				<P><strong><%=p.getPrice()%></strong></P>
			</li>
			<% 
				}
			%>
			</ul>
		</div>
		<%
			List<Product> lastedProducts = (List<Product>) request.getAttribute(Configer.HISTORY_ATTR);
			if (lastedProducts != null) {
		%>
		<div>
			<ul>
				<%
					for (Product p : lastedProducts) {
				%>
				<li>
					<strong><%=p.getName()%></strong>
				</li>
				<% 
					} 
				%>
			</ul>
		</div>
		<% 
			}
		%>
	</body>
</html>