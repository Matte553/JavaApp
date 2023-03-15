package frontend.calendar;

import Entities.CalendarEventEntity;
import Entities.InstrumentEntity;
import EntityController.EntityController;
import Entities.PersonEntity;
import Entities.CalendarEventEntity;
import frontend1.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityController ec = new EntityController();
        /*ArrayList<CalendarEventEntity> list = ec.getCalendarEvent();
        Service test = new Service(list.get(0));
        //System.out.println(test);

        ArrayList<PersonEntity> list2 = ec.getPersons();

        for(PersonEntity pe: list2) {
            System.out.println(pe.getFirstname() + " " + pe.getLastname());
        }*/

        CalendarHandler ch = new CalendarHandler();
        Person peter = new Person(ec.getPersonWithID(4));
        Person anders = new Person(ec.getAdmin());
        String subject = "Reservation";
        String subject2 = "Reperation";
        String subject3 = "Blocked";
        //ec.addCustomer(Person.personToPersonEntity(peter), subject);
        String description = "Anders arbetar inte just nu!";
        String description2 = "Peter kommer och får sitt instrument fixat.";

        addCalendarEvent test1 = new addCalendarEvent();
        Service s = new Service(10, 12, 200, subject, peter, description2, 2234);
        test1.setMonth(3);
        test1.setDay(15);
        test1.setService(s);

        /*Service s2 = new Service(11, 17, 0, subject3, anders, description, 2234);
        test1.setMonth(3);
        test1.setDay(16);
        test1.setService(s2);*/
        test1.save();


        /*Month m = new Month(2, 2023, 3);
        Day d = m.getDays().get(12);
        Service s = new Service(8, 10, 200, subject, peter, "Testar, testar", 2234);
        ch.addEvent(m, d, s);*/
        /*ArrayList<Day> list2 = ch.getList(11, 2023);
        for (Day d : list2) {
            System.out.println("date: " + d.getNumber() + " weekday: " + d.getName());
        }*/
        //ch.removeEvent(8);
    }
}
