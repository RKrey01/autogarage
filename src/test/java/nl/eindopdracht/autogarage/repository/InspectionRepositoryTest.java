package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Inspection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class InspectionRepositoryTest {

    @Autowired
    private InspectionRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkIfRepositoryWorks() {
        //given
        Car car1 = new Car();

        Inspection inspection = new Inspection(
                car1,
                LocalDate.of(2019,03,9),
                "Remschijf en rechter voorlamp"
        );
        underTest.save(inspection);

        //when
        //then
        assertThat(underTest).isNotNull();
    }

}