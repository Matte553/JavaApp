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
                value.getReservationNumber());
    }

    public ReservationService() throws Exception {
    }

    public List<ReservationModel> getAllReservations() {
        ArrayList<ReservationEntity> dbList = ec.getReservations();
        List<ReservationModel> apiList = new ArrayList<>();

        for (ReservationEntity entity : dbList) {
            apiList.add( this.convertReservationEntity(entity));
        }
        return apiList;
    }
}
