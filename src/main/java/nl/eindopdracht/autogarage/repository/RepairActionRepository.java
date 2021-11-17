package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.RepairAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepairActionRepository extends JpaRepository<RepairAction, Long> {
    Optional<RepairAction> findRepairActionByName(String name);

}
