<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(function() {
	$('#loginBtn').click(function() {
		var email = $('#email').val();
		var pass = $('#pass').val();
		
		$.ajax({
			type:'post',
			url:'login.do',
			data:$('#loginFrm').serialize(),
			
			success:function(result) {
				if(result=='false') {
					alert("로그인에 실패하였습니다. 이메일이나 비밀번호가 올바르게 입력되었는지 확인 후 다시 시도하십시오.");
				} else {
					location.href="loginResult.jsp"; // 이거였다 와..! 김미경 짱이다!!
				}	
			}
		}); // ajax
	}); // click	
}); // ready	
</script>
<style>

</style>
</head>
<body>
	<h1>Fiesta에 오신 것을 환영합니다.</h1>
	<form action="login.do" id="loginFrm">
		<input type="radio" name="pick" id="pick" value="customer" checked="checked"> 고객
		<input type="radio" name="pick" id="pick"value="company"> 업체	
		<br>
		이메일 <br><input type="text" name="email" id="email" required="required"><br>
			<span id="emailCheckView"></span>
		비밀번호 <br><input type="password" name="pass" id="pass" required="required"><br>
			<span id="passCheckView"></span>
			<br><br>
		<input type="button" value="로그인" id="loginBtn">	
		<p>비밀번호 찾기</p>
		<a href="register.jsp">계정이 없으신가요?</a>
	</form>
</body>
</html>