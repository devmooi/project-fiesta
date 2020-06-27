<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

$(function() {
	// 각 입력창에 update'하고자' 입력한 값들은, register와 똑같은 정제가 필요
	// 수정 key를 누르면, 새로운 값을 입력하는 란이 나오고 ====> 이걸 구현해야하는데 음...
	// 입력함과 동시에, 다른 click없이, 정제됐는지 확인. 그러므로 keyup인 것도 그대로 씀 ok.
	

	$('#name').keyup(function() {
		var name = $('#name').val();
		if (name.length == 0) {
			$('#nameCheckView').html("이름을 입력해주세요.");
			return;
		} 
	}); // name keyup
	$('#email').keyup(function() {
		var email = $('#email').val();
		if (email.length == 0) {
			$('#emailCheckView').html("이메일을 입력해주세요.");
			return;
		} 
		$.ajax({
			type:'post',
			url:'custEmailExist.do',
			data:$('#updateFrm').serialize(),
			
			success:function(result) {
				if(result=='true')
					$('#emailCheckView').html("이미 사용중인  이메일입니다.");
				else
					$('#emailCheckView').html("사용 가능한 이메일입니다.");
			}
		}); // email ajax
	}); // email keyup
	$('#pass').keyup(function() {
		var pass = $('#pass').val();
		if (pass.length < 8) {
			$('#passCheckView').html("비밀번호는 8자 이상 입력해주세요.");
			 return;
		}
	}); // pass keyup
	
	// button과 입력창의 결합(!)을 위한 click function..!
	// 각 수정 버튼을 누르면, nameBtn 등으로 네이밍한 값들이 updateComponent로 넘어갈 것. 거기서 처리할 것.
	/* $('#nameBtn').click(function() {
		name = $('#name').val();
		var nameBtn = $('#nameBtn').val();
		nameBtn = name;
		$('#nameCheckView').html(nameBtn+"으로 수정되었습니다.");

	}); // name click */
	
	// 각각의 수정버튼을 구현하고자 하며 business logic을 생각해보니, 
	// updateCustomer()는 customer를 인자로 받아.. 이 말은, 이렇게 개별수정은 불가능하고
	// 통째로 들어오고 나서야 진행 가능하다는 소리 아닌가? 난 이렇게 이해해서, 개별수정을 잠시 보류.	
	/* $('#nameBtn').click(function() {
		var name = $('#name').val();
		alert(name);
		$.ajax({
			type:'post',
			url:'updateCustomer.do',
			data:$('#nameUpdateFrm').serialize(),
			
			success:function(result) {
				$('#nameCheckView').html(name+"으로 수정되었습니다.");
			}
		}); // name ajax
		
	}); // name click */
	
	$('#updateBtn').click(function() {
		$.ajax({
			type:'post',
			url:'updateCustomer.do',
			data:$('#updateFrm').serialize(),
			
			success:function(result) {
				$('#updateCheckView').html("수정되었습니다.");
			}
		}); // ajax
	}); // click
}); // ready
</script>
</head>
<body>
	<h2>개인 정보 수정</h2> 	<!-- 업체 / 고객 구분 :  c:if  사용  -->
<!-- 	<form action="updateCustomer.do" id="nameUpdateFrm"> 개별수정 보류 
        그래서	<input type="button" id="nameBtn" value="수정">류도 일단 삭제        -->		
    <form action="updateCustomer.do" id="updateFrm">
	이름  <input type="text" name="name" id="name" value="${customer.custName}" placeholder="${customer.custName}"><br>
		<span id="nameCheckView"></span><br>	
	이메일  <input type="text" name="email" id="email" value="${customer.custEmail}" placeholder="${customer.custEmail}"><br>
		<span id="emailCheckView"></span><br>
	비밀먼호  <input type="password" name="pass" id="pass" value="${customer.custPass}" placeholder="${customer.custPass}"><br>
		<span id="passCheckView"></span><br>
	휴대전화번호  <input type="text" name="tel" id="tel" value="${customer.custTel}" placeholder="${customer.custTel}"><br>
		<span id="telCheckView"></span><br>
	단체명  <input type="text" name="group" id="group" value="${customer.custGroup}" placeholder="${customer.custGroup}"><br>
		<span id="groupCheckView"></span><br>	
	<input type="button" id="updateBtn" value="회원정보수정">
		<span id="updateCheckView"></span>	
	</form>

	




	
	<h2>주문 내역</h2>
	<!-- attribute 저장한 내용을 뿌려주는... 모달로 뿌리는 것도 추가.. 어떤 컬럼을 보여줄건지 -->
	
	<!-- 리뷰하기.. -->
	
<!--<form action="updateCompany.do">
	</form> -->
</body>
</html>