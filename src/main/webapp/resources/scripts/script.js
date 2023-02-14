'use strict';
$(document).ready(function () {
    $("#form div input[type='text']").on('keydown', function (e) {
        if ($(this).val().length >= 0) {
            $(this).closest('div').find('.error').text('');
        }
    });

});

