package frontend.calendar;

import Entities.PersonEntity;
import EntityController.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class Persons implements Serializable {
    @Inject
    private EntityController entityController;
    public ArrayList<Person> getCustomers() throws Exception {
        ArrayList<PersonEntity> privateList = entityController.getPersons();
        ArrayList<Person> result = new ArrayList<>();
        for (PersonEntity pe : privateList) {
            Person temp = new Person(pe);
            result.add(temp);
        }
        return result;
    }

}
