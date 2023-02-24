'use strict';
$(document).ready(function () {
    clearErrorMessages(".chat-form div input[type='text']");
});

/**
 * Clear error messages on keydown events for this.
 * @param target css selector
 */
function clearErrorMessages(target) {
    $(target).on('keydown', function (e) {
        if ($(this).val().length >= 0) {
            $(this).closest('div').find('.error').text('');
        }
    })}