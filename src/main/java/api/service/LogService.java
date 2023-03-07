package api.service;

import EntityController.EntityController;
import api.model.LogModel;
import org.springframework.stereotype.Service;
import Entities.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    EntityController ec = new EntityController();

    public LogService() throws Exception {
    }

    private LogModel convertLogEntity(LogEntity value) {
        return new LogModel(
                value.getId(),
                value.getPersonId(),
                value.getText(),
                value.getLogTimestamp());
    }

    private List<LogModel> convertListEntity(ArrayList<LogEntity> dbList) {
        List<LogModel> apiList = new ArrayList<>();

        for (LogEntity entity : dbList) {
            apiList.add( this.convertLogEntity(entity));
        }
        return apiList;
    }

    public List<LogModel> getLogByPersonId(int id) {
        ArrayList<LogEntity> dbList = ec.getLogWithID(id);
        return this.convertListEntity(dbList);
    }
}
