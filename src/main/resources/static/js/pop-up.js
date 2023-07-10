$(document).ready(function() {
  $('#btn_donate').click(function() {
    const selectedValue = $('input[name="listGroupRadioGrid"]:checked').val();
    const sessionAccount = '${sessionScope.loginMember}';
    let parentURL = window.opener.location.href; // 부모 창의 URL 가져오기
    let id = parentURL.substring(parentURL.lastIndexOf('/') + 1, parentURL.lastIndexOf('')); // 사이트 동적 처리하기 위해 숫자 추출
    if (sessionAccount.includes("MemberVo")) {
      $.ajax({
        url: '../cart',
        method: 'POST',
        data: { selectedValue: selectedValue },
        success: function(response) {
          // 서버 응답 처리 로직 작성
          window.close();
          window.opener.location.href = '../cart';
        },
        error: function(xhr, status, error) {
          // 에러 처리 로직 작성
          alert('에러가 발생했습니다: ' + error);
        }
      });
    } else if(sessionAccount.includes("CompanyVo")){
      alert("일반 회원만 후원 가능합니다!");
    }
    else {
      alert("로그인 먼저 진행해 주세요!");
      window.close();
      window.opener.location.href = '../login?redirectURL=detail/' + id + '';
    }
  });
});