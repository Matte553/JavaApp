//API
package api.service;

import api.model.MessageModel;
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


    private MessageModel convertMessageEntity(MessageEntity value) {
        return new MessageModel(
                value.getId(),
                value.getChatId(),
                value.getPersonId(),
                value.getText(),
                value.getImage(),
                value.getMessageTimestamp());
    }

    private List<MessageModel> convertListEntity(ArrayList<MessageEntity> dbList) {
        List<MessageModel> apiList = new ArrayList<>();

        for (MessageEntity entity : dbList) {
            apiList.add( this.convertMessageEntity(entity) );
        }
        return apiList;
    }

    public List<MessageModel> getMessages(int id1) throws Exception {
        ArrayList<MessageEntity> dbList = ec.getMessages(id1);
        return this.convertListEntity(dbList);
    }

    public List<MessageModel> getAllMessages() throws Exception {
        ArrayList<MessageEntity> dbList = ec.getMessages();
        return this.convertListEntity(dbList);
    }

    public MessageModelPost addMessage(MessageModelPost postMessage) {
        MessageEntity message = ec.addMessage(postMessage.getFromID(), postMessage.getToID(), postMessage.getText(), postMessage.getImageUrl());
        // Blev lite pannkaka här med att returnera MessageEntity -> MessageModel eftersom det är ju MessageModelPost som ska ges tillbaka till klienten.
        return postMessage;
    }
}