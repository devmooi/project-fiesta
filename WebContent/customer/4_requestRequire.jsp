<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiesta - 희망 사항 선택</title>
<link href="../resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<style>

	section {
		min-height: 100vh;
		position: relative;
	}
	section .backgroundBox {
		background-image: url('../resource/img/customerRequest${requestFiesta}.jpg');
        height: 100%;
        min-height:45vh;
        background-size: cover;
        background-position: center;
	}
	section .blackBox {
		background: black;
        opacity: 50%;
        min-height:57vh;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
	}
	
	section h2 {
		font-size: 2rem;
		margin: 0;
		padding: 0;
		font-weight: bold;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	
	#inputFrm {
		position: absolute;
		z-index:99;
		top: 27vh;
		right: 20vh;
		background: white;
		border: 1px solid #ddd;
		padding: 30px;
		width: 612px;
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
	textarea {
		resize: none;
		height: 5rem !important;
		padding: 10px;
	}
	textarea:focus {
		outline: none !important;
		border-color: #009688;
	}
</style>
</head>
<body>
<!-- header import -->
<jsp:include page = "../header.jsp" />
<!-- 항상 section에서 시작 -->
<section>
	<div class="backgroundBox"></div>
 	<div class="blackBox"></div>
	<div id="inputFrm">
	  <progress value="100" max="100"></progress><br>
	  <!-- 최종적으로 session에 담긴 attribute랑 여기에 넣은 값들을 같이 보내기 -->
	  <h2>4. 관련 문의 및 희망 사항을 알려주세요.</h2>
	  <c:if test="${not empty requestFiesta}">
		  <form action="customerRequest.do" id="requireFrm">
			<input type="hidden" name="command" value="require">
		    <input type="hidden" name="requestFiesta" value="${requestFiesta}">
		    <br><br>
		    <textarea id="desc" name="desc" placeholder="ex.이전에 진행했던 행사가 담긴 사진이나 동영상을 보고 싶어요."></textarea>
		    
		    <button id="lastBtn" onClick="history.go(-1)">이전</button>
		    <button type="submit" id="nextBtn">의뢰하기</button>
	    	</form>
	    </c:if>
	    <c:if test="${not empty companycode}">
	    	<style>
	       		section .backgroundBox {
					background-image: url('../Fiesta/resource/img/customerRequest.jpg');
				}
	       	</style>
	    	<form action="customerOrderFrom.do" id="requireFrm">
				<input type="hidden" name="command" value="require">
			    <input type="hidden" name="companycode" value="${companycode}">
            	<input type="hidden" name="serviceCode" value="${serviceCode}">
			    <br><br>
			    <textarea id="desc" name="desc" placeholder="ex.이전에 진행했던 행사가 담긴 사진이나 동영상을 보고 싶어요."></textarea>
			    
			    <button id="lastBtn" onClick="history.go(-1)">이전</button>
			    <button type="submit" id="nextBtn">의뢰하기</button>
	    	</form>
	    </c:if>
    </div>
</section>
</body>
</html>