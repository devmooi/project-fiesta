<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		this.qCode = qCode;
		this.qDate = qDate;
		this.qTitle = qTitle;
		this.qDesc = qDesc;
		Answer answer = new Answer();
		answer.setaCode(aCode);
		answer.setaDesc(aDesc);
		answer.setaDesc(aDate);
<h3>문의내역</h3>
{$qDetail.qCode}
{$qDetail.qDate}
{$qDetail.qTitle}
{$qDetail.qDesc}
<h3>답변내역</h3>
{$qDetail.qCode}
{$qDetail.qDate}
{$qDetail.qTitle}
{$qDetail.qDesc}

</body>
</html>