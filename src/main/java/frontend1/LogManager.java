package frontend1;

import Entities.LogEntity;
import Entities.PersonEntity;
import EntityController.EntityController;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class LogManager implements Serializable {
    private String text;

    private int personId;

    @Inject
    private EntityController entityController;

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<PersonEntity> getCustomers() throws Exception {
        ArrayList<PersonEntity> persons = entityController.getPersons();
        PersonEntity admin = entityController.getAdmin();
        for (int index = 0; index < persons.size(); index++) {
            if (persons.get(index).equals(admin)) {
                persons.remove(index);
                break;
            }
        }
        return persons;
    }

    public ArrayList<LogEntity> getAllLogs() {
        return entityController.getLogWithID(this.personId);
    }

    public void save() {
        entityController.addLog(personId, text);
        text = ""; // Reset log text
    }
}
