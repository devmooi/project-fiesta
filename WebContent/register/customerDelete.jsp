<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(function() {
	$('#deleteBtn').click(function(){
		$.ajax({
			type:'post',
			url:'deleteCustomer.do',
			data:'email=${customer.custEmail}&&pass=${customer.custPass}',
			
			success:function(result) {
				alert("정상적으로 계정삭제되었습니다.");
				location.href="../index.jsp";
			}
		}); // ajax
	}); // click
}); // ready

</script>
</head>
<body>
<c:import url="http://localhost:8888/Fiesta/header.jsp" charEncoding="UTF-8"></c:import>

정말로 계정을 삭제하고 싶으세요?<br>
계정을 삭제하면 모든 개인정보가 삭제됩니다.<br>

<!-- 
[?] 계정삭제 이유..를 어디로 보내지? 이건 따로 어디서 다루기 때문에 받는 것 같은데 음.. 
    이건 deleteComponent로 갈 필요가 없잖아. 어디로 보낼까? 음..
    
삭제버튼 누르면, 뭐 더 붇지도 않고. 그냥 다시 main화면으로 간다. 이걸 어떻게 표현할까 음..
현재 dao는, email과 pass를 인자로 받는다고 되어있는데.. 이럴 필요 없지 않나?
어차피 로그인 되어 있는 상태니까. 음.

아닌가? email과 pass만 보고 나머지는 볼 필요도 없다는 것이 저 인자값의 의미인가?
 -->
 
계정삭제이유(선택)<br>
<input type="text" name="deleteDesc" id="deleteDesc"><br>

<form action="deleteCustomer.do" id="deleteFrm">
	<input type="checkbox" required="required"> ${customer.custName}님의 계정을 삭제하는 것을 확인하였습니다. <br>
	<input type="button" value="계정삭제" id="deleteBtn">
	<button type="button" onclick="location.href='customerMypage.jsp'">취소</button> <!-- 오.. onclick하면 걍 가네 오.. -->
</form>
<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>	
</body>
</html>
