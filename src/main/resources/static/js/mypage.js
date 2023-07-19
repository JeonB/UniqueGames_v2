$(document).ready(function () {

$("#profile-picture-img").change(function(){

    const imageInput = $("#profile-picture-img")[0];

    const formData = new FormData();
    formData.append("image", imageInput.files[0]);

    $.ajax({
        type:"POST",
        url: "profileupload",
        processData: false,
        contentType: false,
        data: formData,
        success: function(rtn){
            // const message = rtn.data.values[0];
            // console.log("message: ", message)
            // $("#").val(message.uploadFilePath)
            console.log(rtn)
            $("input[name='profileImg']").val(rtn);
        },
        err: function(err){
            console.log("err:", err)
        }
    })




})


});