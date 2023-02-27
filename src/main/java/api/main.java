package api;

import Entities.HibernateSetup;
import Entities.MessageEntity;
import Entities.PersonEntity;
import EntityController.EntityController;
import api.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class main {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(main.class, args);
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();

        Query query = session.createQuery(("from PersonEntity "));
        List<PersonEntity> list=query.list();
        list.forEach(System.out::println);

        for(PersonEntity person: list ) {
            person.getId();//Loadsa stuff
        };
        EntityController ec = new EntityController();
        //ArrayList<MessageEntity> testList = ec.getMessagesFromPersonID(1,2);
        //for(MessageEntity message: testList ) {
          //  System.out.println(message); //Loadsa stuff
        //};
    }


    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            System.out.println("Hello world!");
        };
    }
}