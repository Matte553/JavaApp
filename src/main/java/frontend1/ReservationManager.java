package frontend1;

import Entities.PersonEntity;
import Entities.ReservationEntity;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Named
@RequestScoped
public class ReservationManager implements Serializable {
    private ArrayList<ReservationEntity> reservations;

    private int personId;

    @Inject
    private EntityController entityController;

    @PostConstruct
    public void init() {
        reservations = entityController.getReservations();
    }

    public Set<PersonEntity> getCustomers() {
        Set<PersonEntity> customers = new HashSet<>();
        reservations.forEach(reservation -> customers.add(entityController.getPersonWithID(reservation.getPersonId())));
        return customers;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public ArrayList<String> links() {
        ArrayList<String> allLinks = new ArrayList<>();
        String link = "instrument.xhtml?instrumentID=";
        ArrayList<ReservationEntity> res = entityController.getReservationsFromPersonId(personId);
        res.forEach(reservation -> allLinks.add(link + Integer.toString(reservation.getInstrumentId())));
        return allLinks;
    }
}
