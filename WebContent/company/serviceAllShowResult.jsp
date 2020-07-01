<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fiesta - 업체 상세페이지</title>
    <link href="../Fiesta/resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://kit.fontawesome.com/064e5458ec.js" crossorigin="anonymous"></script>
	<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
		$(document).ready(function(){
			$('.tabs').tabs();
			$('.collapsible').collapsible();
			$('select').formSelect();
			
			$(".popCloseBtn").click(function(event){
	            $(".PopupDiv").css("display","none"); //팝업창 display none
	        });
			
			$('#insertReview').click(function() {
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
		
		//찜하기
		function registerWish(){
			var comCode = ${companycode};
			//var existResult = ${existResult};
			$.ajax({
				type:'post',
				url:'wishRegister.do',
				data:"comCode="+comCode,
				
				success:function(result) {
						if(result=='true'){
							$(".wishResult").html("찜하기 성공!!!!");
							
				  			$(".PopupDiv").css("display","block"); //팝업창 display block
					
							$(".PopupDiv").css({
					             "top": (($(window).height()-$(".PopupDiv").outerHeight())/2+$(window).scrollTop())+"px",
					             "left": (($(window).width()-$(".PopupDiv").outerWidth())/2+$(window).scrollLeft())+"px",
					             "background": "#FAE0D4"
						 	});  
						}else{
							$(".wishResult").html("이미 찜했어요 ㅠㅅㅠ");
							$(".favorite").html("mood_bad");
							
				  			$(".PopupDiv").css("display","block"); //팝업창 display block
					
							$(".PopupDiv").css({
					             "top": (($(window).height()-$(".PopupDiv").outerHeight())/2+$(window).scrollTop())+"px",
					             "left": (($(window).width()-$(".PopupDiv").outerWidth())/2+$(window).scrollLeft())+"px",
					             "background": "#D9E5FF"
						 	}); 
						}
				}
			}); // ajax
  			
		}
		
		//서비스등록창 열고닫기
		function serviceOpenClose() {
		      if ( $('.serviceForm').css('display') == 'none' ) {
		        $('.serviceForm').show();
		        /*  $('#answerForm').text('박스 닫기')*/
		      } else {
		        $('.serviceForm').hide();
		        /*$('#answerForm').text('박스 열기')*/
		      }
		}
		
		//답변하기창 열고닫기
		function answerOpenClose() {
		      if ( $('.answerForm').css('display') == 'none' ) {
		        $('.answerForm').show();
		        /*  $('#answerForm').text('박스 닫기')*/
		      } else {
		        $('.answerForm').hide();
		        /*$('#answerForm').text('박스 열기')*/
		      }
		}
		
		//서비스 등록
 		function serviceRegister() {
			$.ajax({
				type:'post',
				url:'serviceRegister.do',
				data:$('#serviceRegisterForm').serialize(),
				
				success:function(result) {
						//alert(comCode); 확인용
						alert("서비스등록");
				}
			}); // ajax
		}
		
		//문의 등록
 		function qRegister() {
			$.ajax({
				type:'post',
				url:'questionRegister.do',
				data:$('#questionForm').serialize(),
				
				success:function(result) {
						//alert(comCode); 확인용
						alert("문의등록");
				}
			}); // ajax
		}
		
    </script>
	<style>
		section {
			width: 1080px;
			margin: auto;
		}
		section a {
			display: block;
		}
		
		#companyInfo {
    		display: flex;
    		padding-top: 30px;
    	}
    	#companyInfo img {
    		width: 150px;
    		height: 150px;
    		border-radius: 5px;
    		margin-right: 20px;
    	}
    	#companyInfo h2 {
    		font-size: 1.7rem;
    		font-weight: bold;
    		margin-top: 20px;
    	}
    	#companyDetail h3 {
    		font-size: 1.3rem;
    		font-weight: bold;
    	}
    	#companyDetail .material-icons {
    		position: relative;
    		top: 6px;
    		margin-right: 10px;
    	}
    	#companyDetail span {
    		font-weight: bold;
    		width: 70px;
    		display: inline-block;
    	}
    	
    	/* 찜하기 */
    	#wishBtn {
    		margin-left: 20px;
    		background: none;
    		border: none;
    		color: black;
    		cursor: pointer;
    	}
    	#wishBtn:hover {
    		color: #009688;
    	}
    	
    	.PopupDiv {  /* 팝업창 css */
    		text-align:center;
		    top : 0px;
		    padding-top: 60px;
		    position: absolute;
		    background: #FAE0D4;
		    width: 300px;
		    height: 300px;
		    display: none;
    	}
    	.PopupDiv a {
    		display: block;
    	}
    	.PopupDiv .popCloseBtn {
    		cursor: pointer;
    		margin-top: 20px;
    		background: none;
		    border: 2px solid black;
		    padding: 5px 20px;
    	}
    	
    	/* 제공 서비스 */
    	#serviceList {
    		margin-bottom: 80px;
    	}
    	#serviceList h3 {
    		font-size: 1.3rem;
    		font-weight: bold;
    		margin-top: 80px;
    	}
    	#serviceList a {
    		display: flex;
    		align-items: center;
    		color: black;
    		margin-bottom: 9px;
    		border: 2px solid #ddd;
    		padding: 10px;
    	}
    	#serviceList a:hover {
    		border: 2px solid #009688;
    		transition-duration: 1.5s;
    	}
    	#serviceList h4 {
    		font-size: 1.1rem;
    		font-weight: bold;
    		width: 10%;
    		margin:0;
    		margin-left: 20px;
    	}
    	#serviceList p {
    		width: 60%;
    		margin: 0;
    	}
    	
    	/* 탭하기 */
    	.tabs .tab a {
    		color: #009688 !important;
    		font-size: 1.2rem !important;
    		font-weight: bold !important;
    	}
    	.tabs .indicator {
    		background-color: #009688 !important;
    	}
    	.tabs .tab a:focus, .tabs .tab a:focus.active {
    		background-color: rgba(20, 150, 135, 0.2) !important;
    	}
	</style>
    <style type="text/css">
        .answerForm {
      		display: none;
    	}
    	
    	.serviceForm {
      		display: none;
    	}
    	.qdetail{
    		background-color: #FAF4C0;
    	}
    	
    	.adetail{
    		background-color: #D4F4FA;
    	}
    </style>
