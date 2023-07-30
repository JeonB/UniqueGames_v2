$(document).ready(function(){
    let email = $("input[name='email']");           let emailMsg = $("#emailMsg");
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
        }else if(emailMsg.css("color") == "rgb(255, 0, 0)") {
            alert("이메일을 확인해주세요");
            email.focus();
            return false;
        }else if (phoneNum.val().trim() != "") {
    		if (!mypagePhoneCheck(phoneNum.val())) {
    			alert("번호는 -를 제외하고 10,11자로 작성해주세요");
    			phoneNum.focus();
    			return false;
    		}else if(phoneMsg.css("color") == "rgb(255, 0, 0)") {
                alert("번호를 확인해주세요");
                phoneNum.focus();
                return false;
            }else {
    			mypageForm.submit();
    		}
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
    });
});
function mypagePhoneCheck(asValue) {
    var regex = /^\d{10,11}$/;
    return regex.test(asValue);
}