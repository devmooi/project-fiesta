<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input[type="radio"] {display:none;}
	input[type="radio"]:checked + label {background:#aaa;color:#000;}
	
	.contents {display:none;}
	input[id="order"]:checked ~ .order {display:block;}
	input[id="request"]:checked ~ .request {display:block;}
	
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
	
	$('#showAllCustOrder').click(function() {
		$.ajax({
			type:'post',
			url:'showAllCustOrder.do',
			data:"",
			// 나는, 비동기를 통해 저 url을 호출만할꺼야. business logic 돌릴려고. 그 돌린 결과를 그냥 단순히 뿌리려고.
			// 그러면 그냥 data 생략하고 가면 안되는거야? 아니면 비동기를 쓰면 안되는거야? 음..
			// "" 넘기니까 일단 동작. ok.
			
			success:function(result) {
				$('#showAllCustOrderView').html(result);		
			}
		}); // ajax
	}); // click
	
	$('#showAllCustRequest').click(function() {
		$.ajax({
			type:'post',
			url:'showAllCustRequest.do',
			data:"",
			
			
			success:function(result) {
				$('#showAllCustRequestView').html(result);	
			}
		}); // ajax
	}); // click  
}); // ready
</script>
</head>
<body>
	<!-- 업체 / 고객 구분 :  c:if  사용  -->
	<h2>개인 정보 수정</h2> 
    <form action="updateCustomer.do" id="customerUpdateFrm">
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
	
	<h2>거래 내역</h2>
	<!-- attribute 저장한 내용을 뿌려주는... 모달로 뿌리는 것도 추가.. 어떤 컬럼을 보여줄건지 -->
	
	<!-- 내가 하고자 하는 건, tab이 이동할 때, 함수만 호출하는건데. 내가 원하는 함수만 호출하는건데. 즉,
	html 상에서 함수호출 할 수 있으면 그냥 끝나는건데. 음.
	
	아니 근데, 그 함수를 호출해서, return값을 결국 여기에, 같은 페이지에 뿌려야하는거니까, 비동기가 맞지 않나?
	html 상에서 함수를 호출한다 해도, return은 어디로 될지 모르는거잖아? 음..-->
	 <div class="tab_content">
		<input type="radio" name="type" id="order" checked>
		<label for="order" id="showAllCustOrder"> 주문 내역 
			location.href='showAllCustOrder.do';
		</label>
			
		<input type="radio" name="type" id="request">
		<label for="request" id="showAllCustRequest"> 의뢰 내역 
			<span id="showAllCustRequestView"></span><br>
		</label>	
				
		 <div class="contents order" id="showAllCustOrderDetail"> 
		 	주문 내역 내용
			주문 내용
		</div><br>
		<div class="contents request" id="showAllCustRequestDetail"> 
			의뢰 내역 내용
			의뢰 내용 
		</div><br>		
	</div>
 
 
 
 
	<!-- 아예 버튼으로 만들어버릴 생각을..   
	  근데 이렇게 해도, 한쪽이 눌렸을 때 한쪽이 사라지는 것은 구현 못함. 음.............
	
		<input type="button" value="주문 내역" name="showAllCustOrder" id="showAllCustOrder"><br>
		<span id="showAllCustOrderView"></span><br>
		<input type="button" value="의뢰 내역" name="showAllCustRequest" id="showAllCustRequest">
		<span id="showAllCustRequestView"></span><br>
	-->
		
				
		<!--  <div class="contents order" id="showAllCustOrderDetail"> 
		 	주문 내역 내용
			주문 내용
		</div><br>
		<div class="contents request" id="showAllCustRequestDetail"> 
			의뢰 내역 내용
			의뢰 내용 
		</div><br>		 -->
		
	<!-- 리뷰하기.. -->
	

</body>
</html>