package frontend.calendar;

import Entities.CalendarEventEntity;
import EntityController.EntityController;
import Entities.PersonEntity;
import Entities.CalendarEventEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityController ec = new EntityController();
        ArrayList<CalendarEventEntity> list = ec.getCalendarEvent();
        Service test = new Service(list.get(0));
        //System.out.println(test);

        CalendarHandler ch = new CalendarHandler();
        ArrayList<Day> list2 = ch.getList(11, 2023);
        for (Day d : list2) {
            System.out.println("date: " + d.number + " weekday: " + d.name);
        }


    }
}
