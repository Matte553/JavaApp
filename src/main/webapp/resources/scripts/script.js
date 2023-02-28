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



function getCurrentTime() {
    const d = new Date(); // for now
    return d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
}

function sendMessage(message, channel, event) {
    let name = message.name;
    let text = message.data.text;
    let image = message.data.image;
    let time = getCurrentTime();

    /*    let msgDarker = '';
        let msgLeftSide = '';
        if (message.isCustomer) {
            msgDarker = 'message-darker';
            msgLeftSide = 'float-right';
        }*/


    let msg = '<div>' +
        '<div class="message-user-name float-right"> ' +
        '<h:panelGroup>' + name + '</h:panelGroup>' +
        '</div>' +
        '<div class="user-message message-darker">' +
        '<h:panelGroup>' + text + '<h:panelGroup>' +
        '<h:outputLink value="http://localhost:8080/test-1.0-SNAPSHOT/jakarta.faces.resource/' + image + '.xhtml?ln=uploads">' +
        '<h:graphicImage library="uploads" ' + 'name=' + image + ' styleClass="img-message"/>' + '</h:outputLink>' +
        '</div>' +
        '<div class="message-time">' +
        ' <h:panelGroup>sent ' + time + '</h:panelGroup>' +
        '</div>' +
        '</div>';
    $('#message-container').append(msg);
}


