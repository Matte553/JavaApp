package frontend.calendar;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class addCalendarEvent implements Serializable {

    private Service service = new Service();

    @Inject
    private CalendarHandler calendarHandler;

    private int day = 1;
    private int month = 1;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void assignCustomer(int id) {
        this.service.setCustomer(id);
    }

    public void save() throws Exception {
        int currentYear = calendarHandler.getCurrentYear();
        int firstWeekDay = calendarHandler.getFirstDaysEachMonth().get(getMonth()-1);
        Month temp_month = new Month(currentYear, getMonth(), firstWeekDay);
        Day temp_day = temp_month.getDays().get(getDay());
        calendarHandler.addEvent(temp_month, temp_day, service);
    }
}
