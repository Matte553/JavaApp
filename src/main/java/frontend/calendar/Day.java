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
        for (int i = 10; i < 18; i++) {
            if (i != 12) {
                Service temp = new Service(Integer.toString(i), Integer.toString(++i));
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