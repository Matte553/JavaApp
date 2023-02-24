package chat;

import Entities.PersonEntity;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.EntityControllerInterface;
import services.PersonService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


@Named
@SessionScoped
public class CustomerManager implements Serializable {

    private PersonEntity person = new PersonEntity();
    private String subject;
    @Inject
    private EntityControllerInterface entityController;

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
                customerNum = entityController.addCustomer(person);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        SessionManager.setAttribute("customerNumber", customerNum);
        SessionManager.setAttribute("username", person.getFirstname());
        SessionManager.setAttribute("subject", subject);
        FacesContext.getCurrentInstance().getExternalContext().redirect("chat.xhtml?faces-redirect=true");
    }
};
