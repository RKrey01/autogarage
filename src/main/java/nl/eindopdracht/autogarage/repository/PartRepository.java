package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findPartByName(String name);

}
