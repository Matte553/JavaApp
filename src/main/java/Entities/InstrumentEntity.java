package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "INSTRUMENT", schema = "APP")
public class InstrumentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "TYPE", nullable = true, length = 255)
    private String type;

    @Basic
    @Column(name = "NAME", nullable = true, length = 255)
    private String name;

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 0)
    private Double price;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 3600)
    private String description;

    public InstrumentEntity() {
    }

    public InstrumentEntity(String type, String name, Double price, String description) {
        setType(type);
        setName(name);
        setPrice(price);
        setDescription(description);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstrumentEntity that = (InstrumentEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "ID: " + id + " TYPE: " + type + " NAME: " + name + " PRICE: " + price + "\n"
                + "DESCRIPTION: " + description;
    }
}
