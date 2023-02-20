package chat;

import Entities.PersonEntity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.PersonService;

import java.io.IOException;
import java.io.Serializable;


@Named
@SessionScoped
public class PersonManager implements Serializable {

    private PersonEntity person = new PersonEntity();

    @Inject
    private PersonService personService;

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public void submit() throws IOException {
        String customerNum = person.getCustomerNumber();
        if (!customerNum.isEmpty()) {
            if (!personService.isAuthorized(customerNum)) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage("Kund nummer hittas ej");
                context.addMessage("form:customerId", message);
                return;
            } else {
                person = personService.getPerson(customerNum);
            }
        } else {
            customerNum = personService.generateCustomerNum();
            person.setCustomerNumber(customerNum);
            personService.addPerson(person);
        }
        SessionManager.setAttribute("customerNumber", customerNum);
        SessionManager.setAttribute("username", person.getFirstname());

        FacesContext.getCurrentInstance().getExternalContext().redirect("chat.xhtml?faces-redirect=true");
    }
};
