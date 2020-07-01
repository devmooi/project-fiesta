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
	<progress value="75" max="100"></progress><br>
	
	<!-- 업체와 직접 상담 or 직접 입력 (가격대 또는 가격) -->
	3. 대략적인 예산을 공유해주세요.
	<form action="customerRequest.do" id="budgetFrm">
	<input type="hidden" name="command" value="budget">
    <input type="hidden" name="requestFiesta" value="${requestFiesta}">
    
		<br><br>
		<input type="text" name="fromBudget" id="fromBudget" placeholder="숫자만 입력해주세요.">만원<br>
		&nbsp;&nbsp;&nbsp;
		<input type="text" name="toBudget" id="toBudget" placeholder="숫자만 입력해주세요.">만원<br>
		
    <button><a href="2_requestPlace.jsp">이전</a></button><input type="submit" value="다음">
    </form>
</body>
</html>