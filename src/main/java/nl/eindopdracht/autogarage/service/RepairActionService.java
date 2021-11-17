package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.RepairAction;

import java.util.List;

public interface RepairActionService {

    List<RepairAction> getRepairActions();

    RepairAction findRepairActionById(Long repairActionId);

    void addNewRepairAction(RepairAction repairAction);

    void deleteRepairActionById(Long repairActionId);

    RepairAction updateRepairAction(Long repairActionId, String name, Double price);
}
