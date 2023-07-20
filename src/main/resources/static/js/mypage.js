$(document).ready(function () {

$("#profile-picture-img").change(function(){
    $("#mypage-update").removeAttr("disabled");
    const imageInput = $("#profile-picture-img")[0];
    let file = $("#profile-picture-img")[0].files[0];

    const formData = new FormData();
    formData.append("image", imageInput.files[0]);

    $.ajax({
        type:"POST",
        url: "profileupload",
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




})


});