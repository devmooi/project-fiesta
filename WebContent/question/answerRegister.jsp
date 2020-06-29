<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>답변하기</h1>
<form action="answerRegister.do" name="registerForm" >
<input type="hidden" name="qCode" value="${qDetail.qCode}">
답변내용 : <input type="text" name="aDesc" required="required"><br><br>
<input type="submit" value="답변 등록">
</form>
</body>
</html>