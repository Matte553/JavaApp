package EntityController;

import Entities.*;
import java.util.ArrayList;
// Kalender Tabell i DB: KALENDER(id, starttid, sluttid, startdatum, slutdatum, errandNumber, ämne, fritext);;


public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();

        ArrayList<CalendarEventEntity> test = ec.getCalendarEvent();

        for (CalendarEventEntity ce : test) {
            System.out.println(ce.getId() + "@" + ce.getPersonId() + ":" + ce.getStartDate() + "-" + ce.getStopTime() + "/" + ce.getReferenceNumber());
        }

        ArrayList<CalendarEventEntity> list = ec.getEventsWithinMonth(5);
        for (int i = 0 ; i < list.size(); i++) {
            System.out.println(list.get(i).getReferenceNumber());
        }
    }
}