package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ReservationEntityPK implements Serializable {
    @Column(name = "RESERVATION_NUMBER", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationNumber;
    @Column(name = "INSTRUMENT_ID", nullable = false)
    @Id
    private int instrumentId;
    @Column(name = "PERSON_ID", nullable = false)
    @Id
    private int personId;

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
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

        ReservationEntityPK that = (ReservationEntityPK) o;

        if (reservationNumber != that.reservationNumber) return false;
        if (instrumentId != that.instrumentId) return false;
        if (personId != that.personId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationNumber;
        result = 31 * result + instrumentId;
        result = 31 * result + personId;
        return result;
    }
}
