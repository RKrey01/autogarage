package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Inspection;
import nl.eindopdracht.autogarage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InspectionServiceImpl implements InspectionService{
    private final InspectionRepository inspectionRepository;

    @Autowired
    public InspectionServiceImpl(@Lazy InspectionRepository inspectionRepository) {
        this.inspectionRepository = inspectionRepository;
    }

    @Override
    public void addNewInspection(Inspection inspection) {
        inspectionRepository.save(inspection);
    }

    @Override
    public List<Inspection> getInspections(){
        return inspectionRepository.findAll();
    }

    @Override
    public Inspection findInspectionById(Long inspectionId) {
        Optional<Inspection> result = inspectionRepository.findById(inspectionId);

        Inspection inspection = null;
        if (result.isPresent()) {
            inspection = result.get();
        } else {
            throw new RuntimeException("Keuring niet gevonden! ID - " + inspectionId);
        }
        return inspection;
    }

    @Override
    public void deleteInspectionById(Long inspectionId) {
        boolean exists = inspectionRepository.existsById(inspectionId);
        if (!exists) {
            throw new IllegalStateException("Keuring met id " + inspectionId + " bestaat niet");
        }
        inspectionRepository.deleteById(inspectionId);
    }

    @Override
    @Transactional
    public Inspection updateInspection(Long inspectionId, Car car, LocalDate date, String shortcoming) {
        Inspection inspection = inspectionRepository.findById(inspectionId)
                .orElseThrow(() -> new IllegalStateException("Keuring met id " + inspectionId + " bestaat niet"));


        if (date != null && date.isAfter(LocalDate.now()) && !Objects.equals(inspection.getDate(), date)) {
            inspection.setDate(date);
        }

        if (shortcoming != null && shortcoming.length() > 0 && !Objects.equals(inspection.getShortcoming(), shortcoming)) {
            inspection.setShortcoming(shortcoming);
        }

        return inspection;
    }
}
