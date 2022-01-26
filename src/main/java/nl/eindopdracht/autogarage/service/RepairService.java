package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;
import nl.eindopdracht.autogarage.model.Part;
import nl.eindopdracht.autogarage.model.Repair;
import nl.eindopdracht.autogarage.model.RepairAction;

import java.util.List;
import java.util.Optional;

public interface RepairService {

    List<Repair> getReparations();

    Repair findReparationById(Long repairId);

    Optional<Repair> findReparationByStatus(RepairStatus status);

    void addNewReparation(Repair repair);

    void deleteReparationById(Long repairId);

    Repair updateReparation(Long repairId, List<Part> usedPart, List<RepairAction> repairAction, RepairStatus status);

}
