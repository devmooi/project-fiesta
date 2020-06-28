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
	<c:choose>
		<c:when test="${!empty customer.custEmail}">
			추천서비스
			${customer.custEmail}
			${company.comEmail}
			<a href="customerMypage.jsp">마이페이지 </a>
		</c:when>
		<c:otherwise>
			추천서비스
			${customer.custEmail}
			${company.comEmail}
			<a href="companyMypage.jsp">마이페이지 </a>		
		</c:otherwise>
	</c:choose>
	
	
	<a href="logout.do">로그아웃(기능 테스트를 위해 임시로 이곳에)</a>

</body>
</html>