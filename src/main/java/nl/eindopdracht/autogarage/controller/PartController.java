package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.Part;
import nl.eindopdracht.autogarage.service.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/part")
public class PartController {
    private final PartServiceImpl partService;

    @Autowired
    public PartController(PartServiceImpl partService) {
        this.partService = partService;
    }

    @GetMapping
    public List<Part> getAllParts() {
        return partService.getParts();
    }

    @GetMapping(path = "{partId}")
    public Part getPartById(@PathVariable("partId") Long partId) {
        return partService.findPartById(partId);
    }

    @PostMapping
    public void addNewPart(@RequestBody Part part) {
        partService.addNewPart(part);
    }

    @DeleteMapping(path = "{partId}")
    public void deletePartById(@PathVariable("partId") Long partId) {
        partService.deletePartById(partId);
    }

    @PutMapping(path = "{partId}")
    public void updatePart(
            @PathVariable("partId") Long partId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer stock) {
        partService.updatePart(partId, name, price, stock);
    }
}
