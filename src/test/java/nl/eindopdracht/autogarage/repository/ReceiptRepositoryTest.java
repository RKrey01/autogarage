package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Receipt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ReceiptRepositoryTest {

    @Autowired
    private ReceiptRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkIfRepositoryWorks() {
        //given
        Receipt receipt = new Receipt(
        );
        underTest.save(receipt);

        //when
        //then
        assertThat(underTest).isNotNull();
    }
}