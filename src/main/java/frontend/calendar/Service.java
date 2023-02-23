package frontend.calendar;

import frontend.test.Person;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
public class Service implements Serializable {
    String startTime;
    String endTime;
    double cost;
    Person customer;
    String description;
    boolean active;

    public Service() {
    }

    public Service(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = 0;
        this.customer = new Person();
        this.description = "Description";
        this.active = false;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
