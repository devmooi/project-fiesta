<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fiesta - 업체 목록</title>
    <link href="../Fiesta/resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">

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

		switch(window.location.href) {
			case 'http://localhost:8888/Fiesta/ShowAllCompany.do':
				$('button[value=0]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=1':
				$('button[value=1]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=2':
				$('button[value=2]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=3':
				$('button[value=3]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=4':
				$('button[value=4]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=5':
				$('button[value=5]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=6':
				$('button[value=6]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=7':
				$('button[value=7]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=8':
				$('button[value=8]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=9':
				$('button[value=9]').addClass('selectCategory');
				break;
			case 'http://localhost:8888/Fiesta/ShowAllCompanyByCategory.do?category=10':
				$('button[value=10]').addClass('selectCategory');
				break;
		}

		var locationSearch = window.location.search.split("&&");
		switch(locationSearch[locationSearch.length-1]) {
		 	case 'sortBy=%EC%B5%9C%EC%8B%A0%EC%88%9C':
				$('[value=최신순]').prop("selected", true);
				break;
			case 'sortBy=%EC%A1%B0%ED%9A%8C%EC%88%9C':
				$('[value=조회순]').prop("selected", true);
				break;
			case 'sortBy=%ED%8F%89%EC%A0%90%EC%88%9C':
				$('[value=평점순]').prop("selected", true);
				break;
			case 'sortBy=%EB%A6%AC%EB%B7%B0%EC%88%9C':
				$('[value=리뷰순]').prop("selected", true);
				break;
		}
		$(document).ready(function(){
			$('select').formSelect();
		});
		
	});//ready
	</script>
	
	<style>
		section {
			width: 1080px;
			margin: auto;
		}
		section h2 {
			font-size: 2rem;
			margin: 0;
			padding: 0;
			font-weight: bold;
			margin-top: 50px;
			margin-bottom: 30px;
		}

		#categoryList button {
			background: transparent;
			border: 1px solid #ddd;
			margin: 3px;
			font-weight: bold;
			cursor: pointer;
			padding: 8px 15px;
			color: #ddd;
			border-radius: 5px;
		}
		#categoryList button:hover {
			color: #009688;
			border: 1px solid #009688;
		}
		#categoryList button.selectCategory {
			color: black;
			border: 1px solid black;
		}

		#searchList {
			margin-top: 15px;
			float: left;
		}
		#searchList .select-wrapper {
			width: 18%;
			display: inline-block;
		}
		#searchList #searchContent {
			width: 60%;
		}
		#searchList [name="search"] {
			background: black;
			padding: 10px 15px;
			border: none;
			border-radius: 5px;
			color: white;
			font-weight: bold;
			cursor: pointer;
		}
		#searchList [name="search"]:hover {
			background: #009888;
		}

		#rightSelect{
			float: right;
			width: 9%;
			margin-top: 20px;
		}

		#mainList {
			clear: both;
			margin-top: 100px;
		}
		#mainList a{
			display: block;
			border-bottom: 1px solid #ddd;
			margin-bottom: 30px;
			padding-bottom: 10px;
			overflow: hidden;
			color: black;
		}
		#mainList a:hover {
			border-bottom: 1px solid #009688;
		}
		#mainList a img {
			width: 150px;
			height: 150px;
			border-radius: 10px;
			float: left;
		}
		#mainList a div {
			float: left;
			margin-left: 20px;
		}
		#mainList a div h3 {
			font-size: 1.2rem;
			font-weight: bold;
		}
	</style>
</head>
<body>
	<!-- header import -->
    <c:import url="http://localhost:8888/Fiesta/header.jsp" charEncoding="UTF-8"></c:import>
    
	<!-- 항상 section에서 시작 -->
	<section>
		<h2>업체 찾기</h2>
		<div id="categoryList">
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
		</div>
		<div id="searchList">
			<select name="searchBy" id="searchBy">
				<option>전체</option>
				<option>회사명</option>
				<option>태그</option>
			</select>
			<input type="text" name="searchContent" id="searchContent">
			<button name="search" value="${category},${searchBy},${searchContent}">검색</button>
		</div>
		<div id="rightSelect">
			<select id="sortBy">
				<option disabled selected>정렬하기</option>
				<option value="최신순">최신순</option>
				<option value="조회순">조회순</option>
				<option value="평점순">평점순</option>
				<option value="리뷰순">리뷰순</option>
			</select>
		</div>

		<div id="mainList">
			<c:forEach items="${list}" var="review">
				<a href="ServiceAllShow.do?companycode=${review.company.comCode}">
					<img src="${review.company.comImg}">
					<div>
						<h3>${review.company.comName}</h3>
						<p>${review.company.comDesc}</p>
						<p>
							<span>조회수 : ${review.company.comCount}</span>
							<span>평점 : ${review.avgReviewScore}</span>
							<span>리뷰수 : ${review.countDesc}</span>
						</p>
					</div>
				</a>
			</c:forEach>
		</div>
	</section>

	<!-- 여기 디자인은 가데이터 넣어진 후.. -->
	<!-- <c:forEach items="${list}" var="review">
		<a href="ServiceAllShow.do?companycode=${review.company.comCode}">
		<span><img src = "${review.company.comImg}" width="100" height="100"></span>
		<span>${review.company.comName}</span>
		<span>${review.company.comDesc}</span>
		<span>조회수 : ${review.company.comCount}</span>
		<span>평점 : ${review.avgReviewScore}</span>
		<span>리뷰수 : ${review.countDesc}</span>
		</a><br>
	</c:forEach> -->
</body>
</html> 