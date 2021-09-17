$(document).ready(function() {

	$(".btn-rfresh-qty").on("click",function(){
		var qty = $(this).closest('.rfresh-control').find('.txt-qty').val() || false,p = $(this).attr('rel-p') || false ;

		if(qty == false || isNaN(qty) || qty <=0) {$.notify({title:"<i class='icon remove'></i> Lỗi .!",message:" số lượng không hợp lệ .!"},{type:"danger"});return false;}
		if(p == false) {$.notify({title:"<i class='icon remove'></i> Lỗi .!",message:" không xác định .!"},{type:"danger"});return false;}

		qty = parseInt(qty);
		$.ajax({
			url: location.origin + '/ajx/ajx.php',
			type: 'GET',
			dataType: 'json',
			data: {act: 'rfreshqty',p:p,q:qty},
		})
		.done(function(rs) {
			if(rs.status == "true") location.reload();
			else $.notify({title:"<i class='icon remove'></i> Lỗi .!",message:" cập nhật thất bại .!"},{type:"danger"});
		})
	});

	$(".btn-trash-cart").on("click",function(){
		var p = $(this).attr("rel-p") || false,
		n = $(this).closest("tr").find(".p-name").html() || "Sản phẩm này",
		t= $(this).closest("tr").find(".thuoc-tinh").html();

		if(p == false) {$.notify({title:"<i class='icon remove'></i> Lỗi .!",message:" không xác định .!"},{type:"danger"});return false;}
		swal({
			title:"Xác nhận",
			text:"Xoá "+ n + " - "+t +" ra khỏi giỏ hàng.?",
			type:"warning",
			showCancelButton:!0,
			confirmButtonColor:"#d26a5c",
			confirmButtonText:"Xoá",
			closeOnConfirm:!1,
			html:!1
		},function(){
			swal.close();
			$.ajax({
				url: location.origin + '/ajx/ajx.php',
				type: 'GET',
				dataType: 'json',
				data: {act: 'trashCart',p:p},
			})
			.done(function(rs) {
				if(rs.status == "true") location.reload();
				else $.notify({title:"<i class='icon remove'></i> Lỗi .!",message:" cập nhật thất bại .!"},{type:"danger"});
			})
		});
	});

});
