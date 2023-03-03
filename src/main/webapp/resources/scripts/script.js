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

    showSlides(0);
    $('#prev').on('click', function () {
        showSlides(-1);
    })
    $('#next').on('click', function () {
        showSlides(1);
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
function scrollToBottom(id) {
    const element = document.getElementById(id);
    element.scrollTop = element.scrollHeight;
}

function showSlides(next) {
    let currentId = '#current-slide';
    let currentSlide = parseInt($(currentId).val());
    let $slides = $('.slides');
    let $dots = $('.dot');
    $slides.each(function () {
        $(this).css('display', 'none');
    });
    $dots.each(function () {
        $(this).removeClass('active');
    });

    currentSlide += next;
    if (currentSlide >= $slides.length) {
        currentSlide = 0;
    }
    if (currentSlide < 0) {
        currentSlide = $slides.length - 1;
    }

    $slides.eq(currentSlide).css('display', 'block');
    $dots.eq(currentSlide).addClass('active');
    $(currentId).val(currentSlide);
}
