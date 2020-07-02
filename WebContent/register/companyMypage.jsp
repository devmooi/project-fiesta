<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fiesta - 업체 정보</title>
	<link href="../resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
	<style>
		input[type="radio"] {display:none;}
		input[type="radio"]:checked + label {background:#aaa;color:#000;}
		
		.contents {display:none;}
		input[id="order"]:checked ~ .order {display:block;}
		input[id="request"]:checked ~ .request {display:block;}
		
		/* 주문내역보기 */
	    .orderdetail{
	   		background-color: #FAF4C0;
	   	}
	   	
	   	/* 주문승인폼 */
	   	.orderApproveForm {
	      	display: none;
	      	background-color: #CEFBC9;
	    }
	    .requestApproveForm {
	    	display: none;
	    }
	    /* 승인된 주문 최종내역 보기 */
		.orderFinalView{
			display: none;
	      	background-color: #CEFBC9;
		}
		.requestFinalView {
			display: none;
		}
	</style>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
	$(function() {
		$('.tabs').tabs();
		$('.collapsible').collapsible();
		
		$('#companyUpdateBtn').click(function() {
			$.ajax({
				type:'post',
				url:'updateCompany.do',
				data:$('#companyUpdateFrm').serialize(),

				success:function(result) {
					$('#updateCompanyCheckView').html("수정되었습니다.");
				}
			}); // ajax
		}); // click
	}); // ready
	
	//주문받기창 열고닫기
	function orderApproveFormOpenClose() {
	      if ( $('.orderApproveForm').css('display') == 'none' ) {
	        $('.orderApproveForm').show();
	        /*  $('#answerForm').text('박스 닫기')*/
	      } else {
	        $('.orderApproveForm').hide();
	        /*$('#answerForm').text('박스 열기')*/
	      }
	}
	
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
	function orderReject(){
		$.ajax({
			type:'post',
			url:'orderReject.do',
			data:$('.orderRejectForm').serialize(),
			
			success:function(result) {
					//alert(comCode); 확인용
					alert("주문을 반려하였습니다");
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
    	#companyUpdateFrm {
    		overflow: hidden;
    	}
    	#companyUpdateBtn {
    		float: right;
    		margin-right: 216px;
		    cursor: pointer;
		    background: none;
		    border: 1px solid #009688;
		    color: #009688;
		    border-radius: 5px;
		    padding: 8px;
    	}
    	#companyUpdateBtn:hover {
    		background: #009688;
    		color: white;
    	}
    	section h2 {
    		font-size: 1.3rem;
    		font-weight: bold;
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
		<div id="companyInfo">
			<img src="${company.comImg}">
			<div>
				<h2>${company.comName}</h2>
				<p>${company.comDesc}</p>
			</div>
		</div>
		<div id="companyDetail">
			<h3>기본 정보 수정</h3>
			<form action="updateCompany.do" id="companyUpdateFrm">
				<input type="hidden" name="comName" id="comName" value="${company.comName}">
				<input type="hidden" name="comEmail" id="comEmail" value="${company.comEmail}">
				<input type="hidden" name="comPass" id="comPass" value="${company.comPass}">
				<input type="hidden" name="comImg" id="comImg" value="${company.comImg}">
				<input type="hidden" name="comDesc" id="comDesc" value="${company.comDesc}">
				<input type="hidden" name="categoryCode" id="categoryCode" value="${company.comCategoryCode}">
				<p><i class="material-icons">email</i><span>이메일</span> ${company.comEmail}</p>
				<p>
					<i class="material-icons">phone</i><span>전화번호</span>
					<input style="width:70%;" type="text" name="comTel" id="comTel" value="${company.comTel}" placeholder="${company.comTel}">
				</p>
				<p>
					<i class="material-icons">map</i><span>주소</span> 
					<input style="width:70%;" type="text" name="comAddr" id="comAddr" value="${company.comAddr}" placeholder="${company.comAddr}">
				</p>
				<input type="button" id="companyUpdateBtn" value="정보수정">
				<!-- <a href="companyDelete.jsp">계정삭제</a> -->
			</form>
		</div>
		
		<!-- 탭 리스트 -->
		<h2>거래 내역</h2>
		<ul class="tabs">
			<li class="tab"><a href="#orderTab">주문 받은 내역</a></li>
			<li class="tab"><a href="#requestTab">의뢰 받은 내역</a></li>
		</ul>
		
		<!-- 탭 내용들 -->
		<div id="orderTab">
			<c:if test="${not empty custOrderList}">
				<ul class="collapsible">
					<c:forEach items="${custOrderList}" var="custOrder">
						<li>
							<div class="collapsible-header">
								<span style="font-weight: bold; margin-right: 60px; margin-left: 10px;">${custOrder.serviceName}</span>
								<span>주문자 : ${custOrder.custEmail}</span>
								<span style="margin-left: 60px;">주문날짜 : ${custOrder.orderSysdate}</span>
								<span style="margin-left: 120px;">${custOrder.orderCondition}</span>
							</div>
							
							<style>
								.orderApprove, .orderRejectForm>input[type=submit] {
									border: 1px solid orange;
									background: none;
									color: orange;
									padding: 8px;
									cursor: pointer;
									border-radius: 5px;
									margin-top: 10px;
								}
								.orderApprove:hover, .orderRejectForm>input[type=submit]:hover {
									background: orange;
									color: white;
									transition-duration: 1.2s;
								}
								.orderApproveForm input[type=submit] {
									border: 1px solid orange;
									background: none;
									color: orange;
									padding: 8px;
									cursor: pointer;
									border-radius: 5px;
									margin-top: 10px;
								}
								.orderApproveForm input[type=submit]:hover {
									background: orange;
									color: white;
									transition-duration: 1.2s;
								}
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
								.orderRejectForm {
									display: inline-block;
								}
								.orderApproveForm {
									background: none;
								}
								.orderApproveForm h4 {
									font-size: 1.2rem;
									font-weight: bold;
								}
								.orderFinalView {
									background: none;
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
						      			<!-- 주문 대기 일때는 주문 받기와 거절이 있다  -->
						      			<c:if test="${custOrder.orderCondition == '주문대기'}">
											<button class = "orderApprove" onclick = "orderApproveFormOpenClose()">주문받기</button>&nbsp;&nbsp;&nbsp;
											<div class = "orderApproveForm">
												<h4>주문받기</h4>
												<form action="orderApprove.do?" name="orderApproveForm" >
													<input type="hidden" name="orderCode" value="${custOrder.orderCode}">
													<input type="hidden" name="serviceCode" value="${custOrder.serviceCode}">
													<input type="text" name="serviceName" value="${custOrder.serviceName}" readonly="readonly">
													<input type="text" name="custEmail" value="${custOrder.custEmail}" readonly="readonly">
													최종금액 : <input type="text" name="totalPrice" required="required"><br><br>
													최종요구사항내역 : <input type="text" name="finalDesc" required="required"><br><br>
													<input type="submit" value="주문 최종완료">
												</form>
											</div>
											
											<form action="" class="orderRejectForm" >
												<input type="hidden" name="orderCode" value="${custOrder.orderCode}">
												<input align="center" onclick ="orderReject()" type="submit" value="주문반려하기 ">
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
		
		<style>
			.requestApprove, .requestRejectForm>input[type=submit] {
				border: 1px solid orange;
				background: none;
				color: orange;
				padding: 8px;
				cursor: pointer;
				border-radius: 5px;
				margin-top: 10px;
			}
			.requestApprove:hover, .requestRejectForm>input[type=submit]:hover {
				background: orange;
				color: white;
				transition-duration: 1.2s;
			}
			.requestApproveForm input[type=submit] {
				border: 1px solid orange;
				background: none;
				color: orange;
				padding: 8px;
				cursor: pointer;
				border-radius: 5px;
				margin-top: 10px;
			}
			.requestApproveForm input[type=submit]:hover {
				background: orange;
				color: white;
				transition-duration: 1.2s;
			}
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
			.requestRejectForm {
				display: inline-block;
			}
			.requestApproveForm {
				background: none;
			}
			.requestApproveForm h4 {
				font-size: 1.2rem;
				font-weight: bold;
			}
			.requestFinalView {
				background: none;
			}
		</style>
		<!-- request!!!! -->
		<div id="requestTab">
			<c:if test="${not empty custRequestList}">
				<ul class="collapsible">
					<c:forEach items="${custRequestList}" var="custRequest">
						<li>
							<div class="collapsible-header">
								<span style="width: 30%; margin-left: 10px;">의뢰자 : ${custRequest.custEmail}</span>
			        			<span>의뢰날짜 : ${custRequest.requestSysdate}</span>
							</div>
							
							<!-- 자세히보기 -->	        
					        <div class="collapsible-body orderdetail">
				      			<h6>의뢰내용</h6>
				      			<p>희망일시 : ${custRequest.requestRevdate}</p>
				      			<p>희망장소 : ${custRequest.requestPlace}</p>
				      			<p>예산 : ${custRequest.requestBudget}</p>
				      			<p>부가사항 : ${custRequest.requestRequire}</p>
				      			
				      			<!-- 의뢰받기 -->
				      			<button class = "requestApprove" onclick = "requestApproveFormOpenClose()">의뢰받기</button>&nbsp;&nbsp;&nbsp;
								<div class = "requestApproveForm">
									<h4>의뢰받기</h4>
									<form action="requestApprove.do?" name="requestApproveForm" >
										<input type="hidden" name="requestCode" value="${custRequest.requestCode}">
										<input type="text" name="custEmail" value="${custRequest.custEmail}" readonly="readonly">
										최종금액 : <input type="text" name="totalPrice" required="required"><br><br>
										최종요구사항내역 : <input type="text" name="finalDesc" required="required"><br><br>
										<input type="submit" value="의뢰 최종완료">
									</form>
								</div>
								
								<form action="" class="orderRejectForm" >
									<input type="hidden" name="orderCode" value="${custOrder.orderCode}">
									<input align="center" onclick ="orderReject()" type="submit" value="의뢰반려하기 ">
								</form>
				      			
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
							
							<!-- 자세히보기 -->
							<c:forEach items="${custOrderDetailList}"  var="custOrderDetail">
								<c:if test="${custOrder.orderCode == custOrderDetail.orderCode}" >
									<div class="collapsible-body orderdetail">
										
										
										<p>희망일시 : ${custOrderDetail.orderRevdate}</p>
						      			<p>희망장소 : ${custOrderDetail.orderPlace}</p>
						      			<p>예산 : ${custOrderDetail.orderBudget}</p>
						      			<p>부가사항 : ${custOrderDetail.orderRequire}</p>
						      			
									</div>
								</c:if>
							</c:forEach>
						</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
	</section>
	
	<jsp:include page = "../footer.jsp" />
</body>
</html>