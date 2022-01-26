package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;
import nl.eindopdracht.autogarage.model.*;
import nl.eindopdracht.autogarage.service.RepairServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/reparation")
public class RepairController {

    private final RepairServiceImpl service;

    @Autowired
    public RepairController (RepairServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Repair> getAllReparations() {
        return service.getReparations();
    }

    @GetMapping(path = "{repairId}")
    public Repair getReparationById(@PathVariable("repairId") Long repairId) {
        return service.findReparationById(repairId);
    }

    @GetMapping(path = "{repairStatus}")
    public Optional<Repair> getReparationByStatus(@RequestParam(value = "repairStatus") RepairStatus status) {
        return service.findReparationByStatus(status);
    }

    @PostMapping
    public void registerNewReparation(@RequestBody Repair repair) {
        service.addNewReparation(repair);
    }

    @DeleteMapping(path = "{repairId}")
    public void deleteReparationById(@PathVariable("repairId") Long repairId) {
        service.deleteReparationById(repairId);
    }

    @PutMapping(path = "{repairId}")
    public void updateReparation(
            @PathVariable("repairId") Long repairId,
            @RequestParam(required = false) List<Part> part,
            @RequestParam(required = false) List<RepairAction> repairAction,
            @RequestParam(required = false) RepairStatus status) {
        service.updateReparation(repairId, part, repairAction, status);
    }
}
