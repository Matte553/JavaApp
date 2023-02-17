package se.miun.dt170g.calendar;

import jakarta.annotation.ManagedBean;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
@Named
@RequestScoped
@ManagedBean
public class Month implements Serializable {
    int number;
    String name;
    ArrayList<Day> days;

    public Month() {
        this.number = 1;
        this.name   = enumToString(intToEnum(number));
        this.days   = createArrayList();
    }
    public Month(int number) {
        this.number = number;
        this.name   = enumToString(intToEnum(number));
        this.days   = createArrayList();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        this.name   = enumToString(intToEnum(number));
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

    private ArrayList<Day> createArrayList() {
        ArrayList<Day> result = new ArrayList<>();
        /*  FEB = 28
        *   JAN, MAR, MAY, JUL, AUG, OKT, DEC = 31
        *   APR, JUN, SEP, NOV = 30
         */
        int maxDays = number == 2 ? 28 : number % 2 == 0 && number < 7 || number % 2 == 1 && number > 8 ? 30 : 31 ;
        for (int i = 1; i <= maxDays; i++) {
            /* OBSERVERA!!!!!!!
            *  Använd kalender objektet så att jag kan få reda på vilken veckodag första dagen i månaden är
            *  BYGG LOGIK SÅ ATT DayNames.MONDAY kan bli utbytt så att det fortsätter rotera så att alla dagar
            *  i månaden får rätt namn!!!
            *
            *
             */
            Day temp = new Day(i, DayNames.MONDAY);
            result.add(temp);
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
