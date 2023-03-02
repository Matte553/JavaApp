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
    //In Postman, POST localhost:8080/person/Sven/Svensson/sven@gmail.com/0707070
    @PostMapping("person/{fname}/{lname}/{mail}/{phone}")
    void postOne(@PathVariable String fname, @PathVariable String lname, @PathVariable String mail, @PathVariable String phone) throws Exception{
        personService.addPerson(fname, lname, mail, phone);
}}