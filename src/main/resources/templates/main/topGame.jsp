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
            <p id="top-title">Top Game</p>
            <p id="top-subtitle">#게임 순위</p>
        </div>
    </div>
</section>
<section id="menu-main2">
    <div id="menu-allList">
        <div id="gameList">
            <p id="intro-allList">게임 순위</p>
            <div>
                <div>
                    <c:forEach var="ranking" items="${ranking }" varStatus="loop">
                        <c:if test="${loop.count <= 10}">
                            <div id="tList-detail">
                                <img alt="#" src="${ranking.image_path } " width="500px" height="240px">
                                <div>
                                    <p>${loop.count} </p>
                                    <p id="cyber">${ranking.name}</p>
                                    <p>${ranking.description }</p>
                                    <button id="btn-readmore-topGame" type="button">자세히보기</button>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>