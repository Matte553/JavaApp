//API
package api.service;

import Entities.CalendarEventEntity;
import EntityController.EntityController;
import api.model.CalenderEventModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalenderEventService {
    EntityController ec = new EntityController();

    public CalenderEventService() throws Exception {

    }

    private CalenderEventModel convertCalenderEntity(CalendarEventEntity value) {
        return new CalenderEventModel(
                value.getId(),
                value.getStartTime(),
                value.getStopTime(),
                value.getStartDate(),
                value.getStopDate(),
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

    public CalenderEventModel addCalenderEvent(CalenderEventModel event) {
        CalendarEventEntity eventEntity = ec.addCalendarEvent(
                event.getStartTime(),
                event.getStopTime(),
                event.getStartDate(),
                event.getStopDate(),
                event.getSubject(),
                event.getFreeText(),
                event.getReferenceNumber(),
                event.getPersonId());
        return this.convertCalenderEntity(eventEntity);
    }
}
