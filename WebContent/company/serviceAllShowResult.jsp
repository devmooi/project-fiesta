<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fiesta - 업체 상세페이지</title>
    <link href="../Fiesta/resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
    
	<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
	    $(function() {
			$('button[name=reviewInsert]').click(function() {
				window.location.href="ShowService.do?companycode="+$('input[type=hidden]').val();
			});//button[name=reviewInsert] click
		});//ready

	    $(function() {
			$('button[name=reviewInsert]').click(function() {
				window.location.href="./review/insertReview.jsp";
			});//button[name=reviewInsert] click
			
			$('.collapsible').collapsible();
			
			/* $('.collapsible').collapsible(function() {
				var qCode = $('#qCode').val();
				if (email.length == 0) {
					$('#emailCheckView').html("이메일을 입력해주세요.");
					return;
				} 
				$.ajax({
					type:'post',
					url:'answerView.do',
					data:$('#registerFrm').serialize(),
					
					success:function(result) {
						if(result=='true')
							$('#emailCheckView').html("이미 사용중인  이메일입니다.");
						else
							$('#emailCheckView').html("사용 가능한 이메일입니다.");
					}
				}); // email ajax
			}); // email keyup */
			
			//$('.answer').;
			
		});//ready

		$(document).ready(function(){
			$('.tabs').tabs();
		});
	</script>
	<style>
		section {
			width: 1080px;
			margin: auto;
		}
		section h2 {
			font-size: 2rem;
			font-weight: bold;
		}
		section h3 {
			font-size: 1.5rem;
			font-weight: bold;
		}
		section a {
			display: block;
		}
	</style>
</head>
<body>
	<!-- header import -->
    <c:import url="http://localhost:8888/Fiesta/header.jsp" charEncoding="UTF-8"></c:import>
    
	<!-- 항상 section에서 시작 -->
	<section>
		<!-- 업체 1개에 대한 정보 / 찜하기 : 업체가 들어왔을시 수정, 삭제 가능 -->
		<h2>업체명</h2>
		<p>업체 정보</p>

		<h3>제공 서비스</h3>
		<c:forEach items="${serviceList}" var="service">
			<a href="#">
				<p>${service.serviceName}</p>
				<p>${service.serviceImg}</p>
				<p>${service.serviceDesc}</p>
				<p>${service.serviceTag}</p>
			</a>
		</c:forEach>
		<a href="ServiceDelete.do?serviceCode=4">삭제</a>

		<!-- 리뷰에 대한 답은 해당 고객만 : 나중에 '리뷰하기'는 마이페이지로 이동 -->
		<h3>리뷰</h3>
		<div id="review">
			<div id="reviewInsert">
			<button name="reviewInsert">리뷰하기</button>
			<input type="hidden" name="companycode" value="${companycode}">
			</div>
			<div id="reviewScore">
			평점 : 
			<span>리뷰개수</span>
			</div>
			<hr>
			<c:forEach items="${list2}" var="review">
			<div id="reviewContent">
			<span>이름 : ${review.customer.custName}, </span><span>만족도 : ${review.reviewScore}, </span><span>일시 : ${review.reviewDate}</span><br>
			<span><img src= "${review.reviewImg}" width=100 height=100></span><br>
			<span>내용 : ${review.reviewDesc}</span><br>
			</div>
			<br>
			</c:forEach>
		</div>

		<!-- 문의/답변하기 : 고객 문의 + 업체 답변 -->
		<h3>문의답변</h3>
		<!-- 주석이 많아서 합쳐지면서 합할 생각중 -->
	</section>

	<!-- footer import -->
    <c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>

</body>
</html>