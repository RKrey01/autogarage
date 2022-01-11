package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Part;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PartRepositoryTest {

    @Autowired
    private PartRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkIfRepositoryWorks() {
        //given
        Part part = new Part(
                "remschijf",
                35.50,
                7
        );
        underTest.save(part);

        //when
        //then
        assertThat(underTest).isNotNull();
    }

    @Test
    void itShouldFindPartByName() {
        //given
        String name = "koplamp";
        Part part = new Part(
                name,
                30.0,
                10
        );
        underTest.save(part);

        //when
        Optional<Part> expected = underTest.findPartByName(name);

        //then
        assertEquals(expected, Optional.of(part));
    }

    @Test
    void itShouldNotFindPartByName() {
        //given
        String name = "koplamp";
        String actualName = "remschijf";
        Part part = new Part(
                actualName,
                35.0,
                8
        );
        underTest.save(part);

        //when
        Optional<Part> expected = underTest.findPartByName(name);

        //then
        assertThat(expected).isNotEqualTo(Optional.of(part));
    }
}