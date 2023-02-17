package example.service;

import Entities.*;
import example.api.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonService {
    private ArrayList<Person> personList;

    public PersonService() throws Exception {
        /*
        personList = new ArrayList<>();
        Person person1 = new Person(1, "Anton", "Holm", "anton1@gmail.com");
        Person person2 = new Person(2, "Anton", "Nor", "anton2@gmail.com");
        Person person3 = new Person(3, "Anton", "Eng", "anton3@gmail.com");
        //Person person4 = new Person(x,x,x,x)

        personList.addAll(Arrays.asList(person1, person2, person3));
        */
        this.retrievePersonsFromDB();

    }

    public void retrievePersonsFromDB() throws Exception {
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();

        Query query = session.createQuery(("from PersonEntity "));
        List<PersonEntity> list=query.list();
        list.forEach(System.out::println);

        personList = new ArrayList<>();

        for(PersonEntity p: list ) {
            Person person = new Person(p.getId(), p.getFirstname(), p.getLastname(), p.getMail());
            personList.add(person);
        };
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
