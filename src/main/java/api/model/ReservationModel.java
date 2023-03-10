//API
package api.model;

import Entities.ReservationEntity;
import jakarta.persistence.*;

public class ReservationModel {
    private int id;
    private int reservationNumber;
    private int instrumentId;
    private int personId;

    public ReservationModel() {
    }

    public ReservationModel(Integer id, Integer reservationNumber, Integer instrumentId, Integer personId) {
        setId(id);
        setReservationNumber(reservationNumber);
        setInstrumentId(instrumentId);
        setPersonId(personId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

        ReservationModel that = (ReservationModel) o;

        if (id != that.id) return false;
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