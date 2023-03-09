package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "RESERVATION", schema = "APP")
@IdClass(ReservationEntityPK.class)
public class ReservationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RESERVATION_NUMBER")
    private int reservationNumber;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "INSTRUMENT_ID")
    private int instrumentId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PERSON_ID")
    private int personId;

    public ReservationEntity() {
    }

    public ReservationEntity(Integer reservationNumber, Integer instrumentId, Integer personId) {
        setReservationNumber(reservationNumber);
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
}
