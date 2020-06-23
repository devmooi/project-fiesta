<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<head>
    <meta charset="UTF-8">
    <title>Company List</title>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</head>
</head>
<body>
<form action="#">
	<button value="1">연예기획사</button>
	<button value="2">숙소</button>
	<button value="3">주류/렌탈</button>
	<button value="4">버스</button>
	<button value="5">음향조명</button>
	<button value="6">보험회사</button>
	<button value="7">푸드트럭</button>
	<button value="8">의류</button>
	<button value="9">현수막</button>
	<button value="10">협찬</button>
	<hr>
	<select name="searchby" id="searchby">
		<option>분류</option>
		<option>회사명</option>
		<option>주소</option>
		<option>전화번호</option>
	</select>
	<input type="text" name="searchContent" id="searchContent">
	<input type="submit" value="검색">
</form>
<select>
	<option>최신순</option>
	<option>평점순</option>
	<option>리뷰순</option>
</select>
<table>
	<thead>
		<tr>
			<th>이미지</th><th>회사명</th><th>설명</th><th>평점</th><th>리뷰</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="review">
		<tr>
			<td><img src = "${review.company.comImg}" width="100" height="100"></td>
			<td>${review.company.comName}</td>
			<td>${review.company.comDesc}</td>
			<td>${review.reviewScore}</td>
			<td>${review.reviewDesc}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html> 