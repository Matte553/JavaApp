// Kom ihåg att starta databasen
// cd C:\Users\Matti\IdeaProjects\DatabaseHibernate\H2\bin
// Kör bat fil

import Entities.HibernateSetup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();                                         // Starta någonting som behövs


        // List<PersonEntity> persons = session.createQuery("select p.firstname from PersonEntity p", PersonEntity.class).list(); // SQL sats mot DB
        // persons.forEach(System.out::println);   // Skriv ut alla object.
        Query query=session.createQuery("from PersonEntity ");
        List list=query.list();
        list.forEach(System.out::println);

        session.getTransaction().commit();      // Skickar commando & slutför koppling?
    }
}
