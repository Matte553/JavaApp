package chat;

import chat.SessionManager;
import Entities.MessageEntity;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.EntityControllerInterface;
import services.MessageService;

import javax.security.auth.Subject;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Named
@SessionScoped
public class MessageManager implements Serializable {

    private static String customerNum;
    private static String subject;
    private MessageEntity message = new MessageEntity();

    @Inject
    private EntityControllerInterface entityController;

    private ArrayList<MessageEntity> messages;

    @PostConstruct
    public void init() {
/*        customerNum = SessionManager.getValue("customerNumber");
        subject = SessionManager.getValue("subject");*/

        customerNum = "";
        subject = "";
        messages = entityController.getMessages(customerNum, subject);
    }

    public MessageEntity getMessage() {
        return message;
    }

    public ArrayList<MessageEntity> getMessages() {
        return entityController.getMessages(customerNum, subject);
    }

    public void submit() throws IOException {
        entityController.addMessage(customerNum,subject, message);
        System.out.println(message.toString());
        System.out.println(messages.size());
        // reset values
        message = new MessageEntity();
    }
}
