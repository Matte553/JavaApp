package EntityController;

import Entities.*;

import java.util.ArrayList;
//Irrelevant to the API-team
public class Main {
    public static void main(String[] args) throws Exception {

        // New Controller
        EntityController ec = new EntityController();
        ec.createChat();


        // Read from all database tables

        ArrayList<PersonEntity> persons         = ec.getPersons();
        /*
        ArrayList<ChatEntity> chats             = ec.getChats();
        ArrayList<ChatmemberEntity> chatMembers = ec.getChatMembers();
        ArrayList<MessageEntity> messages       = ec.getMessages();
        */

        /*ArrayList<MessageEntity> messages = ec.getMessagesFromChatID(1,2);
        */
        // Iterate through list
        System.out.println("Skriver ut listan här: ");
        for (PersonEntity p: persons) {
            System.out.println(p);
        }






    }
}