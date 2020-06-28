<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input[type="radio"] {display:none;}
	input[type="radio"]:checked + label {background:#aaa;color:#000;}
	
	.contents {display:none;}
	input[id="order"]:checked ~ .order {display:block;}
	input[id="request"]:checked ~ .request {display:block;}
	
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(function() {
	$('#comName').keyup(function() {
		var name = $('#comName').val();
		if (name.length == 0) {
			$('#comNameCheckView').html("업체명을 입력해주세요.");
			return;
		} 
	}); // name keyup
	$('#comEmail').keyup(function() {
		var email = $('#comEmail').val();
		if (email.length == 0) {
			$('#comEmailCheckView').html("이메일을 입력해주세요.");
			return;
		} 
		$.ajax({
			type:'post',
			url:'comEmailExist.do',
			data:$('#companyUpdateFrm').serialize(),
			
			success:function(result) {
				if(result=='true')
					$('#comEmailCheckView').html("이미 사용중인  이메일입니다.");
				else
					$('#comEmailCheckView').html("사용 가능한 이메일입니다.");
			}
		}); // email ajax
	}); // email keyup
	$('#comPass').keyup(function() {
		var pass = $('#comPass').val();
		if (pass.length < 8) {
			$('#comPassCheckView').html("비밀번호는 8자 이상 입력해주세요.");
			 return;
		}
	}); // pass keyup
	
	$('#companyUpdateBtn').click(function() {
		$.ajax({
			type:'post',
			url:'updateCompany.do',
			data:$('#companyUpdateFrm').serialize(),
			
			success:function(result) {
				$('#updateCompanyCheckView').html("수정되었습니다.");
			}
		}); // ajax
	}); // click
}); // ready
</script>
</head>
<body>
	
	<h2>업체 정보 수정</h2>
	<form action="updateCompany.do" id="companyUpdateFrm">
	업체명  <input type="text" name="comName" id="comName" value="${company.comName}" placeholder="${company.comName}"><br>
		<span id="comNameCheckView"></span><br>	
	이메일  <input type="text" name="comEmail" id="comEmail" value="${company.comEmail}" placeholder="${company.comEmail}"><br>
		<span id="comEmailCheckView"></span><br>
	비밀먼호  <input type="password" name="comPass" id="comPass" value="${company.comPass}" placeholder="${company.comPass}"><br>
		<span id="comPassCheckView"></span><br>
	전화번호  <input type="text" name="comTel" id="comTel" value="${company.comTel}" placeholder="${company.comTel}"><br>
		<span id="comTelCheckView"></span><br>
	주소  <input type="text" name="comAddr" id="comAddr" value="${company.comAddr}" placeholder="${company.comAddr}"><br>
		<span id="comAddrCheckView"></span><br>
	업체로고등록  <input type="text" name="comImg" id="comImg" value="${company.comImg}" placeholder="${company.comImg}"><br>
		<span id="comImgCheckView"></span><br>
	업체상세정보  <input type="text" name="comDesc" id="comDesc" value="${company.comDesc}" placeholder="${company.comDesc}"><br>
		<span id="comDescCheckView"></span><br>
	업체분류 <select name="categoryCode" id="categoryCode">
			<option selected="selected">${company.comCategoryCode}</option>
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
		</select><br>
	<input type="button" id="companyUpdateBtn" value="업체정보수정">
	<span id="updateCompanyCheckView"></span>	
	</form>
	<a href="companyDelete.jsp">계정삭제</a>


	<h2>거래 내역</h2>
	<!-- attribute 저장한 내용을 뿌려주는... 모달로 뿌리는 것도 추가.. 어떤 컬럼을 보여줄건지 -->
	<div class="tab_content">
		<input type="radio" name="type" id="order" checked>
		<label for="order"> 받은 주문 내역 </label>
		<input type="radio" name="type" id="request">
		<label for="request"> 받은 의뢰 내역 </label>		
		<div class="contents order"> 받은 주문 내역 내용</div>
		<div class="contents request"> 받은 의뢰 내역 내용</div>
	</div>
	
	<!-- 리뷰하기.. -->
	

</body>
</html>