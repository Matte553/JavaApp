package se.miun.dt170g.calendar;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
@Named
@RequestScoped
public class Day implements Serializable {
    int number;
    String name;
    ArrayList<Service> services;

    public Day() {
    }
    public Day(int number, DayNames name) {
        this.number = number;
        this.name = enumToString(name);
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