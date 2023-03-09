package EntityController;

import Entities.*;
import java.util.ArrayList;
// Kalender Tabell i DB: KALENDER(id, starttid, sluttid, startdatum, slutdatum, errandNumber, ämne, fritext);;


public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();

/*
        PersonEntity p = new PersonEntity("Torkel", "Token", "333", "TorkelToken@gmail.com", "14");
        ec.addCustomer(p, "Övrigt");


 */
        //PersonEntity person = ec.getPersonWithID(4);
        //ec.addChat(person, "Reservation");
/*
        ArrayList<MessageEntity> m = ec.getMessages(4, "Reservation");
        for (MessageEntity mess: m){
            System.out.println(mess.getText());
        }
*/

    }
}