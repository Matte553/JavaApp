package example;

import Entities.HibernateSetup;
import Entities.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class main {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(main.class, args);
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();

        Query query = session.createQuery(("from PersonEntity "));
        List list=query.list();
        list.forEach(System.out::println);


    }


    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            System.out.println("Hello world!");
        };
    }
}