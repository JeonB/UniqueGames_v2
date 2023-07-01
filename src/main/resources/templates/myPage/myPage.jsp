<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
	<title>myPage</title>
	<link rel="stylesheet" th:href="@{/css/login.css}">
	<script th:src="@{/js/jquery-3.6.4.min.js}"></script>
	<script th:src="@{//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js}"></script>
	<script th:inline="javascript">
		/*<![CDATA[*/
		$(document).ready(function(){
			var password = /*[[${memberVo.password}]]*/ "";
			var phone1 = /*[[${memberVo.phone1}]]*/ "";
			var phone2 = /*[[${memberVo.phone2}]]*/ "";
			var phone3 = /*[[${memberVo.phone3}]]*/ "";
			var email1 = /*[[${memberVo.email1}]]*/ "";
			var email2 = /*[[${memberVo.email2}]]*/ "";

			function emailCheck(asValue){
				var regex = /[A-za-z0-9]{4,20}$/;
				return regex.test(asValue);
			}

			function phoneCheck(asValue) {
				var regex = /^\d{3,4}$/;
				return regex.test(asValue);
			}

			$("#button-gradient").click(function(){
				if($("input[name='password']").val()==""){
					alert("비밀번호를 입력해주세요");
					$("input[name='password']").focus();
					return false;
				} else if($("input[name='password']").val() != password){
					alert("비밀번호가 일치하지 않습니다");
					$("input[name='password']").focus();
					return false;
				} else if($("input[name='email1']").val()==""){
					alert("이메일은 필수 입력 항목입니다");
					$("input[name='email1']").focus();
					return false;
				} else if(!emailCheck($("input[name='email1']").val())){
					alert("이메일은 영문 또는 숫자로 4자리 이상 입력해주세요");
					$("input[name='email1']").focus();
					return false;
				} else if($("input[name='email2']").val()=="" && $("#selectbox-email").val()=="choose" || $("input[name='email2']").val()=="" && $("#selectbox-email").val()=="direct"){
					alert("이메일 주소를 전부 작성해주세요");
					$("input[name='email2']").focus();
					return false;
				} else if($("#emailAuth").css("color") === "rgb(255, 0, 0)") {
					alert("이메일 인증번호를 확인해주세요");
					$("#email-auth-check").focus();
					return false;
				} else if($("#selectbox-mobile").val()=="choose") {
					alert("통신사를 선택해주세요");
					$("#selectbox-mobile").focus();
					return false;
				} else if($("#selectbox-phone").val()=="choose") {
					alert("휴대전화는 필수 입력 항목입니다");
					$("#selectbox-phone").focus();
					return false;
				} else if($("input[name='phone2']").val()=="") {
					alert("휴대전화는 필수 입력 항목입니다");
					$("input[name='phone2']").focus();
					return false;
				} else if(!phoneCheck($("input[name='phone2']").val())) {
					alert("휴대전화는 숫자 3~4자리로 입력해주세요");
					$("input[name='phone2']").focus();
					return false;
				} else if($("input[name='phone3']").val()=="") {
					alert("휴대전화는 필수 입력 항목입니다");
					$("input[name='phone3']").focus();
					return false;
				} else if(!phoneCheck($("input[name='phone3']").val())) {
					alert("휴대전화는 숫자 4자리로 입력해주세요");
					$("input[name='phone3']").focus();
					return false;
				} else {
					document.myPageForm.submit();
				}
			});

			$("#address-btn-style").click(function(){
				new daum.Postcode({
					oncomplete: function(data) {
						$("input[name='addr1']").val(data.zonecode);
						$("input[name='addr2']").val(data.address);
					}
				}).open();
			});

			$("#email-btn-style").click(function(){
				var email1 = $("input[name='email1']").val();
				var email2 = $("input[name='email2']").val();
				var email3 = $("#selectbox-email").val();
				var email = email1 + "@" + email2;

				if(email1 == ""){
					alert("이메일은 필수 입력 항목입니다");
					$("input[name='email1']").focus();
					return false;
				} else if(!emailCheck(email1)){
					alert("이메일은 영문 또는 숫자로 4자리 이상 입력해주세요");
					$("input[name='email1']").focus();
					return false;
				} else if(email2 == "" && email3 == "choose" || email2 == "" && email3 == "direct"){
					alert("이메일 주소를 전부 작성해주세요");
					$("input[name='email2']").focus();
					return false;
				} else {
					var params = { email: email };
					$.ajax({
						type: "POST",
						url: "myPageEmailAuth",
						data: params,
						success: function(result){
							if(result == "success"){
								$("#email-auth-check").removeAttr("disabled");
								$("#emailAuth").text("인증번호가 전송되었습니다");
								$("#emailAuth").css("color", "#777");
							}
						},
						error: function(e){
							console.log(e);
						}
					});
				}
			});

			$("#email-auth-check").keyup(function(){
				var authCode = $("#email-auth-check").val();
				var params = { authCode: authCode };

				$.ajax({
					type: "POST",
					url: "myPageEmailAuthCheck",
					data: params,
					success: function(result){
						if(result == "success"){
							$("#emailAuth").text("인증이 완료되었습니다");
							$("#emailAuth").css("color", "green");
						} else {
							$("#emailAuth").text("인증번호가 일치하지 않습니다");
							$("#emailAuth").css("color", "red");
						}
					},
					error: function(e){
						console.log(e);
					}
				});
			});
		});
		/*]]>*/
	</script>
