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
			$('#loginFrm').submit(function(event) {
				event.preventDefault();
				$.ajax({
					type:'post',
					url:'login.do',
					data:$(this).serialize(),
					
					success:function(result) {
						if(result=='false') {
							$("#loginFailModal").show();
						} else {
							location.href="http://localhost:8888/Fiesta";
						}
					}
				});//ajax
			});//loginFrm
			
			$('#passFind').click(function() {
				$('#passFindModal').show();
			});
			
			$('#passFindFrm').submit(function(event) {
				event.preventDefault();
				$.ajax({
					type:'post',
					url:'passFind.do',
					data:'pick=' + $(':radio[name="pick"]:checked').val() +'&'+ $(this).serialize(),
					
					success:function(result) {
						$('.searchModal').hide();
					}
				});//ajax
			});//passFindFrm
		}); // ready	
		
		function closeModal() {
			$('.searchModal').hide();
		};
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
		    top: -17px;
		    left: -211px;
		}
		
		/* The Modal (background) */
		.searchModal {
			display: none;
			position: fixed;
			z-index: 999;
			left:0;
			top: 0;
			width: 100%;
			height: 100%;
			overflow: auto;
			background-color: rgba(0, 0, 0, 0.4);
		}
		
		/* Modal Content Box */
		.search-modal-content {
			background-color: #fefefe;
			margin: 12% auto;
			padding: 20px;
			border: 1px solid #888;
			width: 35%;
			text-align: center;
			padding-bottom: 50px;
		}
		.search-modal-content h1 {
			font-size: 1.2rem;
			font-weight: bold;
		}
		.search-modal-content button {
			background: #009688;
		    border: 1px solid #009688;
		    color: white;
		    width: 100%;
		    padding: 12px;
		    cursor: pointer;
		}
		.search-modal-content span {
			color: red;
		}
	</style>
</head>
<body>
	<jsp:include page = "../header.jsp" />
	
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
			
			<div class="input-field">
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" required>
			</div>
			
			<div class="input-field">
				<label for="pass">비밀번호</label>
				<input type="password" name="pass" id="pass" required>
			</div>
			
			<a href="#" id="passFind">비밀번호 찾기</a>
			
			<input type="submit" value="로그인" id="loginBtn">	
		</form>
		<a href="register.jsp">계정이 없으신가요?</a>
	</section>
	
	<div id="loginFailModal" class="searchModal">
		<div class="search-modal-content">
			<h1>로그인에 실패하였습니다</h1>
			<p>이메일이나 비밀번호가 올바르게 입력되었는지 확인 후 다시 시도하십시오.</p>
			<button onClick="closeModal();">확인</button>
		</div>
	</div>
	
	<div id="passFindModal" class="searchModal">
		<div class="search-modal-content">
			<h1>비밀번호 재설정</h1>
			<p>가입한 이메일 주소가 무엇인가요?</p>
			<form action="passFind.do" id="passFindFrm">
				<input type="email" name="email" id="email">
				<p>다음 버튼을 누르시면, 해당 이메일로 비밀번호를<br>재설정하기 위한 <span>임시 비밀번호</span>를 발송합니다.</p>
				<input type="submit" value="비밀번호 재설정하기">
			</form>
		</div>
	</div>

	<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>


