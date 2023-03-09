package EntityController;

import Entities.*;
import java.util.ArrayList;

// Log Tabell i DB: LOG(kundid, text, pictures, datetime);
// Metod för addLog.


// Kalender Tabell i DB: KALENDER(id, starttid, sluttid, startdatum, slutdatum, errandNumber, ämne, fritext);

public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();
        ArrayList<LogEntity> le = ec.getLogWithID(3);

        for(LogEntity e: le){
            System.out.println(e.getText());
        }
    }
}