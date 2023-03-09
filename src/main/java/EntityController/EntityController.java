package EntityController;

import Entities.HibernateSetup;
import Entities.PersonEntity;
import jakarta.persistence.NoResultException;
import org.apache.commons.logging.Log;
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
    Integer AdminID = 1;

    // Constructor that initiates a connection to DB.
    public EntityController() throws Exception {
        sessionFactory = HibernateSetup.getSessionFactory(); // Initiera en koppling för databasen.
        session = sessionFactory.openSession();             // Skapa en session för koppling.
    }





    // <!-- PRIVATE METHODS. Used to retrieve or generate data needed for public functions /////////////////////////--!>

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










    // <!-- PUBLIC ADD METHODS, For inserting data into database ///////////////////////////////////////////////// --!>

    // Adds Customer to database and initiates a chat with Admin, Returns the customer //;
    public PersonEntity addCustomer(PersonEntity person, String subject) throws Exception {

        session.beginTransaction();

        String customerNumber = generateCustomerNumber();
        PersonEntity newPerson = new PersonEntity(person.getFirstname(), person.getLastname(), person.getPhone(), person.getMail(), customerNumber);
        session.persist(person);

        ChatEntity chat = new ChatEntity(subject);
        session.persist(chat);

        ChatmemberEntity chatMember = new ChatmemberEntity(chat.getId(), person.getId());
        session.persist(chatMember);

        ChatmemberEntity chatMemberAdmin = new ChatmemberEntity(chat.getId(), AdminID);
        session.persist(chatMemberAdmin);

        session.getTransaction().commit();
        return person;
    }


    // Adds new messages to database
    // Commits the entry
    public MessageEntity addMessage(Integer fromID, Integer toID, String text, String imageURL) {

        int chatID;

        if(fromID == AdminID){
            chatID = getChat(toID);
        }
        else {
            chatID = getChat(fromID);
        }

        session.beginTransaction();

        long now = System.currentTimeMillis();
        Timestamp sqlTimestamp = new Timestamp(now);
        MessageEntity message = new MessageEntity(fromID, chatID, text, sqlTimestamp, imageURL);
        session.persist(message);

        session.getTransaction().commit();
        return message;
    }

    // Adds new instruments to the database
    // Commits the entry
    public InstrumentEntity addInstrument(String type, String name, Double price, String description) {
        session.beginTransaction();
        InstrumentEntity instrument = new InstrumentEntity(type, name, price, description);
        session.persist(instrument);
        session.getTransaction().commit();
        return instrument;
    }

    // Adds new instrument images to the database
    // Commits the entry
    public InstrumentPicturesEntity addInstrumentPicture(String imageURL, Integer instrumentId) {
        session.beginTransaction();
        InstrumentPicturesEntity instrumentPicture = new InstrumentPicturesEntity(imageURL, instrumentId);
        session.persist(instrumentPicture);
        session.getTransaction().commit();
        return instrumentPicture;
    }

    // Adds new reparation
    // Commits the entry
    public ReparationsEntity addReparation(Integer personId, String description, String type) {
        session.beginTransaction();
        Integer errandNumber = generateErrandNumber();
        ReparationsEntity reparation = new ReparationsEntity(errandNumber, personId, description, type);
        session.persist(reparation);
        session.getTransaction().commit();
        return reparation;
    }

    // Adds new reservations
    // Commits the entry
    public ReservationEntity addReservation(Integer instrumentId, Integer personId) {
        session.beginTransaction();
        Integer reservationNumber = generateReservationNumber();
        ReservationEntity reservation = new ReservationEntity(reservationNumber, instrumentId, personId);
        session.persist(reservation);
        session.getTransaction().commit();
        return reservation;
    }

    public LogEntity addLog(int personID, String text){
        session.beginTransaction();
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        LogEntity logEntity = new LogEntity(personID, text, timestamp);
        session.persist(logEntity);
        session.getTransaction().commit();
        return logEntity;
    }






    // <!-- PUBLIC GET METHODS, For retrieving data from database ///////////////////////////////////////////////// --!>

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

    // Returns a InstrumentEntity object from given instrument ID.
    public InstrumentEntity getInstrumentWithID(int instrumentID){
        String hql = "FROM InstrumentEntity i WHERE i.id = :instrumentID";
        Query query = session.createQuery(hql).setParameter("instrumentID", instrumentID);

        // Throws error if no instrument was found with this ID
        InstrumentEntity instrument = new InstrumentEntity();
        try {
             instrument = (InstrumentEntity) query.getSingleResult();
        }catch (NoResultException e){
            System.err.println("There is no instrument with this ID: " + instrumentID);
        };
        return instrument;
    }

    // Returns an arraylist with all Instrument Pictures from database
    public ArrayList<InstrumentPicturesEntity> getInstrumentPictures() {
        Query query = session.createQuery(("from InstrumentPicturesEntity"));
        List list = query.list();
        return (ArrayList<InstrumentPicturesEntity>) list;
    }

    public ArrayList<LogEntity> getLogWithID(int personID){
        String hql = "FROM LogEntity WHERE personId= :personID";
        Query query = session.createQuery(hql).setParameter("personID", personID);
        ArrayList<LogEntity> result = (ArrayList<LogEntity>) query.getResultList();
        if(result.isEmpty()){
            System.err.println("There is no log for person ID: " + personID);
            return null;
        }
        return result;
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

}