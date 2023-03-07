package api.model;

public class ReparationModel {

    private int errandNumber;
    private int personId;
    private String description;
    private String type;

    public ReparationModel() {
    }

    public ReparationModel(Integer errandNumber, Integer personId, String description, String type) {
        setErrandNumber(errandNumber);
        setPersonId(personId);
        setDescription(description);
        setType(type);
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

        ReparationModel that = (ReparationModel) o;

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
