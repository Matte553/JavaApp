//API
package api.service;

import Entities.CalendarEventEntity;
import EntityController.EntityController;
import api.model.CalenderEventModel;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalenderEventService {
    EntityController ec = new EntityController();

    public CalenderEventService() throws Exception {

    }

    private java.sql.Date stringToEventDate(String eventDate) throws ParseException {
        java.util.Date utilEventDateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
        return new java.sql.Date(utilEventDateFormat.getTime());
    }

    private java.sql.Time stringToEventTime(String eventTime) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:00:00");
        return new java.sql.Time(formatter.parse(eventTime).getTime());
    }

    private CalenderEventModel convertCalenderEntity(CalendarEventEntity value) {
        return new CalenderEventModel(
                value.getId(),
                value.getStartTime().toString(),
                value.getStopTime().toString(),
                value.getStartDate().toString(),
                value.getStopDate().toString(),
                value.getSubject(),
                value.getFreeText(),
                value.getReferenceNumber(),
                value.getPersonId());
    }

    private List<CalenderEventModel> convertListEntity(ArrayList<CalendarEventEntity> dbList) {
        List<CalenderEventModel> apiList = new ArrayList<>();

        for (CalendarEventEntity entity : dbList) {
            apiList.add( this.convertCalenderEntity(entity));
        }
        return apiList;
    }

    public List<CalenderEventModel> getAllCalenderEvent() {
        ArrayList<CalendarEventEntity> dbList = ec.getCalendarEvent();
        return this.convertListEntity(dbList);
    }

    public CalenderEventModel addCalenderEvent(CalenderEventModel event) throws ParseException {

        CalendarEventEntity eventEntity = ec.addCalendarEvent(
                this.stringToEventTime(event.getStartTime()),
                this.stringToEventTime(event.getStopTime()),
                this.stringToEventDate(event.getStartDate()),
                this.stringToEventDate(event.getStopDate()),
                event.getSubject(),
                event.getFreeText(),
                event.getReferenceNumber(),
                event.getPersonId());
        return this.convertCalenderEntity(eventEntity);
    }
}
