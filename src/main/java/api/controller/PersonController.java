package api.controller;

import api.model.Person;
import api.service.PersonService;
import jakarta.ws.rs.Path;
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
    @GetMapping("/persons")
    List<Person> all() throws Exception {
        return personService.getPersons();
    }

    //In Postman, GET localhost:8080/person/1 to get person 1 (Anders)
    @GetMapping("/person")
    Person getOne(@RequestParam Integer id) throws Exception {
        return personService.getPerson(id);
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
    private Person addCustomer(@RequestBody Person postPerson) throws Exception {
        return personService.addPerson(postPerson);
    }
}