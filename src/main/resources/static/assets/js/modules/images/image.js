$(document).ready(function() {
	if(typeof module_images != "undefined")
		$.each(module_images,function(index, el) {
			if($(el.el + el.item).hasClass('effect-hv')){
				$(el.el).hover(function() {
					$(this).find(el.item).removeClass('effect-hv');
					$(this).find(el.item).addClass(el.effectin);
					$(this).find(el.item).removeClass(el.effectout);
				}, function() {
					$(this).find(el.item).addClass(el.effectout);
					$(this).find(el.item).removeClass(el.effectin);
				});
			};
		});
});