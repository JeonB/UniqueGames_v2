<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/login.css">
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
	//현재 비밀번호와 다르게 하는 설정 & 값 입력하라는 유효성 체크
	$(document).ready(function(){
		
		var password = "${password}";
		
		
		$("#button-gradient").click(function(){
			
			if($("input[name='newpassword']").val()==""){
				alert("새 비밀번호를 입력하세요");
				$("input[name='newpassword']").focus();
				return false;
			}else if($("input[name='newpassword-check']").val()==""){
				alert("새 비밀번호 확인칸을 입력하세요");
				$("input[name='newpassword-check']").focus();
				return false;
			}else if($("input[name='newpassword']").val() != $("input[name='newpassword-check']").val()){
				alert("비밀번호가 동일하지 않습니다");
				$("input[name='newpassword-check']").focus();
				return false;
			}else if($("input[name='newpassword']").val() && $("input[name='newpassword-check']").val() === password){
				alert("기존 비밀번호와 동일하게 변경할 수 없습니다");
				$("input[name='newpassword']").focus();
				return false;
			}else {
				mChangePassword.submit();
			}
		
		});
		
		$("#pwd-check-img").change(function(){

			if($("#pwd-check-img").is(":checked") == false) {
				$("input[name='newpassword']").attr("type","password");
			}else if($("#pwd-check-img").is(":checked") == true) {
				$("input[name='newpassword']").attr("type","text");
			}
			
		});
		
		$("#pwd-check-img-1").change(function(){

			if($("#pwd-check-img-1").is(":checked") == false) {
				$("input[name='newpassword-check']").attr("type","password");
			}else if($("#pwd-check-img-1").is(":checked") == true) {
				$("input[name='newpassword-check']").attr("type","text");
			}
			
		});
		
	});

</script>
</head>
<body>
	<header>
		<jsp:include page="../main/header.jsp"></jsp:include>	
	</header>
	<section id="top-bg">
		<div id="base-layer">
			<div id="top-bg-textarea">
				<p id="top-title">Login</p>
				<p id="top-subtitle">#로그인</p>
			</div>
		</div>
	</section>
	<section id="content-1">
		<p id="intro">개인 회원</p>
		<form action="mChangePassword" name="mChangePassword" method="post" id="newPasswordForm">
			<ul>
				<li>
					<input type="password" id="input-common" name="newpassword" placeholder="새로운 비밀번호">
					<input type="checkbox" id="pwd-check-img">
					<input type="hidden" name="member_id" value="${member_id }">
				</li>
				<li>
					<input type="password" id="input-common" name="mnewpassword-check" placeholder="새로운 비밀번호 확인">
					<input type="checkbox" id="pwd-check-img-1">
					<span id="msgPassword"></span>
				</li>
				<li>
					<button type="button" id="button-gradient">수정하기</button>
				</li>
			</ul>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>

</body>
</html>