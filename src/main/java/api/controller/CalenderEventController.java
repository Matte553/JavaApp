package api.controller;
//API
import api.model.CalenderEventModel;
import api.service.CalenderEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalenderEventController {
    private final CalenderEventService service;

    @Autowired
    public CalenderEventController(CalenderEventService service) throws Exception {
        this.service = service;
    }

    @GetMapping("/calender/all")
    List<CalenderEventModel> getAllCalenderEvent() {
        return service.getAllCalenderEvent();
    }


    @PostMapping("/calender/add")
    CalenderEventModel addCalenderEvent(CalenderEventModel calenderEvent) {
        return service.addCalenderEvent(calenderEvent);
    }
}
