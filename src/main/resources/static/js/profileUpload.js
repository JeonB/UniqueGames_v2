$(document).ready(function () {

$("#profile-picture-img").change(function(){
    let file = $("#profile-picture-img")[0].files[0];
    const imageUrl = URL.createObjectURL(file);
    const param = $("input[name='type']").val();
    const originalFile = $("input[name='profileImg']");

    if(param == "join") {
        let output = "<button type='text' class='profileImgConfirm' style='margin-right: 5px' onclick='disapperButton()'>확인</button>" +
            "<button type='button' class='cancelProfileButton' name='cancelProfileButton' onclick='defaultImgNull();'>기본값</button>";
        $(".cancelProfile").html(output).css("text-align", "center");
        $(".profileImgConfirm, .profile-no-change, .cancelProfileButton").css("background", "#393939").css("color", "#FFFFFF");
        $(".join-profile").css("background", "#FFFFFF");
        $(".profile-picture").css("display", "none").css("margin-left", "-20px");
        $("#test2").attr("src", imageUrl).css("width", "150px").css("height", "60px")
            .css("display", "inline-block");

    } else if(param == "mypage") {
        let output = "<button type='text' class='profileImgConfirm' style='margin-right: 5px'>확인</button>" +
            "<button type='button' class='cancelProfileButton' name='cancelProfileButton' style='margin-right: 5px'>기본값</button>" +
            "<button type='button' class='profile-no-change'>변경 안함</button>";
        $(".cancelProfile").html(output).css("text-align", "center");
        $(".profileImgConfirm, .profile-no-change, .cancelProfileButton").css("background", "#393939").css("color", "#FFFFFF");
        $(".mypage-profile, .mypage-cancel").css("margin", "45px 0 0 0").css("background", "#FBFBFB");

        if(originalFile.val() == "") { //프로필 사진 없을 때
            $(".mypage-profile").css("margin", "45px 0 0 45px").css("background", "#FBFBFB");
            $(".profile-picture").css("display", "none").css("margin-left", "-20px");
            $("#test2").attr("src", imageUrl).css("width", "150px").css("height", "60px")
                .css("display", "inline-block");
        }else if(originalFile.val() != "") { //프로필 사진 기존에 있을 때
            $(".profile-picture-1").attr("src", imageUrl).css("width", "150px").css("height", "60px")
                .css("display", "inline-block").css("margin-top", "0px");
        }
        buttonFunction();
    }
})
});

function buttonFunction() {
    let file = $("#profile-picture-img")[0].files[0];
    const originalFile = $("input[name='profileImg']");
    const newProfileFile = $("input[name='newProfileImg']");

    $(".profileImgConfirm").click(function() { //프로필 이미지 변경 확정
        newProfileFile.val(file.name);
        $("#mypage-update").removeAttr("disabled");
        disapperButton();
    });

    $(".profile-no-change").click(function(){ //프로필 이미지 변경 안함

        if(originalFile.val() == "") {
            defaultImgNull();
        }else {
            document.getElementById("profile-picture-img").value = null;
            $(".profile-picture-1").attr("src", "/upload/"+originalFile.val());
            $("#mypage-update").prop("disabled", "true");
        }
        disapperButton();
    });

    $(".cancelProfileButton").click(function(){ //프로필 이미지 기본값
        document.getElementById("profile-picture-img").value = null;
        disapperButton();

        if(originalFile.val() == "") {
            defaultImgNull();
        } else {
            defaultImgNotNull();
            $("#mypage-update").removeAttr("disabled");
        }
    });
}

function defaultImgNull() {
    $(".mypage-profile").css("background", "#D9D9D9");
    $(".profile-picture").css("display", "none").css("margin-left", "20px");
    $("#test2").attr("src", "/images/mypage-profile.png").css("width", "70px").css("height", "70px")
        .css("display", "inline-block");
    document.getElementById("profile-picture-img").value = null;
    disapperButton();
}
function defaultImgNotNull() {
    $(".mypage-cancel").css("width", "110px").css("height", "110px").css("border-radius", "55px").css("background", "#D9D9D9")
        .css("margin", "45px 0 0 45px");
    $(".profile-picture-1").attr("src", "/images/mypage-profile.png").css("margin", "17px 20px").css("width", "70px").css("height", "70px");
    $("input[name='deleteImg']").val("delete");
}

function disapperButton() {
    $(".profileImgConfirm").remove();
    $(".cancelProfileButton").remove();
    $(".profile-no-change").remove();
}