package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSON", schema = "APP")
public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "FIRSTNAME", nullable = true, length = 255)
    private String firstname;
    @Basic
    @Column(name = "LASTNAME", nullable = true, length = 255)
    private String lastname;
    @Basic
    @Column(name = "MAIL", nullable = true, length = 255)
    private String mail;
    @Basic
    @Column(name = "PHONE", nullable = true, length = 30)
    private String phone;
    @Basic
    @Column(name = "CUSTOMER_NUMBER", nullable = true, length = 6)
    private String customerNumber;

    public PersonEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (customerNumber != null ? !customerNumber.equals(that.customerNumber) : that.customerNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (customerNumber != null ? customerNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ID: " + id + " FIRSTNAME: " + firstname + " LASTNAME: " + lastname + "\n"
                + " PHONE: " + phone + " MAIL: " + mail + " CUSTOMER_NUMBER: " + customerNumber;
    }
}
