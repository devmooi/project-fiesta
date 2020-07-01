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
	<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
		$(document).ready(function(){
			$('.tabs').tabs();
			$('.collapsible').collapsible();
			
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
						//alert(comCode); 확인용
						//alert("찜하기 성공");
						//$("#result").html(result); 
						//성공과 실패를 나눠서 팝업창을 띄우고 싶은데 안된다 ㅠㅅㅠ
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
							$(".material-icons").html("mood_bad");
							
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
    	#companyDetail span {
    		font-weight: bold;
    		width: 70px;
    		display: inline-block;
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
    </style>
</head>
<body>
	<!-- header import -->
	<jsp:include page = "../header.jsp" />
	
	<!-- 업체 정보 : 로그인해서 본인 업체로 들어온 경우 / 다른 업체 정보 또는 업체 로그인이 아닐 때 -->
	<!-- 비로그인 또는 고객이 로그인 했을 때는 companyInfo만 있음.. -->
	<!-- 업체가 로그인 해서 업체 정보로 들어가면 company만 있음.. -->
	<!-- 업체가 로그인 해서 다른 업체 정보를 봤을 때는 companyInfo랑 company가 둘다 있음 -->
	<section>
		<c:choose>
			<c:when test="${!empty company}">
				<!-- 회사가 로그인 했을 때 -->
				<c:choose>
					<c:when test=${!empty companyInfo}>
						<!-- 다른 회사 정보를 봤을 때 -->
						회사 로그인 - 다른 회사 정보 company : ${company}
						회사 로그인 - 다른 회사 정보 companyInfo : ${companyInfo}
					</c:when>
					<c:when test=${company eq companyInfo}>
						<!-- 업체 정보가 아닌 다른 곳에서 내 회사 정보를 봤을 때 -->
						
					</c:when>
					<c:otherwise>
						<!-- 내 회사 정보를 봤을 때 -->
						회사 로그인 - 내 회사 정보
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<!-- 회사가 로그인 하지 않았을 때 -->
				company : ${company}
				companyInfo : ${companyInfo}
			</c:otherwise>
		</c:choose>
		<%-- <c:choose>
			<c:when test="${name eq '홍길동'}">
        		홍길동이 맞습니다.
    		</c:when>
    		<c:when test="${name eq '철수'}">
        		홍길동이 아닙니다.
    		</c:when>
    		<c:otherwise>
        		사람이 없습니다 ㅜㅜ
    		</c:otherwise>
		</c:choose> --%>

		<div id="companyInfo">
			<img src="${company.comImg}">
			<div>
				<h2>${company.comName}</h2>
				<p>${company.comDesc}</p>
				<p>조회수 : ${company.comCount}</p>
			</div>
		</div>
		<div id="companyDetail">
			<h3>기본 정보</h3>
			<p><span>이메일</span> ${company.comEmail}</p>
			<p><span>전화번호</span> ${company.comTel}</p>
			<p><span>주소</span> ${company.comAddr}</p>
		</div>
	</section>
	
	<section>
		<div id="companyInfo">
			<img src="${companyInfo.comImg}">
			<div>
				<h2>${companyInfo.comName}</h2>
				<p>${companyInfo.comDesc}</p>
				<p>조회수 : ${companyInfo.comCount}</p>
			</div>
		</div>
	</section>
	
	<section>



		<br><br>
		<!-- 찜하기랑 목록 -->
		<button id = "wishBtn" onclick="registerWish()">찜하기</button>
		
		<!-- 찜성공/실패 팝업창 -->
		<div class="PopupDiv"> 
				<i class="large material-icons">favorite</i>
				<div class="wishResult"></div>
				<a href="customerMypage.do?">나의 찜 목록보기</a> <!-- 경로수정해야함 -->
        		<button class="popCloseBtn">close</button>
    	</div>
    	
    	
    	<!-- 서비스목록 -->
		<br>
		<h3 align="center">서비스</h3><p>
		<table border="2" width="350" bgcolor="yellow" align="center">
			<c:forEach items="${serviceList}" var="service">
				<tr>
					<td>${service.serviceCode}</td>
					<td>${service.serviceName}</td>
					<c:if test="${not empty service.serviceImg}">
					<td><img src= "../resource/file_upload/${service.serviceImg}" width=100 height=100></td>
					</c:if>
					<td>${service.serviceDesc}</td>
					<td>${service.serviceTag}</td>
					<td><a href="ServiceDelete.do?serviceCode=${service.serviceCode}">삭제</a></td>  <!-- 이거는 업체가 로그인했을때만 보일 것 -->
				</tr>
			</c:forEach>
		</table>
		<button class = "serviceRegisterBtn" onclick = "serviceOpenClose()">서비스추가등록</button><br><!-- 이거는 업체가 로그인했을때만 보일 것 -->
		<div class = "serviceForm">
				<h4>서비스등록하기</h4>
				<form action="serviceRegister.do" id="serviceRegisterForm" enctype="multipart/form-data"  method="post">
				<input type="hidden" name="companycode" value="${companycode}">
				서비스 이름 : <input type="text" name="serviceName" required="required"><br><br>
				서비스 설명 : <input type="text" name="serviceDesc" required="required"><br><br>
<!-- 				서비스 사진 : <input type="text" name="serviceImg" required="required"><br><br> -->
				서비스 사진 : <input name="serviceImg" type="file" accept=".jpg, .jpeg, .png" multiple="multiple"><br><br>
				서비스 태그 : <input type="text" name="serviceTag" required="required"><br><br>
				<input onclick="serviceRegister()" type="submit" value="서비스등록">
				<!-- <input type="submit" value="서비스등록"> -->
				</form>
		</div>
		<p></p>
<!-- 여기서부터 탭 !!!! -->
<!-- 탭제목들 -->
		  <div class="row">
		    <div class="col s12">
		      <ul class="tabs">
		        <li class="tab col s3"><a href="#reviewTab">리뷰내역</a></li>
		        <li class="tab col s3"><a href="#qnaTab">문의내역</a></li>
		        <!-- <li class="tab col s3 disabled"><a href="#test3">Disabled Tab</a></li> -->
		        <li class="tab col s3"><a href="#question">문의하기</a></li>
		      </ul>
		    </div>
<!-- 탭내용들 -->	   
	<!-- 리뷰내역 -->
		    <div id="reviewTab" class="col s12">
		    	<div id="review">
					<div id="reviewInsert">
					<h4>리뷰 등록하기</h4>
					<form id="reviewfrm" action="InsertReview.do" method="post" enctype="multipart/form-data">
					<div id="wrap">
						<div id="comDesc">
							<input type="hidden" name="companycode" value="${companycode}">
							<input type="hidden" name="type" value="1">
							<select name="serviceName" required="required">
								<option label="리뷰할 서비스 선택하세요"></option>
								<c:forEach items="${serviceList}" var="service">
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
						<input type="submit" value="등록하기" id="insertReview">
					</div>
					</form>
					</div>
					<br><br><br>
					
					
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
   
		    <!-- 문의내역 -->
		    <div id="qnaTab" class="col s12">
		    	<h6 align="center"><b>문의내역</b></h6>
		    	<br>
		    	<div class= "qnaContent">
				 <ul class="collapsible">
				 <c:forEach items="${questionList}" var="question">
				    <li>
				      <div class="collapsible-header">	<%-- <span>${question.qCode}</span>  --%>
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
		
	</section>
	<!-- footer import -->
    <c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>