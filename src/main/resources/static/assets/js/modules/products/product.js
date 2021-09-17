function rating_item() {
   $(".wm-product.wm-module .pro.rating").rating({
      maxRating: 5,
      onRate: function (value) {
         toogleRating(value, $(this).data("p"), $(this));
      }
   });
}
$(function () {
   var namespace = ".wm-module.wm-product";
   if ((typeof pro_slide) !== 'undefined') {
      for (var i = 0; i < pro_slide.length; i++) {
         var obj = pro_slide[i];
         if (obj.id !== '') {
            $('.' + obj.id).owlCarousel(obj.config);
         }
      }
   }
   rating_item();
   //Load item 
   if ($(namespace + " .btn-l-ac").length > 0) {
      var is_loadding = false;
      $(namespace + " .btn-l-ac").on("click", function () {
         if (is_loadding === true) return false;
         var config = $(this).data("config"),
            $_this = $(this),
            page = $(this).attr("data-page"),
            tab = $(this).attr("data-tab");
         //console.log(config); 
         $(this).attr("data-page", parseInt(page) + 1);
         $.get("/ajx/ajx.php?act=LOADITEM&module=product", {
            config: config,
            page: page,
            tab: tab
         }, function (res) {
            console.log($_this.closest(namespace).find(".cards").attr('class'));
            $_this.closest(namespace).find(".cards").append(res);
            is_loadding = false;
            rating_item();
            if (parseInt(page) + 1 > $_this.data("totalpage")) {
               // $_this.addClass("disabled"); 
               $_this.remove();
            }
            try {
               FB.XFBML.parse();
               gapi.plusone.go();
            } catch (ex) {}
         });
         is_loadding = true;
      });
   }
});