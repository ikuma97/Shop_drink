/** JS chạy class "wow" **/
$(window).load(function(){
  $.getScript('https://cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js', function () {
    var wow = new WOW();
    wow.init();
  });
});