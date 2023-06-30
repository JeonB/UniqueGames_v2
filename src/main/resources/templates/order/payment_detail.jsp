<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Unique Games</title>
	<link rel="stylesheet" href="css/order_style.css">
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
	<!-- 마이크로소프트 jQuery-->
	<script>
		$(document).ready(function (){
			initAjax();
	
			function initAjax() {
				let array = $("#select-order").val();		
					
				$.ajax({
					url : "payment_detail_data",
					data : {
						array : array 
					},
					success : function(result){				
						let jdata = JSON.parse(result);
						let output = "";
							
						if(jdata.nothing == true){
							output += "<div id='div-nothing'>";
							output += "<p id='p-nothing'>결제내역이 없습니다.</p>";
							output += "</div>";
							output += "<div id='div-button'>";
							output += "<a href='/'>";
							output += "<button type='button' id='btn-main'>메인으로</button></a>";
							output += "</div>";
							
							$("#div-nothing").remove();
							$("#div-button").remove();
						}
						else {
							output += '<table id="table-gameList">';
							output += '<tr><th>번호</th><th>날짜</th>';
							output += '<th>상품명</th><th>가격</th>';
							output += '</tr>';
							
							for(obj of jdata.jlist){
								output += "<tr>";
								output += "<td>" + obj.rno + "</td>";
								output += "<td>" + obj.orderdate + "</td>";
								output += "<td>" + obj.gametitle + "</td>";
								output += "<td>" + obj.amount + "</td>";
								output += "</tr>";	
							}
							output += '</table>';
							output += '<div id="div-price">';
							output += '<table id="cart-price">';
							output += '<tr><th>개수</th><th>총 금액</th></tr>';
							output += '<tr>';
							output += "<td>총 " + jdata.count + "개</td>";
							output += "<td>" + jdata.totalAmount + "<td>";
							output += '</tr></table></div>';
							output += '<div id="div-margin">';
						}
						
						// output 출력
						$("#table-gameList").remove();
						$("#div-price").remove();
						$("#div-select").after(output); 
					}
				}); // ajax	
			}// initAjax()
			
			$("#select-order").change(function() {
				initAjax();
			});
		});
	</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../main/header.jsp"></jsp:include>

	<!-- content -->
	<section id="top-bg">
		<div id="base-layer">
			<div id="top-bg-textarea">
				<p id="top-title">Payment Detail</p>
				<p id="top-subtitle">#결제내역</p>
			</div>
		</div>
	</section>

	<h1>Payment Detail</h1>
	<section id="cart">
		<div id="div-gameList">
			<div id="div-select">
				<select id="select-order">
					<option value="orderdate_desc">최근 날짜순</option>
					<option value="orderdate_asc">오래된 날짜순</option>
					<option value="amount_asc">높은 후원금순</option>
					<option value="amount_desc">낮은 후원금순</option>
				</select>
			</div>
		</div>
	</section>

	<!-- footer -->
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>