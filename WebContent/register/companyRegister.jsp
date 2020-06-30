<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fiesta - 업체 회원가입</title>
	<link href="../resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">

	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
		$(function() {
			$('#name').keyup(function() {
				var name = $(this).val();
				if(name.length > 0) {
					$('#name').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
					$('#nameCheckView').html('ok!').css('color', '#4CAF50').css('text-align', 'left');
				} else {
					$('#name').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#nameCheckView').html('업체명을 입력해주세요.').css('color', '#F44336').css('text-align', 'left');
				}
			}); // name keyup
			$('#email').keyup(function() {
				var re = /^[a-z0-9._A-Z]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
				if(!re.test($(this).val())) {
					$('#email').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#emailCheckView').html('이메일 형식에 맞게 입력해주세요.').css('color', '#F44336').css('text-align', 'left');
				} else {
					$.ajax({
						type:'post',
						url:'comEmailExist.do',
						data:'comEmail='+$(this).val(),

						success:function(result) {
							if(result=='true') {
								$('#email').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
								$('#emailCheckView').html('이미 사용중인 이메일입니다.').css('color', '#F44336').css('text-align', 'left');
							}else {
								$('#email').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
								$('#emailCheckView').html('사용 가능한 이메일입니다.').css('color', '#4CAF50').css('text-align', 'left');
							}
						}
					}); // email ajax
				}
			}); // email keyup
			$('#pass').keyup(function() {
				var re = /^[A-Za-z0-9]{8,15}$/;
				if(!re.test($(this).val())) {
					$('#pass').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#passCheckView').html('비밀번호는 8자 이상, 15자 이내로 대소문자, 숫자로 입력해주세요.').css('color', '#F44336').css('text-align', 'left');
				} else {
					$('#pass').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
					$('#passCheckView').html('사용 가능한 비밀번호입니다.').css('color', '#4CAF50').css('text-align', 'left');
				}
			}); // pass keyup
			
			$('select').formSelect();
			
			//선택사항
			$('#option').click(function() {
				$('.optionInput').toggle(function() {
					$('#option').children('.material-icons').html('expand_less');
					$(this).addClass('show');
				}, function() {
					$('#option').children('.material-icons').html('expand_more');
				});
			});
			
			$('#tel').keyup(function() {
				var re = /^\d{2,3}-\d{3,4}-\d{4}$/;
				if(!re.test($(this).val())) {
					$('#tel').css('border-bottom', '1px solid #F44336').css('box-shadow', '0 1px 0 0 #F44336');
					$('#telCheckView').html('잘못된 전화번호입니다.').css('color', '#F44336').css('text-align', 'left');
				} else {
					$('#tel').css('border-bottom', '1px solid #4CAF50').css('box-shadow', '0 1px 0 0 #4CAF50');
					$('#telCheckView').html('ok!').css('color', '#4CAF50').css('text-align', 'left');
				}
			});

		}); // ready
	</script>
	<style>
		section {
			width: 550px;
			margin: auto;
			text-align: center;
		}
		section h2 {
			font-size: 2rem;
			font-weight: bold;
			padding-top: 20px;
		}
		
		section form {
			border: 1px solid #ddd;
			padding: 20px;
		}
		section form .input-field {
			margin-bottom: 30px;
		}
		
		#option {
			display: flex;
			cursor: pointer;
			margin-top: 40px;
			margin-bottom: 30px;
		}
		#option span {
			font-size: 1.2rem;
			font-weight: bold;
		}
		
		.optionShow {
			display: block;
		}
		.optionHide {
			display: none;
		}
		
		#sample6_postcode, #sample6_extraAddress {
			display: none;
		}
		#addressItem {
			position: relative;
			left: -213px;
			background: none;
		    border: 1px solid #009688;
		    color: #009688;
		    padding: 10px;
		    cursor: pointer;
		    border-radius: 5px;
		    margin-bottom: 10px;
		}
		#addressItem:hover {
			background: #009688;
			color: white;
		}
		#addressItem:focus {
			border: 1px solid #009688;
		}
		
		input[type="submit"] {
			width: 100%;
		    background: none;
		    border: 1px solid #009688;
		    color: #009688;
		    border-radius: 5px;
		    padding: 12px;
		    font-weight: bold;
		    cursor: pointer;
		}
		input[type="submit"]:hover {
			background: #009688;
			color: white;
			transition-duration: 1s;
		}
	</style>
