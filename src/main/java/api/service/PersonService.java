package api.service;

import api.model.Person;
import Entities.PersonEntity;
import EntityController.EntityController;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class PersonService {
    EntityController ec = new EntityController();

    public PersonService() throws Exception {
    }

    public ArrayList<Person> getPersons() throws Exception {
        ArrayList<PersonEntity> dblist = ec.getPersons();
        //
        ArrayList<Person> personList = new ArrayList<>();
        for (PersonEntity p : dblist) {
            personList.add(new Person(p.getId(), p.getFirstname(), p.getLastname(), p.getMail(), p.getPhone(), p.getCustomerNumber()));
        }
        return personList;
    }

    public Person getPerson(Integer id) throws Exception{
        ArrayList<Person> personList = this.getPersons();
        for (Person p : personList) {
            if (p.getId() == id) {
                System.out.println(id);
                return p;
            }
        }
        //Throw something yo, as long as it isnt this
        return new Person(-1, "This", "person","did","not","exist");
    }

    public void addPerson(String fname, String lname, String mail, String phone) throws Exception {
        ec.addCustomer(fname, lname, phone, mail, "VarförBehövsSubject?");
    }
}
