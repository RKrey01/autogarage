//package nl.eindopdracht.autogarage.controller;
//
//import nl.eindopdracht.autogarage.model.Car;
//import nl.eindopdracht.autogarage.model.Customer;
//import nl.eindopdracht.autogarage.repository.CustomerRepository;
//import nl.eindopdracht.autogarage.service.CustomerServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CustomerController.class)
//class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private CustomerServiceImpl service;
//
//    @Mock
//    private CustomerRepository repository;
//
//    @Test
//    void shouldGetAllCustomers() throws Exception {
//        List<Customer> customers = repository.findAll();
//        when(service.getCustomers())
//                .thenReturn(customers);
//
//        mvc.perform(get("/api/customer")).andDo(print()).andExpect(status().isOk());
//
//        //to know that the mock is being called
//        verify(service).getCustomers();
//    }
//
//    @Test
//    void shouldGetCustomerById() throws Exception {
//        List<Car> cars = new ArrayList<>();
//
//        Customer customer = new Customer(
//                1L,
//                "Piet",
//                "Pietersen",
//                "Adreslaan 123",
//                "1233AB",
//                "email@mail.com",
//                LocalDate.of(1996,12,18),
//                1234,
//                cars
//        );
//
//        when(service.findCustomerById(customer.getId()))
//                .thenReturn(customer);
//
//        mvc.perform(get("/api/customer/{customerId}", customer.getId())).andDo(print()).andExpect(status().isOk());
//
//        verify(service).findCustomerById(customer.getId());
//    }
//
//    /*@Test
//    void shouldCreateNewCustomer() throws Exception {
//        List<Car> cars = new ArrayList<>();
//
//        Customer customer = new Customer(
//                1L,
//                "Piet",
//                "Pietersen",
//                "Adreslaan 123",
//                "1233AB",
//                "email@mail.com",
//                LocalDate.of(1996,12,18),
//                1234,
//                cars
//        );
//
//        mvc.perform(post("/api/customer/{customerId}", customer.getId())).andExpect(status().isOk());
//
//        verify(service).addNewCustomer(customer);
//
//    }*/
//
//    @Test
//    void shouldDeleteCustomer() throws Exception {
//        List<Car> cars = new ArrayList<>();
//
//        Customer customer = new Customer(
//                1L,
//                "Piet",
//                "Pietersen",
//                "Adreslaan 123",
//                "1233AB",
//                "email@mail.com",
//                LocalDate.of(1996,12,18),
//                1234,
//                cars
//        );
//
//        mvc.perform(delete("/api/customer/{customerId}", customer.getId())).andExpect(status().isOk());
//
//        verify(service).deleteCustomerById(customer.getId());
//
//    }
//}
