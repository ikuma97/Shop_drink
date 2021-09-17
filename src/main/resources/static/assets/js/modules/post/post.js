$(window).load(function() {
    if ((typeof post_slide) !== 'undefined') {
        for (var i = 0; i < post_slide.length; i++) {
            var obj = post_slide[i];
            if(obj.id !== ''){
                $('#' + obj.id).owlCarousel(obj.config);
            }
        }
    }
});