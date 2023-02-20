package EntityController;

import Entities.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        // New Controller
        EntityController ec = new EntityController();

        // Read from all database tables
        ArrayList<PersonEntity> persons         = ec.getPersons();
        ArrayList<ChatEntity> chats             = ec.getChats();
        ArrayList<ChatmemberEntity> chatMembers = ec.getChatMembers();
        ArrayList<MessageEntity> messages       = ec.getMessages();

        // Iterate through list
        for (PersonEntity p: persons) {
            System.out.println(p);
        }


    }
}