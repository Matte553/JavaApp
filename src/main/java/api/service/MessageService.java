package api.service;

import api.model.Message;
import Entities.MessageEntity;
import EntityController.EntityController;
import api.model.MessageModelPost;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    EntityController ec = new EntityController();

    public MessageService() throws Exception {
    }


    private Message convertMessageEntity(MessageEntity value) {
        return new Message(
                value.getId(),
                value.getChatId(),
                value.getPersonId(),
                value.getText(),
                value.getImage(),
                value.getMessageTimestamp());
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

    public List<Message> getAllMessages() throws Exception {
        ArrayList<MessageEntity> dbList = ec.getMessages();
        List<Message> apiList = new ArrayList<>();

        for (MessageEntity m : dbList) {
            apiList.add( this.convertMessageEntity(m) );
        }

        return apiList;
    }

    public MessageModelPost addMessage(MessageModelPost postMessage) {
        ec.addMessage(postMessage.getFromID(), postMessage.getToID(), postMessage.getText(), postMessage.getImageUrl());
        return postMessage;
    }
}