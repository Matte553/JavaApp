package frontend.calendar;

import Entities.CalendarEventEntity;
import EntityController.EntityController;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Time;

@Named
public class Service implements Serializable {
    int startTime;
    int endTime;
    double cost;
    Person customer;
    String description;
    String type;
    int referenceNumber;

    public Service() {
    }

    public Service(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = 0;
        this.type = "noType";
        this.customer = new Person();
        this.description = "Vi testar med en mycket längre description än tidigare och ser vad som händer";
        this.referenceNumber = 0;
    }

    public Service(int startTime, int endTime, double cost, String type, Person customer, String description, int referenceNumber) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.type = type;
        this.customer = customer;
        this.description = description;
        this.referenceNumber   = referenceNumber;
    }

    public Service(CalendarEventEntity ce) throws Exception {
        EntityController ec = new EntityController();
        this.startTime      = ceTimeToInt(ce.getStartTime());
        this.endTime        = ceTimeToInt(ce.getStopTime());
        this.cost           = 150;
        this.type           = ce.getSubject().toLowerCase();
        this.customer       = new Person(ec.getPersonWithID(ce.getPersonId()));
        this.description    = ce.getFreeText();
        this.referenceNumber= ce.getReferenceNumber();
    }

    private int ceTimeToInt(Time time) {
        String subStr = time.toString().substring(0,2);
        return Integer.parseInt(subStr);
    }
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

    public String getType() {
        return type;
    }

    public String getTypeSwedish(String type) {
        if(type.equals("reparation")) return "Reparation";
        if(type.equals("reservation")) return "Reservation";
        if(type.equals("blocked")) return "Blockerad";

        return "-1";
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\t" + referenceNumber + "@" + type + "/" + startTime + ":00-" + endTime + ":00\n" + customer;
    }
}
