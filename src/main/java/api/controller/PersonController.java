package api.controller;
//API
import api.model.PersonModel;
import api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) throws Exception {
        this.personService = personService;
    }
    //In Postman, GET localhost:8080/persons for a list for all people
    @GetMapping("/person/all")
    List<PersonModel> getAllPersons() throws Exception {
        return personService.getAllPersons();
    }

    @GetMapping("/person")
    PersonModel getOne(@RequestParam Integer id) throws Exception {
        return personService.getPerson(id);
    }

    @GetMapping("/person/admin")
    PersonModel getAdmin() throws Exception {
        return personService.getAdmin();
    }
    //In Postman, in body
    //{
    //    "fname": "name1",
    //    "lname": "lname2",
    //    "email": "testmail",
    //    "phone": "testphone",
    //    "customerNumber": "5215215"
    //}
    @PostMapping("person/add")
    private PersonModel addCustomer(@RequestBody PersonModel postPerson) throws Exception {
        return personService.addPerson(postPerson);
    }
}