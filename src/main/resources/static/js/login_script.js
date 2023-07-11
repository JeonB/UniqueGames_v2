function memberIdPassCheck() {
	var memberId = document.querySelector("input[name='memberId']");
	var password = document.querySelector("input[name='password']");
	var memberForm = document.getElementById("individual-loginForm");
	if(memberId.value === "") {
		alert("아이디는 필수입니다");
		memberId.focus();
		return false;
	}else if(password.value=="") {
		alert("비밀번호는 필수입니다");
		password.focus();
		return false;
	}
	if(memberForm != null) {
		memberForm.submit();
	}
	return true;
}

function memberIdDuplicate() {
	var memberId = document.querySelector("input[name='memberId']");
	var idMsg = document.getElementById("idMsg");

	if (memberId.value === "") {
		idMsg.textContent = "필수항목입니다";
		idMsg.style.color = "red";
		idMsg.style.fontSize = "11px";
		idMsg.style.display = "inline";
		memberId.focus();
		return false;
	} else if (!idCheck1(memberId.value)) {
		alert("영문")
		idMsg.textContent = "영문 또는 숫자로 작성해주세요";
		idMsg.style.color = "red";
		idMsg.style.fontSize = "11px";
		idMsg.style.display = "inline";
		memberId.focus();
		return false;
	} else if (!idCheck2(memberId.value)) {
		alert("5자리")
		idMsg.textContent = "5~10자리로 작성해주세요";
		idMsg.style.color = "red";
		idMsg.style.fontSize = "11px";
		idMsg.style.display = "inline";
		memberId.focus();
		return false;
	} else {
		alert("else")
		var request = new XMLHttpRequest();
		var url = "/idCheck";
		var type = "GET";
		var data = "memberId=" + encodeURIComponent(memberId.value);

		request.open(type, url + "?" + data, true);
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		request.onload = function() {
			if (request.status === 200) {
				var result = request.responseText;
				if (result === "1") {
					alert(memberId.value);
					idMsg.textContent = "이미 사용중인 아이디 입니다. 다시 입력해주세요";
					idMsg.style.color = "red";
					idMsg.style.fontSize = "11px";
					idMsg.style.display = "inline";
					memberId.value = "";
					memberId.focus();
				} else if (result === "0") {
					alert(memberId.value+"1231231123");
					idMsg.textContent = "사용 가능한 아이디 입니다";
					idMsg.style.color = "blue";
					idMsg.style.fontSize = "11px";
					idMsg.style.display = "inline";
					// document.querySelector("input[name='password']").focus();
				}
			}
		};
		request.send();
	}
}

function memberPassValid(){ //회원가입, 비밀번호 변경 페이지
	var password = document.querySelector("input[name='password']");

	if(!pwdCheck(password.value)){
		alert("비밀번호는 영문,숫자,특수문자 1글자 이상 조합하여 작성해주세요");
		password.focus();
		return false;
	}
	return true;
}

function memberPassCheck() { //회원가입, 비밀번호 변경 페이지
	var passwordCheck = document.querySelector("input[name='password-check']");

	if (passwordCheck.value === "") {
		alert("비밀번호 확인은 필수입니다");
		passwordCheck.focus();
		return false;
	}
	return true;
}

function memberPassVisible() { //회원가입, 비밀번호 변경 페이지
	var isChecked = document.getElementById("pwd-check-img").checked;
	var password = document.querySelector("input[name='password']");

	if (!isChecked) {
		password.type = "password";
	} else {
		password.type = "text";
	}
}

function memberPassCheckVisible() { //회원가입, 비밀번호 변경 페이지
	var isChecked = document.getElementById("pwd-check-img-1").checked;
	var passwordCheck = document.querySelector("input[name='password-check']");

	if (!isChecked) {
		passwordCheck.type = "password";
	} else {
		passwordCheck.type = "text";
	}
}

function memberNameCheck(){ //회원가입, 개인 마이페이지
	var name = document.querySelector("input[name='name']");

	if(name.value === "") {
		alert("이름은 필수입니다");
		name.focus();
		return false;
	}else if(!nameCheck(name.value)){
		alert("이름은 한글로 2~4글자로 작성해주세요");
		name.focus();
		return false;
	}
	return true;
}

