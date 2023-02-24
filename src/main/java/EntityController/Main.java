package EntityController;

import Entities.*;

import java.util.ArrayList;

//Irrelevant to the API-team
public class Main {
    public static void main(String[] args) throws Exception {

    // New Controller
        EntityController ec = new EntityController();
        //ec.insertTestData();

        //ec.insertAnders();
        //ec.initiateContact(0,"Anders", "Andersson","070-00000000","AndersAndersson@gmail.com","Questions");

    // Read from all database tables
        //ArrayList<PersonEntity> persons         = ec.getPersons();
        //ArrayList<ChatEntity> chats             = ec.getChats();
        //ArrayList<ChatmemberEntity> chatMembers = ec.getChatMembers();
        //ArrayList<MessageEntity> messages       = ec.getMessages();


        //ArrayList<MessageEntity> messages = ec.getMessagesFromChatID(1,2);

    // Create Conversation
        //ec.initiateContact(1, "Kerstin", "Svensson", "070-7189503", "kerstin.svensson@gmail.com", "Reparation");

    // Create Message
        //ec.addMessage(107, 104, "Hej, jag undrar om min gitarr är klar än?", "");

    // Get Messages from persons
        //ArrayList<MessageEntity> message = ec.getMessagesFromPersonID(1,107);
        // Iterate through list

        /*
        for (MessageEntity m: message) {
            System.out.println(m);
        }
        */








    }
}