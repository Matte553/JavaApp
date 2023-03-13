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

        ArrayList<PersonEntity> list2 = ec.getPersons();

        for(PersonEntity pe: list2) {
            System.out.println(pe.getFirstname() + " " + pe.getLastname());
        }

        CalendarHandler ch = new CalendarHandler();
        Person peter = new Person("Peter", "Stegeby", "0737271341", "peter.stegeby@gmail.com");
        String subject = "Reservation";
        ec.addCustomer(Person.personToPersonEntity(peter), subject);
        /*Month m = new Month(2, 2023, 3);
        Day d = m.getDays().get(12);
        Service s = new Service(8, 10, 200, subject, peter, "Testar, testar", 2234);
        ch.addEvent(m, d, s);*/
        /*ArrayList<Day> list2 = ch.getList(11, 2023);
        for (Day d : list2) {
            System.out.println("date: " + d.getNumber() + " weekday: " + d.getName());
        }*/


    }
}
