package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {

}
