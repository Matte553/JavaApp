package api.model;
//API

import java.sql.Timestamp;

public class LogModel {
    private int id;
    private int personId;
    private String text;
    private Timestamp logTimestamp;

    public LogModel() {};

    public LogModel(int id, int personId, String text, Timestamp logTimestamp) {
        this.id = id;
        this.personId = personId;
        this.text = text;
        this.logTimestamp = logTimestamp;
    }

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

        LogModel logEntity = (LogModel) o;

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

