<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Unique Games</title>
	<link rel="stylesheet" href="css/order_style.css">
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script> <!-- 마이크로소프트 jQuery-->
	<script src="js/order_script.js"></script>
</head>
<body>
<!-- header -->
<jsp:include page="../main/header.jsp"></jsp:include>

<!-- content -->
<section id="top-bg">
	<div id="base-layer">
		<div id="top-bg-textarea">
			<p id="top-title">Order</p>
			<p id="top-subtitle">#주문완료</p>
		</div>
	</div>
</section>


<h1>Complete</h1>
<div id="order-complete">
	<div id="div-thankyou">결제가 완료되었습니다.</div>
	<div id="div-button">
		<a href="/"><button type="button" id="btn-main">메인으로</button></a>
		<a href="/paymentDetail"><button type="button" id="btn-list">결제내역</button></a>
	</div>
</div>

<!-- footer -->
<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>