$('.message a').click(function(){
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

$(function () {
    $("#js-username-form").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: false
            },
            password: {
                required: false
            }
        }
    });

    $("#js-register-form").validate({
        rules: {
            name: {
                required: true
            },
            last_name: {
                required: true
            },
            username: {
                required: true,
                minlength: 5
            },
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            },
            password_confirm: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            }
        },
        messages: {
            name: {
                required: false
            },
            last_name: {
                required: false
            },
            username: {
                required: false,
                minlength: false,
            },
            email: {
                required: false,
                email: false
            },
            password: {
                required: false,
                minlength: false
            },
            password_confirm: {
                required: false,
                minlength: false,
                equalTo: false
            }
        }
    });
});