<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fiesta - 고객 회원가입</title>
	<link href="../resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">

	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
		$(function() {
			$('#name').keyup(function() {
				var name = $(this).val();
				if(name.length > 0) {
					$('#name').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
					$('#nameCheckView').html('ok!').css('color', '#4CAF50').css('text-align', 'left');
				} else {
					$('#name').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#nameCheckView').html('이름을 입력해주세요.').css('color', '#F44336').css('text-align', 'left');
				}
			}); // name keyup
			$('#email').keyup(function() {
				var re = /^[a-z0-9._A-Z]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
				if(!re.test($(this).val())) {
					$('#email').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#emailCheckView').html('이메일 형식에 맞게 입력해주세요.').css('color', '#F44336').css('text-align', 'left');
				} else {
					$.ajax({
						type:'post',
						url:'custEmailExist.do',
						data:'custEmail='+$(this).val(),

						success:function(result) {
							if(result=='true') {
								$('#email').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
								$('#emailCheckView').html('이미 사용중인 이메일입니다.').css('color', '#F44336').css('text-align', 'left');
							}else {
								$('#email').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
								$('#emailCheckView').html('사용 가능한 이메일입니다.').css('color', '#4CAF50').css('text-align', 'left');
							}
						}
					}); // email ajax
				}
			}); // email keyup
			$('#pass').keyup(function() {
				var re = /^[A-Za-z0-9]{8,15}$/;
				if(!re.test($(this).val())) {
					$('#pass').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#passCheckView').html('비밀번호는 8자 이상, 15자 이내로 대소문자, 숫자로 입력해주세요.').css('color', '#F44336').css('text-align', 'left');
				} else {
					$('#pass').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
					$('#passCheckView').html('사용 가능한 비밀번호입니다.').css('color', '#4CAF50').css('text-align', 'left');
				}
			}); // pass keyup
			
			$('#tel').keyup(function() {
				var re = /^\d{2,3}-\d{3,4}-\d{4}$/;
				if(!re.test($(this).val())) {
					$('#tel').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#telCheckView').html('잘못된 전화번호입니다.').css('color', '#F44336').css('text-align', 'left');
				} else {
					$('#tel').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
					$('#telCheckView').html('ok!').css('color', '#4CAF50').css('text-align', 'left');
				}
			});
			
			$('#group').keyup(function() {
				var re = /^[가-힣\s]+$/;
				var group = $(this).val();
				if(group.length > 0) {
					if(!re.test($(this).val())) {
						$('#group').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
						$('#groupCheckView').html('단체명을 제대로 입력해주세요.').css('color', '#F44336').css('text-align', 'left');
					} else {
						$('#group').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
						$('#groupCheckView').html('ok!').css('color', '#4CAF50').css('text-align', 'left');
					}
				} else {
					$('#group').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#groupCheckView').html('이름을 입력해주세요.').css('color', '#F44336').css('text-align', 'left');
				}
			});
		}); // ready
	</script>
	<style>
		section {
			width: 550px;
			margin: auto;
			text-align: center;
		}
		section h2 {
			font-size: 2rem;
			font-weight: bold;
			padding-top: 20px;
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
	</style>
</head>
<body>
	<jsp:include page = "../header.jsp" />

	<section>
		<h2>Fiesta에 오신 것을 환영합니다</h2>
		
		<form action="customerRegister.do" id="registerFrm" method="post">
			<div class="input-field">
				<label for="name">이름</label>
				<input type="text" name="name" id="name" required>
				<span class="helper-text" id="nameCheckView"></span>
			</div>
			
			<div class="input-field">
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" required>
				<span class="helper-text" id="emailCheckView"></span>
			</div>
			
			<div class="input-field">
				<label for="pass">비밀번호</label>
				<input type="password" name="pass" id="pass" required>
				<span class="helper-text" id="passCheckView"></span>
			</div>
			
			<div class="input-field">
				<label for="tel">휴대전화번호</label>
				<input type="text" name="tel" id="tel" required>
				<span class="helper-text" id="telCheckView"></span>
			</div>
			
			<div class="input-field">
				<label for="group">단체명</label>
				<input type="text" name="group" id="group" required>
				<span class="helper-text" id="groupCheckView"></span>
			</div>

			<input type="submit" value="회원가입" id="registerBtn">
		</form>
	</section>
	
	<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>
