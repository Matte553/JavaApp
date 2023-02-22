package se.miun.dt170g.calendar;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Named
@RequestScoped
public class CalendarHandler implements Serializable {
    private Calendar calendar;
    private ArrayList<Integer> firstDaysEachMonth;
    private int currentYear;
    private int currentMonth;
    private int currentWeek;
    private int currentDay;
    private ArrayList<Month> months;

    CalendarHandler() {
        this.calendar           = Calendar.getInstance();
        this.currentYear        = getYear();
        this.currentMonth       = getMonth();
        this.currentWeek        = getWeek();
        this.currentDay         = getDay();
        this.firstDaysEachMonth = fillWithFirstDays();
        this.months             = createArrayList();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar year) {
        this.calendar       = year;
        this.currentYear    = getYear();
    }

    public ArrayList<Integer> getFirstDaysEachMonth() {
        return firstDaysEachMonth;
    }
    public void setFirstDaysEachMonth(ArrayList<Integer> firstDaysEachMonth) {
        this.firstDaysEachMonth = firstDaysEachMonth;
    }

    private ArrayList<Integer> fillWithFirstDays() {
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < 12; i++) {
            Calendar temp = new GregorianCalendar(getYear(),i, 1);
            result.add(dateToInt(temp.getTime()));
        }
        return result;
    }
    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }
    public ArrayList<Month> getMonths() {
        return months;
    }
    public void setMonths(ArrayList<Month> months) {
        this.months = months;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    private int dateToInt(Date date) {
        String day = date.toString().substring(0,3);

        if (day.equals("Mon")) return 1;
        if (day.equals("Tue")) return 2;
        if (day.equals("Wed")) return 3;
        if (day.equals("Thu")) return 4;
        if (day.equals("Fri")) return 5;
        if (day.equals("Sat")) return 6;
        if (day.equals("Sun")) return 7;

        return -1;
    }

    private ArrayList<Month> createArrayList() {
        ArrayList<Month> result = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            Month temp = new Month(i, this.currentYear, this.firstDaysEachMonth.get(i-1));
            result.add(temp);
        }
        return result;
    }

    private int getYear() {
        return calendar.get(Calendar.YEAR);
    }
    private int getMonth() {
        return calendar.get(Calendar.MONTH)+1;
    }
    private int getDay() {
        return calendar.get(Calendar.DATE);
    }
    private int getWeek() {return calendar.get(Calendar.WEEK_OF_YEAR);}
}
