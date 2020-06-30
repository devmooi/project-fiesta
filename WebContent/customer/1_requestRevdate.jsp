<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/*datepicer 버튼 롤오버 시 손가락 모양 표시*/
.ui-datepicker-trigger{cursor: pointer;}
/*datepicer input 롤오버 시 손가락 모양 표시*/
.hasDatepicker{cursor: pointer;}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
$(function() {
   //오늘 날짜를 출력
   $("#today").text(new Date().toLocaleDateString());

   //시작일.
   $('#fromDate').datepicker({
				showOn: "both",                     // 달력을 표시할 타이밍 (both: focus or button)
	            buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif", //버튼 이미지 경로
	            buttonImageOnly: true, //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
	            buttonText: "선택", //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
	            yearSuffix: "년", //달력의 년도 부분 뒤에 붙는 텍스트
	            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'], //달력의 월 부분 텍스트
	            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 Tooltip 텍스트
	            dayNamesMin: ['일','월','화','수','목','금','토'], //달력의 요일 부분 텍스트
	            dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'], //달력의 요일 부분 Tooltip 텍스트
   				showMonthAfterYear:true,
				changeYear: true,                    //콤보박스에서 년 선택 가능
		        changeMonth: true,                  //콤보박스에서 월 선택 가능          
	            buttonImageOnly : true,             // 버튼 이미지만 표시할지 여부
	            buttonText: "날짜선택",             // 버튼의 대체 텍스트
	            dateFormat: "yy-mm-dd",             // 날짜의 형식
	            changeMonth: true,                  // 월을 이동하기 위한 선택상자 표시여부
	            minDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
	            onClose: function( selectedDate ) {    
				     $("#toDate").datepicker( "option", "minDate", selectedDate );
	            }                
	});
	//종료일
	$('#toDate').datepicker({
	            showOn: "both", 
	            buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif", 
	            buttonImageOnly: true,
	            buttonText: "선택",               
	            yearSuffix: "년", 
	            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'], 
	            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], 
	            dayNamesMin: ['일','월','화','수','목','금','토'], 
	            dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'], 
				showMonthAfterYear:true,
				changeYear: true,                   
		        changeMonth: true,                  
	            buttonImageOnly : true,
	            buttonText: "날짜선택",
	            dateFormat: "yy-mm-dd",
	            changeMonth: true,
	            minDate: 0, 
	            onClose: function( selectedDate ) {
                     $("#fromDate").datepicker( "option", "maxDate", selectedDate );
                }                
     });
});
</script>
</head>
<body>
		1. 원하는 날짜를 알려주세요.
		오늘 날짜 : <span id="today"></span><br>
		예약 예정일 <br>
        <form action="customerRequest.do" id="dateFrm">
        <input type="hidden" name="command" value="date">
          <label for="fromDate">FROM</label>
          <input type="text" name="fromDate" id="fromDate">
          &nbsp;&nbsp;&nbsp;
          <label for="toDate">TO</label>
          <input type="text" name="toDate" id="toDate"><br>
          <input type="submit" value="다음">
        </form>
</body>
</html>