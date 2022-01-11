package nl.eindopdracht.autogarage.repository;

import nl.eindopdracht.autogarage.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkIfRepositoryWorks() {
        //given
        Customer customer = new Customer(
                "Piet",
                "Pietersen",
                "Adreslaan 123",
                "1233AB",
                "email@mail.com",
                LocalDate.of(1996,12,18),
                1234
        );
        underTest.save(customer);

        //when
        //then
        assertThat(underTest).isNotNull();
    }

    @Test
    void itShouldFindCustomerByEmail() throws Exception {
        //given
        String  email = "test@testmail.com";
        Customer cust1 = new Customer(
                "Jantje",
                "Klaas",
                "Adreslaan 43",
                "1233AC",
                email,
                LocalDate.of(1996,12,18),
                1234
        );
        underTest.save(cust1);

        //when
        Optional<Customer> expected = underTest.findCustomerByEmail(email);

        //then
        assertThat(expected).isEqualTo(Optional.of(cust1));

    }

}