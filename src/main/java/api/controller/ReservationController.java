package api.controller;

import api.model.ReservationModel;
import api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService service;
    @Autowired
    public ReservationController(ReservationService service) throws Exception {
        this.service = service;
    }

    @GetMapping("/reservation")
    List<ReservationModel> getAllReservations() throws Exception {
        return service.getAllReservations();
    }
}
