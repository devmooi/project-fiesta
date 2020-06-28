<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
    $(function() {
		$('button[name=reviewInsert]').click(function() {
			window.location.href="ShowService.do?companycode="+$('input[type=hidden]').val();
		});//button[name=reviewInsert] click
	});//ready
    </script>

    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script type="text/javascript">
   
	
    $(function() {
		$('button[name=reviewInsert]').click(function() {
			window.location.href="./review/insertReview.jsp";
		});//button[name=reviewInsert] click
		
		$('.collapsible').collapsible();
		
		$('#wishBtn').click(function() {
			var comCode = ${companycode};
			
			
			$.ajax({
				type:'post',
				url:'wishRegister.do',
				data:"comCode="+comCode,
				
				success:function(result) {
						//alert(comCode); 확인용
						alert("찜하기 성공");
				}
			}); // ajax
		}); // click

	});//ready
	
	function aOpenClose() {
	      if ( $('#answerForm').css('display') == 'none' ) {
	        $('#answerForm').show();
	        /*  $('#answerForm').text('박스 닫기')*/
	      } else {
	        $('#answerForm').hide();
	        /*$('#answerForm').text('박스 열기')*/
	      }
	}

    </script>
    
    <style type="text/css">
        #answerForm {
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
<!-- 업체 마이페이지로도 사용할 수 있음 -->
<!-- 업체 1개에 대한 정보가 들어옴 / 찜하기 -->
<h3>업체소개</h3>


<!-- 찜하기랑 목록 -->
<button id = "wishBtn">찜하기</button>
<a href="wishList.do">나의 찜 목록보기</a>

<br>
<h3 align="center">서비스</h3><p>
<table border="2" width="350" bgcolor="yellow" align="center">
	<c:forEach items="${serviceList}" var="service">
		<tr>
			<td>${service.serviceCode}</td>
			<td>${service.serviceName}</td>
			<td>${service.serviceImg}</td>
			<td>${service.serviceDesc}</td>
			<td>${service.serviceTag}</td>
			<td><a href="ServiceDelete.do?serviceCode=${service.serviceCode}">삭제</a></td>  <!-- 이거는 업체가 로그인했을때만 보일 것 -->
		</tr>
	</c:forEach>
</table>
<a href="serviceRegister.jsp?companycode=${companycode}">추가등록</a> <!-- 이거는 업체가 로그인했을때만 보일 것 -->
<p></p>
<hr>
<!-- 리뷰 / 문의 : 탭 방식 -->
<!-- 리뷰에 대한 답은 해당 고객만 -->
<!-- 나중에 '리뷰하기'는 마이페이지로 이동 -->
<div id="review">
	<div id="reviewInsert">
	<button name="reviewInsert">리뷰하기</button>
	<input type="hidden" name="companycode" value="${companycode}">
	</div>
	<div id="reviewScore">
	평점 : 
	<span>리뷰개수</span>
	</div>
	<hr>
	<c:forEach items="${list2}" var="review">
	<div id="reviewContent">
	<span>이름 : ${review.customer.custName}, </span><span>만족도 : ${review.reviewScore}, </span><span>일시 : ${review.reviewDate}</span><br>
	<span><img src= "${review.reviewImg}" width=100 height=100></span><br>
	<span>내용 : ${review.reviewDesc}</span><br>
	</div>
	<br>
	</c:forEach>
</div>
<hr>
<!-- 문의/답변하기 -->
<!-- 고객 문의, 업체 답변 -->

<!-- 문의내역 -->
<h4>문의내역</h4>
 <ul class="collapsible">
 <c:forEach items="${questionList}" var="question">
    <li>
      <div class="collapsible-header">	<span>${question.qCode}</span> 
									    <span>${question.qDate}</span> 
									    <span>${question.qTitle}</span> 
									    <span>${question.qDesc}</span> 
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
	      										<span>${answer.aDate}</span></div>
	      		</c:if>
	      	</c:forEach>
      		</c:if>
	
		<c:if test="${question.qCondition eq '답변대기'}">
      		<div class="collapsible-body" class="adetail"><span>답변대기중입니다</span>
      		<button class = "answerBtn" onclick = "aOpenClose()">답변하기</button><br>
      		<div id = "answerForm">
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

<br><br>

<hr>
<br>

<!-- 문의하기   >>> 나중에 탭으로 할것 -->  
<h4>문의하기</h4>
<form action="questionRegister.do?" name="registerForm" >
<input type="hidden" name="companycode" value="${companycode}">
문의제목 : <input type="text" name="qTitle" required="required"><br><br>
문의내용 : <input type="text" name="qDesc" required="required"><br><br>
<input type="submit" value="문의 등록">
</form>
<br><br>
<!-- 문의하기 등록은 바로 되는데 그페이지에서 새로고침 할때마다 계속 생김....ㅠㅅㅠ  
일단 탭으로 하면 바뀔수도 있으니 놔두기... 아니면 ajax이용해야할지듀...-->


<!-- 아직 미완성... 위로갈것같음  -->
<%-- <div id = answerForm>
<h4>답변하기</h4>
<form action="answerRegister.do" name="registerForm" >
<input type="hidden" name="qCode" value="${qDetail.qCode}">
답변내용 : <input type="text" name="aDesc" required="required"><br><br>
<input type="submit" value="답변 등록">
</form>
</div> --%>
<br><br>

</body>
</html>