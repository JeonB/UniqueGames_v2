/* 이미지 클릭 스크립트*/
  $(document).ready(function(){
    $(".screenshot_thumbnail").bind("click",function(){
      var index = $(".screenshot_thumbnail").index(this);
      var content_type = $(".screenshot_thumbnail img").eq(index).attr("data-type");
      var content_url = $(".screenshot_thumbnail img").eq(index).attr("data-url");
      if(content_type=="image/png"){
        var html = content_url;
      }else{
        var html = content_url;
      }
      $('#screenshot_image').html(html);
      $(".screenshot_thumbnail").each(function(i){
        if(i==index){
          $(".screenshot_thumbnail").eq(i).addClass("active");
        }else{
          $(".screenshot_thumbnail").eq(i).removeClass("active");
        }
      });
      return;
    });
  });
  /* sns 스크립트 */
  (function($) {
    $.sns_lib = {
      width : null,
      height : null,
      top : (screen.width-1000)/2,
      left : (screen.height-600)/2,
      title : null,
      url : document.URL,
      img : null,
      summary : null,
      initialize : function(title,img,summary,popup_width,popup_height,url){
        $.sns_lib.title = title;
        $.sns_lib.img = img;
        $.sns_lib.summary = summary;
        $.sns_lib.width = popup_width;
        $.sns_lib.height = popup_height;
        $.sns_lib.url = url;
      },
      go_Facebook : function(){
        var param_title = encodeURIComponent($.sns_lib.title);
        var param_url = encodeURIComponent($.sns_lib.url);
        var param_img = encodeURIComponent($.sns_lib.img);
        var param_summary = encodeURIComponent($.sns_lib.summary);
        var sitePath = "http://www.facebook.com/sharer/sharer.php?s=100&u="+param_url+"&p[title]="+param_title+"&p[images][0]="+param_img+"&p[summary]="+param_summary;
        //var sitePath = "http://www.facebook.com/sharer.php?u="+param_url;
        var a = window.open(sitePath, 'Facebook', 'width='+$.sns_lib.width+',height='+$.sns_lib.height+',left='+$.sns_lib.left+',top='+$.sns_lib.top+',scrollbars=0');
        if(a) { a.focus(); }
      },
      go_Twitter : function(){
        var param_title = encodeURIComponent($.sns_lib.title + " \n" + $.sns_lib.summary);
        var param_url = encodeURIComponent($.sns_lib.url);
        var href = "http://twitter.com/share?_=&count=horizontal&original_referer="+param_url+"&text=" + param_title + "&url=" + param_url;
        var a = window.open(href, 'twitter', 'width='+$.sns_lib.width+',height='+$.sns_lib.height+',left='+$.sns_lib.left+',top='+$.sns_lib.top+',scrollbars=0');
        if(a) { a.focus(); }
      },
      destroy : function(){
        $.sns_lib.title = null;
        $.sns_lib.img = null;
        $.sns_lib.summary = null;
        $.sns_lib.width = null;
        $.sns_lib.height = null;
      },
      isNotReady : function(obj, msg){
        if(obj == null){
          alert(msg);
          return false;
        }else{
          return true;
        }
      }
    };
  })($);


