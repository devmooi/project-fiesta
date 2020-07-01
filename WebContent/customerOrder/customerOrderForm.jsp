<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<h1>주문서</h1>
서비스 이름 : ${service.serviceName}
<c:if test="${not empty service.serviceImg}">
<td><img src= "../resource/file_upload/${service.serviceImg}" width=100 height=100></td>
</c:if>
<c:if test="${empty service.serviceImg}">
<td>이미지 없음</td>
</c:if>
서비스 태그 : ${service.serviceDesc}
<form action="customerOrderFrom.do?" name="orderForm" > 
<input type="hidden" name="companycode" value="${companycode}"> <!-- 회사코드 -->
<input type="hidden" name="serviceCode" value="${serviceCode}">  <!-- 서비스코드 -->
희망일시 : <input type="text" name="orderRevdate" required="required"><br><br>
희망장소 : <input type="text" name="orderPlace" required="required"><br><br>
예산 : <input type="text" name="orderBudget" required="required"><br><br>
부가사항 : <input type="text" name="orderRequire" required="required"><br><br>
<input type="submit" value="서비스 주문 신청">
</form>
</body>
</html>