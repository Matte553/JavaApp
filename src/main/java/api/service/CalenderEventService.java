//API
package api.service;

import Entities.CalendarEventEntity;
import EntityController.EntityController;
import api.model.CalenderEventModel;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CalenderEventService {
    EntityController ec = new EntityController();

    public CalenderEventService() throws Exception {

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
    private Time stringToTime(String s){
        return new Time(s.charAt(0)+s.charAt(1),s.charAt(3)+s.charAt(4),s.charAt(6)+s.charAt(7));
    }
    private java.sql.Date stringToDate(String s){
        return new java.sql.Date(s.charAt(0)+s.charAt(1)+s.charAt(2)+s.charAt(3),s.charAt(5)+s.charAt(6),s.charAt(8)+s.charAt(9));
    }
    public CalenderEventModel addCalenderEvent(CalenderEventModel event) {
        CalendarEventEntity eventEntity = ec.addCalendarEvent(
                stringToTime(event.getStartTime()),
                stringToTime(event.getStopTime()),
                stringToDate(event.getStartDate()),
                stringToDate(event.getStopDate()),
                event.getSubject(),
                event.getFreeText(),
                event.getReferenceNumber(),
                event.getPersonId());
        return this.convertCalenderEntity(eventEntity);
    }
}
