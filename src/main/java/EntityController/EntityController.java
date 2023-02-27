package EntityController;

import Entities.HibernateSetup;
import Entities.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entities.*;

// This Class is used for Retrieving all data from database and also inserting data into database.
public class EntityController {

    SessionFactory sessionFactory;
    Session session;

    // Constructor that initiates a connection to DB.
    public EntityController() throws Exception {
        sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        session = sessionFactory.openSession();                     // Skapa en session för koppling.
    }

    // Creates a person and prepare it to be sent to database. Returns the personID
    private Integer createPerson(String firstname, String lastname, String phone, String mail) throws Exception {
        String customerNumber = generateCustomerNumber();
        PersonEntity person = new PersonEntity(firstname, lastname, phone, mail, customerNumber);
        session.persist(person);
        return person.getId();
    }

    // Generates a random customer number with 6 digits.
    // Controls that there are no duplicates
    private String generateCustomerNumber() throws Exception {
        ArrayList<PersonEntity> persons = this.getPersons();
        ArrayList<String> customerNumbers = new ArrayList<>();
        for(PersonEntity p: persons) {
            customerNumbers.add(p.getCustomerNumber());
        }
        Random rand = new Random();
        Integer number;
        String numberString = null;
        while(true) {
            number = rand.nextInt(999999);
            numberString = String.format("%06d", number);
            if(!customerNumbers.contains(numberString)) {
                break;
            }
        }
        return numberString;
    }

    // Creates a chat and prepares it to be sent to database
    private Integer createChat(String subject) {
        session.beginTransaction();
        ChatEntity chat = new ChatEntity(subject);
        session.persist(chat);
        return chat.getId();
    }

    // Creates chat member and prepares it to be sent to the database
    private void createChatMember(Integer chatID, Integer personID) {
        session.beginTransaction();
        ChatmemberEntity chatMember = new ChatmemberEntity(chatID, personID);
        session.persist(chatMember);
    }

    // Creates chat message and prepares it to be sent to the database
    // Adds current time and date to timestamp, in format YYYY-MM-DD HH:MM:SS,MS
    private void createMessage(Integer personID, Integer chatID, String text, String imageURL) {
        long now = System.currentTimeMillis();
        Timestamp sqlTimestamp = new Timestamp(now);
        MessageEntity message = new MessageEntity(personID, chatID, text, sqlTimestamp, imageURL);
        session.persist(message);
    }

    // Creates new instrument and prepares it to be sent to the database
    private void createInstrument(String type, String name, Double price, String description) {
        InstrumentEntity instrument = new InstrumentEntity(type, name, price, description);
        session.persist(instrument);
    }

    // Creates new instrument pictures and prepares it to be sent to the database
    private void createInstrumentPicture(String imageURL, Integer instrumentId) {
        InstrumentPicturesEntity instrumentPicture = new InstrumentPicturesEntity(imageURL, instrumentId);
        session.persist(instrumentPicture);
    }

    // Creates new reparation and prepares it to be sent to the database
    private void createReparation(Integer personId, String description, String type) {
        ReparationsEntity reparation = new ReparationsEntity(personId, description, type);
        session.persist(reparation);
    }

    // Creates new reservation and prepares it to be sent to the database
    private void createReservation(Integer instrumentId, Integer personId) {
        session.beginTransaction();
        ReservationEntity reservation = new ReservationEntity(instrumentId, personId);
        session.persist(reservation);
    }

    // Public method to add new messages to database
    // Actually commits the entry
    public void addMessage(Integer personID, Integer chatID, String text, String imageURL) {
        session.beginTransaction();
        createMessage(personID, chatID, text, imageURL);
        session.getTransaction().commit();
    }

    // Public method to add new instruments to the database
    // Commits the entry
    public void addInstrument(String type, String name, Double price, String description) {
        session.beginTransaction();
        createInstrument(type, name, price, description);
        session.getTransaction().commit();
    }

    // Public method to add new reparations
    // Commits the entry
    public void addReparation(Integer personId, String description, String type) {
        session.beginTransaction();
        createReparation(personId, description, type);
        session.getTransaction().commit();
    }

    public void addReservation(Integer instrumentId, Integer personId) {
        session.beginTransaction();
        createReservation(instrumentId, personId);
        session.getTransaction().commit();
    }

    // Public method to start conversation. Is to be used when a new customer is added on the frontend.
    // A new chat is created with Anders, and a welcome message is sent from Anders to the new customer to
    // initiate contact. Everything is then committed to the database. A chat is always created for new customers.
    public void initiateContact(Integer employeeID, String firstname, String lastname, String phone, String mail, String subject) throws Exception {
        session.beginTransaction();
        Integer personID = createPerson(firstname, lastname, phone, mail);
        Integer chatID = createChat(subject);
        createChatMember(chatID, personID);
        createChatMember(chatID, employeeID);
        createMessage(employeeID, chatID, "Hej och välkommen! Här kan du skriva med mig, whoho", null);
        session.getTransaction().commit();
    }

    // Public method to fetch a customer's ID based on their customer number
    public Integer getIdFromCustomerNumber(String customerNumber) {
        String hql = "SELECT E.id FROM PersonEntity E WHERE E.customerNumber = :customerNumber";
        Query query = session.createQuery(hql).setParameter("customerNumber", customerNumber);
        List results = query.list();
        Integer personID = (Integer) results.get(0);
        return personID;
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
    public ArrayList<MessageEntity> getMessagesFromPersonID(int person_A_ID, int person_B_ID){
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
        List<PersonEntity> list=query.list();
        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all Chats from database
    public ArrayList<ChatEntity> getChats() throws Exception {
        Query query = session.createQuery(("from ChatEntity "));
        List<ChatEntity> list=query.list();
        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all Messages from database
    public ArrayList<MessageEntity> getMessages() throws Exception {
        Query query = session.createQuery(("from MessageEntity "));
        List<MessageEntity> list=query.list();
        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }

    // Returns an arraylist with all ChatMembers from database
    public ArrayList<ChatmemberEntity> getChatMembers() throws Exception {
        Query query = session.createQuery(("from ChatmemberEntity "));
        List<ChatmemberEntity> list=query.list();
        ArrayList arrayList = (ArrayList) list;
        return arrayList;
    }
}