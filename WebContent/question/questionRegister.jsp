<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>문의하기</h2>
<form action="questionRegister.do?comCode=1" name="registerForm" > <!-- 회사코드 임시로 1로 잡아놓음.  -->
문의제목 : <input type="text" name="qTitle" required="required"><br><br>
문의내용 : <input type="text" name="qDesc" required="required"><br><br>
<input type="submit" value="문의 등록">
</form>
</body>
</html>