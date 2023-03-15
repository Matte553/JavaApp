package api.controller;
//API
import api.model.CalenderEventModel;
import api.service.CalenderEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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
    CalenderEventModel addCalenderEvent(@RequestBody CalenderEventModel calenderEvent) throws ParseException {
        return service.addCalenderEvent(calenderEvent);
    }
}
