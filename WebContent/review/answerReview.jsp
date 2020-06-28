<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 답변</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<div id="head">
	<h2>리뷰 답변하기</h2>
</div>
<form id="frm" action="InsertReview.do" method="post" enctype="multipart/form-data">
<div id="wrap">
	<div id="reviewDesc">
	<input type="hidden" name="codes" value="${review.reviewCode},${review.company.comCode},${review.service.serviceCode}">
	<input type="hidden" name="type" value="2">
	리뷰한 사람 : 
	<input type="text" name="comName" readonly="readonly" value="${review.customer.custName}"><br>
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