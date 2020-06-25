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
		$('#name').keyup(function() {
			var name = $('#name').val();
			if (name.length == 0) {
				$('#nameCheckView').html("이름을 입력해주세요.");
				return;
			} 
		}); // name keyup
		$('#email').keyup(function() {
			var email = $('#email').val();
			if (email.length == 0) {
				$('#emailCheckView').html("이메일을 입력해주세요.");
				return;
			} 
			$.ajax({
				type:'post',
				url:'emailExist.do',
				data:$('#registerFrm').serialize(),
				
				success:function(result) {
					if(result=='true')
						$('#emailCheckView').html("이미 사용중인  이메일입니다.");
					else
						$('#emailCheckView').html("사용 가능한 이메일입니다.");
				}
			}); // email ajax
		}); // email keyup
		$('#pass').keyup(function() {
			var pass = $('#pass').val();
			if (pass.length < 8) {
				$('#passCheckView').html("비밀번호는 8자 이상 입력해주세요.");
				 return;
			}
		}); // pass keyup
	}); // ready
</script>
</head>
<body>
	<h1>Fiesta에 오신 것을 환영합니다.</h1>
	<form action="customerRegister.do" id="registerFrm">
		이름 <br><input type="text" name="name" id="name" required="required"><br>
			<span id="nameCheckView"></span>
		이메일 <br><input type="text" name="email" id="email" required="required"><br>
			<span id="emailCheckView"></span>
		비밀번호 <br><input type="password" name="pass" id="pass" required="required"><br>
			<span id="passCheckView"></span>
		휴대전화번호<br><input type="text" name="tel" id="tel"><br>
			<span id="telCheckView"></span>
		단체명<br><input type="text" name="group" id="group"><br>
			<span id="groupCheckView"></span>	
			<br><br>
		<input type="submit" value="회원가입" id="registerBtn">
	</form>
</body>
</html>