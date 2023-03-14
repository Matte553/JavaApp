'use strict';
function moveToWeek(id) {
    let url = "http://localhost:8080/test-1.0-SNAPSHOT/calendar_week.xhtml?weekID="+id;
    location.href = url;
}



function alterService(id) {
    let zoom_in = 2;
    let usable_id = "#"+id;
    let check = $(usable_id).css('transform');
    let check_phrase = "matrix("+zoom_in+", 0, 0, "+zoom_in+", 0, 0)"
    let test = $(usable_id).css('transform');
    console.log("test: "+test)
    console.log("openService: "+usable_id)

    if (check !== check_phrase) {
        $(usable_id).css({'transform' : 'scale('+zoom_in+')',
                                'position' : 'fixed',
                                'top' : '50%',
                                'left' : '30%',
                                '-webkit-transform': 'scale('+zoom_in+')',
        });
        $('.description', $(usable_id)).css({ 'visibility': 'visible',
                                                    'overflow' : 'auto'
        });
        $('.type', $(usable_id)).css({ 'visibility': 'visible',

        });
        $('.cost', $(usable_id)).css({ 'visibility': 'visible',

        });
        $('.btn', $(usable_id)).css({ 'visibility': 'visible',

        });

    } else {
        $(usable_id).css({'transform' : 'initial',
                                'min-height' : 'auto',
                                'min-width' : 'auto',
                                'position' : 'initial',
                                'margin-left': '8px'
        });
        $('.description', $(usable_id)).css({ 'visibility' : 'collapse',
                                                    'overflow' : 'hidden'
        });
        $('.type', $(usable_id)).css({ 'visibility': 'collapse',

        });
        $('.cost', $(usable_id)).css({ 'visibility': 'collapse',

        });
        $('.btn', $(usable_id)).css({ 'visibility': 'collapse',

        });
    }

}