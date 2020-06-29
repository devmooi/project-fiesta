<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		//localStorage에 저장 - 리스트 목록에서 업체를 클릭했을시 : key - fiesta_업체 코드, value - 업체 이미지, 업체 타이틀 정도?
		localStorage.setItem('fiesta_myCat', 'Tom, Scott');
	
		//localStorage에 값 가져오기
		var cat = localStorage.getItem('fiesta_myCat').split(", ");
		console.log(cat[0]);
		console.log(cat[1]);
		
		//localStorage 값 삭제
		localStorage.removeItem('fiesta_myCat');
		
		//localStorage 순회하기
		for(let i=0; i<localStorage.length; i++) {
		  let key = localStorage.key(i);
		  alert(`${key}: ${localStorage.getItem(key)}`);
		}
		
		// innerHTML - 화면에 뿌리기
		document.getElementById("result").innerHTML = localStorage.getItem("lastname");
	</script>
</head>
<body>
	LocalStorage TEST
</body>
</html>