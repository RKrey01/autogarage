package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Customer;
import nl.eindopdracht.autogarage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email in gebruik");
        }
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers(){
    return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        Optional<Customer> result = customerRepository.findById(customerId);

        Customer customer = null;
        if (result.isPresent()) {
            customer = result.get();
        } else {
            throw new RuntimeException("Klant niet gevonden! ID - " + customerId);
        }
        return customer;
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("Klant met id " + customerId + " bestaat niet");
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long customerId, String address, String zipcode, String email, Integer phoneNumber, Car car) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Klant met id " + customerId + " bestaat niet"));


        if (address != null && address.length() > 0 && !Objects.equals(customer.getAddress(), address)) {
            customer.setAddress(address);
        }

        if (zipcode != null && zipcode.length() > 0 && !Objects.equals(customer.getZipcode(), zipcode)) {
            customer.setZipcode(zipcode);
        }

        if (email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("email in gebruik");
            }

            customer.setEmail(email);
        }

        if (phoneNumber != null && phoneNumber > 0 && !Objects.equals(customer.getPhoneNumber(), phoneNumber)) {
            customer.setPhoneNumber(phoneNumber);
        }

        if (car != null && car.getLicensePlate().length() > 0 && !Objects.equals(customer.getCar(), car)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByLicensePlate(car.getLicensePlate());
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("kenteken al geregistreerd");
            }

            customer.setCar(car);
        }

        return customer;
    }

}
