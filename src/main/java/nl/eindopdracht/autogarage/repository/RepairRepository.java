package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;
import nl.eindopdracht.autogarage.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    Optional<Repair> findReparationByStatus(RepairStatus status);

}
