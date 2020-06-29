<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fiesta - 업체 회원가입</title>
	<link href="../resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">

	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
		$(function() {
			$('#email').keyup(function() {
				$.ajax({
					type:'post',
					url:'comEmailExist.do',
					data:$('#registerFrm').serialize(),
					
					success:function(result) {
						if(result=='true')
							$('#emailCheckView').attr('data-error', '이미 사용중인  이메일입니다.');
						else
							$('#emailCheckView').attr('data-success', '사용 가능한 이메일입니다.');
					}
				}); // email ajax
			}); // email keyup
		}); // ready
		
		$(document).ready(function(){
	    	$('select').formSelect();
	  	});
	</script>
	<style>
		section {
			width: 500px;
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
	</style>
</head>
<body>
	<c:import url="http://localhost:8888/Fiesta/header.jsp" charEncoding="UTF-8"></c:import>
	
	<section>
		<h2>Fiesta에 오신 것을 환영합니다</h2>
		
		<form action="companyRegister.do" id="registerFrm">
			<div class="input-field">
				<label for="name">업체명</label>
				<input type="text" name="name" id="name" class="validate" required>
				<span class="helper-text" data-error="업체명을 입력해주세요." data-success=""></span>
			</div>
			
			<div class="input-field">
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" class="validate" pattern="^[a-z0-9._A-Z]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
				<span class="helper-text" data-error="이메일 형식에 맞게 입력해주세요." data-success="success" id="emailCheckView"></span>
			</div>
			
			<div class="input-field">
				<label for="pass">비밀번호</label>
				<input type="password" name="pass" id="pass" class="validate" pattern="^[A-Za-z0-9]{8,15}$" required>
				<span class="helper-text" data-error="비밀번호는 8자 이상 15자 이하 소문자, 대문자, 숫자로 입력해주세요." data-success="사용 가능한 비밀번호입니다."></span>
			</div>
			
			<!-- 버튼 추가해서 선택사항 숨겨놓기 -->
			
			<div class="input-field">
				<label for="tel">전화번호</label>
				<input type="tel" name="tel" id="tel" class="validate" pattern="^\d{2,3}-\d{3,4}-\d{4}$">
				<span class="helper-text" data-error="잘못된 전화번호입니다." data-success=""></span>
			</div>
			
			<!-- 우편번호 API 사용하는 걸로 변경 -->
			<div class="input-field">
				<label for="addr">주소</label>
				<input type="text" name="addr" id="addr" class="validate">
				<span class="helper-text" data-error="error" data-success="success"></span>
			</div>
			
			<!-- 파일 업로드로 변경 -->
			<div class="input-field">
				<label for="img">업체 로고 등록</label>
				<input type="text" name="img" id="img" class="validate">
				<span class="helper-text" data-error="error" data-success="success"></span>
			</div>
			
			<div class="input-field">
				<label for="desc">업체 상세 정보</label>
				<textarea id="desc" name="desc" class="materialize-textarea"></textarea>
			</div>
			
			<div class="input-field">
				
			    <select name="categoryCode" id="categoryCode" required>
			    	<option disabled selected>선택해주세요</option>
			    	<option value=1>연예기획사</option>
					<option value=2>숙소</option>
					<option value=3>주류/렌탈</option>
					<option value=4>버스</option>
					<option value=5>음향조명</option>
					<option value=6>보험회사</option>
					<option value=7>푸드트럭</option>
					<option value=8>의류</option>
					<option value=9>현수막</option>
					<option value=10>협찬</option>
			    </select>
			    <label for="categoryCode">업체분류</label>
			</div>
			<input type="submit" value="회원가입" id="registerBtn">
		</form>
	</section>
	
	<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>


