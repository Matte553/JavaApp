package Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "LOG", schema = "APP")
public class LogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "TEXT")
    private String text;
    @Basic
    @Column(name = "PERSON_ID")
    private Integer personId;
    @Basic
    @Column(name = "LOG_TIMESTAMP")
    private Timestamp logTimestamp;

    public LogEntity(){

    }

    public LogEntity(int id, String text, Timestamp timestamp){
        setPersonId(id);
        setText(text);
        setLogTimestamp(timestamp);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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
        if (text != null ? !text.equals(logEntity.text) : logEntity.text != null) return false;
        if (personId != null ? !personId.equals(logEntity.personId) : logEntity.personId != null) return false;
        if (logTimestamp != null ? !logTimestamp.equals(logEntity.logTimestamp) : logEntity.logTimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (personId != null ? personId.hashCode() : 0);
        result = 31 * result + (logTimestamp != null ? logTimestamp.hashCode() : 0);
        return result;
    }
}
