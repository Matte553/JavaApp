package EntityController;

import Entities.*;
import java.util.ArrayList;

/*
    Att implementera:
        1. Hämta om person finns eller inte med customer number     public void personExist(customerNumber);
        1. returnera person istället? getIdFromCustomerNumber();
        2. addMessage() ange customerID instället för personID
        2. Hämta en person från customer number                     public void getPerson(customerNumber);
        3. GetMessages ska bytas till GetMessages(customerNumber);
*/

public class Main {
    public static void main(String[] args) throws Exception {

        // New Controller
        EntityController ec = new EntityController();
            // Test getImagesFromInstrumentId
            //ArrayList<String> images = ec.getImagesFromInstrumentId(4);
            //for(String e: images) {
            //    System.out.println(e);
            //}
            //ArrayList<MessageEntity> m = ec.getMessages(2, "Reservation");

        /*
        for(MessageEntity mess: m){
            System.out.println("Meddelande: " + mess.getText());
        }
        */


        //System.out.println("is Auterized?: " + ec.isAuthorized("000000"));

        // READ
        //ArrayList<PersonEntity> persons           = ec.getPersons();
        //ArrayList<ChatEntity> chats             = ec.getChats();
        //ArrayList<ChatmemberEntity> chatMembers = ec.getChatMembers();
        //ArrayList<MessageEntity> messages       = ec.getMessages();
        //ArrayList<MessageEntity> messages = ec.getMessagesFromChatID(1,2);
        //ArrayList<MessageEntity> message = ec.getMessagesFromPersonID(1,107);

        // INSERT
            //ec.initiateContact(1,"Adam", "Gustavsson","070-4444444", "AdamGustavsson@gmail.com","Övrigt");
            //ec.addMessage(4, 3, "Hej, jag undrar om min gitarr är klar än?", "");
            //ec.addCustomer("Mattias", "Larsson", "070-12121212", "MattiasLarsson@gmail.com", "Reperation");
            ec.addMessage(2, "Hej igen anders, kan jag komma idag?", "URL");

        // READ
            //ArrayList<PersonEntity> persons           = ec.getPersons();
            //ArrayList<ChatEntity> chats             = ec.getChats();
            //ArrayList<ChatmemberEntity> chatMembers = ec.getChatMembers();
            ArrayList<MessageEntity> messages       = ec.getMessages(2);
            //ArrayList<MessageEntity> messages2      = ec.getAllMessages();
            //System.out.println("is Auterized?: " + ec.isAuthorized("000000"));
            //PersonEntity p = ec.getCustomer("111111");

        // PRINT

            for (MessageEntity p: messages) {
                System.out.println("P.getText(): " + p.getText());
            }



            //System.out.println(ec.getChatID(2));



    }
}