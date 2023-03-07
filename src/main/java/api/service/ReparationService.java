package api.service;

import Entities.ReparationsEntity;
import EntityController.EntityController;
import api.model.ReparationModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReparationService {

    EntityController ec = new EntityController();

    public ReparationService() throws Exception {
    }


    private ReparationModel convertReparationEntity(ReparationsEntity value) {
        return new ReparationModel(
                value.getErrandNumber(),
                value.getPersonId(),
                value.getDescription(),
                value.getType());
    }

    public List<ReparationModel> getAllReparations() throws Exception {
        ArrayList<ReparationsEntity> dbList = ec.getReparations();
        List<ReparationModel> apiList = new ArrayList<>();

        for (ReparationsEntity entity : dbList) {
            apiList.add( this.convertReparationEntity(entity));
        }

        return apiList;
    }

    public List<ReparationModel> getReparationById(int persId) {
        ArrayList<ReparationsEntity> dbList = ec.getReparationsFromPersonId(persId);
        List<ReparationModel> apiList = new ArrayList<>();

        for (ReparationsEntity entity : dbList) {
            apiList.add( this.convertReparationEntity(entity));
        }

        return apiList;
    }
}
