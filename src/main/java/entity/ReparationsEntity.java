package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Reparations", schema = "APP", catalog = "")
public class ReparationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "CustomerID")
    private int customerId;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "InstrumentType")
    private String instrumentType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReparationsEntity that = (ReparationsEntity) o;
        return id == that.id && customerId == that.customerId && Objects.equals(description, that.description) && Objects.equals(instrumentType, that.instrumentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, description, instrumentType);
    }
}
