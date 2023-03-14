package api.model;
//API

import java.sql.Date;
import java.sql.Time;

public class CalenderEventModel {
    private int id;
    private String startTime;
    private String stopTime;
    private String startDate;
    private String stopDate;
    private String subject;
    private String freeText;
    private int referenceNumber;
    private int personId;

    public CalenderEventModel() {
    }

    public CalenderEventModel(Integer id, String startTime, String stopTime, String startDate, String stopDate, String subject, String freeText, Integer referenceNumber, Integer personId) {
        setId(id);
        setStartTime(startTime);
        setStopTime(stopTime);
        setStartDate(startDate);
        setStopDate(stopDate);
        setSubject(subject);
        setFreeText(freeText);
        setReferenceNumber(referenceNumber);
        setPersonId(personId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public int getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalenderEventModel that = (CalenderEventModel) o;

        if (id != that.id) return false;
        if (referenceNumber != that.referenceNumber) return false;
        if (personId != that.personId) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (stopTime != null ? !stopTime.equals(that.stopTime) : that.stopTime != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (stopDate != null ? !stopDate.equals(that.stopDate) : that.stopDate != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (freeText != null ? !freeText.equals(that.freeText) : that.freeText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (stopTime != null ? stopTime.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (stopDate != null ? stopDate.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (freeText != null ? freeText.hashCode() : 0);
        result = 31 * result + referenceNumber;
        result = 31 * result + personId;
        return result;
    }
}