function findPwd() {
	var id = document.getElementById("input-common-id");
	var name = document.getElementById("input-common-name");
	var phoneNum = document.getElementById("input-common-phone");

	if(id.value === "") {
		alert("아이디는 필수입니다");
		id.focus();
		return false;
	}else if(name.value === "") {
		alert("이름은 필수입니다");
		name.focus();
		return false;
	}else if(phoneNum.value === "") {
		alert("휴대전화는 필수입니다");
		phoneNum.focus();
		return false;
	}

	findPwdForm.submit();
}


function memberEmailCheck(){ //회원가입, 개인, 법인 마이페이지
	var email1 = document.querySelector("input[name='email1']");
	var email2 = document.querySelector("input[name='email2']");
	var email3 = document.querySelector("#selectbox-email");

	if(email1.value === "") {
		alert("이메일은 필수입니다");
		email1.focus();
		return false;
	}else if(email2.value === "") {
		alert("이메일은 필수입니다");
		email2.focus();
		return false;
	}else if(email3.value === "default") {
		alert("이메일은 필수입니다");
		email3.focus();
		return false;
	}else if(!emailCheck(email1.value)){
		alert("이메일은 영문 또는 숫자로 4자리 이상 입력해주세요");
		email1.focus();
		return false;
	}
	return true;
}
/* 옮기기 */
function handleEmailBlur() { //회원가입, 개인, 법인 마이페이지
	var email1 = document.querySelector("input[name='email1']");
	var email2 = document.querySelector("input[name='email2']");
	var emailMsg = document.getElementById("emailMsg");

	if (email1.value === "" || email2.value === "") {
		emailMsg.textContent = "필수항목입니다";
		emailMsg.style.color = "red";
		emailMsg.style.fontSize = "11px";
		emailMsg.style.display = "inline";
		return false;
	} else {
		var request = new XMLHttpRequest();
		var url = "email_check.do";
		var params = "email1=" + encodeURIComponent(email1.value) + "&email2=" + encodeURIComponent(email2.value);
		request.open("POST", url, true);
		request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

		request.onload = function() {
			if (request.status === 200) {
				var result = request.responseText;
				if (result === "1") {
					emailMsg.textContent = "중복된 이메일입니다";
					emailMsg.style.color = "red";
					emailMsg.style.fontSize = "11px";
					emailMsg.style.display = "inline";
					return false;
				} else {
					emailMsg.textContent = "";
					emailMsg.style.color = "blue";
					emailMsg.style.display = "none";
				}
			}
		};

		request.send(params);
	}
}

var code = "";

