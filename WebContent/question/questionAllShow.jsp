<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">나의 문의내역들</h3><p>
<table border="2" width="350" bgcolor="yellow" align="center">
	<c:forEach items="${list}" var="question">
		<tr onClick = "questionView.do?id="+${question.qCode}>
			<td>${question.qCode}</td>
			<td>${question.qDate}</td>
			<td>${question.qTitle}</td>
			<td>${question.qDesc}</td>
			<td>${question.qCondition}</td>
		</tr>
	</c:forEach>
</table>
<a href="AnswerRegister.do">답변하기</a>
</body>
</html>