<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		h1 {
			text-align: center;
		}
		#container {
			display: flex;
			justify-content: center;
		}
		#container > div {
			float: left;
			margin-right: 5px;
		}
		#container img {
			width: 150px;
			height: 150px;
			border: 2px solid purple;
		}
		#container img:hover {
			border: 2px solid blue;
			cursor: pointer;
		}
		#productView {
			text-align: center;
		}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		function addProduct(id) {
			localStorage.setItem(id.getAttribute('id'),id.getAttribute('value'));
		}
		//localStorage 상품 정보 가져오기
		var data = "";
		$(function() {
			refreshPage();
		});
		function refreshPage() {
			for(var key in localStorage) {
				if(key=='length') break;
				data += localStorage.getItem(key) + " ";
				document.getElementById('productView').innerHTML = "오늘 본 상품 : " + data;
			}
		}
	</script>
</head>
<body>
	<h1>Fruit Total List 1.</h1>
	<div id="container">
		<c:forEach items="${list}" var="fruit">
			<div>
				<%-- <a id="${fruit.itemNumber}" value="${fruit.name}" href="itemView.do?id=${fruit.itemNumber}" onclick="addProduct(this)"><img src="${fruit.url}"></a> --%>
				<a href="itemView.do?id=${fruit.itemNumber}"><img src="resource/${fruit.url}"></a>
				<p>상품명 : ${fruit.name}</p>
				<p>가 격 : ${fruit.price}원</p>
			</div>
		</c:forEach>
	</div>

	<!-- 오늘 본 상품정보를 이곳에다가 출력.. -->
	<c:if test="${not empty fruits}">
		<h2 align="center"><font color="purple">오늘 본 상품들</font></h2>
		<table align="center" bgcolor="lightgray">
			<tr>
				<c:forEach items="${fruits}" var="fuit">
					<td>
						<img src="${fuit}" width="100" height="100">
					</td>
				</c:forEach>
			</tr>
		</table>
	</c:if>
</body>
</html>