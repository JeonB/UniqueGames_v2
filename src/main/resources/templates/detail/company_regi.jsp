<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unique Games</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainunigames.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/board.js"></script>
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
      .default-write {
        font-size: 14px;
        border: 1px solid #DDDDDD;
        padding: 12px 16px;
        width: 766px;
      }
      /** board style **/
      #content {
        margin: 100px auto;
      }

      #board-content>table, #board-write>table {
        border-collapse: collapse;
        border-top: 2px solid #393939;
        border-bottom: 2px solid #393939;
      }

      #board-top-menu>p {
        font-size: 24px;
        float: left;
      }

      #board-top-menu>div>label>input {
        width: 215px;
        height: 14px;
        border: 1px solid #DDDDDD;
        padding: 7px 30px 7px 16px;
        font-size: 14px;
      }

      #board-top-menu>div>label {
        position: relative;
        margin-left: 410px;
      }


      #board-top-menu>div>ul>li:last-child>button {
        margin-right: 0;
      }

      #board-top-menu>div>ul>li:last-child>a>button {
        margin-right: 0;
      }

      #board-top-menu>div>ul {
        float: right;
      }

      #board-top-menu>div>ul>li {
        float: left;
        margin-left: 10px;
      }

      #btn-search {
        background: none;
        border: none;
        position: absolute;
        top: -15px;
        right: 10px;
        cursor: pointer;
      }

      /* #btn-search:hover  */



      #paging {
        font-size: 18px;
        list-style-type: none;
        clear: both;
        margin: 0 380px;
      }

      #paging>li {
        float: left;
        padding: 5px;
      }


      /* board - user */
      #user-table th {
        border-bottom: 1px solid #DDDDDD;
      }

      #user-table th,
      td {
        padding: 18px;
        text-align: center;
      }

      #user-btable tr>th:first-child,
      #user-btable tr>td:first-child {
        padding: 8px;
      }

      #user-btable tr>td:nth-child(2) {
        text-align: left;
      }

      #user-btable tr>td:nth-child(3) {
        text-align: center;
      }

      /* board - write */
      #board-write>form>table th {
        padding: 19px 24px;
        font-size: 16px;
        border-bottom: 1px solid #DDDDDD;
      }

      #board-write>form>table td {
        font-size: 14px;
        border-bottom: 1px solid #DDDDDD;
        padding: 8px;
      }

      #default-write {
        font-size: 14px;
        border: 1px solid #DDDDDD;
        padding: 12px 16px;
        width: 766px;
      }

      #board-write>form>table td>input {
        height: 14px;
      }

      #board-write>form>table td>textarea {
        height: 153px;
        resize: none;
      }

      /* board - content */
      #board-content>table th {
        padding: 19px 8px;
        font-size: 16px;
        background: #DDDDDD;
        border-bottom: 1px solid #DDDDDD;
      }

      #board-content>table td {
        font-size: 17px;
        border-bottom: 1px solid #DDDDDD;
        padding: 8px;
      }

      #board-content>table th:first-child {
        text-align: left;
      }

      #board-content>table td:nth-child(2) {
        width: 50%;
      }

      #details {
        padding: 10px 30px;
        text-align: left;
      }

      #clearFix {
        clear: both;
      }



    </style>
</head>

<body>
<%--header--%>
<jsp:include page="../main/header.jsp"></jsp:include>
<section id="top-bg">
    <div id="base-layer">
        <div id="top-bg-textarea">
            <p id="top-title">회사(팀) 소개글</p>
            <p id="top-subtitle">#등록</p>
        </div>
    </div>
</section>
<div id="content">
    <div id="board-write">
        <div id="board-top-menu">
            <p>Game_registration</p>
            <div id="clearFix"></div>
        </div>
        <form  action="insertIntro" method="post" enctype="multipart/form-data">
                <input style="visibility: hidden" type="text" name="c_id" class="default-write"  value="${company.company_id}">
            <table>
                <tr>
                    <th>회사 명</th>
                    <td><input type="text" name="name" class="default-write"  value="${company.name}" readonly></td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" name="title" class="default-write" value="${intro.title}">
                    </td>
                </tr>
                <tr>
                    <th>소개 글</th>
                    <td><textarea type="text" name="content" cols="30" rows="5"
                                  class="default-write">${intro.content}</textarea></td>
                </tr>
                <tr>
                    <th>업로드</th>>
                    <td><input type="file" name ="uploadFile" style="height: 30px;"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" style="height: 30px;" value="새글 등록"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%--footer--%>
<jsp:include page="../main/footer.jsp"></jsp:include>
</body>

</html>