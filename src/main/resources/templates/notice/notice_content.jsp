<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unique Games</title>
<link rel="stylesheet" href="/css/mainunigames.css">
<link rel="stylesheet" href="/css/board.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script><!-- 마이크로소프트 jQuery-->
<script src="/js/board.js"></script>
<c:set var="loginMember" value='${sessionScope.loginMember}'/>
<script type="text/javascript">
	
</script>
<c:if test="${result != null}">
	<script type="text/javascript">
		let result = '<c:out value="${result}"/>';
		getResult(result);
	</script>
</c:if>
<c:if test="${cmtresult != null}">
	<script type="text/javascript">
		let result = '<c:out value="${cmtresult}"/>';
		getResultCmt(result);
	</script>
</c:if>
</head>
<body>
	<header>
		<jsp:include page="../main/header.jsp"></jsp:include>
	</header>
	<section id="top-bg">
		<div id="base-layer">
			<div id="top-bg-textarea">
				<p id="top-title">Notice</p>
				<p id="top-subtitle">#공지사항</p>
			</div>
		</div>
	</section>
	<div id="content">
		<div id="board-content">
			<div id="board-top-menu">
				<p>Notice</p>
				<div>
					<ul>
						<li><button type="button" id="btn-style" name="update">수정</button></li>
						<li><button type="button" id="btn-style" name="delete">삭제</button></li>
						<li><button type="button" id="btn-style" name="list">목록</button></li>
					</ul>
				</div>
				<div id="clearFix"></div>
			</div>
			<table>
				<tr>
					<th>제목</th>
					<td colspan="5" id="btitle"><c:out value="${noticeVo.title}" /></td>
				</tr>
				<tr>
					<th>등록자</th>
					<td><c:out value="${noticeVo.name}" /></td>
					<th>조회수</th>
					<td style="text-align: center;"><c:out value="${noticeVo.notice_hits}" /></td>
					<th>등록일</th>
					<td><c:out value="${noticeVo.date_output}" /></td>
				</tr>
				<tr>
					<td colspan="6" style="border:none;">
						<div id="details"><c:if test="${noticeVo.image_id != null}"><img src="/upload/${noticeVo.image_id}" style="max-width:1024px;"><br></c:if>${noticeVo.content}</div>
					</td>
				</tr>
			</table>
			<input type="hidden" id="authorId" data-author-id="${noticeVo.company_id}">
			
		</div>
		<section id="comment-box">
			<div>
				<!-- Comment form-->
				<form id="comment-write" name="commentWriteForm" action="comment_write_proc" method="post">
					<input type="hidden" name="post_id" value="${noticeVo.post_id}">
					<c:choose>
						<c:when test='${fn:contains(loginMember, "CompanyVo")}'>
							<input type="hidden" id="member_id" name="member_id" value="${loginMember.company_id}">
						</c:when>
						<c:when test='${fn:contains(loginMember, "MemberVo")}'>
							<input type="hidden" id="member_id" name="member_id" value="${loginMember.member_id}">
						</c:when>
						<c:otherwise>
							<input type="hidden" id="member_id" name="member_id" value="">
						</c:otherwise>
					</c:choose>
					<textarea id="form-control" rows="3" name="comment_content"
						placeholder="타인에게 불쾌함을 주는 댓글은 통보없이 삭제될 수 있습니다."></textarea>
					<button type="button" id="btn-style" name="cmtWrite">등록</button>
				</form>
				<!-- Comment -->
					<c:forEach var="c" items="${commList}" varStatus="status">
					<div id="comment${status.count}" class="comment1">
						<input type="hidden" name="no" value="${c.comment_id}">
						<div id="user-name">${c.member_id}</div>
						<div><span id="msg-date">${c.comment_date}</span><c:if test='${fn:contains(loginMember, "MemberVo") && c.member_id eq loginMember.member_id}'><span id="cmtDelete" class="cmtDelete" onclick="javascript:commentDelete(${c.comment_id})">삭제</span></c:if></div>
						${c.comment_content}
					</div>
					</c:forEach>
			</div>
		</section>
		<form name="noticeDelete" action="notice_delete" method="post"><input type="hidden" name="no" value="${noticeVo.post_id}"><c:if test="${noticeVo.image_id !=null}"><input type="hidden" name="imgdel" value="${noticeVo.image_id}"></c:if></form>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
	
</body>
</html>