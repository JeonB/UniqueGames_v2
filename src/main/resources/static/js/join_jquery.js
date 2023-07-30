$(document).ready(function() {
	let joinForm = $("#content-2");						let type2 = $("input[name='type2']");
	let id = $("#id");									let idMsg = $("#idMsg");
	let password = $("input[name='password']");			let passwordCheck = $("input[name='password-check']");
	let name = $("input[name='name']");
	let email = $("input[name='email']");				let emailMsg = $("#emailMsg");		let emailAuthCheck = $("#email-auth-check");
	let chkall = $("#chk-circle");						let chkagree = $("input[name='chk-agree']");

	/**개인 제이쿼리*/
	$("#check-btn-style").click(function () {
		if (id.val() == "") {
			idMsg.text("아이디를 입력하세요").css("font-size", "11px").css("color", "red").css("display", "inline");
			id.focus();
			return false;
		} else if (!idCheck1(id.val())) {
			idMsg.text("영문 또는 숫자로 작성해주세요").css("color", "red").css("font-size", "11px").css("display", "inline");
			id.focus();
			return false;
		} else if (!idCheck2(id.val())) {
			idMsg.text("5~10자리로 작성해주세요").css("color", "red").css("font-size", "11px").css("display", "inline");
		} else {
			$.ajax({
				url: "idcheck1",
				type: "POST",
				data: {
					id: id.val(),
					type2: type2.val()
				},
				success: function (result) {
					if (result == 1) {
						$("#idMsg").text("이미 사용중인 아이디 입니다. 다시 입력해주세요")
							.css("color", "rgb(255, 0, 0)").css("font-size", "11px").css("display", "inline");
						id.val("").focus();
					} else if (result == 0) {
						$("#idMsg").text("사용 가능한 아이디 입니다")
							.css("color", "rgb(0, 0, 255)").css("font-size", "11px").css("display", "inline");
						password.focus();
					}
				}
			});
		}
	});

	/**비밀번호, 비밀번호 확인 동일체크 blur*/
	$(password)
		.add(passwordCheck)
		.blur(function () {
			if (passwordCheck.val() == "") {
				$("#pwdMsg-check").text("필수항목입니다").css("color", "red").css("font-size", "11px").css("display", "inline");
			} else if (password.val() != "" && passwordCheck.val() != "") {
				if (password.val() == passwordCheck.val()) {
					$("#pwdMsg-check").text("비밀번호가 서로 동일합니다").css("color", "blue").css("font-size", "11px").css("display", "inline");
				} else {
					$("#pwdMsg-check").text("비밀번호가 서로 동일하지 않습니다").css("color", "red").css("font-size", "11px").css("display", "inline");
				}
			}
		});

	/** Agreement **/
	chkall.click(function () {
		if (chkall.is(":checked"))
			chkagree.prop("checked", true);
		else
			chkagree.prop("checked", false);
	});

	chkagree.click(function () {
		var total = chkagree.length;
		var checked = $("input[name='chk-agree']:checked").length;

		if (total != checked) {
			chkall.prop("checked", false);
		} else if (total == checked) {
			chkall.prop("checked", true);
		}
	});

	/** Agreement - Detail **/
	$("a").click(function () {
		$(".modal").css("display", "block");

		if ($(this).attr("class") == "detail1") {
			$(".agreement-content").html($("div.detail1").html());
		} else if ($(this).attr("class") == "detail2") {
			$(".agreement-content").html($("div.detail2").html());
		} else {
			$(".modal").css("display", "none");
		}
	});

	/**Agreement - Detail - Close*/
	$(document).on("click", "button[name='btn-agreement']", function () {
		$(".modal").css("display", "none");
	});

	$("#join-button").click(function () {
		if (id.val() == "") {
			alert("아이디는 필수 입력 항목입니다");
			id.focus();
			return false;
		} else if (idMsg.text() == "" || idMsg.css("color") == "rgb(255, 0, 0)") {
			alert("아이디 중복체크1를 해주세요");
			id.focus();
			return false;
		} else if (password.val() == "") {
			alert("비밀번호는 필수 입력 항목입니다");
			password.focus();
			return false;
		} else if (!pwdCheck(password.val())) {
			alert("비밀번호는 영문,숫자,특수문자 1글자 이상 조합하여 작성해주세요");
			password.focus();
			return false;
		} else if (passwordCheck.val() == "") {
			alert("비밀번호 확인칸을 입력해주세요");
			passwordCheck.focus();
			return false;
		} else if (password.val() != passwordCheck.val()) {
			alert("비밀번호가 서로 동일하지 않습니다");
			passwordCheck.focus();
			return false;
		} else if (name.val() == "") {
			alert("이름은 필수 입력 항목입니다");
			name.focus();
			return false;
		} else if (!nameCheck(name.val())) {
			alert("이름은 한글로 2~10글자로 작성해주세요");
			name.focus();
			return false;
		} else if (email.val() == "") {
			alert("이메일은 필수 입력 항목입니다");
			email.focus();
			return false;
		} else if (emailMsg.text() != "") {
			alert("이메일을 확인해주세요");
			email.focus();
			return false;
		} else if (emailAuthCheck.val() == "") {
			alert("이메일 인증번호를 입력해주세요");
			emailAuthCheck.focus();
			return false;
		} else if ($("#emailAuth").css("color") === "rgb(255, 0, 0)") {
			alert("이메일 인증번호를 확인해주세요");
			emailAuthCheck.focus();
			return false;
		} else if ($(".checkbox-agreement:checked").length != 3 && !$("#chk-circle").is(":checked")) {
			alert("약간 동의를 체크해주세요");
			$("#chk-circle").focus();
			return false;
		} else {
			joinForm.submit();
		}
	});
});
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
	var regex =  /^[가-힣]{2,10}$/;
	return regex.test(asValue);
}