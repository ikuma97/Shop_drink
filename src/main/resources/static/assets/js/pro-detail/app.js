/*
|==============================================
|	@a : Mảng thuộc tính
|	@o : Mảng giá trị thuộc tính
|	@p : id sản phẩm
|	@l : số lượng thuộc tính
|==============================================
*/
$(document).ready(function (event) {
	$('.btn-add-cart').click(function(){
		var url = $(this).data('url');
		if (typeof fields !== "undefined") {
			$("#byinfo").form({
				inline: true,
				fields: fields,
				selector: { submit: ".btn-add-cart" },
				onSuccess: function onSuccess(e, fields) {
					var a = [],
						o = [],
						pro = $(e.target).data("p");
						// url = $(event.target).hasClass("btn-add-cart") ? $(event.target).data("url") : $(event.target).closest(".btn-add-cart").data("url");
					delete fields.quantity;
					$.each(fields, function (attr, op) {
						a.push(attr.replace("attr-", ""));
						o.push(op);
					});
					 addtoCart(event, url, pro, a, o);
					return false;
					
				}
			});
		}
	})
	//Canh chỉnh hình sản phẩm
	var $fotoramaDiv = $('.myfotorama').fotorama(),
	    fotorama = $fotoramaDiv.data('fotorama');

	$fotoramaDiv.on('fotorama:ready', function (e, fotorama, extra) {
		$(".fotorama__wrap").addClass('margin-auto');
	});

	function DropdownApi(el, val) {
		if (val === "") return false;
		var a = [],
		    n = {},
		    r = [],
		    p = $(el).attr('rel-pro') || "",
		    c = "",
		    o = [],
		    i = parseInt($(el).attr("rel-index")) || 0,
		    l = $('.dropdown.use.api').length || 0;
		if (val === "refresh") {
			n[$(el).attr('rel-attr')] = "";
			$(".dropdown.use.api").each(function () {
				$(this).dropdown("restore default value").dropdown("restore default text").dropdown("clear");
			});
		} else {
			a.push($(el).attr("rel-attr"));
			o.push(val);
			$(".dropdown.use.api").not(el).each(function () {
				if ($(this).dropdown("get value").length > 0) {
					n[$(this).attr('rel-attr')] = $(this).dropdown("get value");
					a.push($(this).attr("rel-attr"));
					o.push($(this).dropdown("get value"));
				} else n[$(this).attr('rel-attr')] = "";
			});
		}
		$.each(n, function (index, needer) {
			api_uri = location.origin + ("/ajx/ajx.php?act=API&attr=" + a.join(",") + "&pro=" + p + "&op=" + o.join(",") + "&needer=" + index);
			var $element = $(".dropdown.use.api[rel-attr=" + index + "]");
			$.get(api_uri, function (res) {
				res = $.parseJSON(res);var selected;var $element = $(".dropdown.use.api[rel-attr=" + index + "]");
				if (res.success == "false") {
					$element.closest(".field").addClass("wm hiden");
				} else {
					$element.closest(".field").removeClass("wm hiden");
					$element.find("div.menu").empty();
					var newItem = "<div class=\"item\" data-value=\"refresh\">Làm mới</div>";
					$.each(res.results, function (k, value) {
						newItem += "<div class=\"item\" data-value=\"" + value.value + "\">" + value.name + "</div>";
					});
					$element.find("div.menu").append(newItem);
					$element.dropdown("refresh").dropdown("set selected", needer);
				}
			});
		});

		$.get(location.origin + ("/ajx/ajx.php?act=GETINFOPRODUCT&attr=" + a.join(",") + "&pro=" + p + "&op=" + o.join(",")), function (res) {
			if (res.length <= 0) return false;
			try {
				r = $.parseJSON(res);
				console.log(r);
				// $('.lbl-code').addClass('animated zoomIn');
				$(".lbl-code").html(r.code || "NaN");

				// $('.lbl-price').addClass('animated zoomIn');
				$(".lbl-price").html(r.price);

				// $('.lbl-price-compare').addClass('animated zoomIn');
				$(".lbl-price-compare").html(r.price_compare);

				fotorama.show(r.image);

				$(".btn-add-cart").removeClass("disabled");
			} catch (e) {
				console.log(e);
				//$(".btn-add-cart").addClass("disabled");
				//$.notify({title:"<i class='icon remove'></i>",message:"Không có phiên bản cho lựa chọn này .!\n"+e},{type:"warning"});
			}
		});
	} // end change event

	$('.dropdown.use.api').dropdown({
		onChange: function onChange(val) {
			DropdownApi(this, val);
		}
	});
});