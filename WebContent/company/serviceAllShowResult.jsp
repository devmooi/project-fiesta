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
		
		/* $('.collapsible').collapsible(function() {
			var qCode = $('#qCode').val();
			if (email.length == 0) {
				$('#emailCheckView').html("이메일을 입력해주세요.");
				return;
			} 
			$.ajax({
				type:'post',
				url:'answerView.do',
				data:$('#registerFrm').serialize(),
				
				success:function(result) {
					if(result=='true')
						$('#emailCheckView').html("이미 사용중인  이메일입니다.");
					else
						$('#emailCheckView').html("사용 가능한 이메일입니다.");
				}
			}); // email ajax
		}); // email keyup */
		
		//$('.answer').;
		
	});//ready

    </script>
    
    
</head>
<body>
<h3>업체소개</h3>






<h3 align="center">서비스</h3><p>
<table border="2" width="350" bgcolor="yellow" align="center">
	<c:forEach items="${serviceList}" var="service">
		<tr>
			<td>${service.serviceName}</td>
			<td>${service.serviceImg}</td>
			<td>${service.serviceDesc}</td>
			<td>${service.serviceTag}</td>
		</tr>
	</c:forEach>
</table>
<a href="ServiceDelete.do?serviceCode=4">삭제</a>
<p></p>
<hr>
<div id="review">
	<div id="reviewInsert">
	<button name="reviewInsert">리뷰하기</button>
	</div>
	<div id="reviewScore">
	평점 : 
	</div>
	<hr>
	<div id="reviewContent">
	이름 평점 일시 리뷰개수<br>
	사진<br>
	리뷰 내용
	</div>
</div>



<%-- <!-- 문의내역 -->
 <ul class="collapsible">
 <c:forEach items="${questionList}" var="question">
    <li>
      <div class="collapsible-header">	<span>${question.qCode}</span> 
									    <span>${question.qDate}</span> 
									    <span>${question.qTitle}</span> 
									    <span>${question.qDesc}</span> 
									    <span>${question.qCondition}</span> </div>
									    
		<c:set var="loop_flag" value="false" />
		<c:forEach items="${answerList}"  var="answer">
		<c:if test="${not loop_flag }">
			<c:if test="${answer.aCode != null}" >
	      		<div class="collapsible-body"><span>${answer.aDesc}</span>
	      										<span>${answer.aDate}</span></div>
	      		<c:set var="loop_flag" value="true" />
      		</c:if>
      		
      		 <c:if test="${empty answer.aDesc}">
      			<div class="collapsible-body"><span>답변대기중입니다</span></div>
      			<c:set var="loop_flag" value="true" />
     	 	</c:if>
      	</c:if>
      	
      	

		</c:forEach>
    </li>
</c:forEach> 
</ul>
 --%>


<!-- 문의내역 -->
 <ul class="collapsible">
 <c:forEach items="${questionList}" var="question">
    <li>
      <div class="collapsible-header">	<span>${question.qCode}</span> 
									    <span>${question.qDate}</span> 
									    <span>${question.qTitle}</span> 
									    <span>${question.qDesc}</span> 
									    <span>${question.qCondition}</span> </div>
									    
		<c:set var="loop_flag" value="false" />
		<c:forEach items="${answerList}"  var="answer">
		<c:if test="${not loop_flag }">
			<c:if test="${question.qCondition eq '답변완료'}" >
	      		<div class="collapsible-body"><span>${answer.aDesc}</span>
	      										<span>${answer.aDate}</span></div>
	      		<c:set var="loop_flag" value="true" />
      		</c:if>
      		
      		 <c:if test="${question.qCondition eq '답변대기'}">
      			<div class="collapsible-body"><span>답변대기중입니다</span></div>
      			<c:set var="loop_flag" value="true" />
     	 	</c:if>
      	</c:if>


		</c:forEach>
    </li>
</c:forEach> 
</ul>


<%-- <!-- 문의내역 -->
 <ul class="collapsible">
 <c:forEach items="${questionList}" var="question">
    <li>
      <div class="collapsible-header">	<span>${question.qCode}</span> 
									    <span>${question.qDate}</span> 
									    <span>${question.qTitle}</span> 
									    <span>${question.qDesc}</span> 
									    <span>${question.qCondition}</span> </div>
									    
		<c:set var="loop_flag" value="false" />
		
		    		
      		 <c:when test="${question.qCondition eq '답변대기'}">
      			<div class="collapsible-body"><span>답변대기중입니다</span></div>
      			<c:set var="loop_flag" value="true" />
     	 	</c:when>
     	 	<c:otherwise>
     	 		<c:forEach items="${answerList}"  var="answer">
     	 		<c:if test="${not loop_flag }">  
     	 		<div class="collapsible-body"><span>${answer.aDesc}</span>
	      										<span>${answer.aDate}</span></div>
	      		<c:set var="loop_flag" value="true" />
	      		</c:if>
	      		</c:forEach>
     	 	</c:otherwise>
    </li>
</c:forEach> 
</ul>
 --%>





<%-- <!-- 문의내역 -->
 <ul class="collapsible">
 <c:forEach items="${questionList}" var="question">
    <li>
      <div class="collapsible-header">	<span>${question.qCode}</span> 
									    <span>${question.qDate}</span> 
									    <span>${question.qTitle}</span> 
									    <span>${question.qDesc}</span> 
									    <span>${question.qCondition}</span> </div>

     	<c:forEach items="${answerList}"  var="answer">
     		<c:when test="${answer.aCode != null}">
      			<div class="collapsible-body"><span>${answer.aDesc}</span>
      											<span>${answer.aDate}</span></div>
      		</c:when>
      			<c:otherwise>
      				<div class="collapsible-body"><span>답변대기중입니다</span></div>
				</c:otherwise>
		</c:forEach>
    </li>
</c:forEach> 
</ul> --%>

</body>
</html>