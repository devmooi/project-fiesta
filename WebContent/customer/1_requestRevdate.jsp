<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	section {
		width: 2500px;
		margin: auto;
	}
	
	section h2 {
		font-size: 2rem;
		margin: 0;
		padding: 0;
		font-weight: bold;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	
	section h4 {
		font-size: 1.2rem;
		margin: 0;
		padding: 0;
		font-weight: bold;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	
	#backgroundImg {
	    background-image: url('../resource/img/customerRequest${requestFiesta}.png');
        background-size: contain;
        position: relative;
		min-height:20vh;
	}
	
	#blank {
		min-height:15vh;
	}
	
	#inputFrm {
 		width: 550px;
		min-height:20vh;
		margin-top: 40px;
		margin-left: auto;
  		margin-right: auto;
	}
	
	#inputFrm button {
		background: transparent;
		border: 1px solid #009688;
		margin: 3px;
		font-weight: bold;
		cursor: pointer;
		padding: 8px 15px;
		color: #009688;
		border-radius: 5px;
	}
	
	#inputFrm button:hover {
		color: white;
		background: #009688;
		border: 1px solid #009688;
	}
	
	progress {
		width: 550px;
		margin: auto;
	}
	
	 progress {
  	    appearance: none;
    	-moz-appearance: none;
    	-webkit-appearance: none;
	}
	
	progress::-webkit-progress-value {
    	background: #009688;
	}
	
	progress::-webkit-progress-bar {
    	background: lightgray;
	}
	
	
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
<jsp:include page = "../header.jsp" />
 <section>
	<div id="backgroundImg">
	
	</div>
	<div id="inputFrm">
	  <progress value="25" max="100"></progress><br>
		<h2>1. 원하는 날짜를 알려주세요.</h2>
		<br><br>
		<h4>예약 예정일</h4> 
		<br>
        <form action="customerRequest.do" id="dateFrm">
          <input type="hidden" name="command" value="date">
          <input type="hidden" name="requestFiesta" value="${requestFiesta}">
            <label for="fromDate">FROM</label>
            <input type="text" name="fromDate" id="fromDate">
            <label for="toDate">TO</label>
            <input type="text" name="toDate" id="toDate"><br>
         
          <button type="submit" name="nextBtn" id="nextBtn">다음</button>
        </form>
    </div>
	<div id="blank">
	
	</div>
</section>
<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>