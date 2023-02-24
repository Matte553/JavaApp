package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "RESERVATION", schema = "APP")
public class ReservationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RESERVATION_NUMBER", nullable = false)
    private int reservationNumber;

    @Basic
    @Column(name = "INSTRUMENT_ID", nullable = false)
    private int instrumentId;

    @Basic
    @Column(name = "PERSON_ID", nullable = false)
    private int personId;

    public ReservationEntity() {
    }

    public ReservationEntity(Integer instrumentId, Integer personId) {
        setInstrumentId(instrumentId);
        setPersonId(personId);
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

        ReservationEntity that = (ReservationEntity) o;

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

    @Override
    public String toString() {
        return "RESERVATION_NUMBER: " + reservationNumber + " INSTRUMENT_ID: " + instrumentId + " PERSON_ID:" + personId;
    }
}
