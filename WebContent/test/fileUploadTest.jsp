<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="fileUploadTest.do" method="post" enctype="multipart/form-data">
		<input type="text" name="text"><br>
		<input type="file" name="file"><br>
		<input type="submit">
	</form>
</body>
</html>