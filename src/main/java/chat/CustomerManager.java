package chat;

import Entities.PersonEntity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.EntityControllerInterface;
import java.io.IOException;
import java.io.Serializable;


@Named
@SessionScoped
public class CustomerManager implements Serializable {

    private PersonEntity person = new PersonEntity();

    @Inject
    private EntityControllerInterface entityController;

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
            }
        } else { // create new customer
            try {
                person = entityController.addCustomer(person);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        SessionManager.setObjectAttribute("customer", person);
        messageManager.setSender(person);
        messageManager.setReceiver(entityController.getAdmin());
        FacesContext.getCurrentInstance().getExternalContext().redirect("chat.xhtml?faces-redirect=true");
    }
}