</head>
<body>
<header th:include="main/header"></header>
<section id="top-bg">
	<div id="base-layer">
		<div id="top-bg-textarea">
			<p id="top-title">My Page</p>
			<p id="top-subtitle">#마이페이지</p>
		</div>
	</div>
</section>
<section id="content-1">
	<p id="intro">마이페이지</p>
	<form th:action="@{/myPage_proc}" name="myPageForm" method="post" id="content-myPage">
		<div>
			<ul>
				<li id="must-insert">
					<p id="label-dot">*</p>
					<label>아이디</label><span id="idMsg"></span>
				</li>
				<li>
					<input type="text" id="input-common" name="member_id" th:value="${memberVo.member_id}" disabled="disabled">
					<input type="hidden" name="member_id" th:value="${memberVo.member_id}">
				</li>
				<li id="must-insert">
					<p id="label-dot">*</p>
					<label>비밀번호</label><span id="pwdMsg"></span>
				</li>
				<li>
					<input type="password" id="input-common" name="password">
					<input type="checkbox" id="pwd-check-img">
				</li>
				<li>
					<a th:href="@{/myPageChangePassword(member_id=${memberVo.member_id})}" id="link-changePassword">
						<span>비밀번호 변경 ></span>
					</a>
				</li>
				<li id="must-insert">
					<p id="label-dot">*</p>
					<label>이름</label><span id="nameMsg"></span>
				</li>
				<li>
					<input type="text" id="input-common" name="name" th:value="${memberVo.name}" disabled="disabled">
					<input type="hidden" name="name" th:value="${memberVo.name}">
				</li>
				<li id="must-insert">
					<p id="label-dot">*</p>
					<label>이메일</label>
					<span id="emailMsg"></span>
					<span id="emailAuth"></span>
				</li>
				<li>
					<input type="text" id="input-email" name="email1" th:value="${memberVo.email1}">
					@
					<input type="text" id="input-email" name="email2" th:value="${memberVo.email2}">
					<select name="email3" id="selectbox-email">
						<option value="choose">선택</option>
						<option value="naver.com" th:selected="${memberVo.email3 == 'naver.com'}">naver.com</option>
						<option value="gmail.com" th:selected="${memberVo.email3 == 'gmail.com'}">gmail.com</option>
						<option value="daum.net" th:selected="${memberVo.email3 == 'daum.net'}">daum.net</option>
						<option value="direct">직접입력</option>
					</select>
					<button type="button" id="email-btn-style">인증 번호</button>
					<input type="text" name="email-auth-check" id="email-auth-check" placeholder="인증번호 6자리 입력" disabled="disabled">
				</li>
				<li id="must-insert">
					<p id="label-dot">*</p>
					<label>휴대전화</label><span id="phoneMsg"></span>
				</li>
				<li>
					<select name="mobile" id="selectbox-mobile">
						<option value="choose">선택</option>
						<option value="SKT" th:selected="${memberVo.mobile == 'SKT'}">SKT</option>
						<option value="KT" th:selected="${memberVo.mobile == 'KT'}">KT</option>
						<option value="LGU+" th:selected="${memberVo.mobile == 'LGU+'}">LGU+</option>
					</select>
					<span>-</span>
					<select name="phone1" id="selectbox-phone">
						<option value="choose">선택</option>
						<option value="010" th:selected="${memberVo.phone1 == '010'}">010</option>
						<option value="011" th:selected="${memberVo.phone1 == '011'}">011</option>
						<option value="016" th:selected="${memberVo.phone1 == '016'}">016</option>
						<option value="017" th:selected="${memberVo.phone1 == '017'}">017</option>
						<option value="018" th:selected="${memberVo.phone1 == '018'}">018</option>
						<option value="019" th:selected="${memberVo.phone1 == '019'}">019</option>
					</select>
					<span>-</span>
					<input type="text" id="input-phone" name="phone2" th:value="${memberVo.phone2}">
					<span>-</span>
					<input type="text" id="input-phone" name="phone3" th:value="${memberVo.phone3}">
				</li>
				<li id="must-insert">
					<p id="label-dot">*</p>
					<label>주소</label><span id="addressMsg"></span>
				</li>
				<li>
					<input type="text" id="input-common" name="addr1" th:value="${memberVo.addr1}" readonly="readonly">
					<button type="button" id="address-btn-style">주소 찾기</button>
				</li>
				<li>
					<input type="text" id="input-common" name="addr2" th:value="${memberVo.addr2}" readonly="readonly">
				</li>
			</ul>
		</div>
		<button type="button" id="button-gradient">수정 완료</button>
	</form>
</section>
<footer th:include="main/footer"></footer>
<script th:src="@{/js/common.js}"></script>
</body>
</html>
