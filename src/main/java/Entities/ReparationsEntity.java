package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "REPARATIONS", schema = "APP")
public class ReparationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "ERRAND_NUMBER", nullable = false)
    private int errandNumber;
    @Basic
    @Column(name = "PERSON_ID", nullable = false)
    private int personId;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "TYPE")
    private String type;

    public ReparationsEntity() {
    }

    public ReparationsEntity(Integer errandNumber, Integer personId, String description, String type) {
        setErrandNumber(errandNumber);
        setPersonId(personId);
        setDescription(description);
        setType(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReparationsEntity that = (ReparationsEntity) o;

        if (errandNumber != that.errandNumber) return false;
        if (personId != that.personId) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = errandNumber;
        result = 31 * result + personId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
