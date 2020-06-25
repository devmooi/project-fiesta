<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>문의내역</h3>
{$qDetail.qCode}
{$qDetail.qDate}
{$qDetail.qTitle}
{$qDetail.qDesc}
<h3>답변내역</h3>
{$aDetail.aDate}
{$aDetail.aDesc}
{$aDetail.comName}

</body>
</html>