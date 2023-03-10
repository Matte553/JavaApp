package api.controller;
//API
import api.model.LogModel;
import api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogController {

    private final LogService service;

    @Autowired
    public LogController(LogService service) throws Exception {
        this.service = service;
    }


    @GetMapping("/log")
    List<LogModel> getLogByPersonId(@RequestParam Integer persId) {
        return service.getLogByPersonId(persId);
    }


    @PostMapping("/log/add")
    LogModel addLogEntry(@RequestBody LogModel newLogEntry) {
        return service.addLogEntry(newLogEntry);
    }
}
