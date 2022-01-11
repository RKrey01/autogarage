package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;
import nl.eindopdracht.autogarage.model.Repair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RepairRepositoryTest {

    @Autowired
    private RepairRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private RepairActionRepository repairActionRepository;

    @Test
    void checkIfRepositoryWorks() {
        //given
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );
        underTest.save(repair);

        //when
        //then
        assertThat(underTest).isNotNull();
    }

    @Test
    void itShouldFindReparationByStatus() {
        //given
        RepairStatus status = RepairStatus.IN_PROGRESS;
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                status
        );
        underTest.save(repair);

        //when
        Optional<Repair> expected = underTest.findReparationByStatus(status);

        //then
        assertThat(expected).isEqualTo(Optional.of(repair));

    }

    @Test
    void itShouldNotFindReparationByStatus() {
        //given
        RepairStatus status = RepairStatus.IN_PROGRESS;
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                status
        );
        underTest.save(repair);

        //when
        Optional<Repair> expected = underTest.findReparationByStatus(RepairStatus.COMPLETED);

        //then
        assertThat(expected).isNotEqualTo(Optional.of(repair));

    }

}