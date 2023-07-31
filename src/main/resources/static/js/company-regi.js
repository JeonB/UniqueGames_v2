$("#editorFormSubmit").click(function(event){

  let form = $("#editorForm")[0];
  let formData = new FormData(form);

  let action = "/insertIntro";
  if($(location).attr('href').includes("/editIntro") === true){
    action = "/editIntro";
  }

  $.ajax({
    url: action,
    type: "POST",
    data: formData,
    processData: false, // jQuery가 데이터를 프로세싱하는 것을 방지
    contentType: false, // jQuery가 데이터 타입을 설정하는 것을 방지
    success: function(result) {
      console.log(result);
      location.href="../getIntroList";
    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.error("Error submitting the form: " + textStatus, errorThrown);
    }
  });
});


$("#addImg").on("click", function(){
  $("#fileUploadInput").click();

  $('#fileUploadInput').on('change', function() {
    const input = this;
    if (input.files && input.files[0]) {
      const reader = new FileReader();

      // FileReader의 로드 이벤트를 사용하여 미리보기 이미지를 변경
      reader.onload = function(e) {
        $('#addImg').attr('src', e.target.result);
      };

      reader.readAsDataURL(input.files[0]);
    }
    // 업로드된 파일의 이름을 추출하여 출력
    const filename = $(this).val().split('\\').pop();
    $('#filename').text(filename);
  });
});