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
    private LogEntity log;

    private int personId;

    @Inject
    private EntityController entityController;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public LogEntity getLog() {
        return log;
    }

    public void setLog(LogEntity log) {
        this.log = log;
    }

    public ArrayList<PersonEntity> getCustomers() throws Exception {
        return entityController.getPersons();
    }

    public ArrayList<LogEntity> getAllLogs(){
        return entityController.getLogWithID(this.personId);
    }

    public void save(){}
}
