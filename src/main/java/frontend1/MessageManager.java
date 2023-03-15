package frontend1;

import Entities.*;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;


@Named
@SessionScoped
public class MessageManager implements Serializable {
    private String subject;

    private Integer instrumentID;

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

    private ArrayList<MessageEntity> getAllCustomerMessages() {
        ArrayList<MessageEntity> messages = new ArrayList<>();
        ArrayList<MessageEntity> reparations = entityController.getMessagesWithSubject(
                sender.getId(), "Reparation");
        ArrayList<MessageEntity> reservations = entityController.getMessagesWithSubject(
                sender.getId(), "Reservation");
        ArrayList<MessageEntity> boknings = entityController.getMessagesWithSubject(
                sender.getId(), "Bookning");
        ArrayList<MessageEntity> others = entityController.getMessagesWithSubject(
                sender.getId(), "Other");

        if (reparations != null) {
            messages.addAll(reparations);
        }
        if (reservations != null) {
            messages.addAll(reservations);
        }
        if (boknings != null) {
            messages.addAll(boknings);
        }
        if (others != null) {
            messages.addAll(others);
        }
        messages.sort(Comparator.comparing(MessageEntity::getMessageTimestamp));
        return messages;
    }

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

    public Integer getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(Integer instrumentID) {
        this.instrumentID = instrumentID;
    }

    public ArrayList<MessageEntity> getMessages() {
        if (receiver != null && sender != null) {
            this.senderFullName = sender.getFirstname() + " " + sender.getLastname();
            this.receiverFullName = receiver.getFirstname() + " " + receiver.getLastname();
            if (SessionManager.getValue("customer") == null) {
                return entityController.getMessagesWithSubject(receiver.getId(), subject);
            } else {
                return getAllCustomerMessages();
            }
        }
        return new ArrayList<>();
    }

    public void saveInstrument() throws Exception {
        if (instrumentID != null && SessionManager.getValue("customer") != null) {
            EntityController controller = new EntityController();
            String url = "http://localhost:8080/test-1.0-SNAPSHOT/instrument.xhtml?instrumentID=";
            message.setText("Hejsan " + sender.getFirstname() + " ! du har reserverat instrument: " + url + instrumentID);
            String img = controller.getImagesFromInstrumentId(this.instrumentID).get(0);
            controller.addMessage(
                    receiver.getId(), sender.getId(), this.subject, message.getText(), img);
            message = new MessageEntity();
            instrumentID = null;
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
