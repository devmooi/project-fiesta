<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				data:$('#registerFrm').serialize(),
				
				success:function(result) {
					if(result=='true')
						$('#emailCheckView').html("이미 사용중인  이메일입니다.");
					else
						$('#emailCheckView').html("사용 가능한 이메일입니다.");
				}
			}); // email ajax
		}); // click
	}); // ready
</script>
</head>
<body>
	<h1>Fiesta에 오신 것을 환영합니다.</h1>
	<form action="login.do" id="loginFrm">
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