package EntityController;

import Entities.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        // TESTING
        EntityController ec = new EntityController();
        ArrayList<MessageEntity> mess = ec.getMessagesWithSubject(3, "Reparation");
        for (MessageEntity m: mess){
            System.out.println(m.getText());
        }

    }
}