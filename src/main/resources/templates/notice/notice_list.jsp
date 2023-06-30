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
<link rel="stylesheet" href="/css/am-pagination.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
<script src="/js/board.js"></script>
<script src="/js/am-pagination.js"></script>
<c:set var="loginMember" value='<%= session.getAttribute("loginMember") %>'/>
<script>
	$(document).ready(function(){	
		getPagination();
		// diffPage('${loginMember}');

		$('button[name="getList"]').on("click", function() {

			location.href = "notice_list";
		})

 	});
	
	function getPagination() {
		var pager = jQuery('#ampaginationsm').pagination({
			
		    maxSize: '${pageCount}',	    		// max page size
		    totals: '${dbCount}',	// total pages	
		    page: '${page}',		// initial page		
		    pageSize: '${pageSize}',			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'lg'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "/notice_list?page="+e.page);
	    });
	}

</script> 
<c:if test="${result != null}">
	<script type="text/javascript">
		let result = '<c:out value="${result}"/>';
		writeSuccess(result);
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
		<div id="board-list">
			<div id="board-top-menu">
				<c:choose>
					<c:when test='${fn:contains(loginMember, "CompanyVo")}'>
						<p>Notice</p>
						<div>
							  <form name='boardSearch' action='notice_Search' method='get'>
								<label>
									<input type='text' name='keyword' placeholder='검색어를 입력해주세요.'>
									<button type='button' id='btn-search'>
										<img src='/images/btn_boardSearch_press.png'>
									</button>
								</label>
							 </form>
							<ul>
								<li><button type='button' id='btn-style' name='listWrite'>작성</button></li>
								<li><button type='button' id='btn-style' name='listUpdate'>수정</button></li>
								<li><button type='button' id='btn-style' name='listDelete'>삭제</button></li>
								<li><button type='button' id='btn-style' name='listDeleteAll'>전체삭제</button></li>
							</ul>
						</div>
					</c:when>
					<c:otherwise>
						<p>Notice</p>
							<div id='bsearch-box'>
								  <form name='boardSearch' action='notice_Search' method='get'>
									<label>
										<input type='text' name='keyword' placeholder='검색어를 입력해주세요.'>
										<button type='button' id='btn-search'>
											<img src='/images/btn_boardSearch_press.png'>
										</button>
									</label>
								 </form>
								<ul>
									<li><button type='button' id='btn-style' name='getList'>목록</button></li>
								</ul>
							</div>
							<div id='clearFix'></div>
					</c:otherwise>
				</c:choose>
			</div>
			<c:choose>
				<c:when test='${fn:contains(loginMember, "CompanyVo")}'>
					<form name="boardManage" action="board_manage" method="post">
						<table id="admin-btable">
							<tr>
								<th>선택</th>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
							<c:choose>
								<c:when test="${not empty list}">
									<c:forEach var="noticeVo" items="${list}">
										<tr>
											<td><input type="checkbox" name="list"
												value="${noticeVo.post_id}"></td>
											<td><c:out value="${noticeVo.rno}"/></td>
											<td><a href="notice_content?no=${noticeVo.post_id}"><c:out value="${noticeVo.title}" /><c:if test="${noticeVo.cmtCount > 0}"><c:out value="  [${noticeVo.cmtCount}]" /></c:if></a></td>
											<td><c:out value="${noticeVo.name}" /></td>
											<td><c:out value="${noticeVo.date_output}" /></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:when test="${empty list}">
									<tr>
										<td colspan="5" style="padding: 50px 0">등록된 게시글이 없습니다.</td>
									</tr>
								</c:when>
							</c:choose>
							<tr>
								<td colspan="5"><div id="ampaginationsm"></div></td>
							</tr>
						</table>
					</form>
				</c:when>
				<c:otherwise>
					<table id="user-btable">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
							<c:choose>
								<c:when test="${not empty list}">
									<c:forEach var="noticeVo" items="${list}">
										<tr>
											<td><c:out value="${noticeVo.rno}"/></td>
											<td><a href="notice_content?no=${noticeVo.post_id}"><c:out value="${noticeVo.title}" /><c:if test="${noticeVo.cmtCount > 0}"><c:out value=" [${noticeVo.cmtCount}]" /></c:if></a></td>
											<td><c:out value="${noticeVo.name}" /></td>
											<td><c:out value="${noticeVo.date_output}" /></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:when test="${empty list}">
									<tr>
										<td colspan="4" style="padding: 50px 0">등록된 게시글이 없습니다.</td>
									</tr>
								</c:when>
							</c:choose>
							<tr>
								<td colspan="5"><div id="ampaginationsm"></div></td>
							</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>

</html>