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
    	var category = 0;
    	var searchBy ="";
    	var searchContent ="";
    	var arr = {};
    	$('button[name=showAll]').click(function() {
    		window.location.href="ShowAllCompany.do";
    	});//button[name=showAll] click
		$('button[name=category]').click(function() {
			category = $(this).val();
			window.location.href="ShowAllCompanyByCategory.do?category="+category;
		});//button[name=category] click
		
		$('button[name=search]').click(function() {
			arr = $(this).val().split(',');
			category = arr[0];
			searchBy = $('#searchBy').val();
			searchContent = $('#searchContent').val();
			window.location.href="lookupCompany.do?searchBy="+searchBy
					+"&&searchContent="+searchContent
					+"&&category="+category;
			
		});//button[name=search] click
		
		$('#sortBy').change(function() {
			var sortBy = $(this).val();
			arr = $('button[name=search]').val().split(',');
			category = arr[0];
			searchBy = arr[1];
			searchContent = arr[2];
			window.location.href="SortCompany.do?searchBy="+searchBy
			+"&&searchContent="+searchContent
			+"&&category="+category
			+"&&sortBy="+sortBy;
		}); //sortby change
	});//ready
    </script>

</head>
<body>
	<button value="0" name="showAll">전체</button>
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
	</select>
	<input type="text" name="searchContent" id="searchContent">
	<button name="search" value="${category},${searchBy},${searchContent}">검색</button>
	<br>
<select id="sortBy">
	<option>정렬하기</option>
	<option>최신순</option>
	<option>조회순</option>
	<option>평점순</option>
	<option>리뷰순</option>
</select>
<br>
	<c:forEach items="${list}" var="review">
	<a href="ServiceAllShow.do?companycode=${review.company.comCode}">
	<span><img src = "${review.company.comImg}" width="100" height="100"></span>
	<span>${review.company.comName}</span>
	<span>${review.company.comDesc}</span>
	<span>조회수 : ${review.company.comCount}</span>
	<span>평점 : ${review.avgReviewScore}</span>
	<span>리뷰수 : ${review.countDesc}</span>
	</a><br>
	</c:forEach>
</body>
</html> 