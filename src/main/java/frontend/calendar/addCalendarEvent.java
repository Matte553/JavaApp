package frontend.calendar;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class addCalendarEvent implements Serializable {

    private Service service = new Service();

    private Integer day = null;
    private Integer month = null;

    public Integer getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Integer getMonth() {
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

    public void setCustomerID(int id) throws Exception {
        this.service.setCustomer(id);
    }

    public Integer getCustomerID() {
        return this.service.getCustomer().getId();
    }

    private void clear() {
        this.service = new Service();
        this.month = null;
        this.day = null;
    }

    public void save() throws Exception {
        CalendarHandler calendarHandler = new CalendarHandler();
        int currentYear = calendarHandler.getCurrentYear();
        int firstWeekDay = calendarHandler.getFirstDaysEachMonth().get(getMonth()-1);
        System.out.println("currentYear: " + currentYear);
        System.out.println("firstWeekDay: " + firstWeekDay);
        Month temp_month = new Month(getMonth(), currentYear, firstWeekDay);
        Day temp_day = temp_month.getDays().get(getDay()-1);
        calendarHandler.addEvent(temp_month, temp_day, service);
        this.clear();
    }
}
