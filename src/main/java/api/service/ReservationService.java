package api.service;

import Entities.PersonEntity;
import Entities.ReservationEntity;
import EntityController.EntityController;
import api.model.Person;
import api.model.ReservationModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    EntityController ec = new EntityController();

    private ReservationModel convertReservationEntity(ReservationEntity value) {
        return new ReservationModel(
                value.getReservationNumber(),
                value.getInstrumentId(),
                value.getPersonId());
    }

    private List<ReservationModel> convertListEntity(ArrayList<ReservationEntity> dbList) {
        List<ReservationModel> apiList = new ArrayList<>();

        for (ReservationEntity entity : dbList) {
            apiList.add( this.convertReservationEntity(entity));
        }
        return apiList;
    }
    private ReservationEntity convertReservationModel(ReservationModel value) {
        return new ReservationEntity(
                value.getReservationNumber(),
                value.getInstrumentId(),
                value.getReservationNumber());
    }

    public ReservationService() throws Exception {
    }

    public List<ReservationModel> getAllReservations() {
        ArrayList<ReservationEntity> dbList = ec.getReservations();
        return this.convertListEntity(dbList);
    }

    public List<ReservationModel> getReservationByPersonId(Integer persId) {
        ArrayList<ReservationEntity> dbList = ec.getReservationsFromPersonId(persId);
        return this.convertListEntity(dbList);
    }

    public List<ReservationModel> getReservationsByPersonID(Integer id) throws Exception{
        ArrayList<ReservationEntity> dblist = ec.getReservations();
        List<ReservationModel> apiList = new ArrayList<>();

        for (ReservationEntity e : dblist) {
            if (e.getPersonId() == id) {
                apiList.add(this.convertReservationEntity(e));
            }
            return apiList;
        }
        //apiList.add(this.convertReservationEntity(new ReservationEntity(-1, -1, -1)));
        return apiList;
    }

    public ReservationModel addReservation(ReservationModel postReservation) {
        ReservationModel r = new ReservationEntity()
    }

    /*//  PUT
    public ReservationModel updateReservation(ReservationModel resToChange) {
        ReservationModel r = new ReservationModel(resToChange.getReservationNumber(), resToChange.getInstrumentId(), resToChange.getPersonId());
        ec.updateReservation(r);
        return resToChange;
    }*/

}
