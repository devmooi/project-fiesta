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
			
			//오늘본거 함수
			todatview();
			
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
			var data = $(".comName").text() +','+ $(".companycode").val();
			localStorage.setItem($(".comImg").val(), data);
			//localStorage.setItem('test', 1);
		});//ready
		
		//오늘 본거 함수
		function todatview(){
    		var html='';
    		var count=0;
    		var idx = localStorage.length;
    		for(var i=idx-1; i>=0; i--){
    			var key = localStorage.key(i);
    			if(key=='length') break;
    			var datas = localStorage.getItem(key).split(',');
    			//var data = localStorage.getItem(key);
    			//<a href="ServiceAllShow.do?companycode=${recoCom.company.comCode}">
    			html = '<a href="ServiceAllShow.do?companycode='+datas[1]+'"><img width=50 height=50 src="'+key+'"><br>'+datas[0]+'<br></a>';
    			$('.todayView').append(html);
    			//alert(key);
    			count++;
    			if(count==3) break;
    		}
		}
		
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
    	#serviceList h3 {
    		font-size: 1.3rem;
    		font-weight: bold;
    		margin-top: 80px;
    	}
    	#serviceList .serviceOrder {
    		display: flex;
    		align-items: center;
    		color: black;
    		margin-bottom: 9px;
    		border: 2px solid #ddd;
    		padding: 10px;
    	}
    	#serviceList .serviceOrder:hover {
    		border: 2px solid #009688;
    		transition-duration: 1.5s;
    	}
    	#serviceList h4 {
    		font-size: 1.1rem;
    		font-weight: bold;
    		width: 20%;
    		margin:0;
    		margin-left: 20px;
    	}
    	#serviceList p {
    		width: 50%;
    		margin: 0;
    	}
    	
    	/* 탭하기 */
    	.tabs {
    		margin-top: 80px !important;
    	}
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
    	
    	/* 오늘 본거 바 */
    	.todayViewBar { 
        	position:fixed;
        	width:175px;
        	display:inline-block;
        	right:20px; /* 창에서 오른쪽 길이 */
        	top:25%; /* 창에서 위에서 부터의 높이 */
        	background-color: transparent;
        	margin:0;
        	border: 1px solid #ddd;
        	padding: 20px;
        }
        .todayViewBar h6{
        	font-weight: bold;
        	text-align: center;
        	margin-bottom: 20px;
        }
        .todayView {
        	text-align: center;
        }
        .todayView a {
        	margin-bottom: 10px;
        	display: block;
        	color: #009688;
        }
    </style>
