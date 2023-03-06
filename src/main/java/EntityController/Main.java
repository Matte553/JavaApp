package EntityController;

import Entities.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();
        //ec.addMessage(2, 1, "Hej på dig Anders", "URL");
        InstrumentEntity e = ec.getInstrumentWithID(1);
        System.out.println(e.getName());


    }
}