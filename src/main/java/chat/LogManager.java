package chat;

import Entities.PersonEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.EntityControllerInterface;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class LogManager implements Serializable {

    private ArrayList<PersonEntity> customers;

    @Inject
    private MessageManager messageManager;

    @Inject
    private EntityControllerInterface entityController;

    public ArrayList<PersonEntity> getCustomers() {
        return customers;
    }

    @PostConstruct
    public void init() {
        //customers = new ArrayList<>();
        messageManager.setSender(entityController.getAdmin());
        messageManager.setSubject("Reparation"); //default
        customers = entityController.getCustomersBySubject(messageManager.getSubject());
    }

    public void setReceiver(String customerNum){
        messageManager.setReceiver(entityController.getCustomer(customerNum));
        System.out.println(messageManager.getReceiver().toString());//delete
        System.out.println(customerNum);//delete
    }

    public void getCustomersBySubject() throws IOException {
        customers = entityController.getCustomersBySubject(messageManager.getSubject());
        FacesContext.getCurrentInstance().getExternalContext().redirect("admin-chat-log.xhtml");
    }
}
