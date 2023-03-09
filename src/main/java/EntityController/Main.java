package EntityController;

import Entities.*;
import java.util.ArrayList;
////
// Kalender Tabell i DB: KALENDER(id, starttid, sluttid, startdatum, slutdatum, errandNumber, ämne, fritext);;


public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();
        PersonEntity p = ec.getAdmin();
        System.out.println(p.getFirstname());

    }
}