'use strict';
$(document).ready(function () {
    clearErrorMessages(".chat-form div input[type='text']");
    $('#subject-list-container #subject-list li a').on('click', function () {
        $('#subject-list-container #subject-list li a').each(function () {
            if ($(this).hasClass('actived')) {
                $(this).removeClass('actived');
            }
        })
        $(this).addClass('actived');
    })
});

/**
 * Clear error messages on keydown events for this.
 * @param target css selector
 * @param eventType default keydown
 */
function clearErrorMessages(target, eventType = 'keydown') {
    $(target).on(eventType, function (e) {
        if ($(this).val().length >= 0) {
            $(this).closest('div').find('.error').text('');
        }
    })
}

/**
 * Scroll messages to bottom
 * @param id element id
 */
function scrollToBottom(id){
    const element = document.getElementById(id);
    element.scrollTop = element.scrollHeight;
}