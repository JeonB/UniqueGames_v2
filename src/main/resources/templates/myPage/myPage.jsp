<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>myPage</title>
	<link rel="stylesheet" href=/css/login.css">
	<script src="/js/jquery-3.6.4.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	
	$(document).ready(function(){
		var password = "${memberVo.password}";
		var phone1 = "${memberVo.phone1}";
		var phone2 = "${memberVo.phone2}";
		var phone3 = "${memberVo.phone3}";
		var email1 = "${memberVo.email1}";
		var email2 = "${memberVo.email2}";
		
		function emailCheck(asValue){
			var regex = /[A-za-z0-9]{4,20}$/;
			
			return regex.test(asValue);
		}
		
		function phoneCheck(asValue) {
			var regex = /^\d{3,4}$/;
			
			return regex.test(asValue);
		}
		
		$("#button-gradient").click(function(){
			
			if($("input[name='password']").val()==""){
				alert("비밀번호를 입력해주세요");
				$("input[name='password']").focus();
				return false;
			}else if($("input[name='password']").val() != password){
				alert("비밀번호가 일치하지 않습니다");
				$("input[name='password']").focus();
				return false;
			}else if($("input[name='email1']").val()==""){
				alert("이메일은 필수 입력 항목입니다");
				$("input[name='email1']").focus();
				return false;
			}else if(!emailCheck($("input[name='email1']").val())){
				alert("이메일은 영문 또는 숫자로 4자리 이상 입력해주세요");
				$("input[name='email1']").focus();
				return false;
			}else if($("input[name='email2']").val()=="" && $("#selectbox-email").val()=="choose" || $("input[name='email2']").val()=="" && $("#selectbox-email").val()=="direct"){
				alert("이메일 주소를 전부 작성해주세요");
				$("input[name='email2']").focus();
				return false;
			}else if($("#emailAuth").css("color") === "rgb(255, 0, 0)") {
				alert("이메일 인증번호를 확인해주세요");
				$("#email-auth-check").focus();
				return false;
			}else if($("#selectbox-mobile").val()=="choose") {
				alert("통신사를 선택해주세요");
				$("#selectbox-mobile").focus();
				return false;
			}else if($("#selectbox-phone").val()=="choose") {
				alert("휴대전화는 필수 입력 항목입니다");
				$("#selectbox-phone").focus();
				return false;
			}else if($("input[name='phone2']").val()=="") {
				alert("휴대전화는 필수 입력 항목입니다");
				$("input[name='phone2']").focus();
				return false;
			}else if(!phoneCheck($("input[name='phone2']").val())) {
				alert("휴대전화는 숫자 3,4자리로 입력해주세요");
				$("input[name='phone2']").focus();
				return false;
			}else if($("input[name='phone3']").val()=="") {
				alert("휴대전화는 필수 입력 항목입니다");
				$("input[name='phone3']").focus();
				return false;
			}else if(!phoneCheck($("input[name='phone3']").val())) {
				alert("휴대전화는 숫자 3,4자리로 입력해주세요");
				$("input[name='phone3']").focus();
				return false;
			}else if($("#phoneMsg").text() != ""){
				alert("휴대전화를 확인해주세요");
				$("#selectbox-phone").focus();
				return false;
			}else {
				myPageForm.submit();
			}
		});
		
		$("#selectbox-email").change(function(){
			
			if($("#selectbox-email").val()=="choose") {
				$("input[name='email2']").val("");
				$("#emailMsg").text("이메일을 선택해주세요").css("color","red").css("font-size","11px").css("display","inline");
				$("#selectbox-email").focus();
				return false;
			}else if($("#selectbox-email").val()=="direct") {
				$("input[name='email2']").val("").focus();
			}else {
				$("input[name='email2']").val($("#selectbox-email").val());
			}
		});
		
		$("#pwd-check-img").change(function(){

			if($("#pwd-check-img").is(":checked") == false) {
				$("input[name='password']").attr("type","password");
			}else if($("#pwd-check-img").is(":checked") == true) {
				$("input[name='password']").attr("type","text");
			}
			
		});
		
		$("#pwd-check-img-1").change(function(){

			if($("#pwd-check-img-1").is(":checked") == false) {
				$("input[name='password-check']").attr("type","password");
			}else if($("#pwd-check-img-1").is(":checked") == true) {
				$("input[name='password-check']").attr("type","text");
			}
			
		});
		
		/*change password link click*/
		$("#link-changePassword").click(function(){
			
			if($("input[name='password']").val()==""){
				alert("비밀번호를 입력해주세요");
				$("input[name='password']").focus();
				return false;
			}else if($("input[name='password']").val() != password){
				alert("비밀번호가 일치하지 않습니다");
				$("input[name='password']").focus();
				return false;
			}
		});
		
		$("#address-btn-style").click(function(){
			 new daum.Postcode({
			        oncomplete: function(data) {
			        	$("input[name='addr1']").val("("+data.zonecode+") "+data.address);
			        	$("input[name='addr2']").focus();
			        }
			    }).open();
		});
		
		 let code = "";
		  $("#email-btn-style").click(function(){
		  	const email = $("input[name='email1']").val() + "@" + $("input[name='email2']").val();
		  	
		  	$.ajax({
		  		url : "mailCheck",
		  		type: "POST",
		  		data: { email : email },
		  		
		  		success : function(data){
		  			alert("인증번호가 전송되었습니다");
		  			code = data;
		  			$('#email-auth-check').attr('disabled',false);
		  		}
		  	});
		});

		    	
	  	$('#email-auth-check').on("change", function () {
				const inputCode = $(this).val();
			    const resultMsg = $('#emailAuth');
				
				if(inputCode === code){
					resultMsg.text('인증번호가 일치합니다.');
					resultMsg.css("font-size","11px").css('color','blue').css("display","inline");
					$('#email-auth-check').attr('disabled',true);
					$('input[name="email1"]').attr('readonly',true);
					$('input[name="email2"]').attr('readonly',true);
					$('#selectbox-email').attr('disabled',true);
					$('#selectbox-email').attr('onFocus', 'this.initialSelect = this.selectedIndex');
			        $('#selectbox-email').attr('onChange', 'this.selectedIndex = this.initialSelect');
				}else{
					resultMsg.text('인증번호가 불일치 합니다. 다시 확인해주세요!.');
					resultMsg.css("font-size","11px").css('color','rgb(255, 0, 0)').css("display","inline");
				}
		  
	  });
	  	
	  	$("input[name='email1'], input[name='email2'], #selectbox-email").change(function() {
	  		if($("#email-auth-check").val() == ""){
	  			$("#emailAuth").css("font-size","11px").css('color','rgb(255, 0, 0)').css("display","inline").text('이메일 인증을 완료해주세요');
	  		}else {
	  			$("#emailAuth").css('color','rgb(0, 0, 255)').css("display","none");
	  		}
	  	});
	  	
	  	
	  	
	  	$("input[name='email1'], input[name='email2'], #selectbox-email").blur(function(){
    		
	  		if($("input[name='email1']").val() == "" || $("input[name='email2']").val() == "") {
	  			$("#emailMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	  			return false;
	  		}else {
	  			$.ajax({
	  				url : "email_check",
	  				type: "POST",
	  				data: {
	  					email1 : $("input[name='email1']").val(),
	  					email2 : $("input[name='email2']").val()
	  				},
	  				
	  				success : function(result) {
	  					if(result==1) {
	  						if($("input[name='email1']").val()==email1 && $("input[name='email2']").val()==email2) {
	  							$("#emailMsg").css("display","none");
	  							$("#emailAuth").css('color','rgb(0, 0, 255)').css("display","none");
	  							return false;
	  						}
	  						$("#emailMsg").text("중복된 이메일입니다").css("color","red").css("font-size","11px").css("display","inline");
	  						return false;
	  					}else {
	  						$("#emailMsg").text("").css("display","none");
	  					}
	  				}
	  			});
	  		}

	  	});
	  	
	  	$("#selectbox-mobile, #selectbox-phone, input[name='phone2'], input[name='phone3']").blur(function() {
	  	    if ($("#selectbox-mobile").val() == "default" || $("#selectbox-phone").val() == "default" || $("input[name='phone2']").val() == "" || $("input[name='phone3']").val() == "") {
	  			$("#phoneMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	  		}else {
	  			$.ajax({
	  				url : "phone_check",
	  				type: "POST",
	  				data: {
	  					tel : $("#selectbox-mobile").val(),
	  					phone1 : $("#selectbox-phone").val(),
	  					phone2 : $("input[name='phone2']").val(),
	  					phone3 : $("input[name='phone3']").val()
	  				},
	  				
	  				success : function(result) {
	  					if(result==1) {
	  						if($("#selectbox-phone").val()==phone1 && $("input[name='phone2']").val()==phone2 && $("input[name='phone3']").val()==phone3){
	  							$("#phoneMsg").text("").css("display","none");
	  							return false;
	  						}
	  						$("#phoneMsg").text("이미 등록된 휴대전화입니다").css("color","red").css("font-size","11px").css("display","inline");
	  						return false;
	  					}else {
	  						$("#phoneMsg").text("").css("display","none");
	  					}
	  				}
	  			});
	  		}
	  	});
	  	
	});
	
	
	</script>
</head>
<body>
	<header>
		<jsp:include page="../main/header.jsp"></jsp:include>
	</header>
	<section id="top-bg">
		<div id="base-layer">
			<div id="top-bg-textarea">
				<p id="top-title">My Page</p>
				<p id="top-subtitle">#마이페이지</p>
			</div>
		</div>
	</section>
	<section id="content-1">
		<p id="intro">마이페이지</p>
		<form action="myPage_proc" name="myPageForm" method="post" id="content-myPage">
			<div>
				<ul>
					<li id="must-insert">
						<p id="label-dot">*</p>
						<label>아이디</label><span id="idMsg"></span>
					</li>
					<li>
						<input type="text" id="input-common" name="member_id" value="${memberVo.member_id }" disabled>
						<input type="hidden" name="member_id" value="${memberVo.member_id }">
					</li>
					<li id="must-insert">
						<p id="label-dot">*</p>
						<label>비밀번호</label><span id="pwdMsg"></span>
					</li>
					<li>
						<input type="password" id="input-common" name="password">
						<input type="checkbox" id="pwd-check-img">
					</li>
					<li>
						<a href="myPageChangePassword?member_id=${memberVo.member_id }" id="link-changePassword">
							<span>비밀번호 변경 ></span>
						</a>
					</li>
					<li id="must-insert">
						<p id="label-dot">*</p>
						<label>이름</label><span id="nameMsg"></span>
					</li>
					<li>
						<input type="text" id="input-common" name="name" value="${memberVo.name }" disabled>
						<input type="hidden" name="name" value="${memberVo.name }">
					</li>
					<li id="must-insert">
						<p id="label-dot">*</p>
						<label>이메일</label>
						<span id="emailMsg"></span>
						<span id="emailAuth"></span>
					</li>
					<li>
						<input type="text" id="input-email" name="email1" value="${memberVo.email1 }">
						@
						<input type="text" id="input-email" name="email2" value="${memberVo.email2 }">
						<select name="email3" id="selectbox-email">
							<option value="choose">선택</option>
							<option value="naver.com" ${memberVo.email3  == 'naver.com' ? 'selected' : ''}>naver.com</option>
							<option value="gmail.com" ${memberVo.email3  == 'gmail.com' ? 'selected' : ''}>gmail.com</option>
							<option value="daum.net" ${memberVo.email3  == 'daum.net' ? 'selected' : ''}>daum.net</option>
							<option value="direct">직접입력</option>
						</select>
						<button type="button" id="email-btn-style">인증 번호</button>
						<input type="text" name="email-auth-check" id="email-auth-check" placeholder="인증번호 6자리" disabled="disabled">
						<span id="emailMsg"></span>
					</li>
					<li>
						<label>주소</label>
					</li>
					<li>
						<input type="text" id="input-addr1" name="addr1" value="${memberVo.addr1 }">
						<button type="button" id="address-btn-style">주소 검색</button>
					</li>
					<li class="address-input">
						<label>상세 주소</label>
					</li>
					<li>
						<input type="text" id="input-common" name="addr2" value="${memberVo.addr2 }">
					</li>
					<li id="must-insert">
						<p id="label-dot">*</p>
						<label>휴대전화</label><span id="phoneMsg"></span>
					</li>
					<li>
						<select name="tel" id="selectbox-mobile">
							<option value="choose">선택</option>
							<option value="SKT" ${memberVo.tel == 'SKT' ? 'selected' : '' }>SKT</option>
							<option value="KT" ${memberVo.tel == 'KT' ? 'selected' : '' }>KT</option>
							<option value="LGU+" ${memberVo.tel == 'LGU+' ? 'selected' : '' }>LGU+</option>
							<option value="MVNO" ${memberVo.tel == 'MVNO' ? 'selected' : '' }>알뜰폰</option>
						</select>
						<select name="phone1" id="selectbox-phone">
							<option value="choose">선택</option>
							<option value="010" ${memberVo.phone1 == '010' ? 'selected' : ''}>010</option>
    						<option value="011" ${memberVo.phone1 == '011' ? 'selected' : ''}>011</option>
    						<option value="012" ${memberVo.phone1 == '012' ? 'selected' : ''}>012</option>
						</select>
						-
						<input type="text" id="input-phone" name="phone2" value="${memberVo.phone2 }">
						-
						<input type="text" id="input-phone" name="phone3" value="${memberVo.phone3 }">
					</li>
					<li>
						<button type="button" id="button-gradient">수정하기</button>
					</li>
					<li>
						<a href="payment_detail" id="link-deleteAccount">
							<span>결제내역 ></span>
						</a>
						<a href="deletePwd?member_id=${memberVo.member_id }" id="link-deleteAccount">
							<span>회원탈퇴 ></span>
						</a>
					</li>
				</ul>
			</div>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>

</body>
</html>