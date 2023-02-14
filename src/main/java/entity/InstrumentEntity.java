package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Instrument", schema = "APP")
public class InstrumentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Type")
    private String type;
    @Basic
    @Column(name = "Price")
    private String price;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "ReservedBy")
    private Integer reservedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(Integer reservedBy) {
        this.reservedBy = reservedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstrumentEntity that = (InstrumentEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(reservedBy, that.reservedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, description, reservedBy);
    }
}
