<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>上传</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/router?action=doUpload" method="post" enctype="multipart/form-data">
			名字:<input type="text" name="user"/><br/>
			文件1：<input type="file" name="f1"/><br/>
			文件2：<input type="file" name="f2"/><br/>
			<input type="submit" value="确定"/>
		</form>
	</body>
</html>