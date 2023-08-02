$(document).ready(function(){
    let email = $("input[name='email']");           let emailMsg = $("#emailMsg");                  let emailAuth = $("#emailAuth");        let emailAuthCheck = $("#email-auth-check");
    let tel = $(".tel");                            let phoneNum = $("input[name='phoneNum']");     let phoneMsg = $("#phoneMsg");
    let addr1 = $("input[name='addr1']");           let addr2 = $("input[name='addr2']");
    let memberEmail = $("#memberEmail");            let memberPhoneNum = $("#memberPhoneNum");      let memberTel = $("#memberTel");
    let memberAddr1 = $("#memberAddr1");            let memberAddr2 = $("#memberAddr2");
    let companyEmail = $("#companyEmail");          let companyPhoneNum = $("#companyPhoneNum");    let companyTel = $("#companyTel");
    let companyAddr1 = $("#companyAddr1");          let companyAddr2 = $("#companyAddr2");
    let mypageForm = $("form[name='myPageForm']");

    $("#mypage-update").click(function() {
        if(email.val().trim() == "") {
            alert("이메일은 필수 입력 항목입니다");
            email.focus();
            return false;
        }else if(emailAuth.css("color") == "rgb(255, 0, 0)") {
            alert("이메일을 확인해주세요");
            email.focus();
            return false;
        }else if(phoneMsg.css("color") == "rgb(255, 0, 0)") {
            alert("번호를 확인해주세요");
            phoneNum.focus();
            return false;
    	} else {
    		mypageForm.submit();
    	}
    });

    $(email)
        .add(tel)
        .add(phoneNum)
        .add(addr1)
        .add(addr2)
        .change(function(){
            if(email.val() == memberEmail.val() && tel.val() == memberTel.val() && phoneNum.val() == memberPhoneNum.val()
                && addr1.val() == memberAddr1.val() && addr2.val() == memberAddr2.val()) {
                $("#mypage-update").prop("disabled", "true");
            }else if(email.val() == companyEmail.val() && tel.val() == companyTel.val() && phoneNum.val() == companyPhoneNum.val()
                && addr1.val() == companyAddr1.val() && addr2.val() == companyAddr2.val()) {
                $("#mypage-update").prop("disabled", "true");
            }else {
                $("#mypage-update").removeAttr("disabled");
            }

            if(tel.val() == "" && phoneNum.val() == "") {
                phoneMsg.css("color", "initial").css("display", "none");
            }
    });

    $(email).change(function(){
        if (email.val() !== "" && emailAuthCheck.val() === "") {
            // email 값이 변경되고, email-auth-check 값이 null (빈 문자열)인 경우 validation check 실패
            // 여기서 필요한 validation 체크를 수행하면 됩니다.
            if(email.val() == memberEmail.val() || email.val() == companyEmail.val()) {
                emailAuth.css("color", "initial").css("display", "none");
            } else {
                emailAuth.text("이메일 인증번호를 입력하세요").css("color", "red").css("font-size","11px").css("display", "inline");
            }
        }
    });
});