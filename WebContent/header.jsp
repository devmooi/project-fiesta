<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
	<style>
        * {
            margin: 0;
            padding: 0;
        }
        header {
            position: fixed;
            background: white;
            overflow: hidden;
            width: 100%;
            border-bottom: 1px solid black;
        }
        h1 {
            font-family: 'Pacifico', cursive;
            float: left;
            margin-left: 20px;
            margin-top: 5px;
        }
        nav {
            float: right;
            line-height: 70px;
            margin-right: 20px;
        }
        nav a {
            margin-right: 10px;
            text-decoration: none;
            color: black;
        }
        nav a:hover {
            color: blue;
        }
        section {
            padding-top:70px;
        }
    </style>
</head>
<body>
	<header>
        <h1>Fiesta</h1>
        <nav>
            <a href="register/register.jsp">회원가입</a>
            <a href="companylist.do">업체 찾기</a>
            <a href="register/login.jsp">로그인</a>
        </nav>
    </header>
</body>
</html>