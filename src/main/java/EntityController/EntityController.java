package EntityController;

import Entities.HibernateSetup;
import Entities.PersonEntity;
import example.api.model.Person;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Entities.*;

// This Class is used for Retrieving all data from database and also inserting data into database.
public class EntityController {

    SessionFactory sessionFactory;
    Session session;

    // Constructor that initiates a connection to DB.
    public EntityController() throws Exception {
        sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        session = sessionFactory.openSession();                     // Skapa en session för koppling.
        session.beginTransaction();
    }

    public void createChat(){

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
        session.persist(mess);
    }


    // Returns chatID for a chat between two persons;
    public Integer getChatID(int person_A_ID, int person_B_ID){

        String hql;
        Query query;
        List results;

        hql = "SELECT E.chatId FROM ChatmemberEntity E WHERE E.personId = :person_A_ID";
        query = session.createQuery(hql).setParameter("person_A_ID", person_A_ID);
        results = query.list();
        ArrayList<Integer> person_A_Chatlist = (ArrayList) results;

        hql = "SELECT E.chatId FROM ChatmemberEntity E WHERE E.personId = :person_B_ID";
        query = session.createQuery(hql).setParameter("person_B_ID", person_B_ID);
        results = query.list();
        ArrayList<Integer> person_B_Chatlist = (ArrayList) results;

        ArrayList<Integer> compareList = new ArrayList<Integer>(person_A_Chatlist);

        compareList.retainAll(person_B_Chatlist);

        if(compareList.isEmpty()){
            return -1;
        }
        else {
            Integer chatID = compareList.get(0);
            return chatID;
        }
    }

    // Returns arraylist with all messages from a chat ID
    public ArrayList<MessageEntity> getMessagesFromChatID(int person_A_ID, int person_B_ID){
        int chatID = getChatID(person_A_ID, person_B_ID);
        if(chatID == -1){
            System.err.println("There is no chat between these two persons");
            return null;
        }
        String hql = "SELECT E FROM MessageEntity E WHERE E.chatId = :chatID";
        Query query = session.createQuery(hql).setParameter("chatID", chatID);
        List<MessageEntity> list = query.list();
        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all Persons from database
    public ArrayList<PersonEntity> getPersons() throws Exception {
        Query query = session.createQuery(("from PersonEntity "));
        List<ChatEntity> list=query.list();
        list.forEach(System.out::println);

        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all Chats from database
    public ArrayList<ChatEntity> getChats() throws Exception {
        Query query = session.createQuery(("from ChatEntity "));
        List<ChatEntity> list=query.list();
        list.forEach(System.out::println);

        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all Messages from database
    public ArrayList<MessageEntity> getMessages() throws Exception {
        Query query = session.createQuery(("from MessageEntity "));
        List<MessageEntity> list=query.list();
        list.forEach(System.out::println);

        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all ChatMembers from database
    public ArrayList<ChatmemberEntity> getChatMembers() throws Exception {
        Query query = session.createQuery(("from ChatmemberEntity "));
        List<ChatEntity> list=query.list();
        list.forEach(System.out::println);

        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }
}
