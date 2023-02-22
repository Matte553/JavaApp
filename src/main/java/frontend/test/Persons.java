package frontend.test;
import Entities.PersonEntity;
import EntityController.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@RequestScoped
public class Persons implements Serializable {
    EntityController ec;
    ArrayList<Person> persons;

    public Persons() throws Exception {
        /*ec      = new EntityController();
        persons = createArrayList();*/
    }


   /* private ArrayList<Person> createArrayList() throws Exception {
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
    }*/
}
