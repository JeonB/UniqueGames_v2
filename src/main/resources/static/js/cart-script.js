$(document).ready(function (){
	let checkedIdList;
	
	showPrice();
	
	/** cart **/
    $("input[name='checkAll']").change(function (){
        if($(this).is(":checked")){
            $("input[name='checkOne']").prop("checked", true);
        }
        else {
            $("input[name='checkOne']").prop("checked", false);
        }
    });
    $("input[name='checkOne']").change(function (){
        let total = $("input[name='checkOne']").length;
        let checked = $("input[name='checkOne']:checked").length;

        if(total == checked){
            $("input[name='checkAll']").prop("checked", true);
        }
        else {
            $("input[name='checkAll']").prop("checked", false);
        }
    });
    $("input").change(function (){
		showPrice();
    });
    
    $("#btn-deleteSelected").click(function() {
    	let checkedList = [];
        $("input[name='checkOne']:checked").each(function(i) {
        	checkedList.push($(this).val());
        });  
        
        if(checkedList.length == 0){
        	alert("삭제할 항목을 선택해주세요.");
        }
       	else {
	       	$.ajax({
	    		url: 'cartDeleteSelected',
	    		method: 'POST',
	    		data: {
	        		checkedList: checkedList	//보내려는 데이터를 문자열로 변환하여 넣기
	   			},
	    		success: (result) => {
	    			alert("선택된 항목의 삭제가 완료되었습니다.");
	    			location.reload();
	    		}
			});
       	}	
    });
    
    $("#btn-cart").click(function() {
    	let checkedList = [];
        $("input[name='checkOne']:checked").each(function(i) {
        	checkedList.push($(this).val());
        });  
        
        if(checkedList.length == 0){
        	alert("주문할 항목을 선택해주세요.");
        }
       	else {
       		location.href = "/order?checkedList="+checkedList;
       	}	
    });
    
    function showPrice() {
    	let checked = $("input[name='checkOne']:checked").length;		
		$("#count").html("총 " + checked + "개");
		
		var totalAmount = 0;
		$("input[name='checkOne']:checked").each(function() {
			let amount = $(this).parent().attr("id");
			
			totalAmount += parseInt(amount);
		});
		$("#totalAmount").html(totalAmount);
    }
    
    $("#btn-deleteAll").click(function() {
    	location.href="/cart_delete_all";
    });

});