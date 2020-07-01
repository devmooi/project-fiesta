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
	input[type="radio"]:checked + label {background:#aaa;color:#000;}
	
	.contents {display:none;}
	input[id="order"]:checked ~ .order {display:block;}
	input[id="request"]:checked ~ .request {display:block;}
	
    .orderdetail{
   		background-color: #FAF4C0;
   	}
   	
   	.orderApproveForm {
      	display: none;
      	background-color: #CEFBC9;
    }
	
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(function() {
	$('.tabs').tabs();

	$('.collapsible').collapsible();
	
	$('#comName').keyup(function() {
		var name = $('#comName').val();
		if (name.length == 0) {
			$('#comNameCheckView').html("업체명을 입력해주세요.");
			return;
		} 
	}); // name keyup
	$('#comEmail').keyup(function() {
		var email = $('#comEmail').val();
		if (email.length == 0) {
			$('#comEmailCheckView').html("이메일을 입력해주세요.");
			return;
		} 
		$.ajax({
			type:'post',
			url:'comEmailExist.do',
			data:$('#companyUpdateFrm').serialize(),
			
			success:function(result) {
				if(result=='true')
					$('#comEmailCheckView').html("이미 사용중인  이메일입니다.");
				else
					$('#comEmailCheckView').html("사용 가능한 이메일입니다.");
			}
		}); // email ajax
	}); // email keyup
	$('#comPass').keyup(function() {
		var pass = $('#comPass').val();
		if (pass.length < 8) {
			$('#comPassCheckView').html("비밀번호는 8자 이상 입력해주세요.");
			 return;
		}
	}); // pass keyup
	
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

//주문하기창 열고닫기
function orderApproveFormOpenClose() {
      if ( $('.orderApproveForm').css('display') == 'none' ) {
        $('.orderApproveForm').show();
        /*  $('#answerForm').text('박스 닫기')*/
      } else {
        $('.orderApproveForm').hide();
        /*$('#answerForm').text('박스 열기')*/
      }
}


</script>
</head>
<body>

	<jsp:include page = "../header.jsp" />
	<section></section>
	<h2>업체 정보 수정</h2>
	<form action="updateCompany.do" id="companyUpdateFrm">
	업체명  <input type="text" name="comName" id="comName" value="${company.comName}" placeholder="${company.comName}"><br>
		<span id="comNameCheckView"></span><br>	
	이메일  <input type="text" name="comEmail" id="comEmail" value="${company.comEmail}" placeholder="${company.comEmail}"><br>
		<span id="comEmailCheckView"></span><br>
	비밀먼호  <input type="password" name="comPass" id="comPass" value="${company.comPass}" placeholder="${company.comPass}"><br>
		<span id="comPassCheckView"></span><br>
	전화번호  <input type="text" name="comTel" id="comTel" value="${company.comTel}" placeholder="${company.comTel}"><br>
		<span id="comTelCheckView"></span><br>
	주소  <input type="text" name="comAddr" id="comAddr" value="${company.comAddr}" placeholder="${company.comAddr}"><br>
		<span id="comAddrCheckView"></span><br>
	업체로고등록  <input type="text" name="comImg" id="comImg" value="${company.comImg}" placeholder="${company.comImg}"><br>
		<span id="comImgCheckView"></span><br>
	업체상세정보  <input type="text" name="comDesc" id="comDesc" value="${company.comDesc}" placeholder="${company.comDesc}"><br>
		<span id="comDescCheckView"></span><br>
	업체분류 <select name="categoryCode" id="categoryCode">
			<option selected="selected">${company.comCategoryCode}</option>
			<option value=1>연예기획사</option>
			<option value=2>숙소</option>
			<option value=3>주류/렌탈</option>
			<option value=4>버스</option>
			<option value=5>음향조명</option>
			<option value=6>보험회사</option>
			<option value=7>푸드트럭</option>
			<option value=8>의류</option>
			<option value=9>현수막</option>
			<option value=10>협찬</option>		
		</select><br>
	<input type="button" id="companyUpdateBtn" value="업체정보수정">
	<span id="updateCompanyCheckView"></span>	
	</form>
	<a href="companyDelete.jsp">계정삭제</a>

<h4>거래내역</h4>
<!-- 탭제목들 -->
		<div class="row">
		  <div class="col s12">
		    <ul class="tabs">
		      <li class="tab col s3"><a href="#orderTab">주문받은내역</a></li>
		      <li class="tab col s3"><a href="#requestTab">의뢰받은내역</a></li>
		    </ul>
		  </div>
		  
<!-- 탭내용들 -->	
		<!-- 주문받은내역 -->	
		<div id="orderTab" class="col s12">
		  <h6 align="center">주문받은내역</h6>
		  <br>
		    <ul class="collapsible">
		    <c:forEach items="${custOrderList}" var="custOrder">
		      <li>
		        <div class="collapsible-header">
			        <span>${custOrder.serviceCode}</span>&nbsp;&nbsp;&nbsp;&nbsp;
			        <span>${custOrder.serviceName}</span>&nbsp;&nbsp;&nbsp;&nbsp;
			        <span>${custOrder.custEmail}</span>&nbsp;&nbsp;&nbsp;&nbsp;
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
						      			<span>서비스 태그 : ${service.serviceDesc}</span><br>
						      			<c:set var="loop_flag" value="true" />
					      		</c:if>
				      			</c:if>
				      		</c:forEach>
			      			<span>희망일시 : ${custOrderDetail.orderRevdate}</span><br>
			      			<span>희망장소 : ${custOrderDetail.orderPlace}</span><br>
			      			<span>예산 : ${custOrderDetail.orderBudget}</span><br>
			      			<span>부가사항 : ${custOrderDetail.orderRequire}</span><br>
			      			<!-- 주문 대기 일때는 주문 받기와 거절이 있다  -->
							<c:if test="${custOrder.orderCondition == '주문대기'}">
								<button class = "orderApprove" onclick = "orderApproveFormOpenClose()">주문받기</button>&nbsp;&nbsp;&nbsp;
								<div class = "orderApproveForm">
								<h4>주문받기</h4>
								<form action="orderApprove.do?" name="orderApproveForm" >
									<input type="hidden" name="orderCode" value="${custOrder.orderCode}">
									<input type="text" name="serviceCode" value="${custOrder.serviceCode}" disabled>
									<input type="text" name="serviceName" value="${custOrder.serviceName}" disabled>
									<input type="text" name="custEmail" value="${custOrder.custEmail}" disabled>
									최종금액 : <input type="text" name="finalPrice" required="required"><br><br>
									최종요구사항내역 : <input type="text" name="finalDesc" required="required"><br><br>
									<input type="submit" value="주문 최종완료">
								</form>
								</div>
								<button class = "orderReject" onclick = "orderReject()">주문반려하기</button>
							</c:if>
							<!-- 주문 승인완료 일때는 최종거래내역을 볼 수 있다 -->
							<c:if test="${custOrder.orderCondition == '주문승인완료'}">
				      			<a href="answerDelete.do?answerqCode=${answer.qCode}">최종내역보기</a>&nbsp;&nbsp;&nbsp;
							</c:if>
						</div>
		      		</c:if>
				</c:forEach>
	          </li>
	        </c:forEach>
	      </ul> 	
	    </div>			  
	  
	  <!-- 의뢰받은내역 -->	
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
	  </div><!-- tab 전체닫기 div -->			
	 
	

</body>
</html>