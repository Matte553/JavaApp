package Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "LOG", schema = "APP")
@IdClass(LogEntityPK.class)
public class LogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PERSON_ID")
    private int personId;
    @Basic
    @Column(name = "TEXT")
    private String text;
    @Basic
    @Column(name = "LOG_TIMESTAMP")
    private Timestamp logTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(Timestamp logTimestamp) {
        this.logTimestamp = logTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogEntity logEntity = (LogEntity) o;

        if (id != logEntity.id) return false;
        if (personId != logEntity.personId) return false;
        if (text != null ? !text.equals(logEntity.text) : logEntity.text != null) return false;
        if (logTimestamp != null ? !logTimestamp.equals(logEntity.logTimestamp) : logEntity.logTimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + personId;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (logTimestamp != null ? logTimestamp.hashCode() : 0);
        return result;
    }
}
