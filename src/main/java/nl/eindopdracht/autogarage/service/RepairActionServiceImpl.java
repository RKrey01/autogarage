package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.RepairAction;
import nl.eindopdracht.autogarage.repository.RepairActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RepairActionServiceImpl implements RepairActionService{

    private final RepairActionRepository repository;

    @Autowired
    public RepairActionServiceImpl(@Lazy RepairActionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RepairAction> getRepairActions(){
        return repository.findAll();
    }

    @Override
    public RepairAction findRepairActionById(Long repairActionId) {
        Optional<RepairAction> result = repository.findById(repairActionId);

        RepairAction repairAction = null;
        if (result.isPresent()) {
            repairAction = result.get();
        } else {
            throw new RuntimeException("Reparatiehandeling  niet gevonden! ID - " + repairActionId);
        }

        return repairAction;
    }

    @Override
    public void addNewRepairAction(RepairAction repairAction) {
        Optional<RepairAction> repairActionOptional = repository.findRepairActionByName(repairAction.getName());
        if (repairActionOptional.isPresent()) {
            throw new IllegalStateException("naam in gebruik");
        }
        repository.save(repairAction);
    }

    @Override
    public void deleteRepairActionById(Long repairActionId) {
        boolean exists = repository.existsById(repairActionId);
        if (!exists) {
            throw new IllegalStateException("Reparatiehandeling met id " + repairActionId + " bestaat niet");
        }
        repository.deleteById(repairActionId);
    }

    @Override
    @Transactional
    public RepairAction updateRepairAction(Long repairActionId, String name, Double price) {
        RepairAction repairAction = repository.findById(repairActionId)
                .orElseThrow(() -> new IllegalStateException("Reparatiehandeling met id " + repairActionId + " bestaat niet"));

        if (name != null && name.length() > 0 && !Objects.equals(repairAction.getName(), name)) {
            Optional<RepairAction> repairActionOptional = repository.findRepairActionByName(name);
            if (repairActionOptional.isPresent()) {
                throw new IllegalStateException("naam in gebruik");
            }

            repairAction.setName(name);
        }

        if (price != null && price > 0L && !Objects.equals(repairAction.getPrice(), price)) {
            repairAction.setPrice(price);
        }

        return repairAction;
    }
}
