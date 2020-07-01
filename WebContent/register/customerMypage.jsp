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
	/* input[type="radio"] {display:none;}
	input[type="radio"] + label {display:inline-block; background:#ccc;color:#999; cursor:pointer;}
	input[type="radio"]:checked + label {background:#aaa;color:#000;}
	
	.contents {display:none;}
	input[id="order"]:checked ~ .order {display:block;}
	input[id="request"]:checked ~ .request {display:block;} */
	
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
	$('#custName').keyup(function() {
		var name = $('#custName').val();
		if (name.length == 0) {
			$('#custNameCheckView').html("이름을 입력해주세요.");
			return;
		} 
	}); // name keyup
	$('#custEmail').keyup(function() {
		var email = $('#custEmail').val();
		if (email.length == 0) {
			$('#custEmailCheckView').html("이메일을 입력해주세요.");
			return;
		} 
		$.ajax({
			type:'post',
			url:'custEmailExist.do',
			data:$('#customerUpdateFrm').serialize(),
			
			success:function(result) {
				if(result=='true')
					$('#custEmailCheckView').html("이미 사용중인  이메일입니다.");
				else
					$('#custEmailCheckView').html("사용 가능한 이메일입니다.");
			}
		}); // email ajax
	}); // email keyup
	$('#custPass').keyup(function() {
		var pass = $('#custPass').val();
		if (pass.length < 8) {
			$('#custPassCheckView').html("비밀번호는 8자 이상 입력해주세요.");
			 return;
		}
	}); // pass keyup
	
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
	
		// 아래의 두 함수가 핵심이었다..!
		$('.tabs').tabs();
		$('.collapsible').collapsible();
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

</script>
<style type="text/css">
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
	<!-- 업체 / 고객 구분 :  c:if  사용  -->
	<h2>개인 정보 수정</h2> 
    <form action="updafteCustomer.do" id="customerUpdateFrm">
	이름  <input type="text" name="custName" id="custName" value="${customer.custName}" placeholder="${customer.custName}"><br>
		<span id="custNameCheckView"></span><br>	
	이메일  <input type="text" name="custEmail" id="custEmail" value="${customer.custEmail}" placeholder="${customer.custEmail}"><br>
		<span id="custEmailCheckView"></span><br>
	비밀먼호  <input type="password" name="custPass" id="custPass" value="${customer.custPass}" placeholder="${customer.custPass}"><br>
		<span id="custPassCheckView"></span><br>
	휴대전화번호  <input type="text" name="custTel" id="custTel" value="${customer.custTel}" placeholder="${customer.custTel}"><br>
		<span id="custTelCheckView"></span><br>
	단체명  <input type="text" name="custGroup" id="custGroup" value="${customer.custGroup}" placeholder="${customer.custGroup}"><br>
		<span id="custGroupCheckView"></span><br>	
	<input type="button" id="customerUpdateBtn" value="회원정보수정">
	<span id="updateCustomerCheckView"></span>	
	</form>
	<a href="customerDelete.jsp">계정삭제</a>
	


		<h2>거래 내역  </h2>
<!-- 탭제목들 -->
		<div class="row">
		  <div class="col s12">
		    <ul class="tabs">
		      <li class="tab col s3"><a href="#orderTab">주문내역</a></li>
		      <li class="tab col s3"><a href="#requestTab">의뢰내역</a></li>
		      <li class="tab col s3"><a href="#questionTab">문의내역</a></li>
		      <li class="tab col s3"><a href="#wishTab">찜 내역</a></li>
		      <li class="tab col s3"><a href="#reviewTab">리뷰 내역</a></li>
		    </ul>
		  </div>
