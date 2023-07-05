$(document).ready(function (){
	let method = "";
	
	// agreement
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
	
	// modal
    $("#div-agreement a").click(function (){
        $("#modal").show();
        
        if($(this).attr("id") == "detail1"){
        	$("#modal-content").html($("div.detail1").html());
        }
        else if($(this).attr("id") == "detail2"){
        	$("#modal-content").html($("div.detail2").html());
        }
        else if($(this).attr("id") == "detail3"){
        	$("#modal-content").html($("div.detail3").html());
        }
        else if($(this).attr("id") == "detail4"){
        	$("#modal-content").html($("div.detail4").html());
        }
    });
    
    // btn-modal
    $("#btn-modal").click(function() {
    	$("#modal").hide();
	});
    
    // btn-paymentMethod
    $("#div-method #btn-method").click(function() {
    	method = $(this).val();

    	$("#div-method #btn-method").css("opacity", "40%");
    	$(this).css("opacity", "100%");
    });
    
    // btn-order
    $("#btn-order").click(function() {
    	if(method == ""){
    		alert("결제 방식을 선택해주세요.");
    	}
    	else {
    		if( $("input[name='checkAll']").is(":checked") ){
	    		start();
	    	}
	    	else {
	    		alert("결제 약관에 동의해주세요.");
	    	} 
    	}
    		
	});
	
	function start() {
		let uid = "uniquegames";
        $("button[name='btn-deleteOne']").each(function(i) {
        	uid += "_" + $(this).val();
        }); 
        
        var amnt = $("#totalAmount").val();
	
		const IMP = window.IMP; // 생략 가능
		IMP.init("imp15073643");
		
		IMP.request_pay({
				pg: "html5_inicis",
				pay_method: method,
				merchant_uid: uid,   // 주문번호
				name: "UniqueGames",
				amount: amnt,                         // 숫자 타입
				buyer_email: "thddbsk7248@gmail.com",
				buyer_name: "송유나",
				buyer_tel: "010-4242-4242",
				buyer_addr: "서울특별시 강남구 신사동",
				buyer_postcode: "01181"
			}, 
			function(rsp) { // callback
				if(rsp.success){
					location.replace("orderProc?method=" + method);
				}
				else {
					alert("결제에 실패했습니다. 장바구니로 돌아갑니다.");
					location.replace("cart");
				}
		});
	}
	
    
    
    
	/** order_complete **/
	
});