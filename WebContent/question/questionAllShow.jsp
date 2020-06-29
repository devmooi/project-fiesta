<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
	    .question {
	        display: block;
	        color: black;
	        margin: auto;
	        width: 1080px;
	        box-sizing: border-box;
	    }
	    .question:hover {
	        color: red;
	    }
	    .question span {
	        display: inline-block;
	    }
	    .question span:nth-child(1) {
	        width: 15%;
	    }
	    .question span:nth-child(2) {
	        width: 15%;
	    }
	    .question span:nth-child(3) {
	        width: 30%;
	    }
	    .question span:nth-child(4) {
	        width: 20%;
	    }
	</style>
</head>
<body>

<%-- <table border="2" width="350" bgcolor="yellow" align="center">
	<c:forEach items="${list}" var="question">
		<tr onClick = "questionView.do?id="+${question.qCode}>
			<td>${question.qCode}</td>
			<td>${question.qDate}</td>
			<td>${question.qTitle}</td>
			<td>${question.qDesc}</td>
			<td>${question.qCondition}</td>
		</tr>
	</c:forEach>
</table> --%>


 
<%-- <!-- 고객입장 -->
<h3 align="center">나의 문의내역들</h3><p>
<c:forEach items="${list}" var="question">
	<a href="questionView.do?qCode=${question.qCode}" class="question">
	    <span>${question.qCode}</span>
	    <span>${question.qDate}</span>
	    <span>${question.qTitle}</span>
	    <span>${question.qDesc}</span>
	    <span>${question.qCondition}</span>
	    <a href="questionDelete.do?qCode=${question.qCode}">삭제</a>
	</a>
</c:forEach>  --%>


<!-- 고객입장 -->
<h3 align="center">나의 문의내역들</h3><p>
<c:forEach items="${list}" var="question">
		<div>
	    <span>${question.qCode}</span>
	    <span>${question.qDate}</span>
	    <span>${question.qTitle}</span>
	    <span>${question.qDesc}</span>
	    <span>${question.qCondition}</span>
	    <a href="questionDelete.do?qCode=${question.qCode}">삭제</a>
	    </div>
</c:forEach> 


<!-- 회사입장 -->
<%-- <h3 align="center">우리회사에 들어온 문의내역들</h3><p>
<c:forEach items="${list}" var="question">
	<a href="questionView.do?qCode=${question.qCode}" class="question">
	    <span>${question.qCode}</span> 
	    <span>${question.qDate}</span> 
	    <span>${question.qTitle}</span> 
	    <span>${question.qDesc}</span> 
	    <span>${question.qCondition}</span> 
	</a>
	<a href="answerRegister.jsp?qCode=${question.qCode}">답변하기</a>
</c:forEach> --%>
</body>
</html>