<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	section {
		width: 2500px;
		margin: auto;
	}
	
	section h2 {
		font-size: 2rem;
		margin: 0;
		padding: 0;
		font-weight: bold;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	
	#backgroundImg {
	    background-image: url('../resource/img/customerRequest${requestFiesta}.png');
        background-size: contain;
        position: relative;
		min-height:20vh;
	}
	
	#blank {
		min-height:15vh;
	}
	
	#inputFrm {
		width: 550px;
		min-height:20vh;
		margin-top: 40px;
		margin-left: auto;
  		margin-right: auto;
	}
		
	#inputFrm button {
		background: transparent;
		border: 1px solid #009688;
		margin: 3px;
		font-weight: bold;
		cursor: pointer;
		padding: 8px 15px;
		color: #009688;
		border-radius: 5px;
	}
	
	#inputFrm button:hover {
		color: white;
		background: #009688;
		border: 1px solid #009688;
	}
	
	progress {
		width: 550px;
		margin: auto;
	}
	
	 progress {
  	    appearance: none;
    	-moz-appearance: none;
    	-webkit-appearance: none;
	}
	
	progress::-webkit-progress-value {
    	background: #009688;
	}
	
	progress::-webkit-progress-bar {
    	background: lightgray;
	}
	
</style>
</head>
<body>
<!-- header import -->
<jsp:include page = "../header.jsp" />
<!-- 항상 section에서 시작 -->
<section>
	<div id="backgroundImg">
	  <img alt="카테고리 대표사진" src="../resource/img/customerRequest${requestFiesta}.png"> 
	</div>
	<div id="inputFrm">
	  <progress value="100" max="100"></progress><br>
	  <!-- 최종적으로 session에 담긴 attribute랑 여기에 넣은 값들을 같이 보내기 -->
	  <h2>4. 관련 문의 및 희망 사항을 알려주세요.</h2>
	  <form action="customerRequest.do" id="requireFrm">
		<input type="hidden" name="command" value="require">
	    <input type="hidden" name="requestFiesta" value="${requestFiesta}">
	    <textarea id="desc" name="desc" placeholder="ex.이전에 진행했던 행사가 담긴 사진이나 동영상을 보고 싶어요."></textarea>
	    
	    <button name="lastBtn" id="lastBtn"><a href="3_requestBudget.jsp">이전</a></button>
	    <button type="submit" name="nextBtn" id="nextBtn">의뢰하기</button>
    	</form>
    </div>
	<div id="blank">
	
	</div>
</section>
<!-- footer import -->
<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>