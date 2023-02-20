package services;

import Entities.MessageEntity;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;

@Stateless
public class MessageService implements Serializable {

    // Test backend services

    public void addMessage(MessageEntity message, String customerNum){
    }

    public ArrayList<MessageEntity> getMessages(String customerNum){
        return new ArrayList<>();
    }

}
