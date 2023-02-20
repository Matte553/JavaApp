package EntityController;

import Entities.*;

import java.util.ArrayList;
//Irrelevant to the API-team
public class Main {
    public static void main(String[] args) throws Exception {

        EntityController ec = new EntityController();

        ArrayList<PersonEntity> persons         = ec.getPersons();
        ArrayList<ChatEntity> chats             = ec.getChats();
        ArrayList<ChatmemberEntity> chatMembers = ec.getChatMembers();
        ArrayList<MessageEntity> messages       = ec.getMessages();
    }
}