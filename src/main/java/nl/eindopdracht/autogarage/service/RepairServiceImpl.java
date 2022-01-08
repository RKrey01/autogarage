package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;
import nl.eindopdracht.autogarage.model.*;
import nl.eindopdracht.autogarage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService {

    private final RepairRepository repository;

    @Autowired
    public RepairServiceImpl(@Lazy RepairRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewReparation(Repair repair) {
        repository.save(repair);
    }

    @Override
    public List<Repair> getReparations(){
        return repository.findAll();
    }

    @Override
    public Repair findReparationById(Long repairId) {
        Optional<Repair> result = repository.findById(repairId);

        Repair repair = null;
        if (result.isPresent()) {
            repair = result.get();
        } else {
            throw new RuntimeException("Reparatie niet gevonden! ID - " + repairId);
        }
        return repair;
    }

    @Override
    public Optional<Repair> findReparationByStatus (RepairStatus status) {
        return repository.findReparationByStatus(status);
    }

    @Override
    public void deleteReparationById(Long repairId) {
        boolean exists = repository.existsById(repairId);
        if (!exists) {
            throw new IllegalStateException("Reparatie met id " + repairId + " bestaat niet");
        }
        repository.deleteById(repairId);
    }

    @Override
    @Transactional
    public Repair updateReparation(Long repairId, List<Part> usedPart, List<RepairAction> repairAction,
                                   RepairStatus status) {
        Repair repair = repository.findById(repairId)
                .orElseThrow(() -> new IllegalStateException("Reparatie met id " + repairId + " bestaat niet"));


        for (Part p : usedPart) {
            if (p != null && p.toString().length() > 0 && !Objects.equals(repair.getUsedPart(), p)) {
                usedPart.add(p);
            }
        }

        for (RepairAction r : repairAction) {
            if (r != null && r.toString().length() > 0 && !Objects.equals(repair.getRepairAction(), r)) {
                repairAction.add(r);
            }
        }


        if (status != null && status.toString().length() > 0 && !Objects.equals(repair.getStatus(), status)) {
            repair.setStatus(status);
        }

        return repair;
    }
}
