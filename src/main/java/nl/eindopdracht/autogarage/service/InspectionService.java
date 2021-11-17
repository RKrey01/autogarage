package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Inspection;

import java.time.LocalDate;
import java.util.List;

public interface InspectionService {

    List<Inspection> getInspections();

    Inspection findInspectionById(Long inspectionId);

    void addNewInspection(Inspection inspection);

    void deleteInspectionById(Long inspectionId);

    Inspection updateInspection(Long inspectionId, Car car, LocalDate date, String shortcoming);
}
