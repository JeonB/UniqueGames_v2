<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unique Games</title>
	<link rel="stylesheet" href="/css/mainunigames.css">
	<link rel="stylesheet" href="/css/board.css">
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script><!-- 마이크로소프트 jQuery-->
	<script src="/js/board.js"></script>

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
		<div id="board-write">
			<div id="board-top-menu">
				<p>Notice</p>
				<div>
					<ul>
						<li><button type="button" id="btn-style" name="write">저장</button></li>
						<li><button type="button" id="btn-style" name="cancel">취소</button></li>
					</ul>
				</div>
				<div id="clearFix"></div>
			</div>
			<form name="writeForm" action="notice_update_proc" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" id="default-write" value="${notice.title}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" cols="30" rows="5"
								id="default-write">${notice.content}</textarea></td>
					</tr>
					<tr>
						<th>이미지</th>
						<td>
							<div id="filebox">
								<input id="upload-name" value="${notice.image_id}" placeholder="첨부파일" disabled>
								<button type="button" id="btn-style" name="file">파일 찾기</button>
								<input type="file" name="file" id="upload-hidden" accept="image/*">
								<nav id="fileDelbtn"></nav>
								<span>*이미지는 하나만 선택할 수 있으며, 본문 위에 삽입됩니다.</span>
							</div>
						</td>
					</tr>
				</table>
				<input type="hidden" name="post_id" value="${notice.post_id}">
				<input type="hidden" name="image_id" value="${notice.image_id}">
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
	<script type="text/javascript">
		let hidden = document.getElementById("upload-hidden");
		let name = document.getElementById("upload-name");
		let hiddenImageId = document.getElementsByName("image_id");

		if ($('#upload-name').val() != "") {
			let output = "<button type='button' id='btn-style' name='filedel'>취소</button>";
			$('#fileDelbtn').html(output);
			$("#filebox").css("margin-right", "78px");

			// 파일 취소 버튼 이벤트
			$('#filebox button[name="filedel"]').on("click", function() {
				hidden.value = null;
				name.value = "";
				hiddenImageId[0].value = "delete!" + hiddenImageId[0].value;
				$('button[name="filedel"]').remove();
				$("#filebox").css("margin-right", "164px");

			});
		}
	</script>
</body>

</html>