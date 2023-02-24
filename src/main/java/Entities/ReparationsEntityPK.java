package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ReparationsEntityPK implements Serializable {
    @Column(name = "ERRAND_NUMBER", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int errandNumber;

    @Column(name = "PERSON_ID", nullable = false)
    @Id
    private int personId;

    public int getErrandNumber() {
        return errandNumber;
    }

    public void setErrandNumber(int errandNumber) {
        this.errandNumber = errandNumber;
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

        ReparationsEntityPK that = (ReparationsEntityPK) o;

        if (errandNumber != that.errandNumber) return false;
        if (personId != that.personId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = errandNumber;
        result = 31 * result + personId;
        return result;
    }
}
