package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    //om een auto te vinden per kenteken
    Optional<Car> findCarByLicensePlate(String licensePlate);

}
