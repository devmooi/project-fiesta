<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fiesta - 로그인</title>
	<link href="../resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">

	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
		$(function() {
			$('.modal').modal();
			
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
							//location.href="loginResult.jsp"; 
							location.href="registerSuccess.jsp";
						}	
					}
				}); // ajax
			}); // click	
		}); // ready	
	</script>
	<style>
		footer {
			position: absolute;
			left: 0;
			bottom: 0;
			width: 100%;
		}
		
		a {
			color: #009688;
		}
		
		section {
			width: 550px;
			margin: auto;
			text-align: center;
		}
		section h2 {
			font-size: 2rem;
			font-weight: bold;
			padding-top: 20px;
			margin-bottom: 30px;
		}
		
		section form {
			border: 1px solid #ddd;
			padding: 20px;
		}
		section form .input-field {
			margin-bottom: 30px;
		}
		
		input[type="submit"] {
			width: 100%;
		    background: none;
		    border: 1px solid #009688;
		    color: #009688;
		    border-radius: 5px;
		    padding: 12px;
		    font-weight: bold;
		    cursor: pointer;
		}
		input[type="submit"]:hover {
			background: #009688;
			color: white;
			transition-duration: 1s;
		}
		
		#check {
			position: relative;
			left: -165px;
			margin-bottom: 30px;
		}
		#check label {
			margin-right: 25px;
		}
		#loginFrm {
			margin-bottom: 30px;
		}
		#loginFrm > a {
			position: relative;
		    top: -27px;
		    left: -211px;
		}
	</style>
</head>
<body>
	<c:import url="http://localhost:8888/Fiesta/header.jsp" charEncoding="UTF-8"></c:import>
	
	<section>
		<h2>Fiesta에 오신 것을 환영합니다</h2>
		
		<form action="login.do" id="loginFrm">
			<div id="check">
				<label>
					<input name="pick" id="pick" value="customer" type="radio" checked>
					<span>고객</span>
				</label>
				<label>
					<input name="pick" id="pick" value="company" type="radio">
					<span>업체</span>
				</label>
			</div>
		</form>
		
		<!-- <div id="loginFrm">
			
			
			<div class="input-field">
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" required>
			</div>
			
			<div class="input-field">
				<label for="pass">비밀번호</label>
				<input type="password" name="pass" id="pass" required>
			</div>
			
			이메일로 임시 비밀번호 전송
			<a href="passFind.do">비밀번호 찾기</a>
			
			<input type="submit" value="로그인" id="loginBtn">
			<a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>
		</div>
		<a href="register.jsp">계정이 없으신가요?</a>
	</section>
	
	<!-- Modal Trigger
	  <a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>
	
	  Modal Structure
	  <div id="modal1" class="modal">
	    <div class="modal-content">
	      <h4>Modal Header</h4>
	      <p>A bunch of text</p>
	    </div>
	    <div class="modal-footer">
	      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Agree</a>
	    </div>
	  </div> --> 

	<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>


