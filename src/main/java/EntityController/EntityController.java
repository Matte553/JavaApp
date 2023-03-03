package EntityController;

import Entities.HibernateSetup;
import Entities.*;
import jakarta.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entities.*;

// This Class is used for Retrieving all data from database and also inserting data into database.
@Stateless
public class EntityController implements Serializable {
    SessionFactory sessionFactory;
    Session session;
    Integer AdminID = 1;

    // Constructor that initiates a connection to DB.
    public EntityController() throws Exception {
        sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        session = sessionFactory.openSession();             // Skapa en session för koppling.
    }

    // <!-- PRIVATE METHODS --!>

    // Creates a person and prepares it to be sent to database. Returns the personID
    private Integer createPerson(String firstname, String lastname, String phone, String mail) throws Exception {
        String customerNumber = generateCustomerNumber();
        PersonEntity person = new PersonEntity(firstname, lastname, phone, mail, customerNumber);
        session.persist(person);
        return person.getId();
    }

    // Creates a chat and prepares it to be sent to database. Returns the chatId
    private Integer createChat(String subject) {
        ChatEntity chat = new ChatEntity(subject);
        session.persist(chat);
        return chat.getId();
    }

    // Creates chat member and prepares it to be sent to the database
    private void createChatMember(Integer chatID, Integer personID) {
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

    // Creates new instrument picture and prepares it to be sent to the database
    private void createInstrumentPicture(String imageURL, Integer instrumentId) {
        InstrumentPicturesEntity instrumentPicture = new InstrumentPicturesEntity(imageURL, instrumentId);
        session.persist(instrumentPicture);
    }

    // Creates new reparation and prepares it to be sent to the database
    private void createReparation(Integer personId, String description, String type) {
        Integer errandNumber = generateErrandNumber();
        ReparationsEntity reparation = new ReparationsEntity(errandNumber, personId, description, type);
        session.persist(reparation);
    }

    // Creates new reservation and prepares it to be sent to the database
    private void createReservation(Integer instrumentId, Integer personId) {
        Integer reservationNumber = generateReservationNumber();
        ReservationEntity reservation = new ReservationEntity(reservationNumber, instrumentId, personId);
        session.persist(reservation);
    }



    // <!-- PUBLIC METHODS --!>

    // Adds Customer to database and initiates a chat with Admin, Returns the customer;
    public PersonEntity addCustomer(PersonEntity person, String subject) throws Exception {
        session.beginTransaction();
        Integer personID = createPerson(person.getFirstname(), person.getLastname(), person.getPhone(), person.getMail());
        Integer chatID = createChat(subject);
        createChatMember(chatID, personID);
        createChatMember(chatID, AdminID);
        session.getTransaction().commit();
        return person;
    }

    // Adds new messages to database
    // Commits the entry
    public void addMessage(Integer fromID, Integer toID, String text, String imageURL) {

        int chatID;

        if(fromID == AdminID){
            chatID = getChat(toID);
        }
        else {
            chatID = getChat(fromID);
        }

        session.beginTransaction();
        createMessage(fromID, chatID, text, imageURL);
        session.getTransaction().commit();
    }

    // Adds new instruments to the database
    // Commits the entry
    public void addInstrument(String type, String name, Double price, String description) {
        session.beginTransaction();
        createInstrument(type, name, price, description);
        session.getTransaction().commit();
    }

    // Adds new instrument images to the database
    // Commits the entry
    public void addInstrumentPicture(String imageURL, Integer instrumentId) {
        session.beginTransaction();
        createInstrumentPicture(imageURL, instrumentId);
        session.getTransaction().commit();
    }

    // Adds new reparation
    // Commits the entry
    public void addReparation(Integer personId, String description, String type) {
        session.beginTransaction();
        createReparation(personId, description, type);
        session.getTransaction().commit();
    }

    // Adds new reservations
    // Commits the entry
    public void addReservation(Integer instrumentId, Integer personId) {
        session.beginTransaction();
        createReservation(instrumentId, personId);
        session.getTransaction().commit();
    }

    // Returns the customer with the exact customer number.
    public PersonEntity getCustomer(String customerNumber){
        String hql = "SELECT E FROM PersonEntity E WHERE E.customerNumber = :customerNumber";
        Query query = session.createQuery(hql).setParameter("customerNumber", customerNumber);
        List results = query.list();
        if(results.isEmpty()){
            System.err.println("No person with customernumber: " + customerNumber + " exists.");
            return null;
        }
        PersonEntity p = (PersonEntity) results.get(0);
        return p;
    }

    // Fetches all imageURLs for one instrument
    public ArrayList<String> getImagesFromInstrumentId(Integer instrumentId) {
        String hql = "SELECT E.imageUrl FROM InstrumentPicturesEntity E WHERE E.instrumentId = :instrumentId";
        Query query = session.createQuery(hql).setParameter("instrumentId", instrumentId);
        List results = query.list();
        return (ArrayList<String>) results;
    }

    // Fetches a customer's ID based on their customer number
    public Integer getIdFromCustomerNumber(String customerNumber) {
        String hql = "SELECT E.id FROM PersonEntity E WHERE E.customerNumber = :customerNumber";
        Query query = session.createQuery(hql).setParameter("customerNumber", customerNumber);
        List results = query.list();
        Integer personID = (Integer) results.get(0);
        return personID;
    }

    // Fetches all reservations one person has made (Maybe use customerNumber instead on ID?)
    public ArrayList<ReservationEntity> getReservationsFromPersonId(Integer personId) {
        String hql = "SELECT E FROM ReservationEntity E WHERE E.personId = :personId";
        Query query = session.createQuery(hql).setParameter("personId", personId);
        List results = query.list();
        return (ArrayList<ReservationEntity>) results;
    }

    // Fetches all reparations one person has active (Maybe use customerNumber instead on ID?)
    public ArrayList<ReparationsEntity> getReparationsFromPersonId(Integer personId) {
        String hql = "SELECT E FROM ReparationsEntity E WHERE E.personId = :personId";
        Query query = session.createQuery(hql).setParameter("personId", personId);
        List results = query.list();
        return (ArrayList<ReparationsEntity>) results;
    }

    // Fetches Reservation object based on reservation number
    public ReservationEntity getReservationFromReservNumber(Integer reservationNumber) {
        String hql = "SELECT E FROM ReservationEntity E WHERE E.reservationNumber = :reservationNumber";
        Query query = session.createQuery(hql).setParameter("reservationNumber", reservationNumber);
        ReservationEntity result = (ReservationEntity) query.getSingleResult();
        return result;
    }

    // Fetches Reparation object based on errand number
    public ReparationsEntity getReparationFromErrandNumber(Integer errandNumber) {
        String hql = "SELECT E FROM ReparationsEntity E WHERE E.errandNumber = :errandNumber";
        Query query = session.createQuery(hql).setParameter("errandNumber", errandNumber);
        ReparationsEntity result = (ReparationsEntity) query.getSingleResult();
        return result;
    }

    // Returns the chatID for a chat that has the person as a member;
    private int getChat(int personID){
        String hql = "SELECT c.chatId FROM ChatmemberEntity c WHERE c.personId = :personID";
        Query query = session.createQuery(hql).setParameter("personID", personID);
        Integer chatID = (Integer) query.getSingleResult();
        if(chatID == null){
            System.err.println("There is no chat for this person.");
            return -1;
        }
        else{
            return chatID;
        }
    }

    // Returns all chats ID that belong to a given subject
    private Integer getChatWithSubject(int personID, String subject){
        // SELECT * FROM Chatmember INNER JOIN CHAT C ON CHATMEMBER.CHAT_ID=C.ID WHERE PERSON_ID=2 AND SUBJECT='Reservation';
        String hql = "SELECT member.chatId FROM ChatmemberEntity member JOIN ChatEntity chat ON member.chatId=chat.id WHERE member.personId = :personID AND chat.subject = :subject";
        Query query = session.createQuery(hql).setParameter("personID", personID).setParameter("subject",subject);
        List result = query.list();
        if(result.isEmpty()){
            System.err.println("No chat exists for personID " + personID + " and subject " + subject);
            return null;
        }
        return (Integer) result.get(0);
    }

    // Return Person with the given person ID.
    private PersonEntity getPersonWithID(int personID){
        String hql = "FROM PersonEntity p WHERE p.id = :personID";
        Query query = session.createQuery(hql).setParameter("personID", personID);
        List<PersonEntity> result = query.list();
        if(result.isEmpty()){
            System.err.println("No person was found with id: " + personID);
            return null;
        }
        return result.get(0);
    }

    // Return an ArrayList with Persons that have a chat with the given subject name.
    public ArrayList<PersonEntity> getCustomersWithSubject(String subject){
        String hql = "SELECT member.personId FROM ChatmemberEntity member JOIN ChatEntity chat ON member.chatId=chat.id WHERE chat.subject = :subject AND member.personId!=1";
        Query query = session.createQuery(hql).setParameter("subject", subject).setParameter("subject",subject);
        List<Integer> result = query.list();

        ArrayList<PersonEntity> personList = new ArrayList<PersonEntity>();

        for (Integer i: result) {
            System.out.println("personID: " + i);
            PersonEntity p = getPersonWithID(i);
            personList.add(p);
        }
        return personList;
    }

    // Returns arraylist with all messages from the chat containing the given personID. This personID should be the customer.
    public ArrayList<MessageEntity> getMessages(int personID){

        Integer chatID = getChat(personID);
        if(chatID == -1){
            System.err.println("There is no chat between these two persons");
            return null;
        }
        String hql = "SELECT E FROM MessageEntity E WHERE E.chatId = :chatID";
        Query query = session.createQuery(hql).setParameter("chatID", chatID);
        List list = query.list();
        return (ArrayList) list;
    }

    // Returns arraylist with all messages from the chat containing the given personID. This personID should be the customer.
    public ArrayList<MessageEntity> getMessagesWithSubject(int personID, String subject){

        Integer chatID = getChatWithSubject(personID, subject);
        if(chatID == null){
            System.err.println("There is no chat between these two persons");
            return null;
        }
        String hql = "SELECT E FROM MessageEntity E WHERE E.chatId = :chatID";
        Query query = session.createQuery(hql).setParameter("chatID", chatID);
        List<MessageEntity> list = query.list();

        if(list.isEmpty()){
            System.out.println("Chat with id " + chatID + " was not found");
            return null;
        }
        return (ArrayList) list;
    }



    // Returns an arraylist with all Persons from database
    public ArrayList<PersonEntity> getPersons() throws Exception {
        Query query = session.createQuery(("from PersonEntity "));
        List list=query.list();
        return (ArrayList) list;
    }

    // Returns an arraylist with all Chats from database
    public ArrayList<ChatEntity> getChats() throws Exception {
        Query query = session.createQuery(("from ChatEntity "));
        List list=query.list();
        return (ArrayList) list;
    }

    // Returns an arraylist with all Messages from database
    public ArrayList<MessageEntity> getMessages() throws Exception {
        Query query = session.createQuery(("from MessageEntity "));
        List list=query.list();
        return (ArrayList) list;
    }

    // Returns an arraylist with all ChatMembers from database
    public ArrayList<ChatmemberEntity> getChatMembers() throws Exception {
        Query query = session.createQuery(("from ChatmemberEntity "));
        List list=query.list();
        return (ArrayList) list;
    }

    // Returns an arraylist with all Reparations from database
    public ArrayList<ReparationsEntity> getReparations() {
        Query query = session.createQuery(("from ReparationsEntity "));
        List list = query.list();
        return (ArrayList<ReparationsEntity>) list;
    }

    // Returns an arraylist with all Reservations from database
    public ArrayList<ReservationEntity> getReservations() {
        Query query = session.createQuery(("from ReservationEntity"));
        List list = query.list();
        return (ArrayList<ReservationEntity>) list;
    }

    // Returns an arraylist with all Instruments from database
    public ArrayList<InstrumentEntity> getInstruments() {
        Query query = session.createQuery(("from InstrumentEntity"));
        List list = query.list();
        return (ArrayList<InstrumentEntity>) list;
    }

    // Returns an arraylist with all Instrument Pictures from database
    public ArrayList<InstrumentPicturesEntity> getInstrumentPictures() {
        Query query = session.createQuery(("from InstrumentPicturesEntity"));
        List list = query.list();
        return (ArrayList<InstrumentPicturesEntity>) list;
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
        do {
            number = rand.nextInt(999999);
            numberString = String.format("%06d", number);
        } while (customerNumbers.contains(numberString));
        return numberString;
    }

    // Generates reservation number for reservation
    // Controls there are no duplicates
    private Integer generateReservationNumber() {
        ArrayList<ReservationEntity> reservations = this.getReservations();
        ArrayList<Integer> reservationNumbers = new ArrayList<>();
        for(ReservationEntity r: reservations) {
            reservationNumbers.add(r.getReservationNumber());
        }
        Random rand = new Random();
        Integer number;
        do {
            number = rand.nextInt((9999 - 1000) + 1000);
        } while (reservationNumbers.contains(number));
        return number;
    }

    // Generates errand number for reparations
    // Controls there are no duplicates
    private Integer generateErrandNumber() {
        ArrayList<ReparationsEntity> reparations = this.getReparations();
        ArrayList<Integer> errandNumbers = new ArrayList<>();
        for(ReparationsEntity r: reparations) {
            errandNumbers.add(r.getErrandNumber());
        }
        Random rand = new Random();
        Integer number;
        do {
            number = rand.nextInt((9999 - 1000) + 1000);
        } while (errandNumbers.contains(number));
        return number;
    }

    // Returns true if given customerNumber is the Admin
    public Boolean isAuthorized(String customerNumber){
        PersonEntity person = getCustomer(customerNumber);
        if(person == null){
            return false;
        }
        return true;
    }


    // Returns the admin as a PersonEntity object. This object can be used to retrieve the admins details.
    public PersonEntity getAdmin(){
        int adminID = AdminID;
        String hql = "SELECT P FROM PersonEntity P WHERE P.id = :adminID";
        Query query = session.createQuery(hql).setParameter("adminID", adminID);
        List<MessageEntity> list = query.list();
        if(list.isEmpty()){
            System.err.println("Admin could not be found, is admins ID not 0?");
            return null;
        }else {
            return (PersonEntity) query.getSingleResult();
        }
    }


    //<!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!>
    // Anton och Antons favoriter, Matte rör ej!!!
    // Returns chatID for a chat between two persons;
    public Integer getChatIDold(int person_A_ID, int person_B_ID){

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
        int chatID = getChatIDold(person_A_ID, person_B_ID);
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
    //<!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!>
}