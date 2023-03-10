//API
package api.service;

import Entities.CalendarEventEntity;
import EntityController.EntityController;
import api.model.CalenderModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalenderService {
    EntityController ec = new EntityController();

    public CalenderService() throws Exception {

    }

    private CalenderModel convertCalenderEntity(CalendarEventEntity value) {
        return new CalenderModel(
                value.getStartTime(),
                value.getStopTime(),
                value.getStartDate(),
                value.getStopDate(),
                value.getSubject(),
                value.getFreeText(),
                value.getReferenceNumber(),
                value.getPersonId());
    }

    private List<CalenderModel> convertListEntity(ArrayList<CalendarEventEntity> dbList) {
        List<CalenderModel> apiList = new ArrayList<>();

        for (CalendarEventEntity entity : dbList) {
            apiList.add( this.convertCalenderEntity(entity));
        }
        return apiList;
    }

    public List<CalenderModel> getAllCalenderEvent() {
        ArrayList<CalendarEventEntity> dbList = ec.getCalendarEvent();
        return this.convertListEntity(dbList);
    }

    public CalenderModel addCalenderEvent(CalenderModel event) {
        CalendarEventEntity eventEntity = ec.addCalendar(
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
