package service;

import api.model.Person;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonService {
    private List<Person> personList;

    public PersonService(){
        personList = new ArrayList<>();
        Person person1 = new Person(1, "Anton", "Holm", "anton1@gmail.com");
        Person person2 = new Person(2, "Anton", "Nor", "anton2@gmail.com");
        Person person3 = new Person(3, "Anton", "Eng", "anton3@gmail.com");

        personList.addAll(Arrays.asList(person1, person2, person3));
    }
    public Optional<Person> getPerson(Integer id){
        Optional optional = Optional.empty();
        for (Person person: personList){
            if(id==person.getId()){
                optional = Optional.of(person);
                return optional;
            }

        }
        return optional;
    }

    public Person defaultAnswr() {
        return new Person(5, "error", "error mer", "bad@error.com");
    }
}
