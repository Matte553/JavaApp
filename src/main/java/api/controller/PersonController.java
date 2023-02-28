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

    @GetMapping("/persons")
    List<Person> all() throws Exception {
        return personService.getPersons();
    }

    @GetMapping("/person")
    Person getOne(@RequestParam Integer id) throws Exception {
        return personService.getPerson(id);
    }
    //@PostMapping("person")
    //Person postOne(RequestParam)

}
