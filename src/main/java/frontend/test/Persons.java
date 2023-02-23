package frontend.test;

import Entities.PersonEntity;
import EntityController.*;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;

@Named
public class Persons implements Serializable {
    private final EntityController ec;
    private ArrayList<Person> persons;

    public Persons() throws Exception {
        ec      = new EntityController();
        persons = createArrayList();
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    private ArrayList<Person> createArrayList() throws Exception {
        ArrayList<PersonEntity> privateList = ec.getPersons();
        ArrayList<Person> result = new ArrayList<>();
        for (PersonEntity pe : privateList) {
            int id          = pe.getId();
            String fname    = pe.getFirstname();
            String lname    = pe.getLastname();
            String phone    = pe.getPhone();
            String email    = pe.getMail();
            Person temp     = new Person(id, fname, lname, phone, email);
            result.add(temp);

        }
        return result;
    }
}