var title = "디 엑시트 이터널 매터스";
var img = "https://directg.net/upload/product/goods_1681697853_opengraph.jpg";
var summary = "죽음을 넘어선 생명을 느끼다사후 세계로의 초현실적인 여정에서 은신 플레이가 가미된 영화 같은 모험을 하며, 신비하고 부서진 기억의 차원에 감춰진 비밀을 밝혀 내세요. 강렬한 분위기가 돋보이는 경이로운 3D 픽셀의 세계로 뛰어들어, 세상을 구원하세요!&nbsp;영화 같은 복셀 그래픽 세계 체험DE-EXIT는 심플하면서도 매끄럽게 구성된 복셀 그래픽뿐만 아니라 현실적인 움직임과 자연스러운 특수 효과, 자세한 배경을 구현해 마치 영화 같은 분위기가 느껴지는 게임입니다.새로운 세계로 떠나는 신비로운 여행호기심을 자아내는 초현실적인 세상에서 깨어난 당신. 과연 여기는 어디일까요? 여기서 무얼 하는 걸까요? 온 세상이 왜 이렇게 달라 보일까요? 무너진 경계 속의 세상에서 혼란은 점점 커져가지만, 희망을 잃지 않는 주민과 함께 떠난다면 걱정할 필요가 없어요. 낯설고 예측할 수 없으면서도 웅장한 풍경 속에서 비밀로 가득한 &#39;기억의 저편&#39;을 재건해보세요.이야기 중심의 게임손에 땀을 쥐게 하는 흥미진진한 이야기를 중심으로 여러 게임 요소를 즐겨보세요. 퍼즐, 잠행, 플랫폼, 액션 요소 모두 다 한 번에 즐길 수 있습니다.게임으로 조망하는 죽음과 사후세계죽음은 터부시되는 소재이지만 DE-EXIT에서는 이 민감한 소재로 새로운 이야기를 시작합니다. 두렵지만 신비로운 죽음이라는 소재를 긍정적인 관점으로 마주해 보세요. 죽음을 기억하며 아름다운 인생을 즐기는 뜻깊은 시간이 될 겁니다.";
var url = "https://directg.net/game/game_page.html?product_code=50004122";
//트위터 공유
$('#btn_twitter').bind("click",function(){
  goTwitter();
});
//페이스북 공유
$('#btn_facebook').bind("click",function(){
  goFacebook();
});
//구글플러스 공유
$('#btn_google').bind("click",function(){
  goGooglePlus();
});
var goFacebook =function(){
  var popup_width = 600;
  var popup_height = 450;
  $.sns_lib.initialize(title,img,summary,popup_width,popup_height,url);
  $.sns_lib.go_Facebook();
  $.sns_lib.destroy();
}
var goTwitter = function(){
  var popup_width = 450;
  var popup_height = 450 ;
  var txt = summary.substr(0, 100)
  $.sns_lib.initialize(title,'',txt,popup_width,popup_height,url);
  $.sns_lib.go_Twitter();
  $.sns_lib.destroy();
}
var goGooglePlus = function(){
  var link_url = "https://directg.net/game/game_page.html?product_code=50004122";
  var url = "https://plus.google.com/share?url="+link_url;
  window.open(url,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600,left=500,top=250');
}

$('#btn_wishlist').bind("click",function (){
  alert("좋아요!");
});

$('#btn_donate').bind("click", function() {
  const gameName = $('#game_name').text();
  if (gameName) {
    $.ajax({
      url: '../detail/popUp',
      method: 'POST',
      data: { gameName: gameName },
      success: function(response) {
        // 서버 응답 처리 로직 작성
        console.log('서버 응답:', response);
        showPopup(); // Ajax 요청 성공 후 팝업 열기
      },
      error: function(xhr, status, error) {
        // 에러 처리 로직 작성
        console.error('에러:', error);
      }
    });
  } else {
    console.log('라디오 버튼을 선택해주세요.');
  }
});

// const companyId = $("#company_id").text(); // HTML 태그의 텍스트 값을 가져옴
// let currentURL = window.location.href;
// let id = currentURL.substring(currentURL.lastIndexOf('/') + 1, currentURL.lastIndexOf(''));
// $.ajax({
//   url: "../detail/"+id,
//   method: "POST",
//   data: { companyId: companyId }, // companyId 값을 서버로 전송
//   success: function(response) {
//     alert("전송 성공")
//     // 서버 응답 처리 로직
//   },
//   error: function(xhr, status, error) {
//     // 에러 처리 로직
//   }
// });

function showPopup() {
  window.open('../detail/popUp', "popup", "width=500, height=500, left=650, top=250");
}


