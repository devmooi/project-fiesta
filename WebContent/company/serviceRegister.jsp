<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>서비스등록</h3>
<!-- 
	int serviceCode;
	String serviceName;
	String serviceDesc;
	String serviceImg;
	String serviceTag;
	int serviceCount;
	int comCode;
 -->
<form action="serviceRegister.do?" name="registerForm" method="post">
서비스 이름 : <input type="text" name="serviceName" required="required"><br><br>
서비스 설명 : <input type="text" name="serviceDesc" required="required"><br><br>
서비스 사진 : <input type="text" name="serviceImg" required="required"><br><br>
서비스 태그 : <input type="text" name="serviceTag" required="required"><br><br>
<!--회사 코드 : <input type="text" name="comCode" required="required"><br><br> 나중에 세션에서 자동으로 회사코드 받아오기... -->
<input type="submit" value="서비스 등록">
	</form>
</body>
</html>