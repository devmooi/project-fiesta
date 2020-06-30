<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<!-- 최종적으로 session에 담긴 attribute랑 여기에 넣은 값들을 같이 보내기 -->
		4. 관련 문의 및 희망 사항을 알려주세요.
		<form action="customerRequest.do" id="finalFrm">
          	<input type="text" name="four" id="four" placeholder="ex.이전에 진행했던 행사가 담긴 사진이나 동영상을 보고 싶어요.">
          
    	<button><a href="3_requestBudget.jsp">이전</a></button><input type="submit" value="의뢰하기">
    	</form>
</body>
</html>