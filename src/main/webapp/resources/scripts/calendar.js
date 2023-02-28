'use strict';
function moveToWeek(id) {
    let url = "http://localhost:8080/test-1.0-SNAPSHOT/calendar_week.xhtml?weekID="+id;
    location.href = url;
}

function openService() {
    document.getElementsByClassName("description").item(0);
}