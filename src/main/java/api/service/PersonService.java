package api.service;

import api.model.PersonModel;
import Entities.PersonEntity;
import EntityController.EntityController;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    EntityController ec = new EntityController();

    public PersonService() throws Exception {
    }

    private PersonModel convertPersonEntity(PersonEntity value) {
        return new PersonModel(
                value.getId(),
                value.getFirstname(),
                value.getLastname(),
                value.getMail(),
                value.getPhone(),
                value.getCustomerNumber());
    }

    private List<PersonModel> convertListEntity(ArrayList<PersonEntity> dbList) {
        List<PersonModel> apiList = new ArrayList<>();

        for (PersonEntity entity : dbList) {
            apiList.add( this.convertPersonEntity(entity));
        }
        return apiList;
    }

    public List<PersonModel> getAllPersons() throws Exception {
        ArrayList<PersonEntity> dblist = ec.getPersons();
        return this.convertListEntity(dblist);
    }

    public PersonModel getPerson(Integer id) throws Exception{
        List<PersonModel> personModelList = this.getAllPersons();
        for (PersonModel p : personModelList) {
            if (p.getId() == id) {
                System.out.println(id);
                return p;
            }
        }
        //Throw something yo, as long as it isnt this
        return new PersonModel(-1, "This", "person","did","not","exist");
    }

    public PersonModel addPerson(PersonModel postPerson) throws Exception {
        PersonEntity p = new PersonEntity(postPerson.getFname(),postPerson.getLname(),postPerson.getPhone(), postPerson.getEmail(), postPerson.getCustomerNumber());
        ec.addCustomer(p, "Random Subject");
        return postPerson;
    }

    public PersonModel getAdmin() {
        PersonEntity p = ec.getAdmin();
        return this.convertPersonEntity(p);
    }
}
