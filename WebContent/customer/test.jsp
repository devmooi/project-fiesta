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
    $('.datepicker').datepicker();
}); // ready
</script>
</head>
<body>
	고객 의뢰 테스트 페이지
	<form action="customerRequest.do"> 		<!-- 고객 의뢰 관련 요청 모두 다.. 테스트 하는 곳 -->
	
	1. 원하는 날짜를 알려주세요.
	<input type="datepicker">
	
	
	2. 어느 지역인가요?
	  시도 시군구 읍면동
	
	3. 대략적인 예산을 공유해주세요.
	<input type="radio" name="three" id="three"> 1000만원 이상
	<input type="radio" name="three" id="three" value="others">기타
	<input type="text" name="three" id="three" placeholder="직접 입력">
	
	4. 관련 문의 및 희망 사항을 알려주세요.
	<input type="text" name="four" id="four" placeholder="ex.이전에 진행했던 행사가 담긴 사진이나 동영상을 보고 싶어요.">
	
	</form>
</body>
</html>