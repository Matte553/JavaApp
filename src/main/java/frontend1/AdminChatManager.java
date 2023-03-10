package frontend1;

import Entities.*;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class AdminChatManager implements Serializable {

    private ArrayList<PersonEntity> customers;

    @Inject
    private MessageManager messageManager;

    @Inject
    private EntityController entityController;

    public ArrayList<PersonEntity> getCustomers() {
        return customers;
    }

    @PostConstruct
    public void init() {
        messageManager.setSender(entityController.getAdmin());
        messageManager.setSubject("Reparation"); //default
        customers = entityController.getCustomersWithSubject(messageManager.getSubject());
    }

    public void setReceiver(String customerNum) {
        messageManager.setReceiver(entityController.getCustomer(customerNum));
    }

    public void getCustomersBySubject() throws IOException {
        customers = entityController.getCustomersWithSubject(messageManager.getSubject());
        FacesContext.getCurrentInstance().getExternalContext().redirect("admin-chatt.xhtml");
    }
}
