$(document).ready(function () {

$("#profile-picture-img").change(function(){
    $("#mypage-update").removeAttr("disabled");
    const imageInput = $("#profile-picture-img")[0];
    const param = $("input[name='test']").val();

    let file = $("#profile-picture-img")[0].files[0];

    let imageUrl = URL.createObjectURL(file);
    $(".mypage-profile").css("margin-top", "45px").css("background", "#FBFBFB");
    $(".profile-picture").css("display", "none").css("margin-left", "-20px");
    $("#test2").attr("src", imageUrl).css("width", "150px").css("height", "60px")
        .css("display", "inline-block").css("margin-top", "20px");

    const formData = new FormData();
    formData.append("image", imageInput.files[0]);

    if (param == "member") {
        $.ajax({
            type:"POST",
            url: "memberprofile",
            processData: false,
            contentType: false,
            data: formData,
            success: function(rtn){
                console.log(rtn)
                let output = "<input type='text' name='profileImgName'>" +
                    "<button type='button' class='cancelProfileButton' name='cancelProfileButton'>취소</button>";
                $("#cancelProfile").html(output);

                $("input[name='profileImgName']").val(file.name).css("width", "100px")
                    .css("padding", "3px").css("margin-right", "5px");
                $(".cancelProfileButton").css("color", "#393939").css("color", "#FFFFFF");

                $(".cancelProfileButton").click(function(){
                    document.getElementById("profile-picture-img").value = null;
                    document.querySelector("input[name='profileImg']").value = "";
                    $("input[name='profileImgName']").remove();
                    $(".cancelProfileButton").remove();
                });
                $("input[name='profileImg']").val(rtn);
            },
            err: function(err){
                console.log("err:", err)
            }
        })
    }else if(param == "company") {

        $.ajax({
            type:"POST",
            url: "companyprofile",
            processData: false,
            contentType: false,
            data: formData,
            success: function(rtn){
                console.log(rtn)
                let output = "<input type='text' name='profileImgName'>" +
                    "<button type='button' class='cancelProfileButton' name='cancelProfileButton'>취소</button>";
                $("#cancelProfile").html(output);

                $("input[name='profileImgName']").val(file.name).css("width", "100px")
                    .css("padding", "3px").css("margin-right", "5px");
                $(".cancelProfileButton").css("color", "#393939").css("color", "#FFFFFF");

                $(".cancelProfileButton").click(function(){
                    document.getElementById("profile-picture-img").value = null;
                    document.querySelector("input[name='profileImg']").value = "";
                    $("input[name='profileImgName']").remove();
                    $(".cancelProfileButton").remove();
                });
                $("input[name='profileImg']").val(rtn);
            },
            err: function(err){
                console.log("err:", err)
            }
        })
    }

})


});