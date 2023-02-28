package frontend.calendar;

import jakarta.inject.Named;
import java.io.Serializable;

@Named
public class Service implements Serializable {
    int startTime;
    int endTime;
    double cost;
    Person customer;
    String description;
    String type;
    boolean active;

    public Service() {
    }

    public Service(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = 0;
        this.type = "noType";
        this.customer = new Person();
        this.description = "Description";
        this.active = false;
    }

    /*public Service(int startTime, int endTime, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = 0;
        this.type = "noType";
        this.customer = new Person();
        this.description = description;
        this.active = false;
    }*/

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
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