</head>
<body>
	<jsp:include page = "../header.jsp" />
	
	<section>
		<div id="companyInfo">
			<img src="${companyInfo.comImg}">
			<div>
				<h2>${companyInfo.comName}</h2>
				<p>${companyInfo.comDesc}</p>
				<span>조회수 : ${companyInfo.comCount}</span>
				<c:if test="${!empty customer}">
					<button id = "wishBtn" onclick="registerWish()"><i class="far fa-heart"></i> 찜하기</button>
				</c:if>
			</div>
		</div>
		<div id="companyDetail">
			<h3>기본 정보</h3>
			<p><i class="material-icons">email</i><span>이메일</span> ${companyInfo.comEmail}</p>
			<p><i class="material-icons">phone</i><span>전화번호</span> ${companyInfo.comTel}</p>
			<p><i class="material-icons">map</i><span>주소</span> ${companyInfo.comAddr}</p>
		</div>
		
		<div id="serviceList">
			<h3>제공 서비스</h3>
			<!-- 서비스 목록 -->
			<c:forEach items="${serviceList}" var="service">
				<a href="#">
					<img src= "${service.serviceImg}" width=100 height=100>
					<h4>${service.serviceName}</h4>
					<p>${service.serviceDesc}</p>
					<span>${service.serviceTag}</span>
				</a>
			</c:forEach>
		</div>
		
		<ul class="tabs">
			<li class="tab"><a href="#reviewTab">리뷰</a></li>
			<li class="tab"><a href="#qnaTab">문의</a></li>
		</ul>
		
		<style>
			#reviewTab h4 {
				font-size: 1.1rem;
				font-weight: bold;
			}
			#reviewTab .select-wrapper {
				display: block;
			}
		</style>
		
		<!-- 리뷰 -->
		<div id="reviewTab">
			<!-- 고객만 등록 가능 -->
			<div id="reviewInsert">
				<h4>리뷰 등록하기</h4>
				<form id="reviewfrm" action="InsertReview.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="companycode" value="${companycode}">
					<input type="hidden" name="type" value="1">
					<select name="serviceName" required="required">
						<option disabled selected>리뷰할 서비스를 선택하세요</option>
						<c:forEach items="${serviceList}" var="service">
							<option>${service.serviceCode},${service.serviceName}</option>
						</c:forEach>
					</select>
					<div id="reviewScore">
						만족도 :
						<label>
							<input name="reviewScore" type="radio" value="1">
							<span><i class="small material-icons">star_border</i></span>
						</label>
						<label>
							<input name="reviewScore" type="radio" value="2">
							<span><i class="small material-icons">star_border</i></span>
						</label>
						<label>
							<input name="reviewScore" type="radio" value="3">
							<span><i class="small material-icons">star_border</i></span>
						</label>
						<label>
							<input name="reviewScore" type="radio" value="4">
							<span><i class="small material-icons">star_border</i></span>
						</label>
						<label>
							<input name="reviewScore" type="radio" value="5">
							<span><i class="small material-icons">star_border</i></span>
						</label>
					</div>
					<div id="reviewImg">
						사진 올리기: 1024*1024 이하의 이미지만 및 jpg, jpeg, png만 가능 <br>
						<input name="reviewImg" type="file" accept=".jpg, .jpeg, .png" multiple="multiple">
					</div>
					<div id="reviewDesc">
						<textarea name="reviewDesc" rows="5" cols="20" required="required"></textarea>
					</div>
					<input type="submit" value="등록하기" id="insertReview">
				</form>
				
				
			</div>
			
			<!-- 전부 다 볼 수 있는 부분 : 리뷰 내역 -->
			
		</div>
			<br><br><br><br>

					<!-- 리뷰내역  -->
					<div id="reviewScore">
					<span>평점 : </span>
					<c:forEach items="${reviewSrcList}" var="url">
					 <img src ="${url}" width="10px" height="10px">
					</c:forEach>
					<span>리뷰수 : ${review.countDesc}</span>
					</div>
					<hr>
					<c:forEach items="${reviewlist2}" var="review">
					<form id="answerfrm" action="ShowReview.do" method="post">
					<input type="hidden" name="reviewCode" value="${review.reviewCode}">
					<div id="reviewContent">
					<span>이름 : ${review.customer.custName}, </span><span>만족도 : ${review.reviewScore}, </span><span>일시 : ${review.reviewDate}</span><br>
					<span><img src= "${review.reviewImg}" width=100 height=100></span><br>
					<span>내용 : ${review.reviewDesc}</span><br>
					<input type="submit" value="답변하기">
					</div>
					<hr>
					<br>
					</form>
					</c:forEach>
				</div>
				<hr>
		    </div>
		</div>
		<div id="qnaTab">
			문의
		</div>
	</section>
	
