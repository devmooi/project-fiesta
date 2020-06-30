<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 업체와 직접 상담 or 직접 입력 (가격대 또는 가격) -->
	3. 대략적인 예산을 공유해주세요.
	<form action="customerRequest.do" id="budgetFrm">
		<input type="radio" name="three" id="three"> 1000만원 이상
		<input type="radio" name="three" id="three" value="others">기타
		<input type="text" name="three" id="three" placeholder="직접 입력">
    <button><a href="2_requestPlace.jsp">이전</a></button><input type="submit" value="다음">
    </form>
</body>
</html>