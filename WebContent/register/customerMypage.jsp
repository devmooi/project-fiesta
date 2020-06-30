<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	/* input[type="radio"] {display:none;}
	input[type="radio"] + label {display:inline-block; background:#ccc;color:#999; cursor:pointer;}
	input[type="radio"]:checked + label {background:#aaa;color:#000;}
	
	.contents {display:none;}
	input[id="order"]:checked ~ .order {display:block;}
	input[id="request"]:checked ~ .request {display:block;} */
	
	section a { display:block; }
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
</script>
</head>
<body>
    <c:import url="http://localhost:8888/Fiesta/header.jsp" charEncoding="UTF-8"></c:import>

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
	

	<section>
		<h2>거래 내역  </h2>
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
		    		
		<div id="orderTab" class="col s12">
		  <h6 align="center">주문내역</h6>
		  <br>
		    <ul class="collapsible">
		    <c:forEach items="${orderList}" var="order">
		      <li>
		        <div class="collapsible-header">
		        	<span>${order.orderSysdate}</span>
		        	<span>${order.orderRevdate}</span>
		        	<span>${order.orderPlace}</span>
		        	<span>${order.orderBudget}</span>
		        	<span>${order.orderRequire}</span>
		        	<span>${order.orderCondition}</span>
		        </div>	
		      <c:forEach items="${orderDetailList}" var="orderDetail">
		        <div class="collaps-body orderDetail">
		          <h6 align="center">주문 상세 내용</h6>
		            <span>${orderDetail.custdetailDesc}</span></div>
		      </c:forEach>
	          </li>
	        </c:forEach>
	      </ul> 	
	    </div>			  
	  
	  
	  <div id="requestTab" class="col s12">
		  <h6 align="center">의뢰내역</h6>
		  <br>
		    <ul class="collapsible">
		    <c:forEach items="${requestList}" var="order">
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
	  </div>			
	 
	  
	  
	  
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