package nl.eindopdracht.autogarage.config;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;


public class CarConfig {
    @Bean
    CommandLineRunner commandLineRunner(CarRepository repository){
        return args -> {
            Car BMW = new Car(
                    "02-swq-45",
                    "BMW",
                    "serie3",
                    2019
            );

            Car AUDI = new Car(
                    "34-rtw-43",
                    "Audi",
                    "Q8",
                    2020
            );

            repository.saveAll(
                    List.of(BMW, AUDI)
            );
        };
    }
}
