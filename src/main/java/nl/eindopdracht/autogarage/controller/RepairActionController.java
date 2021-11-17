package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.RepairAction;
import nl.eindopdracht.autogarage.service.RepairActionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/repairAction")
public class RepairActionController {
    private final RepairActionServiceImpl service;

    @Autowired
    public RepairActionController(RepairActionServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<RepairAction> getAllRepairActions() {
        return service.getRepairActions();
    }

    @GetMapping(path = "{repairActionId}")
    public RepairAction getRepairActionById(@PathVariable("repairActionId") Long repairActionId) {
        return service.findRepairActionById(repairActionId);
    }

    @PostMapping
    public void addNewRepairAction(@RequestBody RepairAction repairAction) {
        service.addNewRepairAction(repairAction);
    }

    @DeleteMapping(path = "{repairActionId}")
    public void deleteRepairActionById(@PathVariable("repairActionId") Long repairActionId) {
        service.deleteRepairActionById(repairActionId);
    }

    @PutMapping(path = "{repairActionId}")
    public void updateRepairAction(
            @PathVariable("repairActionId") Long repairActionId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price) {
        service.updateRepairAction(repairActionId, name, price);
    }

}
