//API
package api.service;

import Entities.ReservationEntity;
import EntityController.EntityController;
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

    public List<ReservationModel> getReservationByPersonId(Integer id) {
        ArrayList<ReservationEntity> dbList = ec.getReservationsFromPersonId(id);
        return this.convertListEntity(dbList);
    }
//addReservation(Integer instrumentId, Integer personId)
    public ReservationModel addReservation(ReservationModel p) {
        ec.addReservation(p.getInstrumentId(), p.getPersonId());
        return p;
    }

    /*//  PUT
    public ReservationModel updateReservation(ReservationModel resToChange) {
        ReservationModel r = new ReservationModel(resToChange.getReservationNumber(), resToChange.getInstrumentId(), resToChange.getPersonId());
        ec.updateReservation(r);
        return resToChange;
    }*/

}
