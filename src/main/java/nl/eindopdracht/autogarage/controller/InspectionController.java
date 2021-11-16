package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.Enumeration.InspectionStatus;
import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Inspection;
import nl.eindopdracht.autogarage.service.InspectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping(path = "api/inspection")
public class InspectionController {

    private final InspectionServiceImpl inspectionService;

    @Autowired
    public InspectionController (InspectionServiceImpl inspectionService) {
        this.inspectionService = inspectionService;
    }

    @GetMapping
    public List<Inspection> getAllInspections() {
        return inspectionService.getInspections();
    }

    @GetMapping(path = "{inspectionId}")
    public Inspection getInspectionById(@PathVariable("inspectionId") Long inspectionId) {
        return inspectionService.findInspectionById(inspectionId);
    }

    @PostMapping
    public void registerNewInspection(@RequestBody Inspection inspection) {
        inspectionService.addNewInspection(inspection);
    }

    @DeleteMapping(path = "{inspectionId}")
    public void deleteInspectionById(@PathVariable("inspectionId") Long inspectionId) {
        inspectionService.deleteInspectionById(inspectionId);
    }

    @PutMapping(path = "{inspectionId}")
    public void updateInspection(
            @PathVariable("inspectionId") Long inspectionId,
            @RequestParam(required = false) Car car,
            @RequestParam(required = false) Enumeration<InspectionStatus> status,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) String shortcoming) {
        inspectionService.updateInspection(inspectionId, car, status, date, shortcoming);
    }
}
