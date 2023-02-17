import Entities.HibernateSetup;
import Entities.PersonEntity;
import example.api.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import Entities.*;

public class EntityController {

    // Returns an arraylist with all Persons from db
    public ArrayList<PersonEntity> getPersonsFromDB() throws Exception {
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();

        Query query = session.createQuery(("from PersonEntity "));
        List<PersonEntity> list=query.list();
        list.forEach(System.out::println);

        ArrayList personList = new ArrayList<>();

        for(PersonEntity p: list ) {
            Person person = new Person(p.getId(), p.getFirstname(), p.getLastname(), p.getMail());
            personList.add(person);
        };

        return personList;
    }

    // Returns an arraylist with all Chats from db
    public ArrayList<ChatEntity> getChatsFromDB() throws Exception {
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();

        Query query = session.createQuery(("from ChatEntity "));
        List<ChatEntity> list=query.list();
        list.forEach(System.out::println);

        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all Messages from db
    public ArrayList<MessageEntity> getMessagesFromDB() throws Exception {
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();

        Query query = session.createQuery(("from MessageEntity "));
        List<MessageEntity> list=query.list();
        list.forEach(System.out::println);

        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all ChatMembers from db
    public ArrayList<ChatmemberEntity> getChatMembersFromDB() throws Exception {
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();

        Query query = session.createQuery(("from ChatmemberEntity "));
        List<ChatEntity> list=query.list();
        list.forEach(System.out::println);

        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }
}
