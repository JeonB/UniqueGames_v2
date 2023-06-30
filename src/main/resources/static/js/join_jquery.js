$(document).ready(function(){

/*************************************************************************
						개인 - 회원가입 폼 체크
*************************************************************************/
$("#button-gradient").click(function(){
	if($("input[name='member_id']").val()=="") {
		alert("아이디는 필수 입력 항목입니다");
		$("input[name='member_id']").focus();
		return false;
	}else if(!idCheck2($("input[name='member_id']").val())) {
		alert("아이디는 영문 또는 영문+숫자로 작성해주세요");
		$("input[name='member_id']").focus();
		return false;
	}else if(!idCheck3($("input[name='member_id']").val())) {
		alert("아이디는 5~10글자로 작성해주세요");
		$("input[name='member_id']").focus();
		return false;
	}else if($("#idMsg").text() == ""){
		alert("중복체크를 진행해주세요");
		$("#check-btn-style").focus();
		return false;
	}else if($("input[name='password']").val()==""){
		alert("비밀번호는 필수 입력 항목입니다");
		$("input[name='password']").focus();
		return false;
	}else if(!pwdCheck($("input[name='password']").val())) {
		alert("비밀번호는 영문,숫자,특수문자 1글자 이상 조합하여 작성해주세요");
		$("input[name='password']").focus();
		return false;
	}else if($("input[name='password-check']").val()=="") {
		alert("비밀번호 확인칸을 입력해주세요");
		$("input[name='password-check']").focus();
		return false;
	}else if($("input[name='password']").val() != $("input[name='password-check']").val()){
		alert("비밀번호가 서로 동일하지 않습니다");
		$("input[name='password-check']").focus();
		return false;
	}else if($("input[name='name']").val()==""){
		alert("이름은 필수 입력 항목입니다");
		$("input[name='name']").focus();
		return false;
	}else if(!nameCheck($("input[name='name']").val())) {
		alert("이름은 한글로만 작성해주세요");
		$("input[name='name']").focus();
		return false;
	}else if($("input[name='email1']").val()==""){
		alert("이메일은 필수 입력 항목입니다");
		$("input[name='email1']").focus();
		return false;
	}else if(!emailCheck($("input[name='email1']").val())){
		alert("이메일은 영문 또는 숫자로 4자리 이상 입력해주세요");
		$("input[name='email1']").focus();
		return false;
	}else if($("#join-email2").val()=="" && $("#selectbox-email").val()=="default" || $("#join-email2").val()=="" && $("#selectbox-email").val()=="direct"){
		alert("이메일 주소를 전부 작성해주세요");
		$("#join-email2").focus();
		return false;
	}else if($("#emailMsg").text() != ""){
		alert("이메일을 확인해주세요");
		$("input[name='email1']").focus();
		return false;
	}else if($("#email-auth-check").val()=="") {
		alert("이메일 인증번호를 입력해주세요");
		$("#email-auth-check").focus();
		return false;
	}else if($("#emailAuth").css("color") === "rgb(255, 0, 0)") {
		alert("이메일 인증번호를 확인해주세요");
		$("#email-auth-check").focus();
		return false;
	}else if($("#selectbox-mobile").val()=="default") {
		alert("통신사를 선택해주세요");
		$("#selectbox-mobile").focus();
		return false;
	}else if($("#selectbox-phone").val()=="default") {
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
	}else if($("#checkbox-agreement:checked").length!=3 && !$("#chk-circle").is(":checked")) {
		alert("약간 동의를 체크해주세요");
		$("#agreement").focus();
		return false;
	}else {
		joinIndividual.submit();
	}
});
/*************************************************************************
						법인 - 회원가입 폼 체크(회사명: 한글,영어 입력 가능)
*************************************************************************/
$("#button-gradient-company").click(function(){
	if($("input[name='company_id']").val()=="") {
		alert("회사 아이디는 필수 입력 항목입니다");
		$("input[name='company_id']").focus();
		return false;
	}else if(!idCheck2($("input[name='company_id']").val())) {
		alert("회사 아이디는 영문 또는 영문+숫자로 작성해주세요");
		$("input[name='company_id']").focus();
		return false;
	}else if(!idCheck3($("input[name='company_id']").val())) {
		alert("회사 아이디는 5~10글자로 작성해주세요");
		$("input[name='company_id']").focus();
		return false;
	}else if($("#c-idMsg").text() == ""){
		alert("중복체크를 진행해주세요");
		$("#c-check-btn-style").focus();
		return false;
	}else if($("#company-password").val()==""){
		alert("비밀번호는 필수 입력 항목입니다");
		$("#company-password").focus();
		return false;
	}else if(!pwdCheck($("#company-password").val())) {
		alert("비밀번호는 영문,숫자,특수문자 1글자 이상 조합하여 작성해주세요");
		$("#company-password").focus();
		return false;
	}else if($("#company-password-check").val()=="") {
		alert("비밀번호 확인칸을 입력해주세요");
		$("#company-password-check").focus();
		return false;
	}else if($("#company-password").val() != $("#company-password-check").val()){
		alert("비밀번호가 서로 동일하지 않습니다");
		$("#company-password-check").focus();
		return false;
	}else if($("#company-name").val()==""){
		alert("회사명은 필수 입력 항목입니다");
		$("#company-name").focus();
		return false;
	}else if(!nameCheck1($("#company-name").val())) {
		alert("회사명은 한글, 영어로 1~10글자 이내로 작성해주세요");
		$("#company-name").focus();
		return false;
	}else if($("#company-email1").val()==""){
		alert("이메일은 필수 입력 항목입니다");
		$("#company-email1").focus();
		return false;
	}else if(!emailCheck($("#company-email1").val())){
		alert("이메일은 영문 또는 숫자로 4자리 이상 입력해주세요");
		$("#company-email1").focus();
		return false;
	}else if($("#company-email2").val()=="" && $("#company-selectbox-email").val()=="default" || $("#company-email2").val()=="" && $("#company-selectbox-email").val()=="direct"){
		alert("이메일 주소를 전부 작성해주세요");
		$("#company-email2").focus();
		return false;
	}else if($("#c-emailMsg").text() != ""){
		alert("이메일을 확인해주세요");
		$("#company-email1").focus();
		return false;
	}else if($("#c-email-auth-check").val()=="") {
		alert("이메일 인증번호를 입력해주세요");
		$("#c-email-auth-check").focus();
		return false;
	}else if($("#c-emailAuth").css("color") === "rgb(255, 0, 0)") {
		alert("이메일 인증번호를 확인해주세요");
		$("#c-email-auth-check").focus();
		return false;
	}else if($("#company-selectbox-mobile").val()=="default") {
		alert("통신사를 선택해주세요");
		$("#company-selectbox-mobile").focus();
		return false;
	}else if($("#company-selectbox-phone").val()=="default") {
		alert("대표 전화는 필수 입력 항목입니다");
		$("#company-selectbox-phone").focus();
		return false;
	}else if($("#company-phone2").val()=="") {
		alert("대표 전화는 필수 입력 항목입니다");
		$("#company-phone2").focus();
		return false;
	}else if(!phoneCheck($("#company-phone2").val())) {
		alert("대표 전화는 숫자 3,4자리로 입력해주세요");
		$("#company-phone2").focus();
		return false;
	}else if($("#company-phone3").val()=="") {
		alert("대표 전화는 필수 입력 항목입니다");
		$("input[name='phone3']").focus();
		return false;
	}else if(!phoneCheck($("#company-phone3").val())) {
		alert("대표 전화는 숫자 3,4자리로 입력해주세요");
		$("#company-phone3").focus();
		return false;
	}else if($("#c-phoneMsg").text() != ""){
		alert("대표 전화를 확인해주세요");
		$("#company-selectbox-phone").focus();
		return false;
	}else if($("#c-checkbox-agreement:checked").length!=2 && !$("#c-chk-circle").is(":checked")) {
		alert("약간 동의를 체크해주세요");
		$("#agreement").focus();
		return false;
	}else {
		joinCompany.submit();
	}
});
	
	
/*************************************************************************
						input null값 확인
*************************************************************************/
/*function idCheck1(asValue){
	var regex1 = /[^0-9]{5,10}/g;
	
	return regex1.test(asValue);
}*/

function idCheck2(asValue) {
	var regex2 = /^[a-zA-Z0-9]*$/;
	
	return regex2.test(asValue);
}

function idCheck3(asValue) {
	var regex3 = /^[a-zA-Z0-9]{5,10}$/;
	
	return regex3.test(asValue);
}

/*function idCheck4(asValue){//영어로 시작하고 숫자로 시작하면 안됨
	var regex4 = /^[A-Za-z]{1}[A-Za-z0-9]{3,19}$/;
	
	return regex4.test(asValue);
}*/

function pwdCheck(asValue) {
	var regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/g;
	
	return regex.test(asValue);
}

function nameCheck(asValue) {
	var regex =  /^[가-힣]{2,4}$/;
	
	return regex.test(asValue);
}

function nameCheck1(asValue) {
	var regex =  /^[A-Za-z가-힣]{1,7}$/;
	
	return regex.test(asValue);
}

function emailCheck(asValue){
	var regex = /[A-za-z0-9]{4,20}$/;
	
	return regex.test(asValue);
}

function phoneCheck(asValue) {
	var regex = /^\d{3,4}$/;
	
	return regex.test(asValue);
}

/*
$("input[name='member_id']").blur(function(){
	if($("input[name='member_id']").val()==""){
		$("#idMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","block");
	}else {
		if(!idCheck2($("input[name='member_id']").val())){
			$("#idMsg").text("영문 또는 숫자로 작성해주세요").css("color","red").css("font-size","11px").css("display","block");
		}else if(!idCheck3($("input[name='member_id']").val())) {
			$("#idMsg").text("5~10자리로 작성해주세요").css("color","red").css("font-size","11px").css("display","block");
		}else {
			$("#idMsg").css("display","none");
		}
	}
});
*/


$("input[name='password']").blur(function(){
	if($("input[name='password']").val()=="") {
		$("#pwdMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else {
		if(!pwdCheck($("input[name='password']").val())) {
			$("#pwdMsg").text("올바른 비밀번호 형식이 아닙니다").css("color","red").css("font-size","11px").css("display","inline");
		}else {
			$("#pwdMsg").css("display","none");
		}
	}
});


$("input[name='password-check']").blur(function(){
	
	if($("input[name='password-check']").val()=="") {
		
		$("#pwdMsg-check").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
		
	}else if($("input[name='password']").val()!="" && $("input[name='password-check']").val()!="") {
		
		if($("input[name='password']").val() == $("input[name='password-check']").val()){
			$("#pwdMsg-check").text("비밀번호가 서로 동일합니다").css("color","blue").css("font-size","11px").css("display","inline");
		}else {
			$("#pwdMsg-check").text("비밀번호가 서로 동일하지 않습니다").css("color","red").css("font-size","11px").css("display","inline");
		}
	} 
	
});

$("input[name='name']").blur(function(){
	if($("input[name='name']").val()=="") {
		$("#nameMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else {
		if(!nameCheck($("input[name='name']").val())) {
			$("#nameMsg").text("올바른 이름 형식이 아닙니다").css("color","red").css("font-size","11px").css("display","inline");
		}else {
			$("#nameMsg").css("display","none");
		}
	}
});

$("input[name='email1']").blur(function(){
	if($("input[name='email1']").val()=="" && $("#join-email2").val()==""){
		$("#emailMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else{
		
		if(!emailCheck($("input[name='email1']").val())){
			$("#emailMsg").text("이메일은 영문 또는 숫자로 4자리 이상 입력해주세요").css("color","red").css("font-size","11px").css("display","inline");
		}else{
			$("#emailMsg").css("display","none");
		}
		
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
				phone1 : $("#selectbox-phone").val(),
				phone2 : $("input[name='phone2']").val(),
				phone3 : $("input[name='phone3']").val()
			},
			
			success : function(result) {
				if(result==1) {
					$("#phoneMsg").text("이미 등록된 휴대전화입니다").css("color","red").css("font-size","11px").css("display","inline");
					return false;
				}else {
					$("#phoneMsg").text("").css("display","none");
				}
			}
		});
	}
});



$("#company-selectbox-mobile, #company-selectbox-phone, #company-phone2, #company-phone3").blur(function() {
    if ($("#company-selectbox-mobile").val() == "default" || $("#company-selectbox-phone").val() == "default" || $("#company-phone2").val() == "" || $("#company-phone3").val() == "") {
		$("#c-phoneMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else {
		$.ajax({
			url : "c_phone_check",
			type: "POST",
			data: {
				phone1 : $("#company-selectbox-phone").val(),
				phone2 : $("#company-phone2").val(),
				phone3 : $("#company-phone3").val()
			},
			
			success : function(result) {
				if(result==1) {
					$("#c-phoneMsg").text("이미 등록된 휴대전화입니다").css("color","red").css("font-size","11px").css("display","inline");
					return false;
				}else {
					$("#c-phoneMsg").text("").css("display","none");
				}
			}
		});
	}
});
	
//---------------------------------------------법인----------------------------------------------------------------------------
/*
$("input[name='company_id']").blur(function(){
	if($("input[name='company_id']").val()==""){
		$("#c-idMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else {
		if(!idCheck2($("input[name='company_id']").val())){
			$("#c-idMsg").text("영문 또는 숫자로 작성해주세요").css("color","red").css("font-size","11px").css("display","inline");
		}else if(!idCheck3($("input[name='company_id']").val())) {
			$("#c-idMsg").text("5~10자리로 작성해주세요").css("color","red").css("font-size","11px").css("display","inline");
		}else {
			$("#c-idMsg").css("display","none");
		}
	}
});
*/


$("#company-password").blur(function(){
	if($("#company-password").val()=="") {
		$("#c-pwdMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else {
		if(!pwdCheck($("#company-password").val())) {
			$("#c-pwdMsg").text("올바른 비밀번호 형식이 아닙니다").css("color","red").css("font-size","11px").css("display","inline");
		}else {
			$("#c-pwdMsg").css("display","none");
		}
	}
});


$("#company-password-check").blur(function(){
	
	if($("#company-password-check").val()=="") {
		
		$("#c-pwdMsg-check").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
		
	}else if($("#company-password").val()!="" && $("#company-password-check").val()!="") {
		
		if($("#company-password").val() == $("#company-password-check").val()){
			$("#c-pwdMsg-check").text("비밀번호가 서로 동일합니다").css("color","blue").css("font-size","11px").css("display","inline");
		}else {
			$("#c-pwdMsg-check").text("비밀번호가 서로 동일하지 않습니다").css("color","red").css("font-size","11px").css("display","inline");
		}
	} 
	
});

$("#company-name").blur(function(){
	if($("#company-name").val()=="") {
		$("#c-nameMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else {
		if(!nameCheck1($("#company-name").val())) {
			$("#c-nameMsg").text("올바른 이름 형식이 아닙니다").css("color","red").css("font-size","11px").css("display","inline");
		}else {
			$("#c-nameMsg").css("display","none");
		}
	}
});

$("#company-email1").blur(function(){
	if($("#company-email1").val()==""){
		$("#c-emailMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else{
		
		if(!emailCheck($("#company-email1").val())){
			$("#c-emailMsg").text("이메일은 영문 또는 숫자로 4자리 이상 입력해주세요").css("color","red").css("font-size","11px").css("display","inline");
		}else{
			$("#c-emailMsg").css("display","none");
		}
		
	}
});


	
/*************************************************************************
						이메일 선택 시 input창 자동 입력
*************************************************************************/
$("#selectbox-email").change(function(){
	
	if($("#selectbox-email").val()=="default") {
		$("#join-email2").val("");
		$("#emailMsg").text("이메일을 선택해주세요").css("color","red").css("font-size","11px").css("display","inline");
		$("#selectbox-email").focus();
		return false;
	}else if($("#selectbox-email").val()=="direct") {
		$("#join-email2").val("").focus();
	}else {
		$("#join-email2").val($("#selectbox-email").val());
	}
});
//--------------------------법인 이메일 선택 시 input창 자동 입력-----------------------------

$("#company-selectbox-email").change(function(){
	
	if($("#company-selectbox-email").val()=="default") {
		$("#company-email2").val("");
		$("#emailMsg").text("이메일을 선택해주세요").css("color","red").css("font-size","11px").css("display","inline");
		$("#company-selectbox-email").focus();
		return false;
	}else if($("#company-selectbox-email").val()=="direct") {
		$("#company-email2").val("").focus();
	}else {
		$("#company-email2").val($("#company-selectbox-email").val());
	}
});
	
/*************************************************************************
							주소 찾기
*************************************************************************/
$("#address-btn-style").click(function(){
	 new daum.Postcode({
	        oncomplete: function(data) {
	        	$("input[name='addr1']").val("("+data.zonecode+") "+data.address);
	        	$("input[name='addr2']").focus();
	        }
	    }).open();
});

//---------------------------법인------------------------------
$("#company-address-btn-style").click(function(){
	 new daum.Postcode({
	        oncomplete: function(data) {
	        	$("#company-addr1").val("("+data.zonecode+") "+data.address);
	        	$("#company-addr2").focus();
	        }
	    }).open();
});

/*************************************************************************
							비밀번호  누르면 비밀번호 보이기
*************************************************************************/
$("#pwd-check-img").change(function(){

	if($("#pwd-check-img").is(":checked") == false) {
		$("#input-common-password").attr("type","password");
	}else if($("#pwd-check-img").is(":checked") == true) {
		$("#input-common-password").attr("type","text");
	}
	
});

$("#pwd-check-img-1").change(function(){

	if($("#pwd-check-img-1").is(":checked") == false) {
		$("#input-common-password-check").attr("type","password");
	}else if($("#pwd-check-img-1").is(":checked") == true) {
		$("#input-common-password-check").attr("type","text");
	}
});

//-------------------------법인 -------------------------

$("#c-pwd-check-img").on("change", function() {
    var passwordInput = $("#company-password");
    if ($(this).is(":checked")) {
      passwordInput.attr("type", "text");
    } else {
      passwordInput.attr("type", "password");
    }
});

 $("#c-pwd-check-img-1").on("change", function() {
    var passwordCheckInput = $("#company-password-check");
    if ($(this).is(":checked")) {
      passwordCheckInput.attr("type", "text");
    } else {
      passwordCheckInput.attr("type", "password");
    }
});
	
/*************************************************************************
							개인 회원가입; 전체 동의
*************************************************************************/
$("#chk-circle").click(function(){

	if($("#chk-circle").is(":checked"))
		$("input[name='chk-agree']").prop("checked",true);
	else
		$("input[name='chk-agree']").prop("checked",false);
});
	
/*************************************************************************
					개인 회원가입; 동의 하나라도 체크 해제 시 전체동의 해제
*************************************************************************/
$("input[name='chk-agree']").click(function(){
	
	var total = $("input[name='chk-agree']").length;
	var checked = $("input[name='chk-agree']:checked").length;
	
	if(total != checked){
		$("#chk-circle").prop("checked",false);
	}else if(total == checked){
		$("#chk-circle").prop("checked",true);
	}
});
	
/*************************************************************************
							법인 회원가입; 전체 동의
*************************************************************************/
$("#c-chk-circle").click(function(){

	if($("#c-chk-circle").is(":checked"))
		$("input[name='c-chk-agree']").prop("checked",true);
	else
		$("input[name='c-chk-agree']").prop("checked",false);
});
	
/*************************************************************************
					법인 회원가입; 체크 해제 시 전체동의 해제
*************************************************************************/
$("input[name='c-chk-agree']").click(function(){
	
	var total = $("input[name='c-chk-agree']").length;
	var checked = $("input[name='c-chk-agree']:checked").length;
	
	if(total != checked){
		$("#c-chk-circle").prop("checked",false);
	}else if(total == checked){
		$("#c-chk-circle").prop("checked",true);
	}
});	
	
/*************************************************************************
							회원가입 모달창
*************************************************************************/
/** Agreement **/
// Agreement
$("a").click(function(){
    $("#modal").css("display", "block");
	
    if($(this).attr("id") == "detail1"){
    	$("#agreement-content").html($("div.detail1").html());
    }
    else if($(this).attr("id") == "detail2"){
    	$("#agreement-content").html($("div.detail2").html());
    }else {
    	$("#modal").css("display", "none");
    }
});

	
// Agreement-btn
$(document).on("click", "button[name='btn-agreement']", function(){
	$("#modal").css("display", "none");
});
/*************************************************************************
							아이디 중복체크
*************************************************************************/
$("#check-btn-style").click(function(){
		
	if($("#input-id").val()=="") {
		$("#idMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else if(!idCheck2($("input[name='member_id']").val())){
			$("#idMsg").text("영문 또는 숫자로 작성해주세요").css("color","red").css("font-size","11px").css("display","inline");
	}else if(!idCheck3($("input[name='member_id']").val())) {
			$("#idMsg").text("5~10자리로 작성해주세요").css("color","red").css("font-size","11px").css("display","inline");
	}else {
		$.ajax({
			url : "id_check.?member_id="+$("#input-id").val(),
			success : function(result) {
				if(result == 1){
						$("#idMsg").text("이미 사용중인 아이디 입니다. 다시 입력해주세요")
						.css("color","red").css("font-size","11px").css("display","inline");
						$("input[name='member_id']").val("").focus();
					}else if(result == 0){
						$("#idMsg").text("사용 가능한 아이디 입니다")
						.css("color","blue").css("font-size","11px").css("display","inline");
						$("#input-common-password").focus();
						
					}
				}
		});
	}

});

	
//-----------------------법인---------------------------------
$("#c-check-btn-style").click(function(){
		
	if($("input[name='company_id']").val()=="") {
		$("#c-idMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
	}else if(!idCheck2($("input[name='company_id']").val())) {
			$("#c-idMsg").text("영문 또는 숫자로 작성해주세요").css("color","red").css("font-size","11px").css("display","inline");
	}else if(!idCheck3($("input[name='company_id']").val())) {
			$("#c-idMsg").text("5~10자리로 작성해주세요").css("color","red").css("font-size","11px").css("display","inline");
	}else {
		$.ajax({
			url : "c_id_check?company_id="+$("input[name='company_id']").val(),
			success : function(result) {
				if(result == 1){
						$("#c-idMsg").text("이미 사용중인 아이디 입니다. 다시 입력해주세요")
						.css("color","red").css("font-size","11px").css("display","inline");
						
						$("input[name='company_id']").val("").focus();
					}else if(result == 0){
						$("#c-idMsg").text("사용 가능한 아이디 입니다")
						.css("color","blue").css("font-size","11px").css("display","inline");
						$("#company-password").focus();
					}
				}
		});
	}
});

	
/*************************************************************************
						member	이메일 중복체크
*************************************************************************/
$("input[name='email1'], input[name='email2'], #selectbox-email").blur(function(){
    		
	if($("input[name='email2']").val() == "") {
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
					$("#emailMsg").text("중복된 이메일입니다").css("color","red").css("font-size","11px").css("display","inline");
					return false;
				}else {
					$("#emailMsg").text("").css("display","none");
				}
			}
		});
	}

});	
/*************************************************************************
						company	이메일 중복체크
*************************************************************************/
$("#company-email1, #company-email2, #company-selectbox-email").blur(function(){
    		
	if($("#company-email2").val() == "") {
		$("#c-emailMsg").text("필수항목입니다").css("color","red").css("font-size","11px").css("display","inline");
		return false;
	}else {
		$.ajax({
			url : "c_email_check",
			type: "POST",
			data: {
				email1 : $("#company-email1").val(),
				email2 : $("#company-email2").val()
			},
			
			success : function(result) {
				if(result==1) {
					$("#c-emailMsg").text("중복된 이메일입니다").css("color","red").css("font-size","11px").css("display","inline");
					return false;
				}else {
					$("#c-emailMsg").text("").css("display","none");
				}
			}
		});
	}

});

$("input[name='member_id']").keydown(function() {

    if (event.getModifierState("CapsLock")) {
      $("#capsLockMsg").text("Caps Lock이 켜져 있습니다").css("color", "red").css("font-size", "11px").css("display", "inline");
    } else {
      $("#capsLockMsg").css("display", "none");
    }
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
  
  $("#company").click(function(){
  		$('input[name="email1"], input[name="email2"]').attr('readonly',false);
  });
  
  /********************************************법인*************************************************************/
  
  let code1 = "";
  $("#c-email-btn-style").click(function(){
  	const email = $("#company-email1").val() + "@" + $("#company-email2").val();
  	//alert(email);
  	
  	$.ajax({
  		url : "mailCheck",
  		type: "POST",
  		data: { email : email },
  		
  		success : function(data){
  			alert("인증번호가 전송되었습니다");
  			code1 = data;
  			$('#c-email-auth-check').attr('disabled',false);
  		}
  	});
});

    	
  	$('#c-email-auth-check').on("change",function () {

			const inputCode1 = $(this).val();
			const resultMsg1 = $('#c-emailAuth');
			
			if(inputCode1 === code1){
				resultMsg1.text('인증번호가 일치합니다.');
				resultMsg1.css("font-size","11px").css('color','blue').css("display","inline");
				$('#c-email-auth-check').attr('disabled',true);
				$('#company-email1').attr('readonly',true);
				$('#company-email2').attr('readonly',true);
				$('#company-selectbox-email').attr('disabled',true);
				$('#company-selectbox-email').attr('onFocus', 'this.initialSelect = this.selectedIndex');
		        $('#company-selectbox-email').attr('onChange', 'this.selectedIndex = this.initialSelect');
			}else{
				resultMsg1.text('인증번호가 불일치 합니다. 다시 확인해주세요!.');
				resultMsg1.css("font-size","11px").css('color','rgb(255, 0, 0)').css("display","inline");
			}
	  
  });
  
  		
	
});

document.addEventListener('DOMContentLoaded', function() {
    // URL에서 selectedTab 파라미터 값을 가져옴
    var urlParams = new URLSearchParams(window.location.search);
    var selectedTab = urlParams.get('selectedTab');
    
    // 선택한 탭을 표시
    if (selectedTab) {
        document.getElementById(selectedTab).checked = true;
    }
});
