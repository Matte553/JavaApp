package chat;

import Entities.*;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.EntityControllerInterface;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class MessageManager implements Serializable {
    private String subject;
    private PersonEntity sender = null;
    private PersonEntity receiver = null;
    Map<String, Object> messageMap;
    @Inject
    @Push(channel = "chat_channel")
    private PushContext pushMessage;

    private MessageEntity message = new MessageEntity();

    @Inject
    private EntityController entityController;

    private ArrayList<MessageEntity> messages;

    @PostConstruct
    public void init() {
        messages = new ArrayList<>();
        messageMap = new HashMap<>();
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
        if (receiver != null) {
            return entityController.getMessages(receiver.getId(), subject);
        }
        return new ArrayList<MessageEntity>();
    }


    public void submit() throws IOException {
        //entityController.addMessage(sender.getId(), receiver.getId(), subject, message);
        messages.add(message); //temp
        messageMap.put("data", message);
        messageMap.put("name", "fname"); //tmp
        //messageMap.put("name", sender.getFirstname() + " " + sender.getLastname());
        messageMap.put("isCustomer", true);
        if (SessionManager.getValue("customer") == null) {
            messageMap.put("isCustomer", false);
        }
        pushMessage.send(messageMap);
        System.out.println("====Message: " + message.toString());//tmp
        System.out.println(messages.size()); //tmp
        // reset values
        message = new MessageEntity();
    }
}
