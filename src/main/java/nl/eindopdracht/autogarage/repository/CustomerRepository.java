package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //De sql query zou er als volgt uit zien
    //@Query("SELECT s FROM Customer s WHERE s.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);

}
