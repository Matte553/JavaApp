import Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        Session session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();                                         // Starta någonting som behövs
/*
        PersonEntity p1 = new PersonEntity();
        p1.setFirstname("Anders");
        p1.setLastname("Andersson");
        p1.setMail("anders.andersson@gmail.com");
        p1.setPhone("070-0547602");
        p1.setCustomerNumber("0");
        session.persist(p1);

        PersonEntity p2 = new PersonEntity();
        p2.setFirstname("Göran");
        p2.setLastname("Person");
        p2.setMail("goren.persson@gmail.com");
        p2.setPhone("070-8206830");
        p2.setCustomerNumber("000001");
        session.persist(p2);

        ChatEntity chat = new ChatEntity();
        chat.setSubject("Reparation");
        session.persist(chat);

        ChatmemberEntity chat_member1 = new ChatmemberEntity();
        chat_member1.setChatId(chat.getId());
        chat_member1.setPersonId(p1.getId());
        session.persist(chat_member1);

        ChatmemberEntity chat_member2 = new ChatmemberEntity();
        chat_member2.setChatId(chat.getId());
        chat_member2.setPersonId(p2.getId());
        session.persist(chat_member2);

        MessageEntity mess = new MessageEntity();
        mess.setChatId(chat.getId());
        mess.setPersonId(p1.getId());
        mess.setText("Hallåjsan! Din gitarr är klar!");
        long now = System.currentTimeMillis();
        Timestamp sqlTimestamp = new Timestamp(now);
        mess.setMessageTimestamp(sqlTimestamp);
        session.persist(mess);*/

        Query query=session.createQuery("from PersonEntity ");
        List list=query.list();
        list.forEach(System.out::println);

        session.getTransaction().commit();
    }
}