<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	
	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    
    <!-- Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Pacifico&display=swap" rel="stylesheet">

	<!-- Fontawesome Icons -->
    <script src="https://kit.fontawesome.com/064e5458ec.js" crossorigin="anonymous"></script>
    
    <style>
        body {
            font-family: 'Nanum Gothic', sans-serif;
        }
        header {
            position: fixed;
            width: 100%;
            background: white;
            border-bottom: 1px solid #ddd;
            z-index: 99;
        }
        header h1 {
            margin: 10px 25px;
            float: left;
        }
        header h1 a {
            color: #009688;
            font-family: 'Pacifico', cursive;
            font-size: 3rem;
        }
        header ul {
            float: right;
            margin-right: 20px;
        }
        header ul li {
            float: left;
            height: 100%;
            display: block;
        }
        header ul li a {
            margin-right: 30px;
            font-size: 1.2rem;
            font-weight: bold;
            color: black;
            display: block;
            padding: 15px 10px;
        }
        header ul li a:hover {
            color: #009688;
        }
        /* section */
        section {
            padding-top: 92px;
        }

        /* responsive */
        @media (max-width: 538px) {
            header h1 {
                margin: 0;
                width: 100%;
                text-align: center;
            }
            header ul {
                margin: 0;
                width: 100%;
                display: flex;
                justify-content: center;
            }
            header ul li a {
                font-size: 1rem;
            }
            section {
                padding-top: 125px;
            }
        }
    </style>
</head>
<body>
    <header>
        <h1><a href="http://localhost:8888/Fiesta">Fiesta</a></h1>

        <!-- 비회원, 고객 로그인, 업체 로그인 상황에 따라 달라짐 -->
        <ul>
        	<li><a href="http://localhost:8888/Fiesta/ShowAllCompany.do">업체 찾기</a></li>
        	<c:choose>
        		<c:when test="${!empty customer}">
        			<li><a href="customerMypage.do">마이페이지</a></li>
        			<li><a href="http://localhost:8888/Fiesta/logout.do">로그아웃</a></li>
        		</c:when>
        		<c:when test="${!empty company}">
        			<li><a href="companyMypage.do">업체 정보</a></li>
        			<li><a href="http://localhost:8888/Fiesta/logout.do">로그아웃</a></li>
        		</c:when>
        		<c:otherwise>
        			<li><a href="http://localhost:8888/Fiesta/register/register.jsp">회원가입</a></li>
            		<li><a href="http://localhost:8888/Fiesta/register/login.jsp">로그인</a></li>
        		</c:otherwise>
        	</c:choose>
        </ul>
    </header>
</body>
</html>