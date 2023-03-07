package api.controller;

import api.model.ReparationModel;
import api.service.ReparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReparationController {

    private final ReparationService service;
    @Autowired
    public ReparationController(ReparationService service) throws Exception {
        this.service = service;
    }

    @GetMapping("/reparation/all")
    List<ReparationModel> getAllReparations() throws Exception {
        return service.getAllReparations();
    }

    @GetMapping("/reparation")
    List<ReparationModel> getReparationById(@RequestParam Integer persId) {
        return service.getReparationById(persId);
    }


}
