function update_total_money(){
    var total_money_basic = $('#cart_total_money').attr('data-total-money');
    var shipping_fee = $(".lbl-shipfee").attr('data-shipping-fee');
    var discount_coupon = $(".wrap-discount-coupon").attr('data-discount-coupon');
    var total_money_update = parseFloat(total_money_basic) + parseFloat(shipping_fee) - parseFloat(discount_coupon);
    $('#cart_total_money span').text(total_money_update.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,") +'  đ');
}

$(function(){
    $('#selShippingType').dropdown({
        onChange: function(value,text,$choice){
            var fee = $(this).find("option[value="+value+"]").data("fee");
            var shipping_fee = parseInt(fee.replace(/[^0-9.]/g, ""));
            $(".lbl-shipfee").text(fee);
            $(".lbl-shipfee").attr('data-shipping-fee', shipping_fee);
            update_total_money();
        }
    });
    $('#selProvince').change(function(){
        var district_selected = $('#selDistrict').attr('data-selected');
        $('#selDistrict').html('<option value="">-- Chọn quận/huyện --</option>');

        var params = {
            province_id: $(this).val(),
            district_selected: district_selected
        };
        $.post('/ajx/ajx.php?act=GET_DISTRICT_OPTION', params, function(data) {
            var data_parse = JSON.parse(data);
            if(data_parse.status == 1){
                $('#selDistrict').append(data_parse.str_district_option);
            }

            $('#selDistrict')
                .dropdown('clear')
                .closest('div').find('.default.text').text('-- Chọn quận/huyện --');
            if(district_selected === ''){
                // alert('a');
            }else{
                // alert(district_selected);
                $('#selDistrict').dropdown('set selected', district_selected);
            }

            $('#selShippingType').html('<option value="">-- Chọn loại vận chuyển --</option>');
            $('#selShippingType').dropdown('clear').find('.default.text').text('-- Chọn loại vận chuyển --');
        });
        // debugger;
    });
    $('#selProvince').change();

    $('#selDistrict').change(function(){
        $('#selShippingType').html('<option value="">-- Chọn loại vận chuyển --</option>');
        $('#selShippingType').dropdown('clear').find('.default.text').text('-- Chọn loại vận chuyển --');
        var district_id = $(this).val();
        var params = {
            province_id: $('#selProvince').val(),
            district_id: district_id,
            price_calculated: $('#cart_calculated').attr('data-calculated'),
            weigh: $('#cart_calculated').attr('data-weigh'),
            qty:$('#cart_calculated').attr('data-qty')
        };
        $.post('/ajx/ajx.php?act=GET_SHIPPINGTYPE_OPTION', params, function(data) {
            var data_parse = JSON.parse(data);
            var selShippingType;
            if(data_parse.status === 0){
                $('#selShippingType').dropdown();
            }else if(data_parse.status == 1){
                $('#selShippingType').append(data_parse.str_shippingtype_option);
                selShippingType = $('#selShippingType').dropdown();
            }
        });
    
    });
    $('#selDistrict').change();

    $('#selSelectQuick').change(function(){
        var select_quick = $(this).val();
        if(select_quick != -1){
            var params = {
                select_quick: select_quick
            };
            $.post('/ajx/ajx.php?act=SET_SHIPPING_ADDRESS_QUICK', params, function(data) {
                var data_parse = JSON.parse(data);
                if(data_parse.status == 1){
                    $('#fullname').val(data_parse.fullname);
                    $('#phone').val(data_parse.phone);
                    $('#address').val(data_parse.address);
                    $('#selProvince').val(data_parse.selProvince);
                    $('#selDistrict').val(data_parse.selDistrict);
                }
            });
        }
    });
});

$('#toggleInputCoupon').click(function(){
    $(this).closest('div').find('.wrap-form-coupon').show();
    $(this).hide();
});

$('#btnUseCoupon').click(function(){
    var $wrap_form_coupon = $(this).closest('.wrap-form-coupon');
    var $wrap_discount_coupon = $('.wrap-discount-coupon');
    var coupon_code = $wrap_form_coupon.find('.form-group input[type=text]').val();
    if(coupon_code === ''){
        $wrap_form_coupon.hide();
        $('#toggleInputCoupon').show();
    }else{
        var params = {
            coupon_code: coupon_code
        };
        $.post('/ajx/ajx.php?act=GET_COUPON', params, function(data) {
            var data_parse = JSON.parse(data);
            if(data_parse.status === 0){
                $wrap_form_coupon.find('.form-group').addClass('has-error');
                $wrap_form_coupon.find('.form-group .coupon-error').html(data_parse.error_msg);
            }else if(data_parse.status == 1){
                $wrap_form_coupon.find('.form-group input[type=text]').val('');
                $wrap_form_coupon.find('.form-group').removeClass('has-error');
                $wrap_form_coupon.hide();
                $wrap_discount_coupon.attr('data-discount-coupon', data_parse.discount_format.replace(/[^0-9.]/g, ""));
                $wrap_discount_coupon.find('span.discount span').text(data_parse.discount_format+'  đ');
                $('.wrap-discount-coupon .mes').html(data_parse.mes);
                    // $('.wrap-discount-coupon .mes').css({'padding': '5px 0','border':'1px dashed #2196f3','margin-top': '10px'});
                $wrap_discount_coupon.show();
                if(typeof data_parse.mes !='undefined'){
                    $('.wrap-discount-coupon .mes').css({'padding': '5px 0','border':'1px dashed #2196f3','margin-top': '10px'});
                }else{
                    $('.wrap-discount-coupon .mes').removeAttr('style');
                }
            
               /* $('#cart_total_money').attr('data-total-money', data_parse.total_money_format.replace(/[^0-9.]/g, ""));
                $('#cart_total_money span').text(data_parse.total_money_format);*/
                update_total_money();
            }

        });
    }
});

$('.wrap-discount-coupon .remove').click(function(){
    var $wrap_discount_coupon = $('.wrap-discount-coupon');
    var params = {};
    $.post('/ajx/ajx.php?act=DELETE_COUPON', params, function(data) {
        var data_parse = JSON.parse(data);
        if(data_parse.status === 0){
            //Xoa khong thanh cong
        }else if(data_parse.status == 1){
            $wrap_discount_coupon.hide();
            $('#toggleInputCoupon').show();
            // $('#cart_total_money span').text(data_parse.total_money_format);
            $('.wrap-discount-coupon .mes').text('');
            $(".wrap-discount-coupon").attr('data-discount-coupon', 0);
            update_total_money();
        }

    });
});


$('.frm_checkout').form({
    inline : true,
    on: 'blur',
    fields: {
        fullname: {
            identifier  : 'fullname',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Hãy nhập họ và tên'
                }
            ]
        },
        phone: {
            identifier  : 'phone',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Hãy nhập số điện thoại'
                }
            ]
        },
        /*email: {
            identifier  : 'email',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Hãy nhập email'
                },
                {
                    type   : 'email',
                    prompt : 'Hãy nhập email đúng định dạng'
                }
            ]
        },*/
        selProvince: {
            identifier  : 'selProvince',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Hãy chọn tỉnh/thành phố'
                }
            ]
        },
        selDistrict: {
            identifier  : 'selDistrict',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Hãy chọn quận/huyện'
                }
            ]
        },
        address: {
            identifier  : 'address',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Hãy nhập địa chỉ'
                }
            ]
        },
        town_ward: {
            identifier  : 'town_ward',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Hãy nhập phường xã'
                }
            ]
        },
        selShippingType: {
            identifier  : 'selShippingType',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Hãy chọn loại vận chuyển'
                }
            ]
        }
    },
});