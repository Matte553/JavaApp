package chat;

import Entities.MessageEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.MessageService;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Named
@SessionScoped
public class MessageManager implements Serializable {
    private MessageEntity message = new MessageEntity();

    @Inject
    private MessageService messageService;

    private ArrayList<MessageEntity> messages;

    @PostConstruct
    public void init(){
        messages = messageService.getMessages("");
    }

    public MessageEntity getMessage() {
        return message;
    }


    public ArrayList<MessageEntity> getMessages() {
        return messages;
    }

    public Timestamp getTime() {
        return new Timestamp(new Date().getTime());
    }

    public void submit() throws IOException {
        message.setMessageTimestamp(getTime());
        messages.add(message);
        System.out.println(message.toString());
        System.out.println(messages.size());
        messageService.addMessage(message,"");
        // reset values
        message = new MessageEntity();
    }
}
