package frontend1;

import Entities.*;
import EntityController.EntityController;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;


@Named
@SessionScoped
public class CustomerManager implements Serializable {

    private static final String CLIENT_CHAT_PAGE = "chat.xhtml?faces-redirect=true";
    private static final String ADMIN_CHAT_PAGE = "admin-chatt.xhtml?faces-redirect=true";
    private PersonEntity person = new PersonEntity();

    @Inject
    private EntityController entityController;

    @Inject
    private MessageManager messageManager;

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public void submit() throws IOException {
        String customerNum = person.getCustomerNumber();
        if (!customerNum.isEmpty()) { // if try to log in by customer number
            // Check if customer is authorised
            if (!entityController.isAuthorized(customerNum)) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage("Kund nummer hittas ej");
                context.addMessage("form:customerId", message);
                return;
            } else {
                // get customer data
                person = entityController.getCustomer(customerNum);
                if (person.equals(entityController.getAdmin())) { // if logged as Admin, redirect to admin page
                    FacesContext.getCurrentInstance().getExternalContext().redirect(ADMIN_CHAT_PAGE);
                }
            }
        } else { // create new customer
            try {
                person = entityController.addCustomer(person, messageManager.getSubject());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        SessionManager.setObjectAttribute("customer", person);
        messageManager.setSender(person);
        messageManager.setReceiver(entityController.getAdmin());
        FacesContext.getCurrentInstance().getExternalContext().redirect(CLIENT_CHAT_PAGE);
    }
}