</head>
<body>
	<jsp:include page = "../header.jsp" />
	
	<section>
	<!-- 오늘 본거 바 -->
		<div class="todayViewBar">
   			<h6>오늘 본거</h6>
   			<div class="todayView">
   			
   			</div>
   		</div>
   		
		<div id="companyInfo">
			<input type="hidden" class="comImg" value="${companyInfo.comImg}">
			<input type="hidden" class="companycode" value="${companycode}">
			<c:if test="${not empty companyInfo.comImg}">
				<img src="${companyInfo.comImg}">
			</c:if>
			<div>
				<h2 class="comName">${companyInfo.comName}</h2>
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
				<a class="serviceOrder" href="ServiceOrder.do?companycode=${companyInfo.comCode}&serviceCode=${service.serviceCode}">
					<img src= "${service.serviceImg}" width=100 height=100>
					<h4>${service.serviceName}</h4>
					<p>${service.serviceDesc}</p>
					<span>${service.serviceTag}</span>
					<%-- <a href="ServiceDelete.do?serviceCode=${service.serviceCode}">삭제</a> --%><!-- 이거는 업체가 로그인했을때만 보일 것 -->
				</a>
			</c:forEach>
			<c:if test="${not empty company}">
				<button class = "serviceRegisterBtn" onclick = "serviceOpenClose()">서비스추가등록</button>
			</c:if>
		</div>
		
		<style>
			.serviceRegisterBtn {
				background: none;
				border: 1px solid #009688;
				color: #009688;
				padding: 8px;
				cursor: pointer;
				border-radius: 5px;
			}
			.serviceRegisterBtn:hover {
				background: #009688;
				color: white;
			}
			.serviceForm {
				margin-top: 40px;
				overflow: hidden;
			}
			.serviceForm h4 {
				font-size: 1.3rem;
				font-weight: bold;
			}
			.serviceForm input[type=submit] {
				background: none;
				border: 1px solid #009688;
				padding: 8px;
				color: #009688;
				cursor: pointer;
				border-radius: 5px;
				float: right;
			}
			.serviceForm input[type=submit]:hover {
				background: #009688;
				color: white;
			}
		</style>
		<div class = "serviceForm">
			<h4>서비스등록하기</h4>
			<form action="serviceRegister.do" id="serviceRegisterForm" enctype="multipart/form-data"  method="post">
				<input type="hidden" name="companycode" value="${companycode}">
				서비스 이름 : <input type="text" name="serviceName" required="required"><br><br>
				서비스 설명 : <input type="text" name="serviceDesc" required="required"><br><br>
				서비스 사진 : <input name="serviceImg" type="file" accept=".jpg, .jpeg, .png" multiple="multiple"><br><br>
				서비스 태그 : <input type="text" name="serviceTag" required="required"><br><br>
				<input onclick="serviceRegister()" type="submit" value="서비스등록">
				<!-- <input type="submit" value="서비스등록"> -->
			</form>
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
			#reviewTab #reviewScore .star:after {
				display: none !important;
			}
			
			/* 별 평점 */
			.blind {
			  position: absolute;
			  overflow: hidden;
			  margin: -1px;
			  padding: 0;
			  width: 1px;
			  height: 1px;
			  border: none;
			  clip: rect(0, 0, 0, 0);
			}
			
			.startRadio {
			  display: inline-block;
			  overflow: hidden;
			  height: 20px;
			}
			
			.startRadio:after {
			  content: "";
			  display: block;
			  position: relative;
			  z-index: 10;
			  height: 20px;
			  background: url("data:image/png;base64,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") repeat-x 0 0;
			  background-size: contain;
			  pointer-events: none;
			}
			
			.startRadio__box {
			  position: relative;
			  z-index: 1;
			  float: left;
			  width: 20px;
			  height: 40px;
			  cursor: pointer;
			}
			
			.startRadio__box input {
			  opacity: 0 !important;
			  height: 0 !important;
			  width: 0 !important;
			  position: absolute !important;
			}
			
			.startRadio__box input:checked + .startRadio__img {
			  background-color: #009688;
			}
			
			.startRadio__img {
			  display: block;
			  position: absolute;
			  right: 0;
			  width: 500px;
			  height: 40px;
			  pointer-events: none;
			}
			
			[type="radio"]:not(:checked)+span, [type="radio"]:checked+span {
				position: absolute !important;
			}
		
		</style>
		
		<!-- 리뷰 -->
		<div id="reviewTab">
			<!-- 고객만 등록 가능 -->
			<c:if test="${not empty customer}">
				<div id="reviewInsert">
					<h4>리뷰 등록하기</h4>
					<form id="reviewfrm" action="InsertReview.do" method="post" enctype="multipart/form-data">
						<input type="hidden" name="companycode" value="${companycode}">
						<input type="hidden" name="type" value="1">
						<select name="serviceName" required="required">
							<option disabled selected>리뷰할 서비스를 선택하세요</option>
							<c:forEach items="${serviceList}" var="service">
								<option value="${service.serviceCode},${service.serviceName}">${service.serviceName} - ${service.serviceDesc}</option>
							</c:forEach>
						</select>
						
						<!-- <div id="reviewImg">
								사진 올리기:  <br>
								<input  type="file" accept=".jpg, .jpeg, .png" ="multiple">
							</div> -->
							
						<div class="filebox">
							<input class="upload-name" value="1024*1024 이하의 jpg, jpeg, png 이미지 파일만 가능" disabled>
							<label for="ex_filename">업로드</label>
							<input type="file" name="reviewImg" id="ex_filename" class="upload-hidden" accept=".jpg, .jpeg, .png" multiple>
						</div>
						<style>
							.filebox {
								margin-top: 20px;
								margin-bottom: 20px;
							}
							.filebox p {
							    display: block;
							    left: -204px;
							    position: relative;
							    font-weight: bold;
							}
							.filebox input[type="file"] { 
								position: absolute; 
								width: 1px; 
								height: 1px; 
								padding: 0; 
								margin: -1px; 
								overflow: hidden; 
								clip:rect(0,0,0,0); 
								border: 0; 
							} 
							.filebox label { 
								display: inline-block; 
								padding: .5em .75em; 
								font-size: inherit; 
								line-height: normal; 
								vertical-align: middle; 
								cursor: pointer; 
								border-radius: .25em; 
							
							    top: -5px;
							    position: relative;
			    				left: 6px;
			    				padding: 9px;
			    				color: #009688 !important;
			    				border: 1px solid #009688;
			    				
							} /* named upload */ 
							.filebox label:hover {
								background: #009688;
								color: white !important;
							}
							.filebox .upload-name { 
								display: inline-block; 
								padding: .5em .75em; /* label의 패딩값과 일치 */ 
								font-size: inherit; 
								font-family: inherit; 
								line-height: normal; 
								vertical-align: middle; 
								background-color: #f5f5f5; 
								border: 1px solid #ebebeb; 
								border-bottom-color: #e2e2e2; 
								border-radius: .25em; 
								-webkit-appearance: none; /* 네이티브 외형 감추기 */ 
								-moz-appearance: none; 
								appearance: none; 
								
							    width: 400px;
							    height: 18px;
							}
						</style>
						<script>
							$(document).ready(function(){ 
								var fileTarget = $('.filebox .upload-hidden'); 
								fileTarget.on('change', function(){ 
									// 값이 변경되면 
									if(window.FileReader){ 
										// modern browser 
										var filename = $(this)[0].files[0].name; 
									} else { // old IE 
										var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
									} // 추출한 파일명 삽입 
									$(this).siblings('.upload-name').val(filename); 
								}); 
							});
						</script>
						
						<div class="startRadio">
						    <label class="startRadio__box">
						        <input type="radio" name="reviewScore" value="1">
						        <span class="startRadio__img"><span class="blind">별 1개</span></span>
						    </label>
						    <label class="startRadio__box">
						        <input type="radio" name="reviewScore" value="2">
						        <span class="startRadio__img"><span class="blind">별 2개</span></span>
						    </label>
						    <label class="startRadio__box">
						        <input type="radio" name="reviewScore" value="3">
						        <span class="startRadio__img"><span class="blind">별 3개</span></span>
						    </label>
						    <label class="startRadio__box">
						        <input type="radio" name="reviewScore" value="4">
						        <span class="startRadio__img"><span class="blind">별 4개</span></span>
						    </label>
						    <label class="startRadio__box">
						        <input type="radio" name="reviewScore" value="5">
						        <span class="startRadio__img"><span class="blind">별 5개</span></span>
						    </label>
						</div>	
						
						<div id="reviewDesc">
							<textarea name="reviewDesc" rows="5" cols="20" required="required"></textarea>
						</div>
						
						<input type="submit" value="등록하기" id="insertReview">
					
						<style>
							#reviewDesc textarea{
								resize: none;
								height: 5rem;
							}
							#insertReview {
								float: right;
								background: none;
								border: 1px solid #009688;
								color: #009688;
								padding: 7px;
								border-radius: 5px;
								cursor: pointer;
							}
							#insertReview:hover {
								background: #009688;
								color: white;
							}
							form {
								overflow: hidden;
							}
						</style>	
					</form>
				</div>
			</c:if>
			
			<!-- 전부 다 볼 수 있는 부분 : 리뷰 내역 -->
			<div id="reviewScore">
				<h4>리뷰 내역</h4>
				<p>
					<span>평점 : </span>
					<c:forEach items="${reviewSrcList}" var="url">
						<img src ="${url}" width="13px" height="13px">
					</c:forEach>
					(${review.countDesc}개)
				</p>

				<c:forEach items="${reviewlist2}" var="review">
					<form id="answerfrm" action="ShowReview.do" method="post">
					<input type="hidden" name="reviewCode" value="${review.reviewCode}">
					<div id="reviewContent" style="display: flex; align-items: center;">
						<img src= "${review.reviewImg}" width=100 height=100>
						<div style="margin-left: 20px;">
							<p><span style="font-weight: bold; margin-right: 15px;">이름</span> ${review.customer.custName} (${review.reviewScore})</p>
							<p><span style="font-weight: bold; margin-right: 15px;">일시</span> ${review.reviewDate}</p>
							<p><span style="font-weight: bold; margin-right: 15px;">내용</span> ${review.reviewDesc}</p>
							<c:if test="${empty review.answerlist && not empty company}">
								<input type="submit" value="답변하기">
							</c:if>
						</div>
					</div>
						<c:forEach items="${review.answerlist}" var="answer">
						<div id="answerContent">
							<p>답변</p>
							<span><img src="${answer.reviewImg}" width="100" height="100"></span>
							<span>답변 내용 : ${answer.reviewDesc}</span>
						</div>
						</c:forEach>
					<hr>
					<br>
					</form>
					</c:forEach>
			</div>
		 </div>
		 
		 <style>
		 	#qnaTab h4 {
				font-size: 1.1rem;
				font-weight: bold;
				margin-bottom: 20px;
			}
			#question {
				margin-bottom: 20px;
			}
			#questionForm {
				overflow: hidden;
			}
			#questionForm div {
				display: flex;
			}
			#questionForm div span {
				width: 5%;
			}
			#questionForm div input[type=text] {
				height: 1.5rem;
			}
			#questionForm input[type=submit] {
				float: right;
				background: none;
				border: 1px solid #009688;
				color: #009688;
				padding: 7px;
				border-radius: 5px;
				cursor: pointer;
			}
			#questionForm input[type=submit]:hover {
				background: #009688;
				color: white;
			}
		 </style>
		 <!-- 문의 -->
		 <div id="qnaTab">
		 	<c:if test="${not empty customer}">
			 	<div id="question">
			 		<h4>문의하기</h4>
			 		<form id="questionForm">
			 			<input type="hidden" name="companycode" value="${companycode}">
			 			<div>
			 				<span>제목 : </span><input type="text" name="qTitle" required="required">
			 			</div>
						<p>내용 : <input type="text" name="qDesc" required="required"></p>
						<input onclick ="qRegister()" type="submit" value="문의 등록">
			 		</form>
			 	</div>
		 	</c:if>
		 	
		 	<style>
		 		.collapsible-header {
		 			display: flex;
		 		}
		 		.collapsible-header .title {
		 			width: 55%;
		 			text-align: center;
		 		}
		 		.collapsible-header .author {
		 			width: 18%;
		 		}
		 		.collapsible-header .date {
		 			width: 18%;
		 		}
		 		.collapsible-header .header {
		 			font-weight: bold;
		 		}
		 		.answerBtn {
		 			position: relative;
				    left: 815px;
				    background: none;
				    border: none;
				    cursor: pointer;
		 		}
		 		.answerBtn:hover {
		 			color: #009688;
		 		}
		 		form[name=registerForm] {
		 			overflow: hidden;
		 		}
		 		#answer {
	 			    float: right;
				    background: none;
				    border: 1px solid #009688;
				    color: #009688;
				    padding: 8px;
				    border-radius: 5px;
				    cursor: pointer;
		 		}
		 		#answer:hover {
		 			background: #009688;
		 			color: white;
		 		}
		 	</style>
		 	<div id="questionList">
		 		<h4>문의내역</h4>
		 		<div class="qnaContent">
		 			<ul class="collapsible">
		 				<li>
		 					<div class="collapsible-header">
		 						<span class="title header">제목</span>
		 						<span class="author header">작성자</span>
		 						<span class="date header">작성일</span>
		 						<span class="status header">상태</span>
		 					</div>
		 				</li>
		 				<c:forEach items="${questionList}" var="question">
		 					<li>
		 						<div class="collapsible-header">
		 							<span class="title">${question.qTitle}</span>
		 							<span class="author">${question.custEmail}</span>
		 							<span class="date">${question.qDate}</span>
		 							<c:choose>
		 								<c:when test="${question.qCondition eq '답변대기'}">
		 									<span style="color: red;">${question.qCondition}</span>
		 								</c:when>
		 								<c:otherwise>
		 									<span class="status" style="color: blue;">${question.qCondition}</span>
		 								</c:otherwise>
		 							</c:choose>
		 						</div>
		 						
		 						<c:forEach items="${questionDetail}" var="qDetail">
		 							<c:if test="${qDetail.qCode == question.qCode}">
		 								<div class="collapsible-body qdetail">
		 									<span>${qDetail.qDesc}</span>
		 								</div>
		 							</c:if>
		 						</c:forEach>
		 						
		 						<c:if test="${question.qCondition eq '답변완료'}" >
		 							<c:forEach items="${answerList}"  var="answer">
		 								<c:if test="${answer.qCode == question.qCode}" >
		 									<div class="collapsible-body adetail">
		 										<span>${answer.aDate}</span>
		 										<span style="position:relative; left:648px;">${answer.aDesc}</span>
		 										<c:if test="${not empty company}">
		 											<a style="position:relative; left:677px; display:inline;" href="answerDelete.do?answerqCode=${answer.qCode}">삭제</a>
		 										</c:if>
		 									</div>
		 								</c:if>
		 							</c:forEach>
		 						</c:if>
		 						
		 						<c:if test="${question.qCondition eq '답변대기'}">
		 							<div class="collapsible-body" class="adetail">
		 								<span>답변대기중입니다</span>
		 								<c:if test="${not empty company}">
		 									<button class = "answerBtn" onclick = "answerOpenClose()">답변하기</button>
		 								</c:if>
		 								
		 								<div class = "answerForm">
											<h4>답변하기</h4>
											<form action="answerRegister.do?" name="registerForm" >
												<input type="hidden" name="companycode" value="${companycode}">
												<input type="hidden" name="qCode" value="${question.qCode}">
												답변내용 : <input type="text" name="aDesc" required="required"><br><br>
												<input type="submit" value="답변등록" id="answer">
											</form>
										</div>
		 							</div>
		 						</c:if>
		 					</li>
		 				</c:forEach>
		 			</ul>
		 		</div>
		 	</div>
		 </div>
	</section>

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