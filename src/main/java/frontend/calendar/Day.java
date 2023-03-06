package frontend.calendar;

import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named
public class Day implements Serializable {
    int number;
    int weekday;
    String name;
    ArrayList<Service> services;

    public Day() {
        this.number = 1;
        this.weekday= 1;
        this.name = enumToString(intToEnum(weekday));
        this.services = createArrayList();
    }
    public Day(int number, int weekday) {
        this.number = number;
        this.weekday= weekday;
        this.name = enumToString(intToEnum(weekday));
        this.services = createArrayList();
        /*addService(new Service(8,10));
        addService(new Service(13,17));*/
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public void addService(Service newSer) {
        boolean same = false;
        for (Service added : this.services) {
            if (newSer.startTime <= added.startTime && newSer.endTime <= added.endTime || newSer.startTime == 12) {
                same = true;
                break;
            }
        }
        if (!same) {
            this.services.add(newSer);
        }
    }
    private DayNames intToEnum(int dayNumber) {
        DayNames result = DayNames.MONDAY;
        switch (dayNumber) {
            case 1:
                break;
            case 2:
                result = DayNames.TUESDAY;
                break;
            case 3:
                result = DayNames.WEDNESDAY;
                break;
            case 4:
                result = DayNames.THURSDAY;
                break;
            case 5:
                result = DayNames.FRIDAY;
                break;
            case 6:
                result = DayNames.SATURDAY;
                break;
            case 7:
                result = DayNames.SUNDAY;
                break;
        }
        return result;
    }
    private String enumToString(DayNames dn) {
        String result = "";
        switch (dn) {
            case MONDAY:
                result = "Måndag";
                break;
            case TUESDAY:
                result = "Tisdag";
                break;
            case WEDNESDAY:
                result = "Onsdag";
                break;
            case THURSDAY:
                result = "Torsdag";
                break;
            case FRIDAY:
                result = "Fredag";
                break;
            case SATURDAY:
                result = "Lördag";
                break;
            case SUNDAY:
                result = "Söndag";
                break;
        }
        return result;
    }
    private ArrayList<Service> createArrayList() {
        ArrayList<Service> result = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
            types.add("repair");
            types.add("customer");
            types.add("writing");
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Peter", "Stegeby", "+467271341", "peter.stegeby@gmail.com"));
        persons.add(new Person(2, "Brutus", "Frazze", "+461372151", "brutus.frazze@gmail.com"));
        persons.add(new Person(3, "Hans", "Klorén", "+101150123", "hasse.klo@gmail.com"));
        ArrayList<String> descriptions = new ArrayList<>();
            descriptions.add("Time to repair!");
            descriptions.add("Having a customer over.");
            descriptions.add("Writing on paper!");
            descriptions.add("This is a very very long description so that I can show what happens when the description simply does NOT fit in the regular box of description, no! a scrollbar appears and so you can still read all of this without any problems! ;)");



        for (int i = 8; i < 18; i += 1) {
            if (i != 12) {
                Service temp = new Service(i, i+1, (i*2.15),types.get(i%3),persons.get(i%3),descriptions.get(i%4));
                result.add(temp);
            }
        }

        return result;
    }
}

/*  Funktionalitet:
*   Fixa funktion så att man enkelt kan plocka ut en service från dagen.
*   Ersätt en arrayList med en annan. Finns typ redan i setter..
 */