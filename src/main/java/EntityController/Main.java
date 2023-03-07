package EntityController;

import Entities.*;
import java.util.ArrayList;

// Log Tabell i DB: LOG(kundid, text, pictures, datetime); GÖR ER TILL STARK ENTITET FÖR AUTOINCREMENT

//addMessage() now returns message
//addInstrument() now returns instrument
//addInstrumentPicture() now returns instrumentPicture
//addReservation() now returns reservation
//addReparation() now returs reparation
//addLog() now returns log

// Kalender Tabell i DB: KALENDER(id, starttid, sluttid, startdatum, slutdatum, errandNumber, ämne, fritext);


public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();

        //ReservationEntity r = ec.addReservation(101, 3);
        //System.out.println(r.getInstrumentId());


    }
}