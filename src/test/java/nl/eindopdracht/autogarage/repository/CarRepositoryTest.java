package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkIfRepositoryWorks() {
        //given
        Car car = new Car(
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );
        underTest.save(car);

        //when
        //then
        assertThat(underTest).isNotNull();
    }

    @Test
    void itShouldFindCarByLicensePlate() {
        //given
        String licensePlate = "KS-019-F";
        Car car = new Car(
                licensePlate,
                "Audi",
                "A7",
                2020
        );
        underTest.save(car);

        //when
        Optional<Car> expected = underTest.findCarByLicensePlate(licensePlate);

        //then
        assertThat(expected).isEqualTo(Optional.of(car));
    }

    @Test
    void itShouldNotFindCarByLicensePlate() {
        //given
        String licensePlate = "12-KL-OL";
        String actualLicensePlate = "OP-441-B";
        Car car = new Car(
                actualLicensePlate,
                "Volkswagen",
                "Golf",
                2017
        );
        underTest.save(car);

        //when
        Optional<Car> expected = underTest.findCarByLicensePlate(licensePlate);

        //then
        assertThat(expected).isNotEqualTo(Optional.of(car));
    }
}
