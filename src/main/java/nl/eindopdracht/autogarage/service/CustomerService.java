package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer findCustomerById(Long customerId);

    void addNewCustomer(Customer customer);

    void deleteCustomerById(Long customerId);

    Customer updateCustomer(Long customerId, String address, String zipcode, String email, Integer phoneNumber, List<Car> cars);
}