<!-- 여기서부터 탭 !!!! -->
<!-- 탭제목들 -->
<!-- 탭내용들 -->	   
	<!-- 리뷰내역 -->
		   <%--  <div id="reviewTab" class="col s12">
		    	
   
		    <!-- 문의내역 -->
		    <div id="qnaTab" class="col s12">
		    	<h6 align="center"><b>문의내역</b></h6>
		    	<br>
		    	<div class= "qnaContent">
				 <ul class="collapsible">
				 <c:forEach items="${questionList}" var="question">
				    <li>
				      <div class="collapsible-header">	<span>${question.qCode}</span> 
													    <span>${question.qTitle}</span>&nbsp;&nbsp;&nbsp;&nbsp;
													    <span>${question.qDesc}</span>&nbsp;&nbsp;&nbsp;&nbsp;
													    <span>${question.custEmail}</span>&nbsp;&nbsp;&nbsp;&nbsp;
													    <span>${question.qDate}</span>&nbsp;&nbsp;&nbsp;&nbsp;
													    <span>${question.qCondition}</span> </div>
													
							<c:forEach items="${questionDetail}"  var="qDetail">
							<c:if test="${qDetail.qCode == question.qCode}" >						   
							<div class="collapsible-body qdetail"><h6>문의내용</h6><span>${qDetail.qDesc}</span></div>
							</c:if>
							</c:forEach>
				
							<c:if test="${question.qCondition eq '답변완료'}" >
							<c:forEach items="${answerList}"  var="answer">
								<c:if test="${answer.qCode == question.qCode}" >
					      		<div class="collapsible-body adetail"><h6>답변내용</h6><span>${answer.aDesc}</span>
					      										<span>${answer.aDate}</span>
					      										<a href="answerDelete.do?answerqCode=${answer.qCode}">삭제</a></div>
					      		</c:if>
					      	</c:forEach>
				      		</c:if>
					
						<c:if test="${question.qCondition eq '답변대기'}">
				      		<div class="collapsible-body" class="adetail"><span>답변대기중입니다</span>
				      		<button class = "answerBtn" onclick = "answerOpenClose()">답변하기</button><br>
				      		<div class = "answerForm">
								<h4>답변하기</h4>
								<form action="answerRegister.do?" name="registerForm" >
								<input type="hidden" name="companycode" value="${companycode}">
								<input type="hidden" name="qCode" value="${question.qCode}">
								답변내용 : <input type="text" name="aDesc" required="required"><br><br>
								<input type="submit" value="답변 등록">
								</form>
							</div>
				      		</div>
				      		
				     	</c:if>
				    </li>
				</c:forEach>
				</ul>
				</div>
				<a href="#" id="more">더보기</a>
				<br><br>
				<hr>
		    </div>
		   
		    <!-- <div id="test3" class="col s12">Test 3</div> -->
		   
	<!-- 문의하기 -->
		    <div id="question" class="col s12">
		    	<h6 align="center"><b>문의하기</b></h6>
		    	<br>
				<form action="" id="questionForm" >
				<input type="hidden" name="companycode" value="${companycode}">
				문의제목 : <input type="text" name="qTitle" required="required"><br><br>
				문의내용 : <input type="text" name="qDesc" required="required"><br><br>
				<input align="center" onclick ="qRegister()" type="submit" value="문의 등록">
				</form>
		    </div>
		  </div>
		
	
	 --%>
	
	
	<!-- 찜성공/실패 팝업창 -->
	<div class="PopupDiv"> 
		<i class="large material-icons favorite">mood</i>
		<div class="wishResult"></div>
		<a href="customerMypage.do">나의 찜 목록보기</a> 
      	<button class="popCloseBtn">close</button>
   	</div>
   	
	<!-- footer import -->
    <c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>