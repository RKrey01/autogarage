package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.Customer;
import nl.eindopdracht.autogarage.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomerById(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String zipcode,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer phoneNumber) {
        customerService.updateCustomer(customerId, address, zipcode, email, phoneNumber);
    }

}
