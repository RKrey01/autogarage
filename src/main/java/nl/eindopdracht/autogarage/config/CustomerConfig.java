package nl.eindopdracht.autogarage.config;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Customer;
import nl.eindopdracht.autogarage.repository.CarRepository;
import nl.eindopdracht.autogarage.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository, CarRepository carRepository){
        return args -> {
            Customer reyyan = new Customer(
                    "Reyyan",
                    "Kilinc",
                    "Adres1",
                    "postcode1",
                    "RK@hotmail.com",
                    LocalDate.of(2000, DECEMBER, 20),
                    062134553,
                    carRepository.getById(1L)
            );

            Customer jan = new Customer(
                    "Jan",
                    "Jansen",
                    "Adres2",
                    "postcode2",
                    "jan@hotmail.com",
                    LocalDate.of(1993, DECEMBER, 23),
                    06223453,
                    carRepository.getById(2L)
            );

            repository.saveAll(
                    List.of(reyyan, jan)
            );
        };
    }
}
