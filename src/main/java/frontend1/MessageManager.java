package frontend1;

import Entities.*;
import EntityController.EntityController;
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

    private FileUpload file = new FileUpload();

    @Inject
    @Push(channel = "updateMessages")
    private PushContext pushUpdate;

    private MessageEntity message = new MessageEntity();

    @Inject
    private EntityController entityController;

    public FileUpload getFile() {
        return file;
    }

    public void setFile(FileUpload file) {
        this.file = file;
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

    private void saveInstrument() {
        Integer instrumentID = (Integer) SessionManager.getValue("instrumentID");
        if (instrumentID != null && SessionManager.getValue("customer") != null) {
            entityController.addReservation(instrumentID, sender.getId());
            String url = "http://localhost:8080/test-1.0-SNAPSHOT/instrument.xhtml?instrumentID=";
            message.setText("Du har reserverat instrument\n: " + url + Integer.toString(instrumentID));
            entityController.addMessage(
                    sender.getId(), receiver.getId(), this.subject, message.getText(), null);
            message = new MessageEntity();
            SessionManager.setObjectAttribute("instrumentID", null);
        }
    }

    public void initChat() {
        if (this.subject.equals("Reservation")) {
            saveInstrument();
        }
    }

    public void submit() {
        message.setImage(file.getServerFileName());
        entityController.addMessage(sender.getId(), receiver.getId(), this.subject, message.getText(), message.getImage());
        pushUpdate.send("update");
        // reset values
        message = new MessageEntity();
        file.reset();
    }
}
