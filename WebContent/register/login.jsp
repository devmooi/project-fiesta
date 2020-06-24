<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<style>
		* {
			margin: 0;
			padding: 0;
		}
		header {
			position: fixed;
			display: block;
			width: 100%;
			border-bottom: 2px solid black;
			height: 90px;
			background: white;
		}
		header h1 {
			float: left;
			margin-left: 20px;
		}
		header div {
			float: right;
			margin: 35px;
		}
		header div a {
			margin-right: 5px;
		}
		section {
			padding-top: 90px;
		}
	</style>
</head>
<body>
	<header>
		<h1>Fiesta</h1>
		<div>
			<a href="register/register.jsp">회원가입</a>
			<a href="companylist.do">업체 찾기</a>
			<a href="register/login.jsp">로그인</a>
		</div>
	</header>
	<section>
	</section>
	로그인
</body>
</html>