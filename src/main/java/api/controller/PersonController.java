package api.controller;

import api.model.Person;
import api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Optional persOpt = personService.getPerson(id);
        if(persOpt.isPresent()){
            return (Person) persOpt.get();
        }
        return personService.defaultAnswr();
    }
}
