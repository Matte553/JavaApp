package frontend.calendar;


import Entities.PersonEntity;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
public class Person implements Serializable {
    int id;
    String firstName;
    String lastName;
    String phoneNumber;
    String mail;

    public Person() {
        this.id         = 1;
        this.firstName  = "noname";
        this.lastName   = "noname";
        this.phoneNumber= "060-xxxxxx";
        this.mail       = "noname@nomail.no";
    }

    public Person(int id, String firstName, String lastName, String phoneNumber, String mail) {
        this.id         = id;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.phoneNumber= phoneNumber;
        this.mail       = mail;
    }

    public Person(PersonEntity pe) {
        this.id         = pe.getId();
        this.firstName  = pe.getFirstname();
        this.lastName   = pe.getLastname();
        this.phoneNumber= pe.getPhone();
        this.mail       = pe.getMail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "\t"+id + "@" + firstName + "_" + lastName + "\n\t//" + phoneNumber + "\n\t//" + mail;
    }
}
