<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 등록</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#frm').submit(function() {
		var sum =0;
		$('input[name=reviewScore]:checked').each(function() {
			sum+=$(this).val();
		});//each
		if(sum==0){
			alert('만족도를 입력해주세요');
			return false;
		}
	});//submit
});//ready
</script>
</head>
<body>
<div id="head">
	<h2>리뷰 등록하기</h2>
</div>
<form id="frm" action="InsertReview.do" method="post" enctype="multipart/form-data">
<div id="wrap">
	<div id="comDesc">
	<input type="hidden" name="comCode" value="${list[0].company.comCode}">
	<input type="hidden" name="type" value="1">
	업체명 : 
	<input type="text" name="comName" readonly="readonly" value="${list[0].company.comName}"><br>
	<select name="serviceName" required="required">
	<option label="리뷰할 서비스 선택하세요"></option>
	<c:forEach items="${list}" var="service">
		<option>${service.serviceCode},${service.serviceName}</option>
	</c:forEach>
	</select> 
	</div>
	<div id="reviewScore">
		만족도 : 
		<input name="reviewScore" type="radio" value="1">1점
		<input name="reviewScore" type="radio" value="2">2점
		<input name="reviewScore" type="radio" value="3">3점
		<input name="reviewScore" type="radio" value="4">4점
		<input name="reviewScore" type="radio" value="5">5점
	</div>
	<div id="reviewImg">
		사진 올리기: 1024*1024 이하의 이미지만 및 jpg, jpeg, png만 가능 <br>
		<input name="reviewImg" type="file" accept=".jpg, .jpeg, .png" multiple="multiple">
	</div>
	<div id="reviewDesc">
		<textarea name="reviewDesc" rows="5" cols="20" required="required"></textarea>
	</div>
	<input type="submit" value="등록하기">
</div>
</form>
</body>
</html>