</head>
<body>
	<jsp:include page = "../header.jsp" />
	
	<section>
		<h2>Fiesta에 오신 것을 환영합니다</h2>
		
		<form action="companyRegister.do" id="registerFrm" method="post" enctype="multipart/form-data">
			<div class="input-field">
				<label for="name">업체명</label>
				<input type="text" name="name" id="name" required>
				<span class="helper-text" id="nameCheckView"></span>
			</div>
			
			<div class="input-field">
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" required>
				<span class="helper-text" id="emailCheckView"></span>
			</div>
			
			<div class="input-field">
				<label for="pass">비밀번호</label>
				<input type="password" name="pass" id="pass" required>
				<span class="helper-text" id="passCheckView"></span>
			</div>
			
			<div class="input-field">
			    <select name="categoryCode" id="categoryCode" required>
			    	<option disabled selected>선택해주세요 (필수)</option>
			    	<option value="1">연예기획사</option>
					<option value="2">숙소</option>
					<option value="3">주류/렌탈</option>
					<option value="4">버스</option>
					<option value="5">음향조명</option>
					<option value="6">보험회사</option>
					<option value="7">푸드트럭</option>
					<option value="8">의류</option>
					<option value="9">현수막</option>
					<option value="10">협찬</option>
			    </select>
			    <label for="categoryCode">업체분류</label>
			</div>
			
			<p id="option">
				<span>선택사항</span>
				<i class="small material-icons">expand_more</i>
			</p>
			
			<div class="input-field optionInput optionHide">
				<label for="tel">전화번호</label>
				<input type="text" name="tel" id="tel">
				<span class="helper-text" id="telCheckView"></span>
			</div>
			
			<div class="input-field optionInput optionHide">
				<input type="text" id="sample6_postcode" placeholder="우편번호">
				<input id="addressItem" type="button" onclick="sample6_execDaumPostcode()" value="주소검색">
				<input name="addr" type="text" id="sample6_address" placeholder="주소">
				<input name="addr" type="text" id="sample6_detailAddress" placeholder="상세주소">
				<input type="text" id="sample6_extraAddress" placeholder="참고항목">
				<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
				<script>
				    function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
				                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
				                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				                var addr = ''; // 주소 변수
				                var extraAddr = ''; // 참고항목 변수
				
				                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				                    addr = data.roadAddress;
				                } else { // 사용자가 지번 주소를 선택했을 경우(J)
				                    addr = data.jibunAddress;
				                }
				
				                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				                if(data.userSelectedType === 'R'){
				                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
				                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				                        extraAddr += data.bname;
				                    }
				                    // 건물명이 있고, 공동주택일 경우 추가한다.
				                    if(data.buildingName !== '' && data.apartment === 'Y'){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				                    if(extraAddr !== ''){
				                        extraAddr = ' (' + extraAddr + ')';
				                    }
				                    // 조합된 참고항목을 해당 필드에 넣는다.
				                    document.getElementById("sample6_extraAddress").value = extraAddr;
				                
				                } else {
				                    document.getElementById("sample6_extraAddress").value = '';
				                }
				
				                // 우편번호와 주소 정보를 해당 필드에 넣는다.
				                document.getElementById('sample6_postcode').value = data.zonecode;
				                document.getElementById("sample6_address").value = addr;
				                // 커서를 상세주소 필드로 이동한다.
				                document.getElementById("sample6_detailAddress").focus();
				            }
				        }).open();
				    }
				</script>
			</div>
			
			<div class="input-field optionInput optionHide filebox">
				<p>업체 로고 등록</p>
				<input class="upload-name" value="파일선택" disabled>
				<label for="ex_filename">업로드</label>
				<input type="file" name="img" id="ex_filename" class="upload-hidden" accept=".jpg, .jpeg, .png">
			</div>
			<style>
				.filebox p {
				    display: block;
				    left: -204px;
				    position: relative;
				    font-weight: bold;
				}
				.filebox input[type="file"] { 
					position: absolute; 
					width: 1px; 
					height: 1px; 
					padding: 0; 
					margin: -1px; 
					overflow: hidden; 
					clip:rect(0,0,0,0); 
					border: 0; 
				} 
				.filebox label { 
					display: inline-block; 
					padding: .5em .75em; 
					font-size: inherit; 
					line-height: normal; 
					vertical-align: middle; 
					cursor: pointer; 
					border-radius: .25em; 
				
				    top: -17px;
				    position: relative;
    				left: 6px;
    				padding: 9px;
    				color: #009688 !important;
    				border: 1px solid #009688;
    				
				} /* named upload */ 
				.filebox label:hover {
					background: #009688;
					color: white !important;
				}
				.filebox .upload-name { 
					display: inline-block; 
					padding: .5em .75em; /* label의 패딩값과 일치 */ 
					font-size: inherit; 
					font-family: inherit; 
					line-height: normal; 
					vertical-align: middle; 
					background-color: #f5f5f5; 
					border: 1px solid #ebebeb; 
					border-bottom-color: #e2e2e2; 
					border-radius: .25em; 
					-webkit-appearance: none; /* 네이티브 외형 감추기 */ 
					-moz-appearance: none; 
					appearance: none; 
					
				    width: 400px;
				    height: 18px;
				}
			</style>
			<script>
				$(document).ready(function(){ 
					var fileTarget = $('.filebox .upload-hidden'); 
					fileTarget.on('change', function(){ 
						// 값이 변경되면 
						if(window.FileReader){ 
							// modern browser 
							var filename = $(this)[0].files[0].name; 
						} else { // old IE 
							var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
						} // 추출한 파일명 삽입 
						$(this).siblings('.upload-name').val(filename); 
					}); 
				});
			</script>
			
			<div class="input-field optionInput optionHide">
				<label for="desc">업체 상세 정보</label>
				<textarea id="desc" name="desc" class="materialize-textarea"></textarea>
			</div>
			
			<input type="submit" value="회원가입" id="registerBtn">
		</form>
	</section>
	
	<c:import url="http://localhost:8888/Fiesta/footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html>


