package chat;

import Entities.PersonEntity;
import chat.SessionManager;
import Entities.MessageEntity;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.EntityControllerInterface;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class MessageManager implements Serializable {
    private String subject;
    private PersonEntity sender = null;
    private PersonEntity receiver = null;

    private MessageEntity message = new MessageEntity();

    @Inject
    private EntityControllerInterface entityController;

    private ArrayList<MessageEntity> messages;

    @PostConstruct
    public void init() {
        messages = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public PersonEntity getSender() {
        return sender;
    }

    public void setSender(PersonEntity sender) {
        this.sender = sender;
    }

    public PersonEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(PersonEntity receiver) {
        this.receiver = receiver;
    }

    public MessageEntity getMessage() {
        return message;
    }

    public ArrayList<MessageEntity> getMessages() {
/*        if (receiver != null) {
            return entityController.getMessages(receiver.getId(), subject);
        }*/
        return messages;
    }

    public void submit() throws IOException {
        entityController.addMessage(sender.getId(), receiver.getId(), subject, message);
        messages.add(message); //temp
        System.out.println("====Message: " + message.toString());
        System.out.println(messages.size());
        // reset values
        message = new MessageEntity();
    }
}
