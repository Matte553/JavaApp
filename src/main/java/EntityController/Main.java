package EntityController;

import Entities.*;
import java.util.ArrayList;
// Kalender Tabell i DB: KALENDER(id, starttid, sluttid, startdatum, slutdatum, errandNumber, ämne, fritext);;


public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();

        PersonEntity p = new PersonEntity("Torkel", "Token", "333", "TorkelToken@gmail.com", "14");
        ec.addCustomer(p, "Övrigt");

        PersonEntity person = ec.getPersonWithID(6);
        ec.addChat(person, "Reservation");

        //PersonEntity person = ec.getPersonWithID(3);
        //ec.addChat(person, "Reservation");

        //int i = ec.getChatWithSubject(3, "Övrigt");
        //System.out.println(i);
        //ec.addMessage(1,3, "Reparation","Hej", null);

        /*
        ArrayList<MessageEntity> e = ec.getMessagesWithSubject(3, "Reparation");
        for (MessageEntity mess: e){
            System.out.println(mess.getText());
        }.
        */




    }
}