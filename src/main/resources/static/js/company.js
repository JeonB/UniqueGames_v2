/*<![CDATA[*/
new Swiper('.swiper', {
  autoplay: {
    delay: 4000
  },
  loop: true,
  slidesPerView: 4,
  spaceBetween: 20,
  // centeredSlides: true,
  pagination: {
    el: '.swiper-pagination',
    clickable: true
  },
  navigation: {
    prevEl: '.swiper-button-prev',
    nextEl: '.swiper-button-next'
  }
})
/* 수정, 삭제 버튼 스크립트*/
const element = document.getElementById('board-top-menu');
const loginMember = /*[[${session.getAttribute("loginMember")}]]*/ null;
if(loginMember.includes('Member') || loginMember === 'null'){
  element.style.visibility = 'hidden';
}
else if(loginMember.includes('Company')){
  $('button[name="listUpdate"]').on("click", function() {
    location.href = "../detail/updateIntro";
  });
  $('button[name="listDelete"]').on("click", function() {
    location.href = "../detail/deleteIntro";
  });
}
/*]]>*/