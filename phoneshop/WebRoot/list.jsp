<%@ page import="java.util.List, java.lang.String, db.Product, cfg.Configer" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<html>
	<head>
		<meta charset="utf-8" />
		<title>手机列表</title>
		<style>
			.clear {zoom: 1;}
			.clear:after {display: block; content: ""; clear: both;}
			body, ul, p, h3 {margin: 0; padding: 0;}
			li {list-style: none;}
			img {border: none; vertical-align: top;}
			a {text-decoration: none;}
			#box {margin: 100px auto; width: 900px;}
			h3 {padding: 5px; font-size: 18px; background: #eb5345; color: #fff;}
			a {opacity: 0.8; filter: alpha(opacity=80); font-size: 12px; color: #858585; line-height: 19px;}
			a:hover {opacity: 1.0; filter: alpha(opacity=100); color: #c81623; }
			p {font-style: normal; color: #c61822;}
			#all {float: right; width: 685px;}
			#all ul {padding: 20px; border: solid 1px #ddd;}
			#all li {position: relative; float: left; padding: 0 10px; width: 140px; border-left: solid 1px #dddddd;}
			#all ul li:nth-of-type(4n + 1) {border-left: none;}
			#all img {width: 120px; height: 120px;}
			#all p {margin-top: 11px;}
			#all strong {font-size: 14px; line-height: 22px;}
			#all p a:hover {text-decoration: underline;}
			#history {float: left; width: 200px;}
			#history ul {padding: 5px; border: solid 1px #ddd;}
			#history li {padding: 5px 0;}
			#history li div:nth-of-type(1) {float: left;}
			#history li div:nth-last-of-type {float: right;}
			#history img {width: 80px; height: 80px;}
			#history strong {font-size: 12px; line-height: 19px;}
		</style>
	</head>
	<body>
		<div id="box">
			<div id="all">
				<h3>热门精品手机</h3>
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
					<li>
						<a href="">上一页</a><a href="">1</a><a href="">2</a><a href="">下一页</a>		
					</li>
				</ul>
			</div>
			<%
				List<Product> lastedProducts = (List<Product>) request.getAttribute(Configer.HISTORY_ATTR);
				if (lastedProducts != null) {
			%>
			<div id="history">
				<h3>浏览历史</h3>
				<ul>
					<%
						for (Product p : lastedProducts) {
							String shortName = p.getName();
							if (shortName.length() > Configer.SHORT_NAME_MAX_LENGTH) 
								shortName = shortName.substring(0, Configer.SHORT_NAME_CUT_LENGTH) + Configer.SHORT_NAME_CUT_SUFFIX;
					%>
					<li class="clear">
						<div>
							<a href="detail.do?id=<%=p.getId()%>"><img src="<%=String.format("%s/%s", 
								getServletContext().getContextPath(),
									p.getImage())%>"  /></a>
						</div>
						<div>
							<p><a href="detail.do?id=<%=p.getId()%>"><%=shortName%></a><p>
							<P><strong><%=p.getPrice()%></strong></P>
							<p><a href="add.do?id=<%=p.getId()%>">加入购物车</a></p>
							<p><a href="clear.do?id=<%=p.getId()%>">清除</a></p>
						</div>
					</li>
					<% 
						} 
					%>
				</ul>
				
			</div>
			<% 
				}
			%>
		</div>
	</body>
</html>