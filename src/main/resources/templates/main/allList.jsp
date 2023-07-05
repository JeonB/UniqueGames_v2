<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unique Games</title>
    <link rel="stylesheet" href="/css/mainunigames.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<section id="top-bg">
    <div id="base-layer">
        <div id="top-bg-textarea">
            <p id="top-title">All List</p>
            <p id="top-subtitle">#전체 게임목록</p>
        </div>
    </div>
</section>
<section id="menu-main2">
    <div id="menu-allList">
        <div id="gameList">
            <c:forEach var="game" items="${gameList}">
                <div id="gList">
                    <img src="${game.image_path } " width="260px" height="180px">
                    <ul>
                        <li><a href ='detail/${game.description}'>${game.name }</a> </li>
                        <li>${game.game_genre}</li>
                        <li><button id="btn-like-style" type="button"data-game-id="${game.id}"></button></li>
                        <li>${game.like_count}</li>
                        <li><img src="/images/img_icon_rPc.png"></li>
                    </ul>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>