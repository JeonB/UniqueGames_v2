$(document).ready(function() {

	var memberId = $("input[name='memberId']");
	var memberPass = $("#mpass");
	var idMsg = $("#idMsg");

	$("#check-btn-style").click(function() {
		if (memberId.val() == "") {
			idMsg.text("아이디를 입력하세요").css("font-size","11px").css("color","red").css("display","inline");
			memberId.focus();
			return false;
		} else if (!idCheck1(memberId.val())){
			idMsg.text("영문 또는 숫자로 작성해주세요").css("color","red").css("font-size","11px").css("display","inline");
			memberId.focus();
			return false;
		} else if (!idCheck2(memberId.val())){
			idMsg.text("5~10자리로 작성해주세요").css("color","red").css("font-size","11px").css("display","inline");
		} else {

			const memberId = $("input[name='memberId']");


			$.ajax({
				url: "idcheck",
				type: "get",
				data: {
					member_id : memberId.val()
				},
				success: function(result) {
					if(result == 1){
						$("#idMsg").text("이미 사용중인 아이디 입니다. 다시 입력해주세요")
							.css("color","red").css("font-size","11px").css("display","inline");
						$("input[name='member_id']").val("").focus();
					}else if(result == 0){
						$("#idMsg").text("사용 가능한 아이디 입니다")
							.css("color","blue").css("font-size","11px").css("display","inline");
						$("#mpass").focus();
					}
				}
			});
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