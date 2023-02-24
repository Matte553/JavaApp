package frontend.calendar;

import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named
public class Week implements Serializable {
    private int number;
    ArrayList<Day> days;
    Day firstDayOfWeek;

    Week() {
        this.number         = 1;
        this.firstDayOfWeek = new Day(1, 1);
        this.days           = createArrayList();
    }

    Week(int number, Day firstDayOfWeek) {
        this.number         = number;
        this.firstDayOfWeek = firstDayOfWeek;
        this.days           = createArrayList();
    }

    private ArrayList<Day> createArrayList() {
        ArrayList<Day> result = new ArrayList<>();
        int startDay        = this.firstDayOfWeek.number;
        int startWeekDay    = this.firstDayOfWeek.weekday;
        for (int i = 1; i < 8; i++) {

        }

        return result;
    }
}
