package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Person", schema = "APP", catalog = "")
public class PersonEntity {
    @Basic
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic
    @Column(name = "PHONE")
    private String phone;
    @Basic
    @Column(name = "MAIL")
    private String mail;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
        PersonEntity that = (PersonEntity) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(phone, that.phone) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, phone, mail, id);
    }
}
