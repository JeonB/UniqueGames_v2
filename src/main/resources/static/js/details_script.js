$(document).ready(function (){
	showDetails();

    function showDetails() {
    	let array = $("#select-order").val();		
		let m_id = $("#m_id").val();
		
		$.ajax({
			url : "payment_detail_data?m_id="+m_id,
			data : {
				m_id : m_id,
				array : array
			},
			success : function(result){				
				let jdata = JSON.parse(result);
				let output = "";
				
				if(jdata.jlist.nothing == 'true'){
					output += "<div id='div-nothing'>";
					output += "<p id='p-nothing'>결제내역이 없습니다.</p>";
					output += "</div>";
					output += "<div id='div-button'>";
					output += "<a href='/index'>";
					output += "<button type='button' id='btn-main'>메인으로</button></a>";
					output += "</div>";
				}
				else {
					output += '<table id="table-gameList">';
					output += '<tr><th>번호</th><th>날짜</th>';
					output += '<th>상품명</th><th>가격</th></tr>';
					
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
					output += "<td>총 " + jdata.jlist.count + "개</td>";
					output += "<td>" + jdata.jlist.totalAmount + "<td>";
					output += '</tr></table></div>';
				}
				
				// output 출력
				$("#table-gameList").remove();
				$("#div-price").remove();
				$("#div-select").after(output);   		
			} // success	
		}); // ajax	
    }
    
    $("#select-order").change(function() {
    	showDetails();
    });
	
});