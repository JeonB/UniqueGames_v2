<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unique Games</title>
    <link rel="stylesheet" type="text/css" href="/css/mainunigames.css">
	<style>
		#btn-hearder-search {
			border:none;
			background-color: rgba( 255, 255, 255, 0 );
			width: 40px;
		    padding: 4px 6px 4px 6px;
		    
		}
		#input-search {
	        background-image: url('/images/icon_search_header.png');
	        background-repeat: no-repeat;
	        background-position: 15px center; 
	        background-size: 16px; 
	        width: 360px;
	        height: 46px;
	        padding: 2px 2px 2px 40px;
	        position: relative;
	        z-index: 2;
	        border-radius: 40px;
	        border: 1px solid #707070;
	    }
	
	    .search-menu {
	        position: absolute;
	        float: right;
	        top: 60px;
	        right: 170px;
   		}
        #member_id_header {
            font-family: "YouandiModern", sans-serif;
            font-size: 14px;
            color: #ffffff;
            width: 400px;
            text-align: right;
            padding: 5px;
            padding-right: 10px;
        }
        #logout_header {
            padding-left:10px ;
            width: 80px;
        }
	</style>
</head>

<body>
    <header>
        <div id="header-top-menu">
            <c:choose>
                <c:when test="${sessionScope.loginMember.name == null}">
                <nav id="nav1">
                    <ul>
                        <li><a href="/login">Login</a></li>
                        <li><a href="/join">Join</a></li>
<%--                        <li><a href="/myPage"><img src="/images/img_icon_mypage.png"></a></li>--%>
<%--                        <li><a href="/cart?m_id=mtest"><img src="/images/img_icon_cart.png"></a></li>--%>
                        <li><button type="button" id="btn-hearder-search" onclick="toggleSearch()"><img src="/images/img_icon_search.png"></button></li>
                    </ul>
                </nav>
                </c:when>
                <c:otherwise>
                    <nav id="nav1">
                        <ul>
                            <li id="member_id_header">${sessionScope.loginMember.name}님 반갑습니다.</li>
                            <li id="logout_header"><a href="/logout" target="_parent">Logout</a></li>
<%--                            <li><a href="/join">Join</a></li>--%>
                            <li><a href="/myPage"><img src="/images/img_icon_mypage.png"></a></li>
                            <c:if test="${sessionScope.login == 'member' }">
                            	<li><a href="/cart"><img src="/images/img_icon_cart.png"></a></li>
                            </c:if>
                            <li><button type="button" id="btn-hearder-search" onclick="toggleSearch()"><img src="/images/img_icon_search.png"></button></li>
                        </ul>
                    </nav>
                </c:otherwise>
            </c:choose>
<%--            <div class="search-menu" id="search-container">--%>
<%--    			<input type="text" id="input-search" placeholder="검색하실 게임 이름을 입력해주세요.">--%>
<%--    		</div>--%>
        </div>
        <div id="hearder-logo">
            <a href="/" target="_parent">
                <img src="/images/img_title_logo.png">
            </a>
        </div>
        <div id="header-bottom-menu">
            <nav id="nav2">
                <ul>
                    <li><a href="/topgame">Top Game</a></li>
                    <li><a href="/alllist">All List</a></li>
                    <li><a href="/#menu-main1">Recommendations</a></li>
                    <li><a href="/notice_list">Notice</a></li>
                    <li><a href="/detail/getIntroList">Team Intro</a></li>
                </ul>
            </nav>
        </div>
    </header>
<%--    <script>--%>
<%--        // 검색창 토글 함수--%>
<%--        function toggleSearch() {--%>
<%--            var searchContainer = $('#search-container');--%>
<%--            searchContainer.toggle();--%>
<%--        }--%>

<%--        // 엔터 키 이벤트 핸들러--%>
<%--        $(document).on('keydown', '#input-search', function(event) {--%>
<%--            if (event.which === 13) {--%>
<%--                var inputText = $(this).val();--%>
<%--                console.log('입력한 텍스트:', inputText);--%>
<%--                // 입력한 텍스트를 원하는 방식으로 처리할 수 있습니다.--%>
<%--                // 예: 서버로 전송, 검색 실행 등--%>
<%--            }--%>
<%--        });--%>

<%--        // 페이지 로드 시 검색창 초기화--%>
<%--        $(document).ready(function() {--%>
<%--            $('#search-container').hide();--%>
<%--        });--%>
<%--    </script>--%>
</body>
</html>