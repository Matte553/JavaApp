package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "RESERVATION", schema = "APP")
@IdClass(ReservationEntityPK.class)
public class ReservationEntity {
    @Id
    private int reservationNumber;

    @Id
    private int instrumentId;

    @Id
    private int personId;

    public ReservationEntity() {
    }

    public ReservationEntity(Integer instrumentId, Integer personId) {
        setInstrumentId(instrumentId);
        setPersonId(personId);
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RESERVATION_NUMBER", nullable = false)
    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "INSTRUMENT_ID", nullable = false)
    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PERSON_ID", nullable = false)
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
