<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiesta - 예산 선택</title>
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
	input[type=text] {
		width: 150px !important;
	}
	span {
		font-weight: bold;
		font-size: 1.2rem;
		margin-left: 10px;
		margin-right: 10px;
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
	  <progress value="75" max="100"></progress><br>
	  <!-- 업체와 직접 상담 or 직접 입력 (가격대 또는 가격) -->
	  <h2>3. 대략적인 예산을 공유해주세요.</h2>
	  <c:if test="${not empty requestFiesta}">
		  <form action="customerRequest.do" id="budgetFrm">
		    <input type="hidden" name="command" value="budget">
	        <input type="hidden" name="requestFiesta" value="${requestFiesta}">
			<br><br>
			<div>
				<input type="text" name="fromBudget" id="fromBudget" placeholder="숫자만 입력가능">
				<span>~</span>
				<input type="text" name="toBudget" id="toBudget" placeholder="숫자만 입력가능">
				<span>만원대</span>
			</div>
		    <button id="lastBtn" onClick="history.go(-1)">이전</button>
		    <button type="submit" id="nextBtn">다음</button>
	      </form>
      </c:if>
      <c:if test="${not empty companycode}">
      	<style>
       		section .backgroundBox {
				background-image: url('../Fiesta/resource/img/customerRequest.jpg');
			}
       	</style>
      	<form action="customerOrderFrom.do" id="budgetFrm">
		    <input type="hidden" name="command" value="budget">
	        <input type="hidden" name="companycode" value="${companycode}">
            <input type="hidden" name="serviceCode" value="${serviceCode}">
			<br><br>
			<div>
				<input type="text" name="fromBudget" id="fromBudget" placeholder="숫자만 입력가능">
				<span>~</span>
				<input type="text" name="toBudget" id="toBudget" placeholder="숫자만 입력가능">
				<span>만원대</span>
			</div>
		    <button id="lastBtn" onClick="history.go(-1)">이전</button>
		    <button type="submit" id="nextBtn">다음</button>
	      </form>
      </c:if>
    </div>
</section>
</body>
</html>