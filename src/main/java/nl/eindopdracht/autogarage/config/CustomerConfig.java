package nl.eindopdracht.autogarage.config;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Customer;
import nl.eindopdracht.autogarage.repository.CarRepository;
import nl.eindopdracht.autogarage.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository, CarRepository carRepository){
        return args -> {
            List<Car> cars = new ArrayList<>();
            cars.add(carRepository.getById(1L));

            Customer tom = new Customer(
                    "Tom",
                    "Thompson",
                    "Adres1",
                    "postcode1",
                    "TomTom@hotmail.com",
                    LocalDate.of(2000, DECEMBER, 20),
                    062134553,
                    cars
            );

            List<Car> carsJan = new ArrayList<>();
            carsJan.add(carRepository.getById(2L));

            Customer jan = new Customer(
                    "Jan",
                    "Jansen",
                    "Adres2",
                    "postcode2",
                    "jan@hotmail.com",
                    LocalDate.of(1993, DECEMBER, 23),
                    06223453,
                    carsJan
            );

            repository.saveAll(
                    List.of(tom, jan)
            );
        };
    }
}
