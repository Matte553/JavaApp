package frontend.calendar;

import Entities.CalendarEventEntity;
import EntityController.EntityController;
import Entities.PersonEntity;
import Entities.CalendarEventEntity;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityController ec = new EntityController();
        ArrayList<CalendarEventEntity> list = ec.getCalendarEvent();
        Service test = new Service(list.get(0));
        System.out.println(test);

        Month test2 = new Month(3,2023,3);
        ArrayList<Day> list2 = test2.getDays();
        System.out.println(test2.getName());
        for (Day d : list2) {
            System.out.println(d.number);
            for (Service s : d.getServices()) {
                System.out.println(s);
            }
        }


    }
}