<!-- 탭내용들 -->	
		<!-- 주문내역 -->	
		<div id="orderTab" class="col s12">
		  <h6 align="center">주문받은내역</h6>
		  <br>
		    <ul class="collapsible">
		    <c:forEach items="${custOrderList}" var="custOrder">
		      <li>
		        <div class="collapsible-header">
			        <span>${custOrder.serviceCode}</span>&nbsp;&nbsp;&nbsp;&nbsp;
			        <span>${custOrder.serviceName}</span>&nbsp;&nbsp;&nbsp;&nbsp;
		        	<span>${custOrder.orderSysdate}</span>&nbsp;&nbsp;&nbsp;&nbsp;
		        	<span>${custOrder.orderCondition}</span>&nbsp;&nbsp;&nbsp;&nbsp;
		        </div>
		       	
		       	<!-- 자세히보기 -->	        
		        <c:forEach items="${custOrderDetailList}"  var="custOrderDetail">
					<c:if test="${custOrder.orderCode == custOrderDetail.orderCode}" >
			      		<div class="collapsible-body orderdetail">
			      			<h6>주문내용</h6>
			      			<c:set var="loop_flag" value="false" />
			      			<c:forEach items="${custOrderService}"  var="service">
			      				<c:if test="${not loop_flag }">
					      			<c:if test="${service.serviceCode == custOrderDetail.serviceCode}" >
					      				<span>서비스 코드 : ${service.serviceCode}</span><br>
						      			<span>서비스 이름 : ${service.serviceName}</span><br>
						      			<span><c:if test="${not empty service.serviceImg}">
											<td><img src= "../resource/file_upload/${service.serviceImg}" width=100 height=100></td><br>
											</c:if></span>
						      			<span>서비스 내용 : ${service.serviceDesc}</span><br>
						      			<c:set var="loop_flag" value="true" />
					      		</c:if>
				      			</c:if>
				      		</c:forEach>
			      			<span>희망일시 : ${custOrderDetail.orderRevdate}</span><br>
			      			<span>희망장소 : ${custOrderDetail.orderPlace}</span><br>
			      			<span>예산 : ${custOrderDetail.orderBudget}</span><br>
			      			<span>부가사항 : ${custOrderDetail.orderRequire}</span><br>
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
											최종금액 : ${finalDetail.custdetailTotalprice} <br><br>
											최종요구사항내역 : ${finalDetail.custdetailDesc} <br><br>
											최종거래완료날짜 : ${finalDetail.custdetailCompletedate} <br><br>
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
	    </div>
	    
	    
	    
	    			  
	  
	  <!-- 의뢰내역 -->	
	  <div id="requestTab" class="col s12">
		  <h6 align="center">의뢰내역</h6>
		  <br>
		    <ul class="collapsible">
		    <c:forEach items="${requestList}" var="request">
		      <li>
		        <div class="collapsible-header">
		        	<span>${request.requestSysdate}</span>
		        	<span>${request.requestRevdate}</span>
		        	<span>${request.requestPlace}</span>
		        	<span>${request.requestBudget}</span>
		        	<span>${request.requestRequire}</span>
		        	<span>${request.requestFiesta}</span>
		        </div>	
		      <c:forEach items="${requestDetailList}" var="orderDetail">
		        <div class="collaps-body orderDetail">
		          <h6>의뢰 상세 내용</h6>
		            <span>${requestDetail.detailDesc}</span></div>
		      </c:forEach>
	          </li>
	        </c:forEach>
	      </ul> 	
	    </div>
	    
	  <!-- 내가 문의한내역 -->	
	  <div id="questionTab" class="col s12">
		  <h6 align="center">내가 문의한 내역</h6>
		  <br>
		    <ul class="collapsible">
				 <c:forEach items="${questionList}" var="question">
				    <li>
				      <div class="collapsible-header">	<%-- <span>${question.qCode}</span>  --%>
													    <span>${question.qTitle}</span>&nbsp;&nbsp;&nbsp;&nbsp;
													    <span>${question.qDesc}</span>&nbsp;&nbsp;&nbsp;&nbsp;
													    <span>${question.qDate}</span>&nbsp;&nbsp;&nbsp;&nbsp;
													    <span>${question.qCondition}</span> </div>
													
							<c:forEach items="${questionDetail}"  var="qDetail">
							<c:if test="${qDetail.qCode == question.qCode}" >						   
							<div class="collapsible-body qdetail"><h6>문의내용</h6><span>${qDetail.qDesc}</span>
							<a href="questionDelete.do?qCode=${question.qCode}">삭제</a>
							</div>
							</c:if>
							</c:forEach>
				
							<c:if test="${question.qCondition eq '답변완료'}" >
							<c:forEach items="${answerList}"  var="answer">
								<c:if test="${answer.qCode == question.qCode}" >
					      		<div class="collapsible-body adetail"><h6>답변내용</h6><span>${answer.aDesc}</span>
					      										<span>${answer.aDate}</span>
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
	    </div>
	    
	<!-- 내가 찜한내역 -->	
		  <div id="wishTab" class="col s12">
			  <h6 align="center">내가 찜내역</h6>
			  <br>
			   <table border="2" width="350" bgcolor="yellow" align="center">
					<c:forEach items="${wishlist}" var="wish">
						<tr>
							<%-- <td>${wish.wishCode}</td> --%>
							<td>${wish.comName}</td>
							<td>${wish.comDesc}</td>
							<td><a href="wishDelete.do?wishCode=${wish.wishCode}&custEmail=${wish.custEmail}">삭제</a></td>
						</tr>
					</c:forEach>
				</table> 
		    </div>			  
		    	  
	  </div><!-- tab 전체닫기 div -->			
	 
	  
	  
	  
<%-- 	  <div id="orderTab" class="col s12">
		  <h6 align="center">문의내역</h6>
		  <br>
		    <ul class="collapsible">
		    <c:forEach items="${questionList}" var="question">
		      <li>
		        <div class="collapsible-header">
		        	<span>${question.qDate}</span>
		        	<span>${question.qTitle}</span>
		        	<span>${question.qCondition}</span></div>	
		        <div class="collaps-body orderDetail">
		          <h6>문의 상세 내용</h6>
		            <span>${question.qDesc}</span></div>
	          </li>
		    </c:forEach>
	      </ul> 	
	    </div>			  
	  
	  <div id="orderTab" class="col s12">
		  <h6 align="center">찜 내역</h6>
		  <br>
		    <ul class="collapsible">
		    <c:forEach items="" var="">
		      <li>
		        <div class="collapsible-header">
				</div>	
		      <c:forEach items="" var="">
		        <div class="collaps-body orderDetail">
		          <h6>주문 상세 내용</h6>
		        </div>
		      </c:forEach>
	          </li>
		    </c:forEach>
	      </ul> 	
	    </div>	
	    
	    <div id="reviewTab" class="col s12">
		  <h6 align="center">리뷰 내역</h6>
		  <br>
		    <ul class="collapsible">
		    <c:forEach items="" var="">
		      <li>
		        <div class="collapsible-header">
				</div>	
		      <c:forEach items="" var="">
		        <div class="collaps-body orderDetail">
		          <h6>주문 상세 내용</h6>
		        </div>
		      </c:forEach>
	          </li>
		    </c:forEach>
	      </ul> 	
	    </div>	 --%>	    
	    		 
	    		   
    </section>
		
	
    <c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>