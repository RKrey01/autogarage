package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Customer;
import nl.eindopdracht.autogarage.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock //repos works, we already tested it, so we mock it
    private CustomerRepository repository;
    private CustomerServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerServiceImpl(repository);
    }

    @Test
    void canAddNewCustomer() {
        //given
        Customer customer = new Customer(
                "Jantje",
                "Klaas",
                "Adreslaan 43",
                "1233AC",
                "test@testmail.com",
                LocalDate.of(1996,12,18),
                1234
        );

        //when
        underTest.addNewCustomer(customer);

        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(repository).save(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        Customer customer = new Customer(
                "Jantje",
                "Klaas",
                "Adreslaan 43",
                "1233AC",
                "test@testmail.com",
                LocalDate.of(1996,12,18),
                1234
        );

        given(repository.findCustomerByEmail(customer.getEmail())).willReturn(Optional.of(customer));

        //when
        //then
        assertThatThrownBy(() -> underTest.addNewCustomer(customer))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email in gebruik");

        //the repository (mock) never saves any customers
        verify(repository, never()).save(any());

    }

    @Test
    void canGetAllCustomers() {
        //when
        underTest.getCustomers();
        //then
        verify(repository).findAll();
    }

    @Test
    void willFindCustomerById() {
        //given
        Customer customer = new Customer(
                "Jantje",
                "Klaas",
                "Adreslaan 43",
                "1233AC",
                "test@testmail.com",
                LocalDate.of(1996,12,18),
                1234
        );

        //when
        given(repository.findById(customer.getId())).willReturn(Optional.of(customer));
        underTest.findCustomerById(customer.getId());

        //then
        verify(repository).findById(customer.getId());
    }

    @Test
    void willThrowWhenIdDoesNotExist() throws Exception {
        //given
        Customer customer = new Customer(
                "Jantje",
                "Klaas",
                "Adreslaan 43",
                "1233AC",
                "test@testmail.com",
                LocalDate.of(1996,12,18),
                1234
        );

        //when
        given(repository.findById(customer.getId())).willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.findCustomerById(customer.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Klant niet gevonden! ID - " + customer.getId());
    }

    @Test
    void willDeletesCustomerById() {
        //given
        Customer customer = new Customer(
                "Jantje",
                "Klaas",
                "Adreslaan 43",
                "1233AC",
                "test@testmail.com",
                LocalDate.of(1996,12,18),
                1234
        );

        //when
        given(repository.existsById(customer.getId())).willReturn(true);
        underTest.deleteCustomerById(customer.getId());

        //then
        verify(repository).deleteById(customer.getId());
    }

    @Test
    void willThrowWhenDeleteFails() {
        //given
        Customer customer = new Customer(
                "Jantje",
                "Klaas",
                "Adreslaan 43",
                "1233AC",
                "test@testmail.com",
                LocalDate.of(1996,12,18),
                1234
        );

        //when
        given(repository.existsById(customer.getId())).willReturn(false);

        //then
        assertThatThrownBy(() -> underTest.deleteCustomerById(customer.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Klant met id " + customer.getId() + " bestaat niet" );
    }

}