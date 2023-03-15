package frontend.calendar;

import Entities.CalendarEventEntity;
import EntityController.EntityController;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Time;

@Named
@ViewScoped
public class Service implements Serializable {
    private int id;
    private Integer startTime;
    private Integer endTime;
    private Double cost;
    private Person customer;
    private String description;
    private String type;
    private int referenceNumber;

    public Service() {
    }

    public Service(int startTime, int endTime) {
        this.id             = 0;
        this.startTime      = startTime;
        this.endTime        = endTime;
        this.cost           = 0.0;
        this.type           = "noType";
        this.customer       = new Person();
        this.description    = "Vi testar med en mycket längre description än tidigare och ser vad som händer";
        this.referenceNumber= 0;
    }

    public Service(int startTime, int endTime, double cost, String type, Person customer, String description, int referenceNumber) {
        this.id             = -1;
        this.startTime      = startTime;
        this.endTime        = endTime;
        this.cost           = cost;
        this.type           = type.toLowerCase();
        this.customer       = customer;
        this.description    = description;
        this.referenceNumber= referenceNumber;
    }

    public Service(CalendarEventEntity ce) throws Exception {
        EntityController entityController = new EntityController();
        this.id             = ce.getId();
        this.startTime      = ceTimeToInt(ce.getStartTime());
        this.endTime        = ceTimeToInt(ce.getStopTime());
        this.cost           = 150.0;
        this.type           = ce.getSubject().toLowerCase();
        this.customer       = new Person(entityController.getPersonWithID(ce.getPersonId()));
        this.description    = ce.getFreeText();
        this.referenceNumber= ce.getReferenceNumber();
    }
    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
            this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Double getCost() {
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

    public void setCustomer(int id) throws Exception {
        EntityController entityController = new EntityController();
        customer = new Person(entityController.getPersonWithID(id));
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return "\t" + referenceNumber + "@" + type + "/" + startTime + ":00-" + endTime + ":00\n" + customer;
    }

    public static Time intToTime(int time) {
        return new Time(time, 0, 0);
    }

    private int ceTimeToInt(Time time) {
        String subStr = time.toString().substring(0,2);
        return Integer.parseInt(subStr);
    }
}
