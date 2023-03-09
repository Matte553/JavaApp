package api.controller;

import api.model.CalenderModel;
import api.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalenderController {
    private final CalenderService service;

    @Autowired
    public CalenderController(CalenderService service) throws Exception {
        this.service = service;
    }

    @GetMapping("/Calender/all")
    List<CalenderModel> getAllCalenderEvent() {
        return service.getAllCalenderEvent();
    }


    @PostMapping("/Calender/add")
    CalenderModel addCalenderEvent(CalenderModel calenderEvent) {
        return service.addCalenderEvent(calenderEvent);
    }
}
