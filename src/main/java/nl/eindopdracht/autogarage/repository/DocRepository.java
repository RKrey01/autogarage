package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocRepository extends JpaRepository<Doc, Long> {
}
