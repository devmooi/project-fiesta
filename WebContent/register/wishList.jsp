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
<c:import url="http://localhost:8888/Fiesta/header.jsp" charEncoding="UTF-8"></c:import>
<h3 align="center">나의 위시 리스트</h3><p>
<table border="2" width="350" bgcolor="yellow" align="center">
	<c:forEach items="${wishlist}" var="wish">
		<tr>
			<td>${wish.wishCode}</td>
			<td>${wish.comName}</td>
			<td>${wish.comDesc}</td>
			<td><a href="wishDelete.do?wishCode=${wish.wishCode}">삭제</a></td>
		</tr>
	</c:forEach>
</table>
<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>

