package chat;

import Entities.*;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;


@Named
@SessionScoped
public class MessageManager implements Serializable {
    private String subject;

    private String senderFullName;

    private String receiverFullName;

    private PersonEntity sender = null;
    private PersonEntity receiver = null;

    @Inject
    @Push(channel = "updateMessages")
    private PushContext pushUpdate;

    private MessageEntity message = new MessageEntity();

    @Inject
    private EntityController entityController;

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

    public String getReceiverFullName() {
        return receiverFullName;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public ArrayList<MessageEntity> getMessages() {
        if (receiver != null && sender != null) {
            this.senderFullName = sender.getFirstname() + " " + sender.getLastname();
            this.receiverFullName = receiver.getFirstname() + " " + receiver.getLastname();
            if (SessionManager.getValue("customer") == null) {
                return entityController.getMessagesWithSubject(receiver.getId(), subject);
            } else {
                return entityController.getMessagesWithSubject(sender.getId(), subject);
            }
        }
        return new ArrayList<>();
    }

    public void submit() {
        entityController.addMessage(sender.getId(), receiver.getId(),message.getText(), message.getImage());
        pushUpdate.send("update");
        // reset values
        message = new MessageEntity();
    }
}