function sendEmail() { //회원가입, 개인, 법인 마이페이지
	var email = document.querySelector("input[name='email']").value;

	var request = new XMLHttpRequest();
	request.open("POST", "mailCheck", true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	request.onreadystatechange = function () {
		if (request.readyState === 4 && request.status === 200) {
			alert("인증번호가 전송되었습니다");
			alert(email);
			code = request.responseText;
			document.querySelector("#email-auth-check").disabled = false;
		}
	};

	request.send("email=" + email);

}

function checkEmailAuth() { //회원가입, 개인, 법인 마이페이지
	var inputCode = document.querySelector('#email-auth-check').value;
	var resultMsg = document.querySelector('#emailAuth');

	if (inputCode === code) {
		resultMsg.textContent = '인증번호가 일치합니다.';
		resultMsg.style.fontSize = '11px';
		resultMsg.style.color = 'blue';
		resultMsg.style.display = 'inline';
		document.querySelector('#email-auth-check').disabled = true;
		document.querySelector('input[name="email"]').readOnly = true;
	} else {
		resultMsg.textContent = '인증번호가 불일치합니다. 다시 확인해주세요!';
		resultMsg.style.fontSize = '11px';
		resultMsg.style.color = 'rgb(255, 0, 0)';
		resultMsg.style.display = 'inline';
	}
}

function memberPhoneCheck() { //회원가입, 개인, 법인 마이페이지
	var tel = document.querySelector("#selectbox-mobile");
	var phone1 = document.querySelector("#selectbox-phone");
	var phone2 = document.querySelector("input[name='phone2']");
	var phone3 = document.querySelector("input[name='phone3']");

	if(tel.value === "default") {
		alert("휴대전화는 필수입니다");
		tel.focus();
		return false;
	}else if(phone1.value === "default") {
		alert("휴대전화는 필수입니다");
		phone1.focus();
		return false;
	}else if(phone2.value === "") {
		alert("휴대전화는 필수입니다");
		phone2.focus();
		return false;
	}else if(phone3.value === "") {
		alert("휴대전화는 필수입니다");
		phone3.focus();
		return false;
	}else if(!phoneCheck(phone2.value)) {
		alert("휴대전화는 숫자 3,4자리로 입력해주세요");
		phone2.focus();
		return false;
	}else if(!phoneCheck(phone3.value)){
		alert("휴대전화는 숫자 3,4자리로 입력해주세요");
		phone3.focus();
		return false;
	}
	return true;
}

function memberPhoneCheck1() { //?
	var phoneNum = document.querySelector("input[name='phoneNum']");

	if(phoneNum.value=="") {
		alert("휴대전화는 필수입니다");
		phoneNum.focus();
		return false;
	}
}

/*옮기기*/
function handlePhoneBlur() { //회원가입, 개인, 법인 마이페이지
	var phone1 = document.querySelector(".selectbox-phone");
	var phone2 = document.querySelector("input[name='phone2']");
	var phone3 = document.querySelector("input[name='phone3']");
	var phoneMsg = document.getElementById("phoneMsg");

	if(phone1.value === "default" || phone2.value === "" || phone3.value === "") {
		phoneMsg.textContent = "필수항목입니다";
		phoneMsg.style.color = "red";
		phoneMsg.style.fontSize = "11px";
		phoneMsg.style.display = "inline";
		return false;
	} else {
		var request = new XMLHttpRequest();
		var url = "phone_check.do";
		var params = "phone1=" + encodeURIComponent(phone1.value)
			+ "&phone2=" + encodeURIComponent(phone2.value)
			+ "&phone3=" + encodeURIComponent(phone3.value);
		request.open("POST",url,true);
		request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

		request.onload = function() {
			if(request.status === 200) {
				var result = request.responseText;
				if(result === "1") {
					phoneMsg.textContent = "중복된 번호입니다";
					phoneMsg.style.color = "red";
					phoneMsg.style.fontSize = "11px";
					phoneMsg.style.display = "inline";
					return false;
				} else {
					phoneMsg.textContent = "";
					phoneMsg.style.color = "blue";
					phoneMsg.style.display = "none";
				}
			}
		};
		request.send(params);
	}
}
/*******************************정규식******************************************/
function idCheck1(asValue) {
	var regex1 = /^[a-zA-Z0-9]*$/;
	return regex1.test(asValue);
}
function idCheck2(asValue) {
	var regex2 = /^[a-zA-Z0-9]{5,10}$/;
	return regex2.test(asValue);
}
function pwdCheck(asValue) {
	var regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/g;
	return regex.test(asValue);
}
function nameCheck(asValue) {
	var regex =  /^[가-힣]{2,4}$/;
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
/********************이메일 선택 시 input창 자동 입력**************************/

function handleEmailSelection() { //회원가입, 개인, 법인 마이페이지
	var selectbox = document.getElementById("selectbox-email");
	var email2 = document.querySelector("input[name='email2']");
	var emailMsg = document.getElementById("emailMsg");

	if (selectbox.value === "default") {
		email2.value = "";
		emailMsg.textContent = "이메일을 선택해주세요";
		emailMsg.style.color = "red";
		emailMsg.style.fontSize = "11px";
		emailMsg.style.display = "inline";
		selectbox.focus();
		return false;
	} else if (selectbox.value === "direct") {
		email2.value = "";
		email2.focus();
	} else {
		email2.value = selectbox.value;
	}
}
/*********************************개인 회원가입; 전체 동의****************************************/
function agreeAll() { //회원가입
	var chkCircle = document.getElementById("chk-circle");
	var chkAgree = document.querySelectorAll("input[name='chk-agree']");

	if(chkCircle.checked) {
		chkAgree.forEach(function(element) { element.checked = true; });
	} else {
		chkAgree.forEach(function(element) { element.checked = false; });
	}
}

function updateCheckbox() { //회원가입
	var checkboxes = document.querySelectorAll("input[name='chk-agree']");
	var total = checkboxes.length;
	var checked = document.querySelectorAll("input[name='chk-agree']:checked").length;

	if (total !== checked) {
		document.getElementById("chk-circle").checked = false;
	} else if (total === checked) {
		document.getElementById("chk-circle").checked = true;
	}
}

function memberValidation() { //회원가입
	var memberId = document.querySelector("input[name='memberId']");
	var password = document.querySelector("input[name='password']");
	var passwordCheck = document.querySelector("input[name='password-check']");
	var email1 = document.querySelector("input[name='email1']");
	var phone1 = document.querySelector(".selectbox-phone");

	var idMsg = document.getElementById("idMsg");
	var pwdMsg = document.getElementById("pwdMsg");
	var emailMsg = document.getElementById("emailMsg");
	var phoneMsg = document.getElementById("phoneMsg");
	var emailAuth = document.querySelector('#emailAuth');

	var checkboxes = document.querySelectorAll("#checkbox-agreement:checked");
	var total = checkboxes.length;
	var checked = document.querySelector("input[name='agreementAll']:checked");
	var loginForm = document.getElementById("content-2");

	if (!memberIdPassCheck()) {
		return false;
	}else if(password.value !== passwordCheck.value){
		pwdMsg.textContent = "비밀번호가 일치하지 않습니다";
		pwdMsg.style.color = "red";
		pwdMsg.style.fontSize = "11px";
		pwdMsg.style.display = "inline";
		alert("비밀번호가 일치하지 않습니다");
		passwordCheck.focus();
		return false;
	}else if(!memberNameCheck()){
		pwdMsg.style.display = "none";
		return false;
	}else if(!memberPhoneCheck()){
		return false;
	}else {
		loginForm.submit();
	}
}

function companyIdPassCheck() {
	var companyId = document.querySelector("input[name='companyId']");
	var password = document.getElementById("companyPass");
	var companyForm = document.getElementById("company-loginForm");

	if(companyId.value === "") {
		alert("아이디는 필수입니다");
		companyId.focus();
		return false;
	}else if(password.value === "") {
		alert("비밀번호는 필수입니다");
		password.focus();
		return false;
	}
	if(companyForm != null) {
		companyForm.submit();
	}
	return true;
}

function changePassword() {

	var changePassForm = $("#changePassForm");

	if ($("input[name='mnewpassword']").val() == "") {
		alert("새 비밀번호를 입력하세요");
		$("input[name='mnewpassword']").focus();
		return false;
	} else if ($("input[name='mnewpassword']").val() != $("input[name='mnewpassword-check']").val()) {
		alert("비밀번호가 일치하지 않습니다");
		$("input[name='mnewpassword-check']").focus();
		return false;
	} else {
		changePassForm.submit();
	}
}

document.addEventListener('DOMContentLoaded', function() {


	var urlParams = new URLSearchParams(window.location.search);
	var selectedTab = urlParams.get('selectedTab');

	if (selectedTab) {
		document.getElementById(selectedTab).checked = true;
	}

	// document.getElementById("selectbox-email").addEventListener("change", handleEmailSelection);
	// document.querySelector("input[name='email2']").addEventListener("change", function(){
	// 	document.getElementById("selectbox-email").value = "default"
	// });
	// document.querySelectorAll("input[name='email1'], input[name='email2'], #selectbox-email").forEach(function(element) {
	// 	element.addEventListener("blur", handleEmailBlur);
	// });
	// document.querySelectorAll("#selectbox-phone, input[name='phone2'], input[name='phone3']").forEach(function(element) {
	// 	element.addEventListener("blur", handlePhoneBlur);
	// });
	document.querySelector('#email-auth-check').addEventListener('change', checkEmailAuth);
	document.querySelectorAll("input[name='chk-agree']").forEach(function(checkbox) {
		checkbox.addEventListener("click", updateCheckbox);
	});

});
