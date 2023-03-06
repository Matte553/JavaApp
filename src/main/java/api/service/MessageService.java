package api.service;

import api.model.Message;
import Entities.MessageEntity;
import EntityController.EntityController;
import api.model.MessageModelPost;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MessageService {
    EntityController ec = new EntityController();

    public MessageService() throws Exception {
    }

    public ArrayList<Message> getMessages(int id1) throws Exception {
        ArrayList<MessageEntity> dblist = ec.getMessages(id1);
        //
        ArrayList<Message> MessageList = new ArrayList<>();
        for (MessageEntity m : dblist) {
            MessageList.add(new Message(
                    m.getId(), 
                    m.getChatId(), 
                    m.getPersonId(), 
                    m.getText(), 
                    m.getImage(), 
                    m.getMessageTimestamp()));
        }
        return MessageList;
    }

    /*
    public void addMessage(Integer persId, String text, String picUrl) {
        //ec.addMessage(persId, text, picUrl);
    }
    */

    public MessageModelPost addMessage(MessageModelPost postMessage) {
        ec.addMessage(postMessage.getFromID(), postMessage.getToID(), postMessage.getText(), postMessage.getImageUrl());
        return postMessage;
    }
}