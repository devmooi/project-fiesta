<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">서비스</h3><p>
<table border="2" width="350" bgcolor="yellow" align="center">
	<c:forEach items="${list}" var="service">
		<tr>
			<td>${service.serviceName}</td>
			<td>${service.serviceImg}</td>
			<td>${service.serviceDesc}</td>
			<td>${service.serviceTag}</td>
		</tr>
	</c:forEach>
</table>
<a href="ServiceDelete.do?serviceCode=3">삭제</a>
</body>
</html>