//API
package api;

import Entities.HibernateSetup;
import Entities.PersonEntity;
import EntityController.EntityController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
public class main extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(main.class);
    }
    public static void main(String[] args) throws Exception {

        SpringApplication.run(main.class, args);
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();

        //Quick access-tests to database by looking for persons
        //Query query = session.createQuery(("from PersonEntity "));List<PersonEntity> list=query.list();list.forEach(System.out::println);
        //for(PersonEntity person: list ) {person.getId();}
    }

    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            System.out.println("Hello world!");
        };
    }
}