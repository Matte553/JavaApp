package services;

import Entities.PersonEntity;
import jakarta.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class PersonService implements Serializable {
    // Test backend services
    public void addPerson(PersonEntity person){
        System.out.println(person.toString());
    }
    public Boolean isAuthorized(String customerNum){
        return true;
    }

    public PersonEntity getPerson(String customerNum){
        PersonEntity person= new PersonEntity();
        person.setCustomerNumber("123456");
        person.setFirstname("Test");
        return person;
    }

    public String generateCustomerNum(){
        return "123456";
    }
}
