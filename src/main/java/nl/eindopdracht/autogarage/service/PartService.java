package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Part;

import java.util.List;

public interface PartService {

    List<Part> getParts();

    Part findPartById(Long partId);

    void addNewPart(Part part);

    void deletePartById(Long partId);

    Part updatePart(Long partId, String name, Double price, Integer stock);
}
