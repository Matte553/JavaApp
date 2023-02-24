package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "REPARATIONS", schema = "APP")
@IdClass(ReparationsEntityPK.class)
public class ReparationsEntity {
    @Id
    private int errandNumber;

    @Id
    private int personId;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 3600)
    private String description;
    @Basic
    @Column(name = "TYPE", nullable = true, length = 255)
    private String type;

    public ReparationsEntity() {
    }

    public ReparationsEntity(Integer personId, String description, String type) {
        setPersonId(personId);
        setDescription(description);
        setType(type);
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ERRAND_NUMBER", nullable = false)
    public int getErrandNumber() {
        return errandNumber;
    }

    public void setErrandNumber(int errandNumber) {
        this.errandNumber = errandNumber;
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

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 3600)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 255)
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

    @Override
    public String toString() {
        return "ERRAND_NUMBER: " + errandNumber + " PERSON_ID: " + personId + "\n"
                + "DESCRIPTION: " +  description + " TYPE: " + type;
    }
}
