package Entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "CALENDAR", schema = "APP")
public class CalendarEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "START_TIME", nullable = false)
    private Time startTime;
    @Basic
    @Column(name = "STOP_TIME", nullable = false)
    private Time stopTime;
    @Basic
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;
    @Basic
    @Column(name = "STOP_DATE", nullable = false)
    private Date stopDate;
    @Basic
    @Column(name = "SUBJECT", nullable = false, length = 50)
    private String subject;
    @Basic
    @Column(name = "FREE_TEXT", nullable = true, length = 5000)
    private String freeText;
    @Basic
    @Column(name = "REFERENCE_NUMBER", nullable = false)
    private int referenceNumber;
    @Basic
    @Column(name = "PERSON_ID", nullable = false)
    private int personId;

    public CalendarEntity() {
    }

    public CalendarEntity(Time startTime, Time stopTime, Date startDate, Date stopDate, String subject, String freeText, Integer referenceNumber, Integer personId) {
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getStopTime() {
        return stopTime;
    }

    public void setStopTime(Time stopTime) {
        this.stopTime = stopTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
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

        CalendarEntity that = (CalendarEntity) o;

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
