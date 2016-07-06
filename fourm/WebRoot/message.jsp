<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html>
  <head>
   <title>信息</title>
   <style>
   	#tick {color: red;}
   </style>
   <script>
  		window.onload = function() {
  			var tick = document.getElementById("tick"),
  				count = 3;
  			if (tick != null) {
  				setInterval(function() {
  					tick.innerText = --count;
  				}, 1000);
  			}
  		};
   </script>
  </head>
  <body>
	<div>
		<div>
			<p><strong>${message}</strong>&nbsp;<a href="${pageContext.servletContext.contextPath}">立即返回主页</a>
		</div>
	</div>  	
  </body>
</html>
