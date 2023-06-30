<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unique Games</title>
    <link rel="stylesheet" href="/css/mainunigames.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <!-- SWIPER 외부 라이브러리 연결-->
    <script>
        document.addEventListener('click', function (event) {
            // 클릭된 요소가 버튼인지 확인
            if (event.target.matches('#btn-like-style')) {
                var gameId = event.target.getAttribute('data-game-id');

                // AJAX 요청을 통해 서버에 좋아요 처리 요청 전송
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '/like', true);
                xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        alert(response.message);
                        // 좋아요 처리 후 추가적인 작업을 수행할 수 있습니다.
                    }
                };
                xhr.send('gameId=' + gameId);
            }
        });
    </script>
</head>
<body>
<!-- <iframe src="./header.html" scrolling="no" width="100%" height="228px" frameborder=0></iframe> -->
<jsp:include page="header.jsp"></jsp:include>
<div class="swiper">
    <div class="swiper-wrapper">
        <div class="swiper-slide">
            <div class="silde1">
                <div id="slide-text">
                    <h2>디 엑시트 이터널 매터스</h2>
                    <h1>DE-EXIT - Eternal Matters</h1>
                    <p>죽음을 넘어선 생명을 느끼다</p>
                    <a href="detail/1"><button id="btn-main-style" type="button" style='cursor:pointer'>자세히보기</button></a>
                </div>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="silde2">
                <div id="slide-text">
                    <h2>스트리트 파이터 6 디럭스 에디션</h2>
                    <h1>Street Fighter 6</h1>
                    <p>시리즈 최신작 『Street Fighter 6(스트리트 파이터 6)』</p>
                    <a href="detail/2"><button id="btn-main-style" type="button" style='cursor:pointer'>자세히보기</button></a>
                </div>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="silde3">
                <div id="slide-text">
                    <h2>뱀파이어: 마스커레이드 - 스완송</h2>
                    <h1>Vampire: The Masquerade</h1>
                    <p>월드 오브 다크니스에 오신 것을 환영합니다</p>
                    <a href="detail/3"><button id="btn-main-style" type="button" style='cursor:pointer'>자세히보기</button></a>
                </div>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="silde4">
                <div id="slide-text">
                    <h2>더 라스트 오브 어스 파트 1 디럭스 에디션</h2>
                    <h1>The Last of Us Part I </h1>
                    <p>살기 위해 오늘도 길을 나선다 탐험 액션</p>
                    <a href="detail/4"><button id="btn-main-style" type="button"  style='cursor:pointer'>자세히보기</button></a>
                </div>
            </div>
        </div>
    </div>

    <div class="swiper-pagination"></div>

    <div class="swiper-button-prev">
        <button class="btn__prev"><img src="/images/btn_main-arrow_left.png"></button>
    </div>
    <div class="swiper-button-next">
        <button class="btn__next"><img src="/images/btn_main-arrow_right.png"></button>
    </div>
</div>
<img id="topGame-title" src="/images/imt_title_top.png" width="135px" height="32px">
<div id="menu-topGame">
    <div id="topGameList">
        <c:forEach var="game" items="${ranking }" varStatus="loop">
            <c:if test="${loop.count <= 4}">
                <div id="tList">
                    <a href="detail/${game.id}"><img alt="#" src="${game.image_path }" width="500px" height="240px"></a>
                    <div>
                        <p>${loop.count} </p>
                        <li id="cyber"><a href ='detail/${game.id}'>${game.name }</a> </li>
                        <p id="cyber2">${game.description }</p>
                        <a href="topgame"><button id="readmore-plus" type="button"><img src="/images/btn_plus_normal.png" width="30px" height="30px"></button><a>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
<section id="menu-main1">
    <img id="recommendations-title" src="/images/img_title_recommendations.png" width="262px" height="26px">
    <p id="subTitle">#최근 반응이 좋은</p>
    <div id="menu-recommendations">
        <div id="recommendList">
            <c:forEach var="game" items="${donation}" varStatus="loop">
                <c:if test="${loop.index < 4}">
                    <div id="rList">
                        <img src="${game.image_path}" width="260px" height="180px">
                        <ul>
                            <li>Hot</li>
                            <li>${game.name}</li>
                            <li>${game.game_genre}</li>
                            <li><img src="/images/img_icon_rPc.png"></li>
                        </ul>
                        <div>
                            <a href="detail/${game.id }">
                                <button id="btn-recommend-style" type="button">자세히보기</button>
                            </a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</section>
<section id="menu-main2">
    <img id="allList-title" src="/images/img_title_AllList.png" width="100px" height="24px">
    <div id="menu-allList">
        <div id="gameList">
            <c:forEach var="game" items="${gameList}">
                <div id="gList">
                    <img src="${game.image_path } " width="260px" height="180px">
                    <ul>
                        <li><a href="detail/${game.id }">${game.name }</a> </li>
                        <li>${game.game_genre}</li>
                        <li><a href="detail/${game.id }"><button id="btn-like-style" type="button"data-game-id="${game.id}"></button></a></li>
                        <li>${game.like_count}</li>
                        <li><img src="/images/img_icon_rPc.png"></li>
                    </ul>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<section id="menu-main3">
    <img id="solution-title" src="/images/img_title_notice.png" width="90px" height="25px">
    <p id="subTitle">#공지사항 #필독</p>
    <div id="menu-solution">
        <table>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
            <c:forEach var="noticeVo" items="${noticeList}">
                <tr>
                    <td>${noticeVo.rno}</td>
                    <td><a href="notice_content?no=${noticeVo.post_id}">${noticeVo.title}</a></td>
                    <td>${noticeVo.company_id}</td>
                    <td>${noticeVo.date_output}</td>
                </tr>
            </c:forEach>
        </table>
        <div id="solution-btn-area">
            <a href="notice_list"><button id="btn-solution" type="button">자세히보기</button></a>
        </div>
    </div>
</section>
<!-- <iframe src="./footer.html" scrolling="no" width="100%" height="646px" frameborder=0></iframe> -->
<jsp:include page="footer.jsp"></jsp:include>
<script>
    new Swiper('.swiper', {
        autoplay: {
            delay: 4000
        },
        loop: true,
        slidesPerView: 1,
        spaceBetween: 0,
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
</script>
</body>
</html>