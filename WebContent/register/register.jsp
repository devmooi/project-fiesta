<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fiesta - 회원가입</title>
	<link href="../resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
	<style>
		section h2 {
			font-size: 2rem;
			font-weight: bold;
			text-align: center;
		}
		section a {
			color: black;
		}
		footer {
			position: absolute;
			left: 0;
			bottom: 0;
			width: 100%;
		}
		#register {
			display: flex;
			margin-top: 50px;
			min-height: 50vh;
		}
		#register h3 {
			font-size: 1.5rem;
		}
		#register a {
			width: 50%;
			text-align: center;
			border: 1px solid #ddd;
			margin: 60px;
			cursor: pointer;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		#register a:hover {
			border: 1px solid #009688;
			color: white;
			background: #009688;
			transition-duration: 1s;
		}
	</style>
</head>
<body>
	<jsp:include page = "../header.jsp" />
	<section>
		<h2>회원가입</h2>
		<div id="register">
			<a href="companyRegister.jsp" id="companyRegister">
				<div>
					<h3>업체로 가입하기</h3>
					<p>더 많은 고객을 만나고 싶어요</p>
				</div>
				
			</a>
			<a href="customerRegister.jsp" id="customerRegister">
				<div>
					<h3>더 많은 업체를 만나고 싶어요</h3>
					<p>가입하고 업체한테 요청하기</p>
				</div>
			</a>
		</div>
	</section>
	<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>	
</body>
</html>

