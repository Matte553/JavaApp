package api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Importing the user Model and Service
import api.model.Person;
import service.PersonService;

import java.util.Optional;

@RestController
public class PersonController {

    private PersonService personService;
    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/person")
    public Person getPerson(@RequestParam Integer id){
        Optional person = personService.getPerson(id);
        if(person.isPresent()){
            return (Person) person.get();
        }
        return personService.defaultAnswr();
    }
}
