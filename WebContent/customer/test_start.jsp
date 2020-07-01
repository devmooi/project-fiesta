<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<form action="customerRequest.do">
<input type="hidden" name="command" value="begin">
	<select name="requestFiesta" id="categoryCode" required>
	  <option disabled selected>선택해주세요 (필수)</option>
	  <option value="1">연예기획사</option>
	  <option value="2">숙소</option>
	  <option value="3">주류/렌탈</option>
	  <option value="4">버스</option>
	  <option value="5">음향조명</option>
	  <option value="6">보험회사</option>
	  <option value="7">푸드트럭</option>
	  <option value="8">의류</option>
	  <option value="9">현수막</option>
	  <option value="10">협찬</option>
    </select>
<input type="submit" value="의뢰하기">
</form>

<!-- <input type="button" id="requestBtn" value="의뢰하기">
<span id="requestView"></span>
 -->
</body>
</html>