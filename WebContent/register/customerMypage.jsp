<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Fiesta - 마이페이지</title>
    <link href="../Fiesta/resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    
<style>
	input[type="radio"] {display:none;}
	input[type="radio"] + label {display:inline-block; background:#ccc;color:#999; cursor:pointer;}
	input[type="radio"]:checked + label {background:#aaa;color:#000;}
	
	.contents {display:none;}
	input[id="order"]:checked ~ .order {display:block;}
	input[id="request"]:checked ~ .request {display:block;}
	
	section a { display:block; }
	
	/* 주문내역보기 */
    .orderdetail{
   		background-color: #FAF4C0;
   	}
   	
   	/* 주문승인폼 */
   	.orderApproveForm {
      	display: none;
      	background-color: #CEFBC9;
    }
    /* 승인된 주문 최종내역 보기 */
	.orderFinalView{
		display: none;
      	background-color: #CEFBC9;
	}
</style>
<script>
$(function() {
	$('.tabs').tabs();
	$('.collapsible').collapsible();
	
	$('#customerUpdateBtn').click(function() {
		$.ajax({
			type:'post',
			url:'updateCustomer.do',
			data:$('#customerUpdateFrm').serialize(),
			
			success:function(result) {
				$('#updateCustomerCheckView').html("수정되었습니다.");
			}
		}); // ajax
	}); // click

}); // ready

//주문승인된 최종내역 창 열고닫기
function orderFinalViewOpenClose() {
      if ( $('.orderFinalView').css('display') == 'none' ) {
        $('.orderFinalView').show();
        /*  $('#answerForm').text('박스 닫기')*/
      } else {
        $('.orderFinalView').hide();
        /*$('#answerForm').text('박스 열기')*/
      }
}
//주문 반려하기 버튼 클릭하면 나오는 함수
function orderCancel(){
	$.ajax({
		type:'post',
		url:'orderCancel.do',
		data:$('.orderCancelForm').serialize(),
		
		success:function(result) {
				//alert(comCode); 확인용
				alert("주문을 취소하였습니다");
		}
	}); // ajax
}

function requestCancel(){
	alert($('input[name=requestCode]').val());
	$.ajax({
		type:'post',
		url:'requestCancel.do',
		data:$('.requestCancelForm').serialize(),
		
		success:function(result) {
				alert("의뢰를 취소하였습니다");
		}
	}); // ajax
}

</script>
<style type="text/css">
    	.qdetail{
    		background-color: #FAF4C0;
    	}
    	
    	.adetail{
    		background-color: #D4F4FA;
    	}
    	
   	section {
   		width: 1080px;
   		margin: auto;
   	}
   	section h2 {
   		font-weight: bold;
   		font-size: 2rem;
   	}
   	#customerUpdateFrm {
   		overflow: hidden;
   	}
   	#customerUpdateFrm input {
   		margin-bottom: 20px;
   	}
   	#customerUpdateFrm input[type=button] {
   		float: right;
   		background: none;
   		cursor: pointer;
   		border: 1px solid #009688;
   		padding: 8px;
   		color: #009688;
   		border-radius: 5px;
   	}
   	#customerUpdateFrm input[type=button]:hover {
   		background: #009688;
   		color: white;
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
</head>
<body>
    <jsp:include page = "../header.jsp" /> 
      
	<section>
		<h2>개인 정보 수정</h2> 
	    <form action="updateCustomer.do" id="customerUpdateFrm">
			이름  <input disabled type="text" name="custName" id="custName" value="${customer.custName}" placeholder="${customer.custName}">
			이메일  <input disabled type="text" name="custEmail" id="custEmail" value="${customer.custEmail}" placeholder="${customer.custEmail}">
			비밀번호  <input type="password" name="custPass" id="custPass" value="${customer.custPass}" placeholder="${customer.custPass}">
			휴대전화번호  <input type="text" name="custTel" id="custTel" value="${customer.custTel}" placeholder="${customer.custTel}">
			단체명  <input type="text" name="custGroup" id="custGroup" value="${customer.custGroup}" placeholder="${customer.custGroup}">
			<input type="button" id="customerUpdateBtn" value="회원정보수정">
		</form>
		<!-- <a href="customerDelete.jsp">계정삭제</a> -->

		<h2>거래 내역  </h2>
		<!-- 탭제목들 -->
		<ul class="tabs">
			<li class="tab"><a href="#orderTab">주문</a></li>
			<li class="tab"><a href="#requestTab">의뢰</a></li>
			<li class="tab"><a href="#questionTab">문의</a></li>
		    <li class="tab"><a href="#wishTab">찜</a></li>
		    <li class="tab"><a href="#reviewTab">리뷰</a></li>
		</ul>
		
		<!-- 주문내역 -->	
		<div id="orderTab">
			<c:if test="${not empty custOrderList}">
			    <ul class="collapsible">
			    <c:forEach items="${custOrderList}" var="custOrder">
			      <li>
			        <div class="collapsible-header">
				        <span style="font-weight: bold; margin-right: 60px; margin-left: 10px;">${custOrder.serviceName}</span>
			        	<span style="margin-left: 60px;">${custOrder.orderSysdate}</span>
			        	<span style="margin-left: 120px;">${custOrder.orderCondition}</span>
			        </div>
			        
			        <style>
			        	.orderFinalViewBtn {
							border: 1px solid orange;
							background: none;
							color: orange;
							padding: 8px;
							cursor: pointer;
							border-radius: 5px;
							margin-top: 10px;
						}
						.orderFinalViewBtn:hover {
							background: orange;
							color: white;
							transition-duration: 1.2s;
						}
						.orderFinalView {
							background: none;
						}
						.orderCancelForm>input[type=submit] {
							border: 1px solid orange;
							background: none;
							color: orange;
							padding: 8px;
							cursor: pointer;
							border-radius: 5px;
							margin-top: 10px;
						}
						.orderCancelForm>input[type=submit]:hover {
							background: orange;
							color: white;
							transition-duration: 1.2s;
						}
			        </style>
			       	
			       	<!-- 자세히보기 -->	        
			        <c:forEach items="${custOrderDetailList}"  var="custOrderDetail">
						<c:if test="${custOrder.orderCode == custOrderDetail.orderCode}" >
				      		<div class="collapsible-body orderdetail">
				      			<h6>주문내용</h6>
				      			<c:set var="loop_flag" value="false" />
				      			<c:forEach items="${custOrderService}"  var="service">
				      				<c:if test="${not loop_flag }">
						      			<c:if test="${service.serviceCode == custOrderDetail.serviceCode}" >
							      			<p>서비스 이름 : ${service.serviceName}</p>
							      			<p>서비스 내용 : ${service.serviceDesc}</p>
							      			<c:set var="loop_flag" value="true" />
						      		</c:if>
					      			</c:if>
					      		</c:forEach>
				      			<p>희망일시 : ${custOrderDetail.orderRevdate}</p>
				      			<p>희망장소 : ${custOrderDetail.orderPlace}</p>
				      			<p>예산 : ${custOrderDetail.orderBudget}</p>
				      			<p>부가사항 : ${custOrderDetail.orderRequire}</p>
				      			<!-- 상태는 주문대기, 주문취소, 주문반려, 주문승인완료 가 있다-->
				      			<!-- 주문 대기 일때는 주문 취소를 할 수 있다  -->
								<c:if test="${custOrder.orderCondition == '주문대기'}">
									<form action="" class="orderCancelForm" >
										<input type="hidden" name="orderCode" value="${custOrder.orderCode}">
									<input align="center" onclick ="orderCancel()" type="submit" value="주문취소하기 ">
									</form>
								</c:if>
								<!-- 주문 승인완료 일때는 최종거래내역을 볼 수 있다 -->
								<c:if test="${custOrder.orderCondition == '주문승인완료'}">
									<button class = "orderFinalViewBtn" onclick = "orderFinalViewOpenClose()">최종내역보기</button>
									<div class="orderFinalView">
										<c:forEach items="${custOrderFinalDetail}"  var="finalDetail">
											<c:if test="${custOrder.orderCode == finalDetail.orderCode}">
												<p>최종금액 : ${finalDetail.custdetailTotalprice}</p>
												<p>최종요구사항내역 : ${finalDetail.custdetailDesc}</p>
												<p>최종거래완료날짜 : ${finalDetail.custdetailCompletedate}</p>
											</c:if>
										</c:forEach>
									</div>
								</c:if>
							</div>
			      		</c:if>
					</c:forEach>
		          </li>
		        </c:forEach>
		      </ul> 	
	      </c:if>
	    </div>
	    
	    <!-- request!! -->
	    <style>
        	.requestFinalViewBtn {
				border: 1px solid orange;
				background: none;
				color: orange;
				padding: 8px;
				cursor: pointer;
				border-radius: 5px;
				margin-top: 10px;
			}
			.requestFinalViewBtn:hover {
				background: orange;
				color: white;
				transition-duration: 1.2s;
			}
			.requestFinalView {
				background: none;
			}
			.requestCancleForm>input[type=submit] {
				border: 1px solid orange;
				background: none;
				color: orange;
				padding: 8px;
				cursor: pointer;
				border-radius: 5px;
				margin-top: 10px;
			}
			.requestCancleForm>input[type=submit]:hover {
				background: orange;
				color: white;
				transition-duration: 1.2s;
			}
        </style>
		<div id="requestTab">
			<c:if test="${not empty custRequestList}">
			    <ul class="collapsible">
			    <c:forEach items="${custRequestList}" var="custRequest">
			      <li>
			        <div class="collapsible-header">
				        <span style="font-weight: bold; width: 15%; margin-left: 10px;">${custRequest.comcategory.comCategoryDesc}</span>
			        	<span>${custRequest.requestSysdate}</span>
			        </div>

			       	<!-- 자세히보기 -->	        
			        <div class="collapsible-body orderdetail">
		      			<h6>의뢰내용</h6>
		      			<p>희망일시 : ${custRequest.requestRevdate}</p>
		      			<p>희망장소 : ${custRequest.requestPlace}</p>
		      			<p>예산 : ${custRequest.requestBudget}</p>
		      			<p>부가사항 : ${custRequest.requestRequire}</p>
		      			<!-- 의뢰 취소를 할 수 있다  -->
		      			<form class="requestCancleForm">
		      				<input type="hidden" name="requestCode" value="${custRequest.requestCode}">
		      				<input align="center" onclick="requestCancel()" type="submit" value="의뢰취소하기">
		      			</form>

						<!-- 의뢰 승인완료 일때는 최종거래내역을 볼 수 있다 -->
						<%-- <c:if test="${custOrder.orderCondition == '주문승인완료'}">
							<button class = "orderFinalViewBtn" onclick = "orderFinalViewOpenClose()">최종내역보기</button>
							<div class="orderFinalView">
								<c:forEach items="${custOrderFinalDetail}"  var="finalDetail">
									<c:if test="${custOrder.orderCode == finalDetail.orderCode}">
										<p>최종금액 : ${finalDetail.custdetailTotalprice}</p>
										<p>최종요구사항내역 : ${finalDetail.custdetailDesc}</p>
										<p>최종거래완료날짜 : ${finalDetail.custdetailCompletedate}</p>
									</c:if>
								</c:forEach>
							</div>
						</c:if> --%>
					</div>
		          </li>
		        </c:forEach>
		      </ul> 	
	      </c:if>
		</div>
	<%-- <style>
		#requestTab .collapsible-header {
			display: block;
		}
		#requestTab .collapsible-header h3 {
			font-size: 1.2rem;
			font-weight: bold;
		}
	</style>
	  <!-- 의뢰내역 -->	
	  <div id="requestTab">
	  	<c:if test="${not empty requestList}">
	  		<ul class="collapsible">
			    <c:forEach items="${requestList}" var="request">
			      <li>
			        <div class="collapsible-header">
			        	<h3>신청한 분류 : ${request.comcategory.comCategoryDesc}</h3>
			        	<p>의뢰 일시 : ${request.requestSysdate}</p>
			        	<p>희망 일시 : ${request.requestRevdate}</p>
			        	<p>희망 장소 : ${request.requestPlace}</p>
			        	<p>예산 : ${request.requestBudget}</p>
			        	<p>희망 사항 : ${request.requestRequire}</p>
			        </div>	
			      <c:forEach items="${requestDetailList}" var="orderDetail">
			        <div class="collaps-body orderDetail">
			          <h6>의뢰 상세 내용</h6>
			            <span>${requestDetail.detailDesc}</span></div>
			      </c:forEach>
		          </li>
		        </c:forEach>
		      </ul> 	
	  	</c:if>
	</div> --%>
	    
	    <style>
	    	.collapsible-header {
	 			display: flex;
	 		}
	 		.collapsible-header .title {
	 			width: 70%;
	 			text-align: center;
	 		}
	 		.collapsible-header .date {
	 			width: 18%;
	 		}
	 		.collapsible-header .header {
	 			font-weight: bold;
	 		}
	 		
	 		.collapsible-header .comName {
	 			width: 30%;
	 			margin-left: 20px;
	 		}
	 		.collapsible-header .comDesc {
	 			width: 55%;
	 		}
	 		.collapsible-header .comStatus {
	 			width: 15%;
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
	  <!-- 내가 문의한내역 -->	
	  <div id="questionTab" class="col s12">
		<c:if test="${not empty questionList}">
		    <ul class="collapsible">
		    	<li>
		 			<div class="collapsible-header">
		 				<span class="title header">제목</span>
		 				<span class="date header">작성일</span>
		 				<span class="status header">상태</span>
		 			</div>
		 		</li>
				 <c:forEach items="${questionList}" var="question">
				    <li>
				      <div class="collapsible-header">
				      	<span class="title">${question.qDate}</span>
				      	<span class="date">${question.qDesc}</span>
				      	<c:choose>
							<c:when test="${question.qCondition eq '답변대기'}">
								<span style="color: red;">${question.qCondition}</span>
							</c:when>
							<c:otherwise>
								<span class="status" style="color: blue;">${question.qCondition}</span>
							</c:otherwise>
						</c:choose>
					  </div>
													
							<c:forEach items="${questionDetail}"  var="qDetail">
							<c:if test="${qDetail.qCode == question.qCode}" >						   
							<div class="collapsible-body qdetail" style="position:relative;">
								<span>${qDetail.qDesc}</span>
								<a style="position:absolute; right:115px; display:inline;" href="questionDelete.do?qCode=${question.qCode}">삭제</a>
							</div>
							</c:if>
							</c:forEach>
				
							<c:if test="${question.qCondition eq '답변완료'}" >
							<c:forEach items="${answerList}"  var="answer">
								<c:if test="${answer.qCode == question.qCode}" >
					      		<div class="collapsible-body adetail" style="position:relative;">
					      			<span>${answer.aDate}</span>
					      			<span style="position:absolute; right:180px;">${answer.aDesc}</span>
					      		</div>
					      		</c:if>
					      	</c:forEach>
				      		</c:if>
					
						<c:if test="${question.qCondition eq '답변대기'}">
				      		<div class="collapsible-body" class="adetail"><span>답변대기중입니다</span>
				      		</div>
				     	</c:if>
				    </li>
				</c:forEach>
				</ul>	
			</c:if>
	    </div>
	    
	<!-- 내가 찜한내역 -->	
		<div id="wishTab">
			<c:if test="${not empty wishlist}">
				<ul class="collapsible">
			    	<li>
			 			<div class="collapsible-header">
			 				<span class="comName header">업체명</span>
			 				<span class="comDesc header">업체소개</span>
			 				<span class="comStatus header">삭제</span>
			 			</div>
			 		</li>
			 		<c:forEach items="${wishlist}" var="wish">
					    <li>
					      <div class="collapsible-header">
					      	<span class="comName">${wish.comName}</span>
					      	<span class="comDesc">${wish.comDesc}</span>
					      	<span class="comStatus"><a href="wishDelete.do?wishCode=${wish.wishCode}&custEmail=${wish.custEmail}">삭제</a></span>
					      </div>
					    </li>
					</c:forEach>
			 	</ul>
			</c:if>
		</div>	
		    
	<!-- 나의 리뷰 내역 -->	
		  <div id="reviewTab" class="col s12">
			  <c:if test="${not empty reviewlist}">
			  		<ul class="collapsible">
				   		<li>
				   			<div class="collapsible-header">
				   				<span class="comName header">업체명</span>
				   				<span class="comStatus header">만족도</span>
				   				<span class="comName header">이미지</span>
				 				<span class="comDesc header">리뷰내용</span>
				 				<span class="comStatus header">삭제</span>
				   			</div>
				   		</li>
						<c:forEach items="${reviewlist}" var="review">
							<li>
								<div class="collapsible-header">
								<span class="comName">${review.company.comName}</span>
								<span class="comStatus">${review.reviewScore}</span>
								<span class="comName"><img src="${review.reviewImg}" width="100" height="100"></span>
								<span class="comDesc">${review.reviewDesc}</span>
								<span class="comStatus"><a href="reviewDelete.do?reviewCode=${review.reviewCode}">삭제</a></span>
								</div>
							</li>
								<c:if test="${not empty review.answerlist}">
								<li>
								<div class="collapsible-header">
									<c:forEach items="${review.answerlist}" var="answer">
										<span class="comName"></span>
										<span class="comStatus">답변</span>
										<span class="comName"><img src = "${answer.reviewImg}" width="100" height="100"></span>
										<span class="comDesc">${answer.reviewDesc}</span>
										<span class="comStatus"></span>
									</c:forEach>
								</div>
								</li>
								</c:if>
						</c:forEach>
					</ul>
				</c:if>
		    </div>		  
		   
    </section>

    <c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>