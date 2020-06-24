<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Company List</title>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
    $(function() {
		$('button[name=category]').click(function() {
			var category = $(this).val();
			if(category=="0") window.location.href="ShowAllCompany.do";
			else window.location.href="ShowAllCompanyByCategory.do?category="+category;
		});//button[name=category] click
		
		$('button[name=search]').click(function() {
			var searchBy = $('#searchBy').val();
			var searchContent = $('#searchContent').val();
			
		});//button[name=search] click
	});//ready
    </script>

</head>
<body>
	<button value="0" name="category">전체</button>
	<button value="1" name="category">연예기획사</button>
	<button value="2" name="category">숙소</button>
	<button value="3" name="category">주류/렌탈</button>
	<button value="4" name="category">버스</button>
	<button value="5" name="category">음향조명</button>
	<button value="6" name="category">보험회사</button>
	<button value="7" name="category">푸드트럭</button>
	<button value="8" name="category">의류</button>
	<button value="9" name="category">현수막</button>
	<button value="10" name="category">협찬</button>
	<hr>
	<select name="searchBy" id="searchBy">
		<option>전체</option>
		<option>회사명</option>
		<option>태그</option>
		<option>업체분류</option>
	</select>
	<input type="text" name="searchContent" id="searchContent">
	<button value="search" name="search">검색</button>
	<br>
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