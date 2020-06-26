<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>문의내역</h3>
문의번호 : ${qDetail.qCode} <br>
문의 날짜 : ${qDetail.qDate} <br>
문의 제목 : ${qDetail.qTitle} <br>
문의 내용 : ${qDetail.qDesc} <br>
<a href="answerRegister.jsp?qCode=${qDetail.qCode}">답변하기</a>
<br><br>

<h3>답변내역</h3>
답변 날짜 : ${aDetail.aDate}<br>
답변 내용: ${aDetail.aDesc}<br>
답변회사이름 : ${aDetail.comName}<br>

</body>
</html>