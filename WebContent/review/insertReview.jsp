<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 등록</title>
</head>
<body>
<div id="head">
	<h2>리뷰 등록하기</h2>
</div>
<form action="InsertReview.do">
<div id="wrap">
	<div id="reviewScore">
		평점 : 
		<input name="reviewScore" type="radio" value="1">1점
		<input name="reviewScore" type="radio" value="2">2점
		<input name="reviewScore" type="radio" value="3">3점
		<input name="reviewScore" type="radio" value="4">4점
		<input name="reviewScore" type="radio" value="5">5점
	</div>
	<div id="reviewImg">
		사진 올리기: jpg, jpeg, png만 가능
		<input name="reviewImg" type="file" accept=".jpg, .jpeg, .png" multiple="multiple">
	</div>
	<div id="reviewDesc">
		<textarea name="reviewDesc" rows="5" cols="20"></textarea>
	</div>
	<input type="submit" value="등록하기">
</div>
</form>
</body>
</html>