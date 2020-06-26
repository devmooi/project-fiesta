<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
/* $(function() {
	
	수정을 누르면, 이걸 줘야지.
	<input type="text" id="name" name="name">
	
}); // ready */
</script>
</head>
<body>
	<h2>개인 정보 수정</h2> 	<!-- 업체 / 고객 구분 :  c:if  사용  -->
	<form action="updateCustomer.do" id="updateFrm">
	이름 ${customer.custName} <input type="button" id="nameBtn" value="수정"><br> 
	이메일  ${customer.custEmail} <input type="text" id="email" name="email"><input type="button" id="emailBtn" value="수정"><br>
	비밀번호  ${customer.custPass} <input type="password" id="pass" name="pass"><input type="button" id="passBtn" value="수정"><br>
	휴대전화번호  ${customer.custTel}<input type="text" id="tel" name="tel"><input type="button" id="telBtn" value="수정"><br>
	단체명 ${customer.custGroup}<input type="text" id="group" name="group"><input type="button" id="groupBtn" value="수정"><br>
	</form>

	




	
	<h2>주문 내역</h2>
	<!-- attribute 저장한 내용을 뿌려주는... 모달로 뿌리는 것도 추가.. 어떤 컬럼을 보여줄건지 -->
	
	<!-- 리뷰하기.. -->
	
<!--<form action="updateCompany.do">
	</form> -->
</body>
</html>