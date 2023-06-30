<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회사 소개페이지 리스트</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainunigames.css">
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
    <style>
      .btn-style{
        background: #393939;
        border-radius: 5px;
        width: 80px;
        height: 30px;
        color: #FFFFFF;
        font-size: 14px;
        vertical-align: top;
        cursor: pointer;
      }
    </style>
</head>
<body>
<%--header--%>
<jsp:include page="../main/header.jsp"></jsp:include>
<section id="top-bg">
    <div id="base-layer">
        <div id="top-bg-textarea">
            <p id="top-title">All Company List</p>
            <p id="top-subtitle">#전체 회사(개발팀)목록</p>
        </div>
    </div>
</section>
<section id="menu-main2">
    <div id="board-top-menu">
        <div>

            <ul>
                <li><button type="button" class="btn-style" name="listWrite">작성</button></li>
                <li><button type="button" class="btn-style" name="listUpdate">수정</button></li>
                <li><button type="button" class="btn-style" name="listDelete">삭제</button></li>
                <li><button type="button" class="btn-style" name="listDeleteAll">전체삭제</button></li>
            </ul>
        </div>
    </div>
    <div id="menu-allList">
        <div id="gameList">
            <c:forEach items="${companyList}" var="intro">
                <div id="gList">

                    <img src="${pageContext.request.contextPath}/upload/${intro.upload}" width="260px" height="180px">
                    <ul>
                        <li><a href="getIntro?id=${intro.id}" style="cursor: pointer">${intro.name}</a></li>
                        <li>${intro.id}</li>
                        <li><img src="${pageContext.request.contextPath}/images/img_icon_rPc.png"></li>
                    </ul>
                </div>
            </c:forEach>
        </div>
    </div>

</section>
<%--footer--%>
<jsp:include page="../main/footer.jsp"></jsp:include>
<script>

  <%-- 회원의 타입에 따라 해당 메뉴 출력 여부 구현 --%>
  const element = document.getElementById('board-top-menu');
  const loginMember = '<%=session.getAttribute("loginMember")%>';
  if(loginMember.includes('MemberVo') || loginMember === 'null'){
    element.style.visibility = 'hidden';
  }else if(loginMember.includes('CompanyVo')  && '${status}' === 'writeOnce'){
    $('button[name="listWrite"]').on("click", function() {
      alert("글 작성은 한번만 가능합니다!");
    });
  }else if(loginMember.includes('CompanyVo')){
    $('button[name="listWrite"]').on("click", function() {
      location.href = "../detail/insertIntro";
    });
  }

</script>
</body>
</html>
