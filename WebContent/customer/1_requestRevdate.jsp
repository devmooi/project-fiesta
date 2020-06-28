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
	<!-- 달력 (기간 설정 되는지) + 직접 입력.. -->
	<form action=customerRequest.do>
	<input type="datepicker">
	</form>
</body>
</html>