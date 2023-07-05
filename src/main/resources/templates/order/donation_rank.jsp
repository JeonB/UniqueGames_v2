<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Unique Games</title>
	<link rel="stylesheet" href="css/order_style.css">
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script> <!-- 마이크로소프트 jQuery-->
	<script>
	$(document).ready(function (){
		showDetails();

	    function showDetails() {
			let c_id = $("#c_id").val();
			
			$.ajax({
				url : "donationRankData",
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
					}
					else {
						output += '<table id="table-gameList">';
						output += '<tr><th>번호</th><th>유저 아이디</th>';
						output += '<th>게임 타이틀</th><th>가격</th></tr>';
						
						for(obj of jdata.jlist){
							output += "<tr>";
							output += "<td>" + obj.rno + "</td>";
							output += "<td>" + obj.userId + "</td>";
							output += "<td>" + obj.gametitle + "</td>";
							output += "<td>" + obj.amount + "</td>";
							output += "</tr>";	
						}
						output += '</table>';
					}
					
					// output 출력
					$("#div-select").after(output);   		
				} // success	
			}); // ajax	
	    }
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
			<p id="top-title">Donation Ranking</p>
			<p id="top-subtitle">#후원 순위</p>
		</div>
	</div>
</section>

<h1>Donation Ranking</h1>
<section id="cart">
	<div id="div-gameList">
		<div id="div-select">
		</div>
	</div>
</section>

<!-- footer -->
<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>