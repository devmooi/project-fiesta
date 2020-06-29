<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- outh 인증 방법부터 -->
	<script src="http://developers.kakao.com/sdk/js/kakao.min.js"></script>
 
<div id="kakao_btn_changed">
<a id="custom-login-btn" href="javascript:loginWithKakao()">
<img src="../images/sns/Kakao_login_btn.png" width="300"/></a>
</div>
 
 
 
<script type='text/javascript'>
// 버튼 이미지 전환 
$(document).ready(function(){
 
    $("#custom-login-btn, #custom-logout-btn").hover(
    function(){
        $("#custom-login-btn img").attr('src','../images/sns/Kakao_login_btn_c.png');
        $("#custom-logout-btn img").attr('src','../images/sns/Kakao_logout_btn_c.png');
        
    }, 
    function(){
        $("#custom-login-btn img").attr('src','../images/sns/Kakao_login_btn.png');
        $("#custom-logout-btn img").attr('src','../images/sns/Kakao_logout_btn.png');
        
    });
 
});
 
 
// 로그인 및 로그아웃 버튼 생성 처리
var cookiedata = document.cookie;
 
if(cookiedata.indexOf('kakao_login=done') < 0){
    createLoginKakao();
}else{
    createLogoutKakao();
}
 
/* 로그인 관련 쿠키 생성 및 삭제 */
function setCookie( name , value , expired ){
 
 var date = new Date();
 date.setHours(date.getHours() + expired);
 var expried_set = "expries="+date.toGMTString();
 document.cookie = name + "=" + value + "; path=/;" + expried_set + ";"
 
}
 
/* 쿠키 삭제 다른방법
function deleteCookie( name ){
    
    var date = new Date();
     date.setHours(date.getHours() - 1);
     var expried_set = "expries="+date.toGMTString();
     document.cookie = name + "="  + "; path=/;" + expried_set + ";"
}
*/
 
// 
function getCookie(name){
 
    var nameofCookie = name + "=";
    var x = 0;
    while(x <= document.cookie.length){
        var y = ( x + nameofCookie.length);
        if(document.cookie.substring(x,y) == nameofCookie){
            if((endofCookie = document.cookie.indexOf(";",y)) == -1)
                endofCookie = document.cookie.length;
            return unescape(document.cookie.substring(y,endofCookie));
        }
        x = document.cookie.indexOf(" ",x) + 1;
        if( x == 0 )
            break;
        }
        
        return "";
}
 
 
// 카카오 script key 입력
Kakao.init('d9e257ac0e0613ae52e422b3db018879');
 
 // 로그인 처리
function loginWithKakao(){
    
    Kakao.Auth.cleanup();
    Kakao.Auth.login({
        persistAccessToken: true,
        persistRefreshToken: true,
        success: function(authObj) {
            setCookie("kakao_login","done",1); // 쿠키생성 (로그인)
            //alert(cookiedata);
            createLogoutKakao();
            window.location.href="../index.html";
        },
            fail: function(err) {
             alert(JSON.stringify(err));
        }
                 
    });
}
 
 
// 로그아웃 처리
function logoutWithKakao(){
    Kakao.Auth.logout();
    alert('카카오 로그아웃 완료!');
    setCookie("kakao_login","",-1);  // 쿠키삭제 (로그아웃)
    //deleteCookie( "kakao_login" ); 쿠키삭제 다른 방법
    createLoginKakao();
    window.location.href="../index.html";
}
 
 
 
// 로그인 버튼생성
function createLoginKakao(){
 var login_btn = "<a id='custom-login-btn' href='javascript:loginWithKakao()'>"+
                "<img src='../images/sns/Kakao_login_btn.png' width='250'/>"+
                "</a>";
 document.getElementById('kakao_btn_changed').innerHTML  = login_btn;
}
 
 
// 로그아웃 버튼생성
function createLogoutKakao(){
 var logout_btn = "<a id='custom-logout-btn' href='javascript:logoutWithKakao()'>"+
                "<img src='../images/sns/Kakao_logout_btn.png' width='200'/>"+
                "</a>";
 document.getElementById('kakao_btn_changed').innerHTML  = logout_btn;
 
}
 
 
  //]]>
</script>

</body>
</html>