package EntityController;

import Entities.*;
import java.util.ArrayList;
// Kalender Tabell i DB: KALENDER(id, starttid, sluttid, startdatum, slutdatum, errandNumber, ämne, fritext);;


public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();
        ec.addLog(2, "Detta är min log för Anton");

        ArrayList<CalendarEventEntity> list = ec.getEventsWithinMonth(5);
        for (int i = 0 ; i < list.size(); i++) {
            System.out.println(list.get(i).getReferenceNumber());
        }
    }
}