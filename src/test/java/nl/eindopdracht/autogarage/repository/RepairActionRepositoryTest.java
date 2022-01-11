package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.RepairAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RepairActionRepositoryTest {

    @Autowired
    private RepairActionRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkIfRepositoryWorks() {
        //given
        RepairAction repairAction = new RepairAction(
                "remschijf verwisselen",
                50.0
        );
        underTest.save(repairAction);

        //when
        //then
        assertThat(underTest).isNotNull();
    }

    @Test
    void findRepairActionByName() {
        //given
        String name = "remschijf verwisselen";
        RepairAction repairAction = new RepairAction(
                name,
                50.0
        );
        underTest.save(repairAction);

        //when
        Optional<RepairAction> expected = underTest.findRepairActionByName(name);

        //then
        assertThat(expected).isEqualTo(Optional.of(repairAction));

    }
}