import Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws Exception {

        EntityController ec = new EntityController();

        ArrayList<PersonEntity> persons         = ec.getPersons();
        ArrayList<ChatEntity> chats             = ec.getChats();
        ArrayList<ChatmemberEntity> chatMembers = ec.getChatMembers();
        ArrayList<MessageEntity> messages       = ec.getMessages();
    }
}