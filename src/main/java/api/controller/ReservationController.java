package api.controller;
//API
import api.model.ReservationModel;
import api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService service;
    @Autowired
    public ReservationController(ReservationService service) throws Exception {
        this.service = service;
    }

    @GetMapping("/reservation/all")
    List<ReservationModel> getAllReservations() throws Exception {
        return service.getAllReservations();
    }
    //in Postman, GET localhost:8080/reservation?id=2   [for all reservations made by person with id 2]
    @GetMapping("/reservation")
    List<ReservationModel> getReservationByPersonId(@RequestParam Integer persId) {
        return service.getReservationByPersonId(persId);
    }

    @PostMapping("reservation/add")
    ReservationModel addReservation(@RequestBody ReservationModel p) throws Exception {
        return service.addReservation(p);
    }

    @PutMapping("reservation/update")
    ReservationModel updateReservation(@RequestBody ReservationModel p) throws Exception {
        return service.updateReservation(p);
    }
}
