//API
package api.service;

import Entities.CalendarEntity;
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

    private CalenderModel convertCalenderEntity(CalendarEntity value) {
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

    private List<CalenderModel> convertListEntity(ArrayList<CalendarEntity> dbList) {
        List<CalenderModel> apiList = new ArrayList<>();

        for (CalendarEntity entity : dbList) {
            apiList.add( this.convertCalenderEntity(entity));
        }
        return apiList;
    }

    public List<CalenderModel> getAllCalenderEvent() {
        ArrayList<CalendarEntity> dbList = ec.getCalendar();
        return this.convertListEntity(dbList);
    }

    public CalenderModel addCalenderEvent(CalenderModel event) {
        CalendarEntity eventEntity = ec.addCalendar(
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
