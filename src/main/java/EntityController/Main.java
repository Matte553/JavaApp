package EntityController;

import Entities.*;

import java.util.ArrayList;
//Irrelevant to the API-team
public class Main {
    public static void main(String[] args) throws Exception {

        // New Controller
        EntityController ec = new EntityController();
        //ec.insertTestData();

        // Read from all database tables

        ArrayList<PersonEntity> persons         = ec.getPersons();

        /*
        ArrayList<ChatEntity> chats             = ec.getChats();
        ArrayList<ChatmemberEntity> chatMembers = ec.getChatMembers();
        ArrayList<MessageEntity> messages       = ec.getMessages();
        */

        //ArrayList<MessageEntity> messages = ec.getMessagesFromChatID(1,2);

        //ec.initiateContact(7, "Kerstin", "Svensson", "070-7189503", "kerstin.svensson@gmail.com", "Reparation");

        ec.addMessage(102, 102, "Hej, jag undrar om min gitarr är klar än?", "");

        // Iterate through list
        for (PersonEntity m: persons) {
            System.out.println(m);
        }






    }
}