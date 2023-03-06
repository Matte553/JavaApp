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
    /*let test = $(usable_id).css('margin-right');
    console.log("test: "+test)*/
    console.log("openService: "+usable_id)

    if (check !== check_phrase) {
        $(usable_id).css({'transform' : 'scale('+ zoom_in +')',
                                'min-height' : '25vh',
                                'position' : 'flexed',
                                'margin-right': '50%',
        });
        $('.description', $(usable_id)).css({ 'visibility': 'visible',
                                                    'overflow' : 'auto'
        });
        $('.btn', $(usable_id)).css({ 'visibility': 'visible',

        });

    } else {
        $(usable_id).css({'transform' : 'initial',
                                'min-height' : 'auto',
                                'position' : 'initial',
                                'margin-right': '8px'
        });
        $('.description', $(usable_id)).css({ 'visibility' : 'collapse',
                                                    'overflow' : 'hidden'
        });
        $('.btn', $(usable_id)).css({ 'visibility': 'collapse',

        });
    }

}