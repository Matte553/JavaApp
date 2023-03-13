package frontend.calendar;


import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import EntityController.EntityController;
import Entities.CalendarEventEntity;

@Named
public class Month implements Serializable {
    private int number;
    private String name;
    private ArrayList<Day> days;
    private int year;
    private int firstWeekday;
    // paddedDays exists so that I can get the first day under the correct day of the week during the month view.
    private int paddedDays;

    public Month() throws Exception {
        this.number         = 1;
        this.name           = enumToString(intToEnum(number));
        this.days           = createArrayList();
        this.year           = 2023;
        this.firstWeekday   = 1;
    }
    public Month(int number, int year, int firstWeekday) throws Exception {
        this.number         = number;
        this.name           = enumToString(intToEnum(number));
        this.firstWeekday   = firstWeekday;
        this.year           = year;
        this.days           = createArrayList();
        this.paddedDays     = firstWeekday-1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws Exception {
        this.number = number;
        this.name   = enumToString(intToEnum(number));
        this.days   = createArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFirstWeekday() {
        return firstWeekday;
    }

    public void setFirstWeekday(int firstWeekday) {
        this.firstWeekday = firstWeekday;
    }

    public int getPaddedDays() {
        return paddedDays;
    }

    public void setPaddedDays(int paddedDays) {
        this.paddedDays = paddedDays;
    }

    public static java.sql.Date intToDate(int year, int month, int day) {
        return new java.sql.Date(year-1900, month, day);
    }

    /* PRIVATE FUNCTIONS */
    private int ecDateToInt(Date date) {
        String subString = date.toString().substring(8,10);
        return Integer.parseInt(subString);
    }

    private ArrayList<Day> createArrayList() throws Exception {
        ArrayList<Day> result = new ArrayList<>();
        EntityController ec = new EntityController();
        /*  FEB = 28
        *   JAN, MAR, MAY, JUL, AUG, OKT, DEC = 31
        *   APR, JUN, SEP, NOV = 30
        */
        int maxDays = number == 2 ? 28 : number % 2 == 0 && number < 7 || number % 2 == 1 && number > 8 ? 30 : 31;
        int weekday = firstWeekday;
        for (int i = 1; i <= maxDays; i++) {
            Day temp = new Day(i, weekday);
            weekday++;
            weekday = weekday % 8 == 0 ? 1 : weekday;
            result.add(temp);
        }

        // Fill days with Services
        ArrayList<CalendarEventEntity> list = ec.getEventsWithinMonth(this.number);
        for (CalendarEventEntity cee : list) {
            Date date = cee.getStartDate();
            Service serv_temp = new Service(cee);
            result.get(ecDateToInt(date)-1).addService(serv_temp);
        }

        return result;
    }
    private String enumToString(MonthDays month) {
        String result = "";
        switch (month) {
            case JANUARY:
                result = "Januari";
                break;
            case FEBRUARY:
                result = "Februari";
                break;
            case MARCH:
                result = "Mars";
                break;
            case APRIL:
                result = "April";
                break;
            case MAY:
                result = "Maj";
                break;
            case JUNE:
                result = "Juni";
                break;
            case JULY:
                result = "Juli";
                break;
            case AUGUST:
                result = "Augusti";
                break;
            case SEPTEMBER:
                result = "September";
                break;
            case OCTOBER:
                result = "Oktober";
                break;
            case NOVEMBER:
                result = "November";
                break;
            case DECEMBER:
                result = "December";
                break;
        }
        return result;
    }
    private MonthDays intToEnum(int monthNumber) {
        MonthDays result = MonthDays.JANUARY;
        switch (monthNumber) {
            case 1:
                break;
            case 2:
                result = MonthDays.FEBRUARY;
                break;
            case 3:
                result = MonthDays.MARCH;
                break;
            case 4:
                result = MonthDays.APRIL;
                break;
            case 5:
                result = MonthDays.MAY;
                break;
            case 6:
                result = MonthDays.JUNE;
                break;
            case 7:
                result = MonthDays.JULY;
                break;
            case 8:
                result = MonthDays.AUGUST;
                break;
            case 9:
                result = MonthDays.SEPTEMBER;
                break;
            case 10:
                result = MonthDays.OCTOBER;
                break;
            case 11:
                result = MonthDays.NOVEMBER;
                break;
            case 12:
                result = MonthDays.DECEMBER;
                break;
        }
        return result;
    }


}
