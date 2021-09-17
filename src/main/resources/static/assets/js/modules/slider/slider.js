$(document).ready(function() {
    if ((typeof config_slider) !== 'undefined') {
        for (var i = 0; i < config_slider.length; i++) {
            var obj = config_slider[i];
            if(obj.id !== ''){
                var $slider = $('#' + obj.id);
                $slider.owlCarousel(obj.config);
                $slider.on('changed.owl.carousel', function() {
                    text_animate($(this),"changed");
                });
            }
        }
    }
